package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class AutonomusTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        MecanumWheels drive = new MecanumWheels(rightBack,leftBack,rightFront,leftFront);
        drive.moveForward(5);
        drive.moveBackwards(5);
        drive.moveLeft(5);
        drive.moveRight(5);
    }
}
