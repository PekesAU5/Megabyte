package org.firstinspires.ftc.teamcode.Subsystems;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class TransferSubsystem {
    public static final double SERVO_CLOSE = 0;
    public static final double SERVO_OPEN = 10;
    private final Servo leftServo;

    private final DcMotor motor;
    public TransferSubsystem(HardwareMap hw) {
        leftServo = hw.get(Servo.class, "transferservo");
        motor = hw.get(DcMotor.class, "transfer");

        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftServo.setDirection(Servo.Direction.FORWARD);
    }

    public void setServoAngle(double angle) {
        leftServo.setPosition(angle);
    }

    public void setTransferPower(double power) {motor.setPower(power);}

    public void activateTransfer() {
        motor.setPower(Constants.TransferConstants.TRANSFER_ACTIVE);
    }

    public void reverseTransfer() {
        motor.setPower(Constants.TransferConstants.TRANSFER_REVERSE);
    }

    public void stopTransfer(){
        motor.setPower(0);
    }

    public void setPositionDegrees(double degrees){
        double pos = Math.max(0.0,
                Math.min(1.0,degrees/SERVO_OPEN));
        leftServo.setPosition(pos);
    }

    public void OPEN() {setServoAngle(SERVO_OPEN);}
    public void CLOSE(){ setServoAngle(SERVO_CLOSE);}


}
