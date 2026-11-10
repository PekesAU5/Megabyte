package Tests;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "AprilTag Limelight Test", group = "Test")
public class AprilTagLimelightTest extends OpMode {

    private Limelight3A limelight;
    private IMU imu;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight");
        limelight.pipelineSwitch(Integer.parseInt("8")); //41,42,43,44
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

    }

    @Override
    public void start() {
        limelight.start();
    }

    @Override
    public void loop() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();

        if (llResult == null) {
            telemetry.addData("Status", "No result from Limelight (check connection/power)");
        } else if (llResult.isValid()) {
            telemetry.addData("Status", "Result received, but no valid target in view");
        } else {
            Pose3D botpose = llResult.getBotpose_MT2();
            telemetry.addData("Status", "Target found");
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());
            telemetry.addData("BotPose", llResult.getBotpose_MT2());
            telemetry.addData("Yaw", botpose.getOrientation().getYaw());
        }
        /*if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            telemetry.addData("Status", "Target found");
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());
            telemetry.addData("BotPose",llResult.getBotpose_MT2());
            telemetry.addData("Yaw",botpose.getOrientation().getYaw());
        } else if (llResult == null ){
            telemetry.addData("Status", "No result from Limelight (check connection/power)");
        } else{
            telemetry.addData("Status", "Result received, but no valid target in view");
        }

        telemetry.update();

    }*/

   /*public void runOpMode(){
        while(gamepad1.rightBumperWasPressed()){
            double DetectAprilTag =
        }

   }*/
    }
}