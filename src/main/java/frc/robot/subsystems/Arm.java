// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private CANSparkMax armExtender;

  public Arm() {
    armExtender = new CANSparkMax(7, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  /**
   * extends/retracts the arm based on the speed value
   * @param speed number value that is given by the ExtendArm & ReverseExtendArm command
   */  
  public void spin(double speed) {
    armExtender.set(speed);
  }
}
