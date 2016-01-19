package kuvaldis.ev3.play;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.util.Arrays;

public class UltrasonicPlay {
    public void run() {
        final EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
        final SampleProvider distanceMode = ultrasonicSensor.getDistanceMode();
        final float[] distanceSample = new float[distanceMode.sampleSize()];
        while (!Button.ESCAPE.isDown()) {
            distanceMode.fetchSample(distanceSample, 0);
            System.err.println(Arrays.toString(distanceSample));
            Delay.msDelay(50);
        }
        ultrasonicSensor.close();
    }
}
