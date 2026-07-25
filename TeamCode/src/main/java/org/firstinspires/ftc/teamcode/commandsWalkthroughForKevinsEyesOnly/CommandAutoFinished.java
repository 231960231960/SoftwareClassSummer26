package org.firstinspires.ftc.teamcode.commandsWalkthroughForKevinsEyesOnly;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.Day3CommandsListAnswerKey;
import org.firstinspires.ftc.teamcode.subsystems.day1Collector.CollectorChallengeAnswerKey;
import org.firstinspires.ftc.teamcode.utils.command.CommandBuilder;

@Autonomous(name="Command Auto")
public class CommandAutoFinished extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CollectorChallengeAnswerKey collector = new CollectorChallengeAnswerKey(hardwareMap, telemetry);

        // if you are using Roadrunner or PIDDrive:
        Action pidDriveAction = null;
        Command preloadDrive = new CommandBuilder()
                .setIsFinished(() -> !pidDriveAction.run(new TelemetryPacket()))
                .build();
        Command secondSpike = null;

        CommandScheduler.getInstance().reset();

        CommandScheduler.getInstance().schedule(
                new ParallelCommandGroup(
                        new SequentialCommandGroup(
                                preloadDrive,
                                new ParallelCommandGroup(
                                        secondSpike,
                                        Day3CommandsListAnswerKey.collectorIntake(collector)
                                )
                                // continue auto sequence below
                        )
                        // insert shooting system command here
                )
        );

        waitForStart();

        while(opModeIsActive()) {
            // if you are using Pedro:
            // follower.update();

            CommandScheduler.getInstance().run();
            collector.update();
        }
    }
}
