// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.IOException;
import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;

import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;
import org.photonvision.EstimatedRobotPose;

public class PhotonVision extends SubsystemBase implements Loggable {
  
  PhotonCamera camera; 
  @Log double yaw;

  AprilTagFieldLayout aprilTagFieldLayout;
  PhotonPoseEstimator photonPoseEstimator;
  @Log Pose3d poseEstimate;

  PhotonPipelineResult currentResult;

  /** Creates a new PhotonVision. */
  public PhotonVision() {
    camera = new PhotonCamera("camera");

    Transform3d robotToCam = new Transform3d(new Translation3d(0, 0, 0), new Rotation3d(0,0,0));
    photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.LOWEST_AMBIGUITY, camera, robotToCam);

    try {
      aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PhotonPipelineResult getResult(){
    if (camera.getLatestResult().hasTargets()) {
      return camera.getLatestResult();
    }
    return null;
  }

  public double getYaw() {
    double yaw = currentResult == null ? 0 : currentResult.getBestTarget().getYaw();
    return yaw;
  }

  public void updatePose() {
      Optional<EstimatedRobotPose> estimate = photonPoseEstimator.update();

      if (estimate.isPresent())
        poseEstimate = estimate.get().estimatedPose;
  }
  
  @Override
  public void periodic() {
    
    //Assigns the result of this loop to a variable
    if (camera.getLatestResult().hasTargets()) {
      currentResult = camera.getLatestResult();
    }
    
    yaw = getYaw();
    
    //updatePose();
  }
}
