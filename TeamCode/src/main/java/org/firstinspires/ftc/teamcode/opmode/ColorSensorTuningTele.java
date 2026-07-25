package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.ColorSensorWrapper;
@TeleOp(name = "Color Sensor Tuning Tele")
public class ColorSensorTuningTele extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        ColorSensorWrapper colorSensor = new ColorSensorWrapper(hardwareMap, telemetry);

        waitForStart();

        while(opModeIsActive()){

            colorSensor.update();

            telemetry.update();

        }
    }

}
