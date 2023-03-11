// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DifferentialDriveTrain extends SubsystemBase {
  private CANSparkMax leftFrontMC = null;
  private CANSparkMax leftRearMC = null;
  private CANSparkMax rightFrontMC = null;
  private CANSparkMax rightRearMC = null;

  MotorControllerGroup leftMotors = null;
  MotorControllerGroup rightMotors = null;

  public DifferentialDrive differentialDrive = null;

  public SparkMaxPIDController m_pidleftFront;
  public SparkMaxPIDController m_pidleftRear;
  public SparkMaxPIDController m_pidrightFront;
  public SparkMaxPIDController m_pidrightRear;
  public RelativeEncoder m_encoderleftFront;
  public RelativeEncoder m_encoderleftRear;
  public RelativeEncoder m_encoderrightFront;
  public RelativeEncoder m_encoderrightRear;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  /** Creates a new ExampleSubsystem. */
  public DifferentialDriveTrain() {
    leftFrontMC = new CANSparkMax(2, MotorType.kBrushless);
    leftRearMC = new CANSparkMax(1, MotorType.kBrushless);
    rightFrontMC = new CANSparkMax(5, MotorType.kBrushless);
    rightRearMC = new CANSparkMax(4, MotorType.kBrushless);

    leftRearMC.follow(leftFrontMC);
    rightRearMC.follow(rightFrontMC);

    leftFrontMC.setInverted(true);
    leftRearMC.setInverted(true);

    m_pidleftFront = leftFrontMC.getPIDController();
    m_pidleftRear = leftRearMC.getPIDController();
    m_pidrightFront = rightFrontMC.getPIDController();
    m_pidrightRear = rightRearMC.getPIDController();

    m_encoderleftFront = leftFrontMC.getEncoder();
    m_encoderleftRear = leftRearMC.getEncoder();
    m_encoderrightFront = rightFrontMC.getEncoder();
    m_encoderrightRear = rightRearMC.getEncoder();


    leftMotors = new MotorControllerGroup(leftFrontMC, leftRearMC);
    //leftMotors.setInverted(true);
    rightMotors = new MotorControllerGroup(rightFrontMC, rightRearMC);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    //atempt to lengthen watchdog timer for differential drive
    differentialDrive.setExpiration(.1);


    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 0.8; 
    kMinOutput = -0.8;

    // set PID coefficients
    m_pidleftFront.setP(kP);
    m_pidleftFront.setI(kI);
    m_pidleftFront.setD(kD);
    m_pidleftFront.setIZone(kIz);
    m_pidleftFront.setFF(kFF);
    m_pidleftFront.setOutputRange(kMinOutput, kMaxOutput);

    m_pidrightFront.setP(kP);
    m_pidrightFront.setI(kI);
    m_pidrightFront.setD(kD);
    m_pidrightFront.setIZone(kIz);
    m_pidrightFront.setFF(kFF);
    m_pidrightFront.setOutputRange(kMinOutput, kMaxOutput);

  
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }


  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("LeftDriveEncoder", m_encoderleftFront.getPosition());
    SmartDashboard.putNumber("RightDriveEncoder", m_encoderrightFront.getPosition());

    double P = SmartDashboard.getNumber("P Gain", kP);
    //System.out.println(P);
    if (P != kP) {
      m_pidleftFront.setP(P);
      m_pidrightFront.setP(P);
      kP = P;
    }

    double D = SmartDashboard.getNumber("D Gain", kD);
    if (D !=kD) {
      m_pidleftFront.setD(D);
      m_pidrightFront.setD(D);
      kD = D;
    }

    double I = SmartDashboard.getNumber("I Gain", kI);
    if (I != kI) {
      m_pidleftFront.setI(I);
      m_pidrightFront.setI(I);
      kI = I;
    }
  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
