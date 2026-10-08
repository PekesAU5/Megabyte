package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Timer;

@TeleOp(name = "PruebaSensor", group = "")
public class PruebaSensor extends LinearOpMode {

    private ColorSensor rawSensor;

    private Servo servo;

    private  DistanceSensor sensorDistance;

    private enum States{
        DETECTING_COLOR,
        TRANSFERRING,
        OUTTAKING
    }

    @Override
    public void runOpMode(){
        servo = hardwareMap.get(Servo.class, "lservo");


        rawSensor = hardwareMap.get(ColorSensor.class, "colorsensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "distancesens");

        States states = States.DETECTING_COLOR;
        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
    //    Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) sensorDistance;



    Timer timer = new Timer();


        waitForStart();

        while(opModeIsActive()){



            servo.scaleRange(-135, 135);


            switch(states){
                case DETECTING_COLOR:
                    if((rawSensor.red() >15 && rawSensor.blue() <7  && rawSensor.green() < 5 ) || (rawSensor.red() <6 && rawSensor.blue() >10) ){
                        servo.setPosition(130);
                        states = States.TRANSFERRING;
                    }else if(rawSensor.red() >20 && rawSensor.blue() <7  && rawSensor.green() > 20 ){
                        servo.setPosition(0);
                        states = States.TRANSFERRING;
                    }
                    break;
                case TRANSFERRING:
                    if(ballDetected()){
                        states = States.OUTTAKING;
                    }
                    break;
                case OUTTAKING:
                    if (!ballDetected()){
                        sleep(1500);
                        states = States.DETECTING_COLOR;
                    }
                    break;

            }






  ;

            telemetry.addData("--- Raw RGB ---", "");
            telemetry.addData("Red Raw", rawSensor.red());
            telemetry.addData("Blue Raw", rawSensor.blue());
            telemetry.addData("Green Raw", rawSensor.green());
            telemetry.addData("SensorDistance", sensorDistance.getDistance(DistanceUnit.MM));
            telemetry.addData("SensorState", states);
            telemetry.addData("balldetected", ballDetected());
            telemetry.update();
        }



    }
    public boolean ballDetected(){

        return sensorDistance.getDistance(DistanceUnit.MM) < 38;
    }
}
