package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.HashMap;

public class MecanumWheels extends OpMode {
    private final double power = .5;
    final double degreesPerSecond = 30;
    private ElapsedTime time;
    private final double secondsPerMeter = 4;
    private DcMotor leftBack = hardwareMap.get(DcMotor.class,"leftBack");
    private DcMotor leftFront = hardwareMap.get(DcMotor.class,"leftFront");
    private DcMotor rightFront = hardwareMap.get(DcMotor.class,"rightFront");
    private DcMotor rightBack = hardwareMap.get(DcMotor.class,"rightBack");
    public MecanumWheels()
    {
        time = new ElapsedTime();

    }
    private boolean[] changeDefaultValues(boolean[] b)
    {
        for (int i =0;i<b.length;i++)
        {
            b[i] = true;
        }
        return b;
    }
    public void moveForward(double meters)
    {
        //All wheels forwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        motors.add(leftBack);
        motors.add(leftFront);
        motors.add(rightFront);
        motors.add(rightBack);
        boolean[] directions = new boolean[4];
        directions = changeDefaultValues(directions);
        executeFunction(secondsPerMeter*meters,motors,directions);

    }
    public void moveBackwards(double meters)
    {
        //All wheels backwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        motors.add(leftBack);
        motors.add(leftFront);
        motors.add(rightFront);
        motors.add(rightBack);
        boolean[] directions = new boolean[4];
        directions = changeDefaultValues(directions);
        directions[0] = false;
        directions[1] = false;
        directions[2] = false;
        directions[3] = false;
        executeFunction(secondsPerMeter*meters,motors,directions);
    }
    public void moveRight(double meters)
    {
        //Front left forward, front right backwards, back left backwards, back right forwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        motors.add(leftBack);
        motors.add(leftFront);
        motors.add(rightFront);
        motors.add(rightBack);
        boolean[] directions = new boolean[4];
        directions = changeDefaultValues(directions);
        directions[0] = false;
        directions[2] = false;
        executeFunction(secondsPerMeter*meters,motors,directions);
    }
    public void moveLeft(double meters)
    {
        //front left wheel back, front right forward, back left forward, back right backwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        motors.add(leftBack);
        motors.add(leftFront);
        motors.add(rightFront);
        motors.add(rightBack);
        boolean[] directions = new boolean[4];
        directions = changeDefaultValues(directions);
        directions[1] = false;
        directions[3] = false;

        executeFunction(secondsPerMeter*meters,motors,directions);
    }
    public void moveDiagonal(String direction, double meters)
    {
        //Forward Right - back right forward, front left forwards
        //Forward Left - front right forward, back left forwards
        //Backward left - front right backward, back left backwards
        // backward right - back right backwards, front left backwards
        ArrayList<DcMotor> motors = new ArrayList<>();

        boolean[] directions = new boolean[2];
        directions = changeDefaultValues(directions);
        switch(direction){
            case "forwardLeft":
                motors.add(rightFront); motors.add(leftBack);
                executeFunction(secondsPerMeter*meters,motors,directions);
                motors.clear();
                break;
            case "forwardRight":
                motors.add(rightBack); motors.add(leftFront);
                executeFunction(secondsPerMeter*meters, motors, directions);
                motors.clear();
                break;
            case "backwardLeft":
                motors.add(rightFront); motors.add(leftBack);
                directions[0] = false;
                directions[1] = false;
                executeFunction(secondsPerMeter*meters, motors,directions);
                motors.clear();
                break;
            case "backwardRight":
                motors.add(rightBack); motors.add(leftFront);
                directions[0] = false;
                directions[1] = false;
                executeFunction(secondsPerMeter*meters, motors, directions);
                motors.clear();
                break;
             default:
                 telemetry.addData("Invalid Direction", direction);
                 telemetry.update();
                 break;
        }
    }
    public void turnAboutCenter(double degrees,String direction)
    {
        //CW - right backwards, left forwards
        //CCW left backwards, right forwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        boolean[] directions = new boolean[4];
        directions = changeDefaultValues(directions);
        switch(direction){

            case "clockwise":
                motors.add(leftBack);
                motors.add(leftFront);
                motors.add(rightFront);
                motors.add(rightBack);
                directions[2] = false;
                directions[3] = false;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
            case "counterClockwise":
                motors.add(leftBack);
                motors.add(leftFront);
                motors.add(rightFront);
                motors.add(rightBack);
                directions[0] = false;
                directions[1] = false;
                directions[2] = true;
                directions[3] = true;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
        }

    }
    public void turnAboutRearAxis(double degrees, String direction)
    {
        // CC - Front Right backwards, front left forwards
        //CCW - Front right forwards, front left backwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        boolean[] directions = new boolean[2];
        directions = changeDefaultValues(directions);
        switch(direction){

            case "clockwise":

                motors.add(leftFront);
                motors.add(rightFront);
                directions[0] = true;
                directions[1] = false;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
            case "counterClockwise":
                motors.add(leftFront);
                motors.add(rightFront);
                directions[0] = false;
                directions[1] = true;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
        }
    }

    public void turnAboutFrontAxis(double degrees, String direction)
    {
        // CC - back right backwards, back left forwards
        //CCW - back right forwards, back left backwards
        ArrayList<DcMotor> motors = new ArrayList<>();
        boolean[] directions = new boolean[2];
        directions = changeDefaultValues(directions);
        switch(direction){

            case "clockwise":

                motors.add(leftBack);
                motors.add(rightBack);
                directions[0] = true;
                directions[1] = false;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
            case "counterClockwise":
                motors.add(leftFront);
                motors.add(rightFront);
                directions[0] = false;
                directions[1] = true;
                executeFunction(degrees/degreesPerSecond,motors,directions);
                break;
        }
    }
    private void executeFunction(double t, ArrayList<DcMotor> motorsToUse, boolean[] directions)
    {
        time.reset();
        for (int i=0; i< motorsToUse.size();i++)
        {
            motorsToUse.get(i).setPower(directions[i] ? power : power*-1);
        }
        while (time.time()<t)
        {

        }
    }
    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

}
