// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {

  private TalonFX intakeMotor = new TalonFX(0);

  /** Creates a new Intake. */
  public Intake() {
    intakeMotor.setInverted(false);
  }


  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }


  public enum Speed{
    FAST_IN(-.5),
    FAST_OUT(.5),
    SLOW_IN(-.25),
    SLOW_OUT(.25);

    private Speed(double speed)
    {
      this.speed = speed;

    }

    public double speed;
  }

  public void setSpeed(Speed speed)
  {
    intakeMotor.set(speed.speed);
  }

  public void off(){
    intakeMotor.set(0);
  }



}
