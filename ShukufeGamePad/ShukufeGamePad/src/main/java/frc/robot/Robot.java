



package frc.robot;




import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;

import com.kauailabs.navx.frc.AHRS;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private final XboxController gamePadController = new XboxController(1);
  Joystick joystick = new Joystick(0);
  private PWMVictorSPX leftMotor1;
  private PWMVictorSPX leftMotor2;
  private PWMVictorSPX rightMotor1;
  private PWMVictorSPX rightMotor2;
  private VictorSP ArmMotor;
  private VictorSP intakeMotor;
  
  
  private MotorControllerGroup leftMotor;
  private MotorControllerGroup rightMotor;
  private DifferentialDrive shukufe;
  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private double offset = 0;
  private int count = 0;

  private boolean balanced = false;
  
  boolean autoBalanceXMode;
  boolean autoBalanceYMode;

  private static final int leftMotorPort1 = 0;
  private static final int leftMotorPort2 = 1;
  private static final int rightMotorPort1 = 2;
  private static final int rightMotorPort2 = 3;
  private static final int ArmMotorPort = 7;
  private static final int intakeMotorPort = 6;
  private double startTime; 
  static final double kOffBalanceAngleThresholdDegrees = 10;
    static final double kOonBalanceAngleThresholdDegrees  = 5;
 


   
      
  
  @Override
  public void robotInit() {
    

    
   
    m_robotContainer = new RobotContainer();
  leftMotor1 = new PWMVictorSPX(leftMotorPort1);
  leftMotor2 = new PWMVictorSPX(leftMotorPort2);
  rightMotor1 = new PWMVictorSPX(rightMotorPort1);
  rightMotor2 = new PWMVictorSPX(rightMotorPort2);
  ArmMotor = new VictorSP(ArmMotorPort);
  intakeMotor = new VictorSP(intakeMotorPort);




  leftMotor = new MotorControllerGroup(leftMotor1,leftMotor2);
  rightMotor = new MotorControllerGroup(rightMotor1,rightMotor2);
  shukufe = new DifferentialDrive(leftMotor, rightMotor);
  rightMotor.setInverted(true);



  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("aci", ahrs.getPitch());
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
  startTime = Timer.getFPGATimestamp();
  offset = ahrs.getPitch();

  }

  @Override
  public void autonomousPeriodic() {
   double time = Timer.getFPGATimestamp();

   

   
   
   // Control drive system automatically, 
   // driving in reverse direction of pitch/roll angle,
   // with a magnitude based upon the angle
   
  
   
   
   
    //Taksi Sistemi 
      /*if(time - startTime < 3){
      intakeMotor.set(-0.5);
    }
    else{
      intakeMotor.set(0);
    
    }  

   //2 top ve taksi sistemi

   /**if(time - startTime > 5 && time - startTime < 7){
    leftMotor1.set(-0.6);
    leftMotor2.set(-0.6);
    rightMotor1.set(0.6);
    rightMotor2.set(0.6);


  }
   else{
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }
   /**if(time - startTime > 7 && time - startTime < 8){
    leftMotor1.set(-0.6);
    leftMotor2.set(-0.6);
    rightMotor1.set(0.6);
    rightMotor2.set(0.6);

  }
   else{
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }
   if(time - startTime > 8 && time - startTime < 9){
    ArmMotor.set(0.7);
   }
   else{
    ArmMotor.set(0);
   }
   if (time - startTime > 9 && time - startTime < 10){
    intakeMotor.set(-0.7);
    leftMotor1.set(-0.3);
    leftMotor2.set(-0.3);
    rightMotor1.set(0.3);
    rightMotor2.set(0.3);

   }
   else {
    intakeMotor.set(0);
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }
   if(time - startTime > 10 && time - startTime < 12){
    leftMotor1.set(0.6);
    leftMotor2.set(0.6);
    rightMotor1.set(0.6);
    rightMotor2.set(0.6);

  }
   else{
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }*/
   
   
   // şarj istasyonuna bağlı kalma 
   if(time - startTime < 3){
    intakeMotor.set(-0.5);
  }
  else{
    intakeMotor.set(0);
  
  }  
   
   if( time - startTime > 3 && time - startTime <= 7.5 ){
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }
   else if(time - startTime > 7.3 && time - startTime <= 9){
    leftMotor1.set(-0.45);
    leftMotor2.set(-0.45);
    rightMotor1.set(0.45);
    rightMotor2.set(0.45);
   }
   else if(time - startTime > 9.7 && time - startTime <= 15 && !balanced){
    if(Math.abs(ahrs.getPitch()) - offset < 3.0)
    {
      count++;
    }

    if(count > 4)
    {
      balanced = true;
    }

    leftMotor1.set(-0.29);
    leftMotor2.set(-0.29);
    rightMotor1.set(0.29);
    rightMotor2.set(0.29);
   }
   else
   {
    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);
   }
  }
  @Override
  public void teleopInit() {
   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    shukufe.arcadeDrive(-gamePadController.getRawAxis(1), -gamePadController.getRawAxis(4));
    
    // Arm System 
    
    if(joystick.getRawButton(5))  //Arm yukarı
    {
      ArmMotor.set(0.6);
    }
    else if(joystick.getRawButton(3))   //Arm aşağı
    {
      ArmMotor.set(-0.6);
    }
    else
    {
      ArmMotor.set(0);
    }

    //Intake System 

    if(joystick.getRawButton(1))  // küp al
    {
      intakeMotor.set(0.5);
    }
    else if(joystick.getRawButton(2))   //alt, orta küp at
    {
      intakeMotor.set(-0.5);
    }
    else if(joystick.getRawButton (7))   //yukarı küp at 
    {
      intakeMotor.set(-0.55);
    }
    else
    {
      intakeMotor.set(0);
    }
                                                                                                                                                               


  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
