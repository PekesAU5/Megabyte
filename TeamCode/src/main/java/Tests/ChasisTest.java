package Tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ChasisTest", group = "Test")
public class ChasisTest extends LinearOpMode {

    //private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

   // private DcMotor intakeMotor = null;

   // private Servo leftServo = null;
    //private Servo rightServo = null;

    private DcMotor leftShootMotor = null;
    //private DcMotor rightShootMotor = null;

    @Override
    public void runOpMode() {

        //frontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        //frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");

        //intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        //leftServo = hardwareMap.get(Servo.class, "lservo");
        //rightServo = hardwareMap.get(Servo.class, "rservo");

        leftShootMotor = hardwareMap.get(DcMotor.class, "lshoot");
        //rightShootMotor = hardwareMap.get(DcMotor.class, "rshoot");


        //rightServo.scaleRange(0, 300);
        //leftServo.scaleRange(0, 300);


        //frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
       // frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        //intakeMotor.setDirection(DcMotor.Direction.FORWARD);
       // rightServo.setDirection(Servo.Direction.REVERSE);
        //leftServo.setDirection(Servo.Direction.REVERSE);


        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x * 1.1;
            double turn = -gamepad1.right_stick_x;


            double flDrive = forward + strafe + turn;
            double frDrive = forward - strafe - turn;
            double blDrive = forward - strafe + turn;
            double brDrive = forward + strafe - turn;


            double max = Math.max(1.0, Math.max(Math.abs(flDrive), Math.max(Math.abs(frDrive), Math.max(Math.abs(blDrive), Math.abs((brDrive))))));

            //flDrive /= max;
            blDrive /= max;
            frDrive /= max;
            brDrive /= max;


            //frontLeftDrive.setPower(flDrive);
            // frontRightDrive.setPower(frDrive);
            backLeftDrive.setPower(blDrive);
            backRightDrive.setPower(brDrive);

            double power = gamepad1.left_trigger;
            leftShootMotor.setPower(power);
        }
    }
}