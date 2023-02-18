package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;


@Autonomous(group = "Main")
public class EncoderLeftAuto extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyRight;
    DcMotor SlippyLeft;
    Servo Claw;


    private int Pos;
    private int height;
    private int rightangle;

    @Override
    public void runOpMode() throws InterruptedException {
// Defining motors
        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        Pos = 0;
        rightangle = 719;

        waitForStart();
        telemetry.update();
        //sees qr code 1 from left side of alliance (Right wheels lined up with right edge of square mat) //1 in = 45
        height=0;
        Claw.setPosition(0.25);
        drive(45*7, .5);  //driving forward 5 inches = 228 //
      //  driveC(rightangle,0.35);
       // drive(45*20,.35);
       // driveCC(rightangle,0.35);
        straferight(50*24,0.35);
        drive(-45*8, -.35); //backwards to line up
        drive(45*53,0.43); //little more than 52 inches
        driveCC(rightangle/2,0.35);
        lift(3460, 0.8);
        telemetry.update();
        sleep(2500);
        drive(46*11,.35); //little bit more than 11 inches to the pole
        sleep(400);
        Claw.setPosition(0.6);
        sleep(700);
        drive(-1*45,-.5);
        sleep(200);
        lift(3460/5, -0.8);
        sleep(1000);
        drive(-10*45,-.5); //10 inches back
        telemetry.update();
        sleep(500);
        driveCC(340,0.35);
        drive(52*45,.6); //2422 = 50 inches
        Claw.setPosition(0.3);
        sleep(500);
        lift(1400,0.8);
        sleep(500);
        drive(-52*45,-0.5);
        lift(0,-0.8);
        sleep(1000);

        /*



//sees qr code number 2 from left side of alliance
        drive(228, 0.4); //driving forward 5 inches
        stopDriving();
        TurnC(0.4,1000); //turning clockwise 90 degrees !! TEST THIS
        resetEncoders();
        drive(914, 0.4); //driving forward 20 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning CC 90 degrees
        drive(2285, 0.4); //driving forward 50 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 0.4); //driving forward 6 inches
        stopDriving();
        lift(1000,1); //test this!!!!!
        resetEncoders();
        drive(46,0.2);
        stopDriving();
        resetEncoders();
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,-0.2); //check negatives
        stopDriving();
        resetEncoders();
        lift(0,-1); //dropping lift
        resetEncoders();
        drive(-274,-0.4); //driving 6 inches backward
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,0.4); //driving forward 55 inches
        stopDriving();
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        resetEncoders();
        drive(-2742,-0.3); //driving backwards
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning 120 degrees counterclockwise
        drive(594,.3); //driving to large size pole (13 inches)
        stopDriving();
        resetEncoders();
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,-0.4); //drives backwards to park
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning 60 degrees
        drive(-1371, 0.4);
        stopDriving();
        resetEncoders();
*/

        //sees qr code number 3 from left side of alliance
        /* drive(228, 0.4); //driving forward 5 inches
        stopDriving();
        TurnC(0.4,1000); //turning clockwise 90 degrees !! TEST THIS
        resetEncoders();
        drive(914, 0.4); //driving forward 20 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning CC 90 degrees
        drive(2285, 0.4); //driving forward 50 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 0.4); //driving forward 6 inches
        stopDriving();
        lift(1000,1); //test this!!!!!
        resetEncoders();
        drive(46,0.2);
        stopDriving();
        resetEncoders();
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,-0.2); //check negatives
        stopDriving();
        resetEncoders();
        lift(0,-1); //dropping lift
        resetEncoders();
        drive(-274,-0.4); //driving 6 inches backward
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,0.4); //driving forward 55 inches
        stopDriving();
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        resetEncoders();
        drive(-2742,-0.3); //driving backwards
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning 120 degrees counterclockwise
        drive(594,.3); //driving to large size pole (13 inches)
        stopDriving();
        resetEncoders();
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,-0.4); //drives backwards to park
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning 60 degrees
        drive(-3108, -0.4);
        stopDriving();
        resetEncoders();
*/}


    private void driveCC (int Postarget, double speed) {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Pos = Postarget;
        frontLeft.setTargetPosition(-Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(-Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        frontLeft.setPower(-speed);
        backRight.setPower(speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }

        sleep(500);
    }
        private void driveC (int Postarget, double speed) {
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            Pos = Postarget;
            frontLeft.setTargetPosition(Pos);
            frontRight.setTargetPosition(-Pos);
            backRight.setTargetPosition(-Pos);
            backLeft.setTargetPosition(Pos);

            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontRight.setPower(-speed);
            backLeft.setPower(speed);
            frontLeft.setPower(speed);
            backRight.setPower(-speed);

            while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                idle();
            }
            sleep(500);
        }


        private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        height = Postarget; //test negative sign
        telemetry.update();
        SlippyRight.setTargetPosition(height);
        SlippyLeft.setTargetPosition(-height);
        SlippyRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.update();
        SlippyRight.setPower(speed);
        SlippyLeft.setPower(-speed);
        if (SlippyRight.getCurrentPosition() == height || SlippyLeft.getCurrentPosition() == -height){
            SlippyLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setPower(0);
            SlippyLeft.setPower(0);
            telemetry.update();
        }
        //while (opModeIsActive() && SlippyRight.isBusy()) {
            // idle();
           // }
        sleep(500);
        }



    private void drive (int Postarget, double speed){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Pos = Postarget;
        frontLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(speed);
        backRight.setPower(speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }
        sleep(500);
    }
    private void straferight(int Postarget, double speed){ //This should strafe 48 inches, so 50 encoders/inch
        Pos = Postarget;
        frontLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(-Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(-Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        frontLeft.setPower(speed);
        backRight.setPower(speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }


    }
}