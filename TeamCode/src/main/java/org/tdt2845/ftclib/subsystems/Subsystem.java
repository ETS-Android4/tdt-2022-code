/**
 * Base subsystem class
 */
package org.tdt2845.ftclib.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.tdt2845.ftclib.opmodes.CommandOpMode;

public abstract class Subsystem {
    HardwareMap map;
    public CommandOpMode opMode;
    public Telemetry telemetry;

    /**
     * Constructs a subsystem
     * @param opMode opMode currently being run, provides subsystem with HardwareMap
     */
    public Subsystem(CommandOpMode opMode) {
        this.map = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.opMode = opMode;
    }
}
