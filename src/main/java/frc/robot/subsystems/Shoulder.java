// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shoulder extends SubsystemBase {
  /** Creates a new Arm. */
  private CANSparkMax shoulderExtender;

  public Shoulder() {
    shoulderExtender = new CANSparkMax(8, MotorType.kBrushed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * rotate the shoulder with the given speed
   * @param speed the value given by the RotateShoulder & ReverseRotateSoulder command
   */
  public void spin(double speed) {
    shoulderExtender.set(speed);
  }
}
