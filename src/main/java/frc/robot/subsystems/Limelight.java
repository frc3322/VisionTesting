// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Limelight extends SubsystemBase implements Loggable {
  /** Creates a new Limelight. */
  NetworkTable leftLimelight;
  NetworkTable rightLimelight;

  @Log
  public double leftXPose = -2;
  @Log
  public double leftYPose = -2;
  @Log
  public double leftZPose = -2;

  @Log
  public double rightXPose = -2;
  @Log
  public double rightYPose = -2;
  @Log
  public double rightZPose = -2;

  public Limelight() {
    NetworkTableInstance netTable = NetworkTableInstance.getDefault();
    leftLimelight = netTable.getTable("limelight-left");
    rightLimelight = netTable.getTable("limelight-right");
  }

  private double[] getLeftPose() {
    return leftLimelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[] {-2, -2, -2});
  }

  private double[] getRightPose() {
    return rightLimelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[] {-2, -2, -2});
  }

  @Override
  public void periodic() {
    leftXPose = getLeftPose()[0];
    leftYPose = getLeftPose()[1];
    leftZPose = getLeftPose()[2];

    rightXPose = getRightPose()[0];
    rightYPose = getRightPose()[1];
    rightZPose = getRightPose()[2];
  }
}
