/**
 * Command run during init() phase of OpMode, before play button is pressed
 */

package org.tdt2845.ftclib.commands;

import org.tdt2845.ftclib.opmodes.CommandOpMode;

public class WaitForStartCommand extends Command {
    boolean gameStarted = false;

    /**
     * Constructs WaitForStartCommand
     * @param opMode current OpMode
     */
    public WaitForStartCommand(CommandOpMode opMode){
        super(opMode);
    }

    /**
     * Called in CommandOpMode, ends WaitForStartCommand and continues to other OpMode commands in queue
     */
    public void startGame() {
        gameStarted = true;
    }

    @Override
    public void start() {
        telemetry.addLine("running wait for start command");
    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {
        telemetry.addLine("ending wait for start command");
    }

    /**
     * @return boolean representing if startGame() is called by Command OpMode
     */
    @Override
    public boolean isFinished() {
        return gameStarted;
    }
}
