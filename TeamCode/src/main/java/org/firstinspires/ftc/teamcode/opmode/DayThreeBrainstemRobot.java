package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.BatteryVoltageFilter;
import org.firstinspires.ftc.teamcode.subsystems.RaisingArm;
import org.firstinspires.ftc.teamcode.utils.drivetrain.MecanumDrive;

public class DayThreeBrainstemRobot {
   private final BatteryVoltageFilter batteryVoltageFilter;
   private final MecanumDrive drive;
   private final RaisingArm raisingArm;
   private final Arm arm;

    public double startX, startY, startHeading;
    public DayThreeBrainstemRobot(HardwareMap hardwareMap, Telemetry telemetry, Pose2d pose2d){
        arm = new Arm(hardwareMap, telemetry);
        raisingArm =  new RaisingArm(hardwareMap, telemetry);
        drive = new MecanumDrive(hardwareMap,  new Pose2d(startX, startY, startHeading));
       batteryVoltageFilter = new BatteryVoltageFilter(hardwareMap);
    }
    public void update(){
        batteryVoltageFilter.update();

        double batteryVoltage = batteryVoltageFilter.getVoltage();

        arm.setBatteryVoltage(batteryVoltage);

        raisingArm.setBatteryVoltage(batteryVoltage);

        drive.pinpoint().update();

        arm.update();

        raisingArm.update();

    }
}
