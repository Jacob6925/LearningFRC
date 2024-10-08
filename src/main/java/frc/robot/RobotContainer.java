// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShooterCommand;

public class RobotContainer {
  // private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(operator, 0).whileTrue(new ShooterCommand());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
