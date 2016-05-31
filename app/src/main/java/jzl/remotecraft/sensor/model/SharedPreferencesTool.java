package jzl.remotecraft.sensor.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesTool {
	private static Editor editor;

	public static void putBoolean(Context context, String name, String key,
			boolean value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public static boolean getBoolean(Context context, String name, String key) {
		return getBoolean(context, name, key, false);
	}

	public static boolean getBoolean(Context context, String name, String key,
			boolean defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, defaultValue);
	}

	public static void putInt(Context context, String name, String key,
			int value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	public static int getInt(Context context, String name, String key) {
		return getInt(context, name, key, 1);
	}

	public static int getInt(Context context, String name, String key,
			int defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(key, defaultValue);
	}

	public static float getFloat(Context context, String name, String key,
			float defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		return sharedPreferences.getFloat(key, defaultValue);
	}

	public static void putFloat(Context context, String name, String key,
			float value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putFloat(key, value);
		editor.apply();
	}

	public static void putString(Context context, String name, String key,
			String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static void clearKey(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.remove(key);
		editor.apply();
	}

	public static String getString(Context context, String name, String key,
			String defValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, defValue);
	}

	public static void putLong(Context context, String name, String key,
			long value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	public static long getLong(Context context, String name, String key,
			long defValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(key, defValue);
	}

	public static void removeKey(Context context, String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.remove(key);
		editor.apply();
	}
}
