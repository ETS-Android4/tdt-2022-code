/**
 *  Sequencer controlling execution of commands
 */

package org.tdt2845.ftclib.commands;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandScheduler {
    public ExecutorService exec;
    public ExecutorService backgroundExec;
    public boolean running = true;
    public ArrayList<Thread> backgroundThreads = new ArrayList<>();

    /**
     * Instantiates a new CommandScheduler
     */
    public CommandScheduler() {
        backgroundExec = Executors.newCachedThreadPool();
        exec = Executors.newSingleThreadExecutor();
    }

    /**
     * Adds a Runnable to a queue of robot commands
     * @param command command to be added to queue
     */
    public void add(final Runnable command) {
        exec.execute(command);
    }

    /**
     * Adds a Runnable to queue; will run in background and move on in queue upon being reached
     * @param command command to be run in background
     */
    public void runInBackground(final Runnable command) {
        exec.execute((new Runnable() {
            @Override
            public void run() {
                backgroundExec.execute(command);
            }
        }));
    }

    /**
     * Interrupts all running commands to shut down robot
     */
    public void stop(){
        running = false;
        exec.shutdownNow();
        backgroundExec.shutdownNow();
    }

    /**
     * Prevents scheduler from taking in new commands
     */
    public void finish() {
        exec.shutdown();
        backgroundExec.shutdown();
    }

    /**
     * Checks if scheduler is still running (robot is still active)
     * @return boolean value true if robot is inactive, false if active
     */
    public boolean isTerminated() {
        return exec.isTerminated() && backgroundExec.isTerminated();
    }
}
