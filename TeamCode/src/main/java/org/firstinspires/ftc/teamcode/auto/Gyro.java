
package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.checkerframework.checker.units.qual.degrees;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.auto.LeftAuto;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name="Gyro", group="LeftAuto")
public class Gyro extends LinearOpMode {

    BNO055IMU imu;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyLeft;
    DcMotor SlippyRight;
    Servo Claw;
    OpenCvCamera Eye;



    LeftAuto    robot = new LeftAuto();
    private ElapsedTime  runtime = new ElapsedTime();

    private Orientation lastAngles = new Orientation();
    private double currAngle = 0.0;



    @Override
    public void runOpMode(){

        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


        waitForStart();

        turn(90);
    }

    public void resetAngle(){ //reset angle
        lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;
    }

    public double getAngle(){

        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = orientation.firstAngle - lastAngles.firstAngle;
        if (deltaAngle > 180) {
            deltaAngle -= 360;
        } else if (deltaAngle <= -180){
            deltaAngle += 360;
        }

        currAngle += deltaAngle;
        lastAngles = orientation;
        telemetry.addData("gyro", orientation.firstAngle);
        return currAngle;
    }

    public void turn(double degrees){

        resetAngle();
        double error = degrees;
        while (opModeIsActive() && Math.abs(error) > 2){
            double motorPower = (error < 0 ? -0.3: 0.3);
            robot.setMotorPower(-motorPower,motorPower,-motorPower,motorPower);
            error = degrees - getAngle();
            telemetry.addData("error", error);
            telemetry.update();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void turnTo(double degrees){
        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double error = degrees - orientation.firstAngle;

        if(error > 180) {
            error -= 360;
        } else if (error < -180) {
            error += 360;
        }

        turn(error);

        }
    }
