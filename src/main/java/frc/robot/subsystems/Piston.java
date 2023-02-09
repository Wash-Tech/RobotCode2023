// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Piston extends SubsystemBase {
  Solenoid pitchSolenoid = null;
  /** Creates a new Piston. */
  public Piston() {
    pitchSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  }

  public void pitchPiston(){
    pitchSolenoid.set(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
