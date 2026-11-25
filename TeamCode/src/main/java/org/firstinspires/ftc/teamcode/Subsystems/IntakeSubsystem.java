package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Ground.IntakeConstants;

public class IntakeSubsystem {



    private final DcMotor intakeMotor;

    private final Servo leftflowerServo;

    private final Servo rightflowerServo;


    public IntakeSubsystem(HardwareMap hw) {

        intakeMotor = hw.get(DcMotor.class, "intake");
        leftflowerServo = hw.get(Servo.class, "lservo");
        rightflowerServo = hw.get(Servo.class, "rservo");

        rightflowerServo.setDirection(Servo.Direction.REVERSE);
    }

    public void setPositionDegrees(double degrees){
        double pos = Math.max(0.0,
                Math.min(1.0,degrees/ IntakeConstants.SERVO_MAX));
        leftflowerServo.setPosition(pos);
        rightflowerServo.setPosition(pos);
    }
    public void resetservo(){
        setPositionDegrees(0);
    }

    public void setPower(double power) { intakeMotor.setPower(power); }

    public void in(double power)  { intakeMotor.setPower(Math.abs(power)); }
    public void out(double power) { intakeMotor.setPower(-Math.abs(power)); }

    public void UP(){
        setPositionDegrees(0);
    }
    public void DOWN(){
        setPositionDegrees(78);
    }
    public void stop()            { intakeMotor.setPower(0); }
}
