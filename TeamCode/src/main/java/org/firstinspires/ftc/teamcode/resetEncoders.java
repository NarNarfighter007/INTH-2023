package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.openftc.easyopencv.OpenCvCamera;

@TeleOp(group = "Main")

public class resetEncoders extends OpMode {

    BNO055IMU imu;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyRight;
    DcMotor SlippyLeft;
    Servo Claw;
    OpenCvCamera Eye;

    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());

        SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void loop() {

        telemetry.update();

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());

    }
}
