package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

@TeleOp(group = "Main")

public class Gamepad extends OpMode {

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
        SlippyRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      /*  frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */
    }

    boolean changed = false; //Outside of loop()

    @Override

    public void loop() {
        frontLeft.setPower((0.35) * ((1.5*gamepad1.left_stick_y) + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.35) * ((1.5*gamepad1.left_stick_y) + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.35) * ((-1.5*gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.35) * ((1.5*gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));

        telemetry.update();

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());


   /*     if(gamepad1.dpad_right && !changed) {
            if(Claw.getPosition() == 0.3) Claw.setPosition(0);
            else Claw.setPosition(0.3);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false; */

        if (gamepad2.dpad_up) {
            telemetry.addLine("dpadup");
            SlippyRight.setPower(-1);
            SlippyLeft.setPower(-1);
        }

        SlippyRight.setPower(0);
        SlippyLeft.setPower(0);


        if (gamepad2.dpad_down) {
            SlippyRight.setPower(0.5);
            SlippyLeft.setPower(0.5);

        }


        if (gamepad2.right_trigger > 0 ) {
            gamepad2.setLedColor(100,100,100, 100);
            Claw.setPosition(0.55); //Open;
            telemetry.addData("Position:", Claw.getPosition());
        } else if (gamepad2.left_trigger > 0 ){
            gamepad2.setLedColor(100,100,100, 100);
            gamepad2.rumbleBlips(1);
            Claw.setPosition(0); //Closed
            telemetry.addData("Position:", Claw.getPosition());
        }


    }
    }