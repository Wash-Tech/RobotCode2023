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
    //addRequirements(RobotContainer.m_DifferentialDriveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
    m_autoDrive = autoDrive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_autoDrive.m_pidleftFront.setReference(100, CANSparkMax.ControlType.kPosition);
    m_autoDrive.m_pidrightFront.setReference(100,CANSparkMax.ControlType.kPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
