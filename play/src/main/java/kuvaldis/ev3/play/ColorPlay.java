package kuvaldis.ev3.play;

import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.util.Arrays;

public class ColorPlay {
    public void run() {
        final EV3 ev3 = (EV3) BrickFinder.getLocal();
        final Port ps2 = ev3.getPort("S2");
        final Keys keys = ev3.getKeys();
        final EV3ColorSensor colorSensor = new EV3ColorSensor(ps2);
        System.err.println("Available modes");
        for (String mode : colorSensor.getAvailableModes()) {
            System.err.println(mode);
        }
//        keys.waitForAnyPress();
        final SampleProvider redMode = colorSensor.getRedMode();
        float[] sample = new float[redMode.sampleSize()];
        final Key key = ev3.getKey("Escape");
        while (!key.isDown()) {
            redMode.fetchSample(sample, 0);
            System.err.println("Red mode sample: " + Arrays.toString(sample));
            Delay.msDelay(50);
        }
        Delay.msDelay(1000);
        final SampleProvider rgbMode = colorSensor.getRGBMode();
        sample = new float[rgbMode.sampleSize()];
        while (!key.isDown()) {
            rgbMode.fetchSample(sample, 0);
            System.err.println("Rgb mode sample: " + Arrays.toString(sample));
            Delay.msDelay(50);
        }
        Delay.msDelay(1000);

        final SampleProvider df = colorSensor.getAmbientMode();

        colorSensor.close();
    }
}
