package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
//Holds every subsystem so OpModes only need: Robot robot = new Robot(hardwareMap);

public class Robot {
        public final DriveSubsystem drive = null;
        public final IntakeSubsystem intake;
        public final ShooterSubsystem shooter;
        public final TransferSubsystem transfer;

        public Robot (HardwareMap hw) {
            intake = new IntakeSubsystem(hw);
            shooter = new ShooterSubsystem(hw);
            transfer = new TransferSubsystem(hw);
        }

        public void stopAll() {
            drive.stop();
            intake.stop();
            shooter.stop();
        }
}
