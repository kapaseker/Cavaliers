package com.bfdelivery.cavaliers.background.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharedPreference的记录器
 */

public final class PreferenceRecorder {

	private static final String RECORD = "record_data";
	/**
	 * 登陆的token
	 */
	private static final String ACCESS_TOKEN = "access_token";

	/**
	 * JPUSH应用的注册ID
	 */
	private static final String JPUSH_ID = "jpush_id";

	private static SharedPreferences mRecorder = null;

	public static enum INNERTYPE {
		BOOL, INT, LONG, FLOAT, STRING
	}

	public static final void init(Context context) {
		mRecorder = context.getSharedPreferences(RECORD, Context.MODE_PRIVATE);
	}

	public static void saveAccessToken(String token) {
		saveData(ACCESS_TOKEN, token);
	}

	public static boolean needLogin() {
		return TextUtils.isEmpty(getToken());
	}

	public static String getToken() {
		return (String) readData(ACCESS_TOKEN, "", INNERTYPE.STRING);
	}

	public static void saveJpushId(String id) {
		saveData(JPUSH_ID, id);
	}

	public static String getJpushId() {
		return (String) readData(JPUSH_ID, "", INNERTYPE.STRING);
	}

	private static Object readData(String key, Object defVal, INNERTYPE typeCls) {

		if (typeCls == null) return null;

		if (typeCls.equals(INNERTYPE.BOOL)) {
			return mRecorder.getBoolean(key, (Boolean) defVal);
		} else if (typeCls.equals(INNERTYPE.STRING)) {
			return mRecorder.getString(key, (String) defVal);
		} else if (typeCls.equals(INNERTYPE.INT)) {
			return mRecorder.getInt(key, (Integer) defVal);
		} else if (typeCls.equals(INNERTYPE.FLOAT)) {
			return mRecorder.getFloat(key, (Float) defVal);
		} else if (typeCls.equals(INNERTYPE.LONG)) {
			return mRecorder.getLong(key, (Long) defVal);
		}

		return null;
	}

	private static void saveData(String[] keys, Object[] dataArr) {

		if (dataArr == null || keys == null) return;
		if (dataArr.length != keys.length)
			throw new RuntimeException("Your data's length is not equal key's length");
		SharedPreferences.Editor editor = mRecorder.edit();

		for (int i = 0, len = keys.length; i < len; ++i) {

			String key = keys[i];
			Object data = dataArr[i];

			if (data instanceof Boolean) {
				editor.putBoolean(key, (Boolean) data);
			} else if (data instanceof String) {
				editor.putString(key, (String) data);
			} else if (data instanceof Integer) {
				editor.putInt(key, (Integer) data);
			} else if (data instanceof Float) {
				editor.putFloat(key, (Float) data);
			} else if (data instanceof Long) {
				editor.putLong(key, (Long) data);
			}
		}


		editor.commit();
	}

	private static void saveData(String key, Object data) {
		if (data == null || key == null) return;
		SharedPreferences.Editor editor = mRecorder.edit();

		if (data instanceof Boolean) {
			editor.putBoolean(key, (Boolean) data);
		} else if (data instanceof String) {
			editor.putString(key, (String) data);
		} else if (data instanceof Integer) {
			editor.putInt(key, (Integer) data);
		} else if (data instanceof Float) {
			editor.putFloat(key, (Float) data);
		} else if (data instanceof Long) {
			editor.putLong(key, (Long) data);
		}

		editor.commit();
	}
}
