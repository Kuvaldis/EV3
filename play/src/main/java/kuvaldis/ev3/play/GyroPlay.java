package kuvaldis.ev3.play;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.util.Arrays;

public class GyroPlay {
    public void run() {
        final EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);
        final SampleProvider angleAndRateMode = gyroSensor.getAngleAndRateMode();
        final float[] rateSample = new float[angleAndRateMode.sampleSize()];
        while (!Button.ESCAPE.isDown()) {
            angleAndRateMode.fetchSample(rateSample, 0);
            System.err.println(Arrays.toString(rateSample));
            Delay.msDelay(50);
        }
        gyroSensor.close();
    }
}
