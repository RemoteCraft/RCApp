package jzl.remotecraft.sensor.core;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorMotion extends SensorBasic {
    private Sensor accelerometer;
    private Sensor magnetic;
    private float[] trueacceleration = new float[4];

    public SensorMotion(Context _context){
        context = _context;
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		accelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        values = new float[3];
	}
	public boolean register(){
		try {
            listener = new Listener(context);
			mSensorManager.registerListener(listener,
                accelerometer, Sensor.TYPE_ACCELEROMETER);
	        mSensorManager.registerListener(listener, magnetic,
	                Sensor.TYPE_MAGNETIC_FIELD);
	        return true;
		} catch (Exception e) {
			return false;
		}
	}

    @Override
	protected void calculate() {
        float[] R = new float[9];
        float[] Rotate = new float[16];
        float[] I = new float[9];
        float[] II = new float[9];
        SensorManager.getRotationMatrix(R, I, accelerometer_values,
                magnetic_field_values);
        SensorManager.getOrientation(R, values);
        values[0] = (float) Math.toDegrees(values[0]);
        values[1] = (float) Math.toDegrees(values[1]);
        values[2] = (float) Math.toDegrees(values[2]);
//        SensorManager.getRotationMatrix(R, I, gravity,
//                 magneticFieldValues);
//
//        float [] A_D = liner_acc.clone();
//        trueacceleration[0] = R[0] * A_D[0] + R[1] * A_D[1] + R[2] * A_D[2];
//        trueacceleration[1] = R[3] * A_D[0] + R[4] * A_D[1] + R[5] * A_D[2];
//        trueacceleration[2] = R[6] * A_D[0] + R[7] * A_D[1] + R[8] * A_D[2];


//        SensorManager.getRotationMatrix(Rotate, II, gravity, magneticFieldValues);
//        float[] relativacc = new float[4];
//        float[] inv = new float[16];
//        relativacc[0]=liner_acc[0];
//        relativacc[1]=liner_acc[1];
//        relativacc[2]=liner_acc[2];
//        relativacc[3]=0;
//        android.opengl.Matrix.invertM(inv, 0, Rotate, 0);
//        android.opengl.Matrix.multiplyMV(trueacceleration, 0, inv, 0, relativacc, 0);
    }



}