package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterSubsystem {

    private final DcMotor ShooterMotor;

    public ShooterSubsystem(HardwareMap hw){
        ShooterMotor = hw.get(DcMotor.class, "shooter");
        ShooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setShooterPower(double shooterpower) {
        ShooterMotor.setPower(shooterpower);
    }
    public void stop(){
        ShooterMotor.setPower(0);
    }
    public double getPower(){ return ShooterMotor.getPower(); }


}
