// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Piston extends SubsystemBase {
  Solenoid pitchSolenoid = null;
  //Compressor pcmCompressor = new Compressor(0,PneumaticsModuleType.CTREPCM);
  /** Creates a new Piston. */
  public Piston() {
    pitchSolenoid = new Solenoid(1,PneumaticsModuleType.CTREPCM, 0);
    //pcmCompressor.enableDigital();
  }
  
  /**
   * Shoots out piston
   */
  public void pitchPiston(){
    System.out.println("subsystem: pitchSolenoid");
    pitchSolenoid.set(true);
    
  }

  /**
   * retracts the piston
   */
  public void retractPiston(){
    System.out.println("subsystem: retract");
    pitchSolenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
