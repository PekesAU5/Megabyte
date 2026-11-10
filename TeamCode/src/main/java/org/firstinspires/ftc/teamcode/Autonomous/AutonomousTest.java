package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name ="AutonomousTest", group = "LinearOpMode")
public abstract class AutonomousTest extends LinearOpMode {

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

    private DcMotor intakeMotor = null;

    private Servo leftServo = null;
    private  Servo rightServo = null;

    private DcMotor leftShootMotor = null;
    private DcMotor rightShootMotor = null;

    @Override
    public void runOpMode(){
        frontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        leftServo = hardwareMap.get(Servo.class, "lservo");
        rightServo = hardwareMap.get(Servo.class, "rservo");

        leftShootMotor = hardwareMap.get(DcMotor.class, "lshoot");
        rightShootMotor = hardwareMap.get(DcMotor.class, "rshoot");

    }

}

