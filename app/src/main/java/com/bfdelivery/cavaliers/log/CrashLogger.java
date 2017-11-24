package com.bfdelivery.cavaliers.log;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/24.
 */

public class CrashLogger implements Thread.UncaughtExceptionHandler {

	/**
	 * Debug Log tag
	 */
	private static final String LOG_TAG = "AppCrash";

	/**
	 * 系统默认的UncaughtException处理类
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	/**
	 * CrashHandler实例
	 */
	private static final CrashLogger instance = new CrashLogger();
	/**
	 * 程序的Context对象
	 */
	private Context mContext;

	/**
	 * CrashLogger
	 */
	private CrashLogger() {
	}

	/**
	 * CrashLogger ,单例模式
	 */
	public static CrashLogger getInstance() {
		return instance;
	}

	private static final String errorFileName = "cav_crash.log";


	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 *
	 * @param ctx
	 */
	public void init(Context ctx) {
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
	 *
	 * @param ex
	 * @return
	 */
	private boolean handleException(Throwable ex) {

		if (ex == null) {
			return false;
		}

		// 保存错误报告文件
		saveCrashInfo(ex);

		return true;
	}


	/**
	 * 保存错误信息到文件中
	 *
	 * @param ex
	 * @return
	 */
	private String saveCrashInfo(Throwable ex) {
		long timestamp = System.currentTimeMillis();

		StringWriter info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}

		String logInfo = info.toString();
		printWriter.close();

		if (logInfo == null || logInfo.trim().length() == 0) {
			return logInfo;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
		String time = format.format(new Date(timestamp));
		StringBuilder sb = new StringBuilder();
		sb.append(time + " -----timestamp : " + timestamp);
		sb.append("\r\n");
		sb.append(logInfo);
		String content = sb.toString();

		LotToFileDir(content);
		LotToSDCard(content);

		return content;
	}

	/**
	 * 将错误日志写入sd卡，不断添加
	 *
	 * @param content
	 */
	private void LotToSDCard(String content) {

		String fileName = getSDCardPath();
		if (fileName == null)
			return;
		File dir = new File(fileName + "/cav/log");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			fileName = dir.getAbsolutePath() + "/" + errorFileName;
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.write("\r\n-------------------\r\n");
			writer.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 将日志写入应用私有文件夹
	 *
	 * @param content
	 */
	private void LotToFileDir(String content) {
		try {
			String errorFile = mContext.getFilesDir().getAbsolutePath();
			File errorF = new File(errorFile + "/" + errorFileName);
			FileWriter writer = new FileWriter(errorF, true);
			writer.write(content);
			writer.write("\r\n-------------------\r\n");
			writer.close();

		} catch (Exception e) {
		}
	}

	/**
	 * 得到存储卡路径
	 *
	 * @return 未有sd卡返回null
	 */
	private String getSDCardPath() {
		File sdDir = null;
		// 判断sd卡 或可存储空间是否存在
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取sd卡或可存储空间的跟目录
		}

		return sdDir == null ? null : sdDir.toString();
	}
}
