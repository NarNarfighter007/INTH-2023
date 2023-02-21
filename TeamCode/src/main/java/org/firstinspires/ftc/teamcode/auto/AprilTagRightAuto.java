package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class AprilTagRightAuto extends LinearOpMode
{
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
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int Left = 1;
    int Middle = 2;
    int Right =3;
    AprilTagDetection tagOfInterest = null;
    double close = 0;
    double open = 0.2;


    @Override
    public void runOpMode()
    {
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
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

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
        rightangle = 740;
        height=0;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Eye"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == Left || tag.id == Middle || tag.id == Right)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tseag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */
        if (tagOfInterest == null || tagOfInterest.id == Left){

            //trajectory for Left tag detection
            telemetry.addLine("Did not find tag of interest, defaulting to Left; Or found Left tag");
            telemetry.update(); //low = 1578 middle = 2531 high = 3489
            Claw.setPosition(0);
            drive(45*4, .5);  //driving forward 5 inches = 228 //
            strafeleft(50*20,0.35);
            sleep(1000);
            drive(-45*7, -.35); //backwards to line up
            drive(45*53,0.43); //little more than 52 inches
            driveC(400,0.35);
            lift(3489, 0.8);
            telemetry.update();
            sleep(2500);
            drive(46*11,.35); //little bit more than 11 inches to the pole
            sleep(400);
            Claw.setPosition(0.2);
            sleep(700);
            drive(-1*45,-.5);
            sleep(200);
            lift(3460/5, -0.8);
            sleep(1000);
            drive(-10*45,-.5); //10 inches back
            telemetry.update();
            sleep(500);
            driveC(340,0.35);
            drive(50*45+100,.6); //2422 = 50 inches
            Claw.setPosition(0);
            sleep(500);
            lift(1400,0.8);
            sleep(500);
            drive(-52*45-100,-0.5);
            lift(0,-0.8);
            sleep(1000);


        } else if(tagOfInterest.id == Middle){

            //trajectory for Middle tag detection
            telemetry.addLine("Found Middle Tag");
            telemetry.update(); //low = 1578 middle = 2531 high = 3489
            Claw.setPosition(0);
            drive(45*4, .5);  //driving forward 5 inches = 228 //
            strafeleft(50*20,0.35);
            sleep(1000);
            drive(-45*7, -.35); //backwards to line up
            drive(45*53,0.43); //little more than 52 inches
            driveC(400,0.35);
            lift(3489, 0.8);
            telemetry.update();
            sleep(2500);
            drive(46*11,.35); //little bit more than 11 inches to the pole
            sleep(400);
            Claw.setPosition(0.2);
            sleep(700);
            drive(-1*45,-.5);
            sleep(200);
            lift(3460/5, -0.8);
            sleep(1000);
            drive(-10*45,-.5); //10 inches back
            telemetry.update();
            sleep(500);
            driveC(340,0.35);
            drive(50*45+50,.6); //2422 = 50 inches
            Claw.setPosition(0);
            sleep(500);
            lift(1400,0.8);
            sleep(500);
            drive(-30*45,-0.5);
            lift(0,-0.8);
            sleep(1000);
        }else{

            //trajectory for Right tag detection
            telemetry.addLine("Found Right Tag");
            telemetry.update(); //low = 1578 middle = 2531 high = 3489
            Claw.setPosition(0);
            drive(45*4, .5);  //driving forward 5 inches = 228 //
            strafeleft(50*20,0.35);
            sleep(1000);
            drive(-45*7, -.35); //backwards to line up
            drive(45*53,0.43); //little more than 52 inches
            driveC(400,0.35);
            lift(3489, 0.8);
            telemetry.update();
            sleep(2500);
            drive(46*11,.35); //little bit more than 11 inches to the pole
            sleep(400);
            Claw.setPosition(0.2);
            sleep(700);
            drive(-1*45,-.5);
            sleep(200);
            lift(3460/5, -0.8);
            sleep(1000);
            drive(-10*45,-.5); //10 inches back
            telemetry.update();
            sleep(500);
            driveC(340,0.35);
            drive(50*45+50,.6); //2422 = 50 inches
            Claw.setPosition(0);
            sleep(500);
            lift(1400,0.8);
            sleep(500);
            drive(-5*45,-0.5);
            lift(0,-0.8);
            sleep(1000);
        }


    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

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
    private void strafeleft(int Postarget, double speed){ //This should strafe 48 inches, so 50 encoders/inch
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Pos = Postarget;
        frontLeft.setTargetPosition(-Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(-Pos);
        backLeft.setTargetPosition(Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(-speed);
        backRight.setPower(-speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }


    }
}