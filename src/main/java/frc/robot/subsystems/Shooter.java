// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  /** Creates a new Shooter. */
  public final TalonFX top = new TalonFX(0);
  public final TalonFX bottom = new TalonFX(1);

  public Shooter() {
    top.setInverted(false);
    bottom.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public enum Speed {
    FAST(0.5),
    SLOW(0.25);

    public double speed;
    private Speed(double speed) {
      this.speed = speed;
    }
  }

  public void setSpeed(Speed speed) {
    top.set(speed.speed);
    bottom.set(speed.speed);
  }

  public void turnOff() {
    top.set(0);
    bottom.set(0);
  }
}
