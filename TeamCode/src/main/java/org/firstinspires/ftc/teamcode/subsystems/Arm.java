package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    public DcMotorEx ArmMotor;
    private PIDController pid;
    public static double Kp, Ki, Kd, Ks, Kg;
    public double targetAngle;
    public static double ArmLength;
    public double currentAngle;
    public double encodersPerAngle;
    public double armFromMiddle;
    public double batteryVoltage;
    public double setBatteryVoltage;
    public double gravityPower;


    public Arm(HardwareMap hardwareMap, Telemetry telemetry){
        ArmMotor = hardwareMap.get(DcMotorEx.class, "Lift Motor");
        pid = new PIDController(Kp,Ki, Kd);

    }
    public void update(){
        currentAngle = encodersPerAngle * ArmMotor.getCurrentPosition();
        pid.setPID(Kp, Ki, Kd);
        double pidPower = pid.calculate(currentAngle, targetAngle);
        double feedforwardPower = calculateFeedForwardPower() + gravityPower;
        double power = (pidPower + feedforwardPower) / batteryVoltage;
        if(Math.abs(targetAngle - currentAngle) <= 3) {
            ArmMotor.setPower(power);
        }else{
            ArmMotor.setPower(gravityPower / batteryVoltage);
        }
        setBatteryVoltage(setBatteryVoltage);
        telemetry.addData("How far is it from deadband", Math.toRadians(Math.abs((targetAngle - 3) - currentAngle)));
    }
    public double calculateFeedForwardPower(){
        double frictionPower = Math.signum(targetAngle - currentAngle) * Ks;
        armFromMiddle = Math.sin(currentAngle) * ArmLength;
        gravityPower = armFromMiddle * Kg;
        return frictionPower;

    }
    public void setBatteryVoltage(double batteryVoltage){
        batteryVoltage = this.batteryVoltage;
    }
}
