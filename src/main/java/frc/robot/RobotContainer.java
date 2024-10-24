// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.ShooterSubsys;
import frc.robot.subsystems.SwerveSubsys;
import frc.robot.subsystems.IntakeSubsys;

public class RobotContainer {
  private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);

  private final IntakeSubsys s_Intake;
  private final ShooterSubsys s_Shooter;
  private final SwerveSubsys s_Swerve;

  public RobotContainer() {
    s_Shooter = new ShooterSubsys();
    s_Intake = new IntakeSubsys();
    s_Swerve = new SwerveSubsys();
    configureBindings();
    setupAutoNamedCommands();
  }

  private void configureBindings() {
    /* Driver Buttons */
    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve, 
            () -> -driver.getRawAxis(XboxController.Axis.kLeftY.value), 
            () -> -driver.getRawAxis(XboxController.Axis.kLeftX.value),
            () -> -driver.getRawAxis(XboxController.Axis.kRightX.value)
        )
    );

    new JoystickButton(operator, 1).whileTrue(new ShooterCommand(s_Shooter, ShooterSubsys.Speed.FAST));
    new JoystickButton(operator, 11).whileTrue(new IntakeCommand(s_Intake, IntakeSubsys.Speed.FAST_OUT));
    new JoystickButton(operator, 12).whileTrue(new IntakeCommand(s_Intake, IntakeSubsys.Speed.FAST_IN));
  }

  private void setupAutoNamedCommands() {
    NamedCommands.registerCommand("ShootNote", new SequentialCommandGroup(
      new InstantCommand(() -> s_Shooter.setSpeed(ShooterSubsys.Speed.FAST), s_Shooter),
      new WaitCommand(1),
      new InstantCommand(() -> s_Intake.setSpeed(IntakeSubsys.Speed.FAST_OUT), s_Intake),
      new WaitCommand(1),
      new InstantCommand(()-> {
        s_Intake.setSpeed(IntakeSubsys.Speed.OFF);
        s_Shooter.setSpeed(ShooterSubsys.Speed.OFF);
      })
    ));

    NamedCommands.registerCommand("PickupNote", new SequentialCommandGroup(
      new InstantCommand(() -> s_Intake.setSpeed(IntakeSubsys.Speed.SLOW_IN), s_Intake),
      new WaitCommand(1),
      new InstantCommand(() -> s_Intake.setSpeed(IntakeSubsys.Speed.OFF), s_Intake)
    ));
  }

  public Command getAutonomousCommand() {
    return new PathPlannerAuto("GetNote2");
  }
}