package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;


@Autonomous(group = "Main")
public class autodebug extends Gyro {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyRight;
    DcMotor SlippyLeft;
    Servo Claw;
    OpenCvCamera Eye;

    private int Pos;
    private int height;

    public final static double cycle = 537.4; //encoder value = 11.87 inches

    @Override
    public void runOpMode() {
// Defining motors
        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);


        SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        Pos = 0;

        waitForStart();
        telemetry.update();
    }
}