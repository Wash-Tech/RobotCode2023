// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;
import frc.robot.subsystems.Piston;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Shoulder;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static final DifferentialDriveTrain m_DifferentialDriveTrain = new DifferentialDriveTrain();
  public static final Piston m_Piston = new Piston();
  public static final Arm m_arm = new Arm();
  public static final Shoulder m_shoulder = new Shoulder();
  public static final XboxController m_driverController = new XboxController(0);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_DifferentialDriveTrain.setDefaultCommand(new DriveArcade());
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  private void configureBindings() {
    Trigger rightBumperButton = new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value);
    rightBumperButton.whileTrue(new ShootPiston());

    Trigger aButton = new JoystickButton(m_driverController, XboxController.Button.kA.value);
    aButton.whileTrue(new ExtendArm(m_arm));

    Trigger bButton = new JoystickButton(m_driverController, XboxController.Button.kB.value);
    bButton.whileTrue(new RotateShoulder(m_shoulder));

    Trigger yButton = new JoystickButton(m_driverController, XboxController.Button.kY.value);
    yButton.whileTrue(new ReverseRotateShoulder(m_shoulder));

    Trigger xButton = new JoystickButton(m_driverController, XboxController.Button.kX.value);
    xButton.whileTrue(new ReverseExtendArm(m_arm));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }
  
  public XboxController getdriverController() {
    return m_driverController;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
