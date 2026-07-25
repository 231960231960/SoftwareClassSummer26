package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorWrapper{
    public static double minR = 0;  public static double maxR = 0;
    public static double minG = 0;  public static double maxG = 0;
    public static double minB = 0; public static double maxB = 0;
    private double Rred, Rgreen, Rblue;
    private double red, green, blue;
    private double totalColor;
    private final Telemetry telemetry;
    private final ColorSensor colorSensor;
    public ColorSensorWrapper(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.colorSensor = hardwareMap.get(ColorSensor.class, "Color Sensor");
    }
    public void update(){
        Rblue = colorSensor.blue();
        Rred = colorSensor.red();
        Rgreen = colorSensor.green();
        totalColor = Rred + Rgreen + Rblue;
        blue = Rblue / totalColor;
        red = Rred / totalColor;
        green = Rgreen / totalColor;
        telemetry.addData("blue", blue);
        telemetry.addData("red", red);
        telemetry.addData("green", green);
        SeesColor();


    }
    public void SeesColor(){
        boolean redValid = minR <= red && red <= maxR;

        boolean blueValid = blue <= maxB && blue >= minB;

        boolean greenValid = green <= maxG && green >= minG;
        telemetry.addData("Sees Blue", blueValid);
        telemetry.addData("Sees Red", redValid);
        telemetry.addData("Sees Green", greenValid);

    }
}
