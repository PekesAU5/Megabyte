package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferSubsystem {
    public static final double SERVO_DOWN = 0;
    public static final double SERVO_UP = 300;
    private final Servo leftServo;

    public TransferSubsystem(HardwareMap hw) {
        leftServo = hw.get(Servo.class, "lservo");

        leftServo.setDirection(Servo.Direction.FORWARD);
    }

    public void setServoAngle(double angle) {
        leftServo.setPosition(angle);
    }

    public void setPositionDegrees(double degrees){
        double pos = Math.max(0.0,Math.min(1.0,degrees/SERVO_UP));
        leftServo.setPosition(pos);
    }

    public void UP() {setServoAngle(SERVO_UP);}
    public void DOWN(){ setServoAngle(SERVO_DOWN);}


}
