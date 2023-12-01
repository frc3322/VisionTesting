// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.PhotonUtils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class PhotonVision extends SubsystemBase implements Loggable {
  
  PhotonCamera camera = new PhotonCamera("Arducam_OV9281_USB_Camera");
  /** Creates a new PhotonVision. */
  public PhotonVision() {
    camera.getLatestResult();
  }

  @Override
  public void periodic() {
    
  }
}
