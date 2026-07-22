package org.firstinspires.ftc.teamcode.opmode.turretTest;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.collector.CollectorEmpty;

@TeleOp(name="Collector Test Tele")
@Config
public class CollectorTest extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        CollectorEmpty collector = new CollectorEmpty(hardwareMap, telemetry);
        telemetry.setMsTransmissionInterval(20);
        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.right_trigger >= 0.1){
                collector.setCollectorState(CollectorEmpty.CollectorState.COLLECT);
            } else if (gamepad1.left_trigger >= 0.1) {
                collector.setCollectorState(CollectorEmpty.CollectorState.EXTAKE);
            } else{
                    collector.setCollectorState(CollectorEmpty.CollectorState.OFF);
            }

            collector.update();
            telemetry.update();
        }
    }
}
