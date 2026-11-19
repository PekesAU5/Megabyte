package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name ="AutonomousTest", group = "LinearOpMode")
public class AutonomousTest extends LinearOpMode {

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

    /*private DcMotor intakeMotor = null;

    private Servo leftServo = null;
    private  Servo rightServo = null;

    private DcMotor leftShootMotor = null;
    private DcMotor rightShootMotor = null;

     */
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 537.7;   // goBILDA 5203, 19.2:1 (includes the gearbox)
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // only change if you add gears/belts after the motor
    static final double WHEEL_DIAMETER_CM = 9.6;        // 96 mm wheel
    static final double TICKS_PER_CM =
            (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (Math.PI * WHEEL_DIAMETER_CM);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode(){
        frontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        /*intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        leftServo = hardwareMap.get(Servo.class, "lservo");
        rightServo = hardwareMap.get(Servo.class, "rservo");

        leftShootMotor = hardwareMap.get(DcMotor.class, "lshoot");
        rightShootMotor = hardwareMap.get(DcMotor.class, "rshoot");

         */

        //RESETTING ENCODERS BEFORE THE PROGRAM BEGINS
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





        telemetry.addData("Starting at",  "%7d :%7d",
                frontLeftDrive.getCurrentPosition(),
                frontRightDrive.getCurrentPosition(),
                backLeftDrive.getCurrentPosition(),
                backRightDrive.getCurrentPosition());

        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        encoderDrive(DRIVE_SPEED,48,  48, 48, 48,5);  // S1: Forward 47 cm with 5 Sec timeout
        encoderDrive(TURN_SPEED,   12, -12, 12,-12,4.0);  // S2: Turn Right 12 cm with 4 Sec timeout
        encoderDrive(DRIVE_SPEED, -24, -24, -24,-24,4.0);  // S3: Reverse 24 cm with 4 Sec timeout


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the OpMode running.
     */
    public void encoderDrive(double speed,
                             double frontLeftCM, double frontRightCM,
                             double backLeftCM, double backRightCM,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;
        int newBackLeftTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeftDrive.getCurrentPosition() + (int)(frontLeftCM * TICKS_PER_CM);
            newFrontRightTarget = frontRightDrive.getCurrentPosition() + (int)(frontRightCM * TICKS_PER_CM);
            newBackRightTarget = backRightDrive.getCurrentPosition() + (int)(backRightCM * TICKS_PER_CM);
            newBackLeftTarget = backLeftDrive.getCurrentPosition() + (int)(backLeftCM * TICKS_PER_CM);

            frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            frontRightDrive.setTargetPosition(newFrontRightTarget);
            backLeftDrive.setTargetPosition(newBackLeftTarget);
            backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            frontLeftDrive.setPower(Math.abs(speed));
            frontRightDrive.setPower(Math.abs(speed));
            backLeftDrive.setPower(Math.abs(speed));
            backRightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeftDrive.isBusy() && frontRightDrive.isBusy()
                            && backRightDrive.isBusy() && backLeftDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to", "%7d :%7d :%7d :%7d",
                        newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Currently at", "%7d :%7d :%7d :%7d",
                        frontLeftDrive.getCurrentPosition(), frontRightDrive.getCurrentPosition(),
                        backLeftDrive.getCurrentPosition(), backRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
}
