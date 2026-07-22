package org.firstinspires.ftc.teamcode.subsystems.collector;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.stream.Collector;


public class CollectorEmpty {
    public enum CollectorState {
        COLLECT,
        EXTAKE,
        OFF
    }
    private CollectorState collectorState;
    private DcMotorEx collectorMotor;
    private CollectorState oldState;
    private CollectorState newState;

    public CollectorEmpty(HardwareMap hardwareMap, Telemetry telemetry) {
        collectorMotor = hardwareMap.get(DcMotorEx.class, "intake");
        collectorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setCollectorState(CollectorState.OFF);
    }
    public void update(){
        newState = collectorState;
//        if(oldState != newState){
            switch(collectorState){
                case COLLECT:
                    collectorMotor.setPower(0.99);
                    break;
                case EXTAKE:
                    collectorMotor.setPower(-0.99);
                    break;
                case OFF:
                    collectorMotor.setPower(0);
                    break;
            }
//        }


       oldState = collectorState;
    }
    public CollectorState getCollectorState(){
        return collectorState;
    }
    public void setCollectorState(CollectorState collectingState) {
       collectorState = collectingState;

    }



}
