// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DifferentialDriveTrain;
import com.revrobotics.CANSparkMax;


public class PIDDriveAuto extends CommandBase {
  DifferentialDriveTrain m_autoDrive;
  /** Creates a new PIDDriveAuto. */
  public PIDDriveAuto(DifferentialDriveTrain autoDrive) {

    // Use addRequirements() here to declare subsystem dependencies.
    m_autoDrive = autoDrive;
    addRequirements(m_autoDrive);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_autoDrive.differentialDrive.setSafetyEnabled(false);
    m_autoDrive.m_encoderleftFront.setPosition(0);
    m_autoDrive.m_encoderrightFront.setPosition(0);

    double setpoint = -100;
    
    //PIDslot 0 is distance PID, 1 will be Velocity PID
    m_autoDrive.m_pidleftFront.setReference(setpoint, CANSparkMax.ControlType.kPosition, 0);
    m_autoDrive.m_pidrightFront.setReference(setpoint,CANSparkMax.ControlType.kPosition, 0);

    System.out.println("Pid set. Target: " + setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Pid driving");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_autoDrive.differentialDrive.setSafetyEnabled(true);
    m_autoDrive.arcadeDrive(0, 0);

    System.out.println("Pid fiished");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
