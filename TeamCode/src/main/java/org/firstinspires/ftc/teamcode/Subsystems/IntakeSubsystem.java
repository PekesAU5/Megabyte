package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem {
    private final DcMotor intakeMotor;

    public IntakeSubsystem(HardwareMap hw) {

        intakeMotor = hw.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setPower(double power) { intakeMotor.setPower(power); }

    public void in(double power)  { intakeMotor.setPower(Math.abs(power)); }
    public void out(double power) { intakeMotor.setPower(-Math.abs(power)); }
    public void stop()            { intakeMotor.setPower(0); }
}
