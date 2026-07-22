package org.firstinspires.ftc.teamcode.FRQs;
public class Bottle{

    public double bottleAmount;
    public double maxCapacity;
    public double removeAm;


    public Bottle(double maxCapacity){
        this.maxCapacity = maxCapacity;
        bottleAmount = maxCapacity;
        updateAmount(removeAm);
    }

    public double updateAmount(double removeAmount){
       bottleAmount -= removeAmount;
       if(bottleAmount <= maxCapacity / 4){
           bottleAmount = maxCapacity;
       }

       return bottleAmount;

    }








}

