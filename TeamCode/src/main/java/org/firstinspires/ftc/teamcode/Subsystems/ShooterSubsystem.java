package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;

public class ShooterSubsystem {

    private final DcMotor ShooterMotor;

    public ShooterSubsystem(HardwareMap hw){
        ShooterMotor = hw.get(DcMotor.class, "shooter");
    }

    public void setShooterPower(double shooterpower) {
        ShooterMotor.setPower(shooterpower);
    }
    public void stop(){
        ShooterMotor.setPower(0);
    }
    public double getPower(){ return ShooterMotor.getPower(); }


}
