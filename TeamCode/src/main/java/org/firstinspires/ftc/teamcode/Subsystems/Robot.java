package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
//Holds every subsystem so OpModes only need: Robot robot = new Robot(hardwareMap);

public class Robot {
        public final DriveSubsystem Chassis;
        public final IntakeSubsystem intake;
        public final ShooterSubsystem shooter;
        public final TransferSubsystem transfer;

        public Robot (HardwareMap hw) {
            Chassis = new DriveSubsystem(hw);
            intake = new IntakeSubsystem(hw);
            shooter = new ShooterSubsystem(hw);
            transfer = new TransferSubsystem(hw);
        }


        public void stopAll() {
            Chassis.stop();
            intake.stop();
            shooter.stop();
        }
}
