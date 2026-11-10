package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {
    public static final double STRAFE = 1.1;
    public static final double TICKS_PER_MM = 1.645723325;
    private final DcMotor frontLeftDrive;
    private final DcMotor backLeftDrive;
    private final DcMotor frontRightDrive;
    private final DcMotor backRightDrive;

    public DriveSubsystem(HardwareMap hw){
        frontLeftDrive = hw.get(DcMotor.class, "fl");
        backLeftDrive = hw.get(DcMotor.class, "bl");
        frontRightDrive = hw.get(DcMotor.class, "fr");
        backRightDrive = hw.get(DcMotor.class,"br");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        for (DcMotor m : motors()) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private DcMotor[] motors() {
        return new DcMotor[]{frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive};
    }

    private void setRunMode(DcMotor.RunMode mode) {
        for (DcMotor m : motors()) m.setMode(mode);
    }

    // Mecanum drive, robot-centric. Use in TeleOp every loop
    public void drive(double forward, double strafe, double turn) {
        double flDrive = forward + strafe + turn;
        double frDrive = forward - strafe - turn;
        double blDrive = forward - strafe + turn;
        double brDrive = forward + strafe - turn;

        double max = Math.max(1.0, Math.max(Math.abs(flDrive), Math.max(Math.abs(frDrive), Math.max(Math.abs(blDrive), Math.abs((brDrive))))));

        flDrive /= max;
        blDrive /= max;
        frDrive /= max;
        brDrive /= max;
    }

    public void stop() {
        drive(0, 0, 0);
    }

    // Autonomous helpers (encoder based)

    //Starts a move without blocking. Check isBusy() to know when it finishes.
    public void startMoveMilimeters(double forwardMM, double strafeInches, double power) {
        int f = (int) Math.round(forwardMM * TICKS_PER_MM);
        int s = (int) Math.round(strafeInches * TICKS_PER_MM * STRAFE);

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(f + s);
        backLeftDrive.setTargetPosition(f - s);
        backRightDrive.setTargetPosition(f + s);
        frontRightDrive.setTargetPosition(f - s);

        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        double p = Math.abs(power);
        for (DcMotor m : motors()) m.setPower(p);
    }

    public boolean isBusy() {
        for (DcMotor m : motors()) {
            if (m.isBusy()) return true;
        }
        return false;
    }

    //Call after a move finishes to go back to normal driving.
    public void finishMove() {
        stop();
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}


