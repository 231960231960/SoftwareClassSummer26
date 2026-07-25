package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class RaisingArm {
    public DcMotorEx armRaiserMotor;
    public double armHeight;
    private double gravityPower;

    public double encoderPerInch;
    public static double Kp, Kg, Kf, Kv, Ki, Kd;
    private PIDController pid;
    private double targetArmPosition;
    public double batteryVoltage;
    private double setBatteryVoltage;

    public RaisingArm(HardwareMap hardwareMap, Telemetry telemetry){
        armRaiserMotor = hardwareMap.get(DcMotorEx.class, "RaiserMotor");
        pid = new PIDController(Kp, Ki, Kd);


    }

    public void update(){
        armHeight = encoderPerInch * armRaiserMotor.getCurrentPosition();
        pid.setPID(Kp ,Ki ,Kd);

        double feedForward = calculatePower();
        double PidPower = pid.calculate(armHeight, targetArmPosition);
        PidPower = Range.clip(PidPower, -1, 1);
        setBatteryVoltage(setBatteryVoltage);
        double motorPower = feedForward + PidPower;
        if(Math.abs(targetArmPosition - armHeight) <= 1){
            armRaiserMotor.setPower(gravityPower / batteryVoltage);
        }else{
            armRaiserMotor.setPower(motorPower / batteryVoltage);
        }


    }
    public void setTargetArmPosition(double targetArmPosition){
        this.targetArmPosition = targetArmPosition;
    }
    private double calculatePower(){
        double frictionPower = Math.signum(targetArmPosition - armHeight) * Kf;
         gravityPower = Kg;
        double FeedforwardPower = frictionPower + gravityPower;
        return FeedforwardPower;
    }
    public void setBatteryVoltage(double batteryVoltage){
        batteryVoltage = this.batteryVoltage;
    }

}
