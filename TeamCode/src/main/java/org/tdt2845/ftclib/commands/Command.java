/**
 * Command used to control robot during OpMode
 */
package org.tdt2845.ftclib.commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.tdt2845.ftclib.opmodes.CommandOpMode;

public abstract class Command implements Runnable {

    public Gamepad gamepad1;
    public Gamepad gamepad2;
    public Telemetry telemetry;


    /**
     * Instantiates a command to be run in a CommandScheduler
     * @param opMode opMode in which command is being run
     */
    public Command(CommandOpMode opMode) {
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.telemetry = opMode.telemetry;
    }
    boolean isInterrupted = false;


    protected Command() {
    }

    /**
     * Called when CommandScheduler executes command; runs once before update() runs for the first time
     */
    public abstract void start();

    /**
     * Called repeatedly until isFinished() returns true
     */
    public abstract void update();

    /**
     * Called when command finishes and update() is no longer being called; isFinished() returned true or command is interrupted
     */
    public abstract void stop();

    /**
     * Determines when program is finished
     * @return evaluation of condition checked to determine if command is over
     */
    public abstract boolean isFinished();

    /**
     * Defines life cycle of a command
     */
    @Override
    public void run() {
        start();
        while (!isFinished() && !isInterrupted && !Thread.interrupted()) {
            update();
        }
        stop();
    }
}
