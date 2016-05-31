package jzl.remotecraft.sensor.core;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorBasic {
	protected SensorManager mSensorManager = null;
	protected Context context = null;
	protected Activity activity = null;
	protected float[] values = null;
	protected float[] accelerometer_values = new float[3];
	protected float[] magnetic_field_values = new float[3];
	protected float[] liner_acc_values = new float[3];
	protected float[] gravity_values = new float[3];

	protected Listener listener = null;

	public float[] getValue(){
		return values;
	}

	protected void calculate(){

	}

	public boolean unRegister(){
		try {
			if (mSensorManager != null && listener != null) {
				mSensorManager.unregisterListener(listener);
				mSensorManager = null;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected class Listener implements SensorEventListener {
		private Context mContext = null;
		public Listener(Context context){
			mContext = context;
		}
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelerometer_values = event.values;
			}
			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				magnetic_field_values = event.values;
			}

			if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
				gravity_values = event.values;
			}

			if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
				liner_acc_values = event.values;
			}
			calculate();
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

	}
}