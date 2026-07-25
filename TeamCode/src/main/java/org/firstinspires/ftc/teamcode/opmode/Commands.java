package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.RaisingArm;
import org.firstinspires.ftc.teamcode.subsystems.collector.CollectorChallengeAnswerKey;
import org.firstinspires.ftc.teamcode.subsystems.collector.CollectorEmpty;

import java.time.Instant;

public class Commands {
    public static InstantCommand collectorIntake(CollectorEmpty collector) {
        return new InstantCommand(() -> collector.setCollectorState(CollectorEmpty.CollectorState.COLLECT));
    }
    public static InstantCommand collectorOutake(CollectorEmpty collector){
      return new InstantCommand(() -> collector.setCollectorState(CollectorEmpty.CollectorState.EXTAKE));
    }
    // I Did not make a turret class//
    public static InstantCommand setRaisingArm(RaisingArm raisingArm){
        return new InstantCommand(() -> raisingArm.setTargetArmPosition(50));
    }
    public static InstantCommand setArmAngle(Arm arm){
        return new InstantCommand(() -> arm.targetAngle = Math.toRadians(90));
    }
}

