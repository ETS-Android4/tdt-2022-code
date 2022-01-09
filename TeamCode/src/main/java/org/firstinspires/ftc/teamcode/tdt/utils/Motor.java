package org.firstinspires.ftc.teamcode.tdt.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motor {
    private DcMotor motor;
    private double previousPos = 0;
    private double time = 0;
    private double previousReset = 0;
    private double previousTime = 0;
    private final double NANOSECONDS_PER_MIN = 6e+10;
    private double cpr;
    private double MAX_RPM;
    private double rpm;
    private double wheelDiameter;
    double TICKS_PER_INCH;

    private double countMultiplier = 1.0;


    public Motor(String name , HardwareMap hwmap){
        motor = hwmap.dcMotor.get(name);
    }

    public Motor(String name , double cpr , double wheelDiameter , HardwareMap hwmap){
        motor = hwmap.dcMotor.get(name);
        this.cpr = cpr;
        this.wheelDiameter = wheelDiameter;
        this.TICKS_PER_INCH = this.cpr / (this.wheelDiameter * Math.PI);
    }

    public void resetMotor(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void RUN_USING_ENCODER(){
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double getRpm(){
        time = System.currentTimeMillis() - previousReset;
        double deltaPos = motor.getCurrentPosition() - previousPos;
        double deltaTime = (System.nanoTime() - previousTime) / NANOSECONDS_PER_MIN;

        if(time > 10){
            rpm = (deltaPos / cpr) / deltaTime;
            previousPos = getCurrentPosition();
            previousReset = System.currentTimeMillis();
            previousTime = System.nanoTime();
        }

        return rpm;
    }

    public void reverse(){motor.setDirection(DcMotorSimple.Direction.REVERSE);}

    public void setMode(DcMotor.RunMode mode){
        motor.setMode(mode);
    }

    public void setBreakMode(){
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setFloatMode(){
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power){
        motor.setPower(power);
    }

    public void setReverse(boolean reverse){
        countMultiplier = reverse ? -1.0 : 1.0;
    }

    public void setWheelDiameter(double wheelDiameter){  this.wheelDiameter = wheelDiameter; }

    public double getCurrPosInches(){
        return getCurrentPosition() / TICKS_PER_INCH;
    }

    public double getPower(){return motor.getPower();}

    public double getMAX_RPM(){
        return this.MAX_RPM;
    }

    public double getCurrentPosition(){
        return countMultiplier * motor.getCurrentPosition();
    }

}
