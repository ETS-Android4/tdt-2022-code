/**
 * Controls sequence of events in OpMode
 */
package org.tdt2845.ftclib.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.tdt2845.ftclib.commands.CommandScheduler;
import org.tdt2845.ftclib.commands.WaitForStartCommand;

public abstract class CommandOpMode extends OpMode {
    public CommandScheduler scheduler;
    public WaitForStartCommand waitForStartCommand;

    /**
     * Defined in an OpMode; adds commands to CommandScheduler
     */
    public abstract void setup();

    /**
     * Starts OpMode after start button is pressed
     */
    @Override
    public void init() {
        scheduler = new CommandScheduler();
        waitForStartCommand = new WaitForStartCommand(this);
        setup();
    }

    /**
     * Updates telemetry throughout OpMode period
     */
    @Override
    public void loop() {
        telemetry.update();
    }

    /**
     * Ends WaitForStartCommand when play button is pressed
     */
    @Override
    public void start() {
        waitForStartCommand.startGame();
    }

    /**
     * Ends program
     */
    @Override
    public void stop() {
        super.stop();
        scheduler.stop();
    }
}
