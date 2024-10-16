// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class RobotContainer {
  // private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);

  private Intake intake;
  private Shooter shooter;


  public RobotContainer() {
    configureBindings();
    shooter = new Shooter();
    intake = new Intake();
  }

  private void configureBindings() {
    
    new JoystickButton(operator, 0).onTrue(new ShooterCommand(shooter, Shooter.Speed.FAST));
    new JoystickButton(operator, 11).whileTrue(new IntakeCommand(intake, Intake.Speed.FAST_OUT));
    new JoystickButton(operator, 12).whileTrue(new IntakeCommand(intake, Intake.Speed.FAST_IN));
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
