package com.study.goforit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 *
 * 推送相关的工具类
 *
 * */
public class Utils {
  /*
  * 把字符串保存起来
  *
  * */
    public static void saveString(Context context,String key,Object object){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        }else if (object instanceof Integer){
            editor.putInt(key, (Integer) object);
        }else if (object instanceof Boolean){
            editor.putBoolean(key,(Boolean)object);
        }
        editor.commit();

    }
    /*
    * 读取保存的string
    * */
    public static String readString(Context context,String key){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(key, "");
    }
    /*
   * 读取保存的int
   * */
    public static int readInteger(Context context,String key){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getInt(key, 0);
    }
    /*
    * 清除指定的数据
    * */
    public static void clearSaveInfo(Context context,String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    /*
       * 清除全部sp的数据
       * */
    public static void clearAllSaveInfo(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
