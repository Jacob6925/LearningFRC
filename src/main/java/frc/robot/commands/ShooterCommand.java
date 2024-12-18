// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsys;
import frc.robot.subsystems.ShooterSubsys.Speed;

public class ShooterCommand extends Command {
  private final Speed speed;
  private final ShooterSubsys shooter;
  /** Creates a new ShooterCommand. */
  public ShooterCommand(ShooterSubsys shooter, ShooterSubsys.Speed speed) {
    this.speed = speed;
    this.shooter = shooter;
    
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.shooter.setSpeed(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.turnOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}