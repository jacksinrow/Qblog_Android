package com.qyh.myblog_android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CrashHandler implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler mInstance = new CrashHandler();
    private Context mContext;
    private Map<String, String> mLogInfo = new HashMap<String, String>();

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init(Context paramContext) {
        mContext = paramContext;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @SuppressWarnings("static-access")
    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
        if (!handleException(paramThrowable) && mDefaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(paramThread, paramThrowable);
        } else {
            try {
                // 如果处理了，让程序继续运行1.5秒再退出，保证文件保存并上传到服务器
                paramThread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * handleException:{自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.}
     *
     * @return true:如果处理了该异常信息;否则返回false.
     */
    public boolean handleException(Throwable paramThrowable) {
        if (paramThrowable == null)
            return false;
        new Thread() {
            public void run() {
                Looper.prepare();
//                ToastUtil.showShortToast(mContext, mContext.getString(R.string.crash));
                Looper.loop();
            }
        }.start();
        // 获取设备参数信息
        getDeviceInfo(mContext);
        // 保存日志文件
        saveCrashLogToFile(paramThrowable);
        return true;
    }

    /**
     * getDeviceInfo:{获取设备参数信息}
     */
    public void getDeviceInfo(Context paramContext) {
        try {
            // 获得包管理器
            PackageManager mPackageManager = paramContext.getPackageManager();
            // 得到该应用的信息，即主Activity
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(paramContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (mPackageInfo != null) {
                String versionName = mPackageInfo.versionName == null ? "null" : mPackageInfo.versionName;
                String versionCode = mPackageInfo.versionCode + "";
                Date d = new Date();
                SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd_HH-mm-ss", Locale.CHINA);
                String date = sd.format(d);
                mLogInfo.put("versionName", versionName);
                mLogInfo.put("versionCode", versionCode);
                mLogInfo.put("date", date);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        // 反射机制
        Field[] mFields = Build.class.getDeclaredFields();
        // 迭代Build的字段key-value 此处的信息主要是为了在服务器端手机各种版本手机报错的原因
        for (Field field : mFields) {
            try {
                field.setAccessible(true);
                mLogInfo.put(field.getName(), field.get("").toString());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * saveCrashLogToFile:{将崩溃的Log保存到本地} 可拓展，将Log上传至指定服务器路径
     */
    private void saveCrashLogToFile(Throwable paramThrowable) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : mLogInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            mStringBuffer.append(key + "=" + value + "\r\n");
        }
        Writer mWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mWriter);
        paramThrowable.printStackTrace(mPrintWriter);
        paramThrowable.printStackTrace();
        Throwable mThrowable = paramThrowable.getCause();
        // 迭代栈队列把所有的异常信息写入writer中
        while (mThrowable != null) {
            mThrowable.printStackTrace(mPrintWriter);
            // 换行 每个个异常栈之间换行
            mPrintWriter.append("\r\n");
            mThrowable = mThrowable.getCause();
        }
        // 记得关闭
        mPrintWriter.close();
        String mResult = mWriter.toString();
        mStringBuffer.append(mResult);
        try {
            // 存放到Android/data/包名/根目录下
            File file = mContext.getExternalFilesDir(null);
            FileOutputStream mFileOutputStream = new FileOutputStream(new File(file.getPath() + File.separator + "crash.txt"));
            mFileOutputStream.write(mStringBuffer.toString().getBytes());
            mFileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            try {
//                File mDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + File.separator + "Shaun");
//                if (!mDirectory.exists())
//                    mDirectory.mkdir();
//                FileOutputStream mFileOutputStream = new FileOutputStream(mDirectory + File.separator + "error.txt");
//                mFileOutputStream.write(mStringBuffer.toString().getBytes());
//                mFileOutputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
