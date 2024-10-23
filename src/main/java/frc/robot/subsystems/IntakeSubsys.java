// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IntakeSubsys extends SubsystemBase {
  private final TalonFX intakeMotor = new TalonFX(14);

  /** Creates a new Intake. */
  public IntakeSubsys() {
    intakeMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public enum Speed{
    FAST_IN(-.5),
    FAST_OUT(.5),
    SLOW_IN(-.25),
    SLOW_OUT(.25),
    OFF(0);

    public double speed;
    private Speed(double speed) {
      this.speed = speed;
    }
  }

  public void setSpeed(Speed speed) {
    intakeMotor.set(speed.speed);
  }

  public void off() {
    intakeMotor.set(0);
  }
}
