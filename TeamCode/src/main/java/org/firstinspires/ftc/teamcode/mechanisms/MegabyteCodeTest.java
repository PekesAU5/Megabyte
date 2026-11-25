package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@TeleOp (name = "MegabyteCodeTest", group = "Test")
public class MegabyteCodeTest extends LinearOpMode {


    @Override
    public void runOpMode(){
        Robot robot = new Robot(hardwareMap);
        telemetry.addData("Status", "Robot Initialized");
        telemetry.update();

robot.transfer.resetServo();
robot.intake.resetservo();
        waitForStart();

        while(opModeIsActive()) {
            robot.drive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x * DriveSubsystem.STRAFE, -gamepad1.right_stick_x);

            if (gamepad1.left_bumper){
                robot.intake.out(0.6);
                robot.transfer.reverseTransfer();


            }else{robot.intake.setPower(-gamepad1.left_trigger);
            robot.transfer.activateTransfer();}

            robot.shooter.setShooterPower(gamepad1.right_trigger);



            if(gamepad1.x){
                robot.transfer.CLOSE();
            } else if(gamepad1.b){
                robot.transfer.OPEN();
            }

            if(gamepad1.y){
                robot.intake.UP();
            } else if(gamepad1.a){
                robot.intake.DOWN();
            }

            robot.stopAll();


        }

    }

}
