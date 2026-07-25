package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class Refresh {
   public DcMotorEx liftMotor;
   public PIDController pid;
   private double armHeight;
   private double encodersPerInch;
   public double kP, kG, kF, kI, kD;
   public double targetHeight;


    public Refresh(HardwareMap hardwareMap, Telemetry telemetry){
     liftMotor = hardwareMap.get(DcMotorEx.class, "Lift Motor");
     pid = new PIDController(kP, kI, kD);



    }

    public void update(){
        armHeight = liftMotor.getCurrentPosition() * encodersPerInch;
        double PidPower = pid.calculate(armHeight, targetHeight);
        double FeedForwardPower = calculateFeedforward();
        liftMotor.setPower(PidPower + FeedForwardPower);


    }
    public double calculateFeedforward(){
        double gravityPower = kG;
        double frictionPower = Math.signum(targetHeight - armHeight) * kF;
        double feedforwardPowers = gravityPower + frictionPower;
        return feedforwardPowers;
    }

}
