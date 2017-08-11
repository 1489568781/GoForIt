package com.study.goforit;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * String 和 List之间的转换工具类
 * Created by wxw on 2016/11/10.
 */
public class StringtoListUtil {

    /**
     * 把List经过Base64转换成 String字符串
     * */
    public static String SceneList2String(List SceneList)
            throws IOException {
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(SceneList);
        // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
        String SceneListString = new String(Base64.encode(
                byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭objectOutputStream
        objectOutputStream.close();
        return SceneListString;

    }


/**
 * 把String字符串经过Base64转换成 List
 * */
    public static List String2SceneList(String SceneListString)
            throws IOException, ClassNotFoundException {
        if (SceneListString != null||SceneListString.equals("")) {
            byte[] mobileBytes = Base64.decode(SceneListString.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    mobileBytes);
            if (byteArrayInputStream.available()>0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        byteArrayInputStream);
                List SceneList = (List) objectInputStream
                        .readObject();
                objectInputStream.close();
                return SceneList;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }


}
