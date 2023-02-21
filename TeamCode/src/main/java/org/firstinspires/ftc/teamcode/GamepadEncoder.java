package org.firstinspires.ftc.teamcode;

import android.os.Bundle;
import android.widget.Button;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;


@TeleOp(group = "Main")

public class GamepadEncoder extends OpMode {

    BNO055IMU imu;
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
        //       telemetry.addData("imu", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES));


        //    SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //    SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      /*  frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    boolean changed = false; //Outside of loop()

    private static final int LIFT_SPEED = -1;

    private int liftEncoderValue = 0;

    @Override

    public void loop() {


       frontLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.35) * ((-1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));



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
            else Claw.setPosition(0);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false; */

        //low level = slippyRight = 1460
        //medium level = slippyRight = 2416
        //top level = slippyRight = 3400

        if (gamepad2.a) { //low
            telemetry.addLine("a button pressed");
            lift(1460, 1);
        }

        if (gamepad2.b) { //bring lift down
            telemetry.addLine("b button pressed");
            lift(0, -1);
        }

        if (gamepad2.y) { //high
            telemetry.addLine("y button pressed");
            lift(3400, 1);
        }

        if (gamepad2.x) { //medium
            telemetry.addLine("x button pressed");
            lift(2416, 1);
        }

        if(gamepad2.dpad_up){
            telemetry.addLine("dpadup2");
            liftSlide();
        }

        if(gamepad2.dpad_down){
            telemetry.addLine("dpaddown2");
            lowerSlide();
        }

//0.55 = open
        if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0) {
            telemetry.addLine("KILL SWITCH ACTIVE");
            System.exit(0); // Terminate the program
        }

        if (gamepad2.right_trigger > 0) {
            gamepad2.setLedColor(100, 100, 100, 100);
            gamepad2.rumbleBlips(1);
            Claw.setPosition(.2); //Closed
            telemetry.addData("Position:", Claw.getPosition());
        } else {
            Claw.setPosition(0);
            telemetry.addData("Position:", Claw.getPosition());
        }


    }

    private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        height = Postarget; //test negative sign
        SlippyRight.setTargetPosition(height);
        SlippyLeft.setTargetPosition(-height);
        SlippyRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyRight.setPower(speed);
        SlippyLeft.setPower(-speed);
        if (SlippyRight.getCurrentPosition() == height || SlippyLeft.getCurrentPosition() == -height) {
            SlippyLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setPower(0);
            SlippyLeft.setPower(0);
        }
        //while (opModeIsActive() && SlippyRight.isBusy()) {
        // idle();
        // }
    }

    private void liftSlide() {
        liftEncoderValue += 100; // Increase the encoder value by 100. You can adjust this to change the amount the slide moves.
        lift(liftEncoderValue, LIFT_SPEED); // Call the lift method with the new encoder value and lift speed.
    }

    // This is the method that will be called when you press the button to lower the slide.
    private void lowerSlide() {
        liftEncoderValue -= 100; // Decrease the encoder value by 100. You can adjust this to change the amount the slide moves.
        lift(liftEncoderValue, -0.3); // Call the lift method with the new encoder value and lift speed.
    }
}

  /*  protected void onCreate(Bundle savedInstanceState) {
        // Set up the PS4 controller input.
        // Replace the button and joystick constants with the actual values from your controller.
        final Button liftButton = gamepad2;
        final Joystick liftJoystick = gamepad.getJoystick(LEFT_JOYSTICK);

        // Set up a timer to update the lift position based on the joystick input.
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int joystickY = liftJoystick.getY();
                if (Math.abs(joystickY) > 0.1) { // Check that the joystick is not in the deadzone.
                    liftEncoderValue += joystickY * 10; // Adjust the encoder value based on the joystick input.
                    lift(liftEncoderValue, LIFT_SPEED); // Call the lift method with the new encoder value and lift speed.
                }
                handler.postDelayed(this, 20); // Update the lift position every 20 milliseconds.
            }
        });

        // Set up the button listeners.
        liftButton.setOnPressListener(new Runnable() {
            @Override
            public void run() {
                liftSlide();
            }
        });
        liftButton.setOnReleaseListener(new Runnable() {
            @Override
            public void run() {
                // Stop lifting the slide when the button is released.
                lift(0, 0);
            }
        });
        liftButton.setOnHoldListener(new Runnable() {
            @Override
            public void run() {
                // You can use this to implement continuous lifting while the button is held down.
            }
        });
    }
}
*/


