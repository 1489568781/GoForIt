package com.study.goforit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxw on 2017/8/10.
 */
public class ShowPassActivity extends AppCompatActivity {
    private List<Point> pointList = null;
    private boolean isFirst = true;
    String state = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_pass_activity);
        state = getIntent().getStringExtra("key");
        pointList = new ArrayList<Point>();
        final GestureLockView glv = (GestureLockView) findViewById(R.id.gv);
        glv.setResetHaltTime(800);
        glv.setSelectedMinSize(4);
        glv.setLineVisible(true);
        glv.setGestureListener(new GestureListener() {
            @Override
            public boolean getPointList(List<Point> list) {
                if (state.equals("check")){ //检查密码
                    System.out.println("11");
                    try {
                        pointList.clear();
                        System.out.println("00");
                        String saveStr = Utils.readString(getApplicationContext(),"savePass");
                        pointList = StringtoListUtil.String2SceneList(saveStr);
                        System.out.println("55");
                        for (Point point:pointList){
                            System.out.println("xX="+ point.getX()+",yY="+point.getY());
                        }
                        for (Point point:list){
                            System.out.println("listxX="+ point.getX()+",listyY="+point.getY());
                        }
                        if (compare(pointList,list)){
                            System.out.println("22");
                            Utils.saveString(getApplicationContext(),"you_can_pass","yes");
                            Intent intent = new Intent(ShowPassActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            System.out.println("不一样！！");


                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else { //设置密码

                    if (list.size() > 5) {
                        if (isFirst) { //第一次设置
                            pointList = list;
                            isFirst = false;
                        } else {
                            if (pointList == list) {
                                try {
                                    String save = StringtoListUtil.SceneList2String(list);
                                    Utils.saveString(getApplicationContext(), "savePass", save);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for (Point point:list){
                                    System.out.println("X="+ point.getX()+",Y="+point.getY());
                                }
                                Toast.makeText(getApplicationContext(), "设置密码成功~", Toast.LENGTH_SHORT).show();
                                Utils.saveString(getApplicationContext(), "havePass", "yes");//保存已经设置密码的状态
                            } else {
                                Toast.makeText(getApplicationContext(), "两次输入的密码不一样！", Toast.LENGTH_SHORT).show();
                            }

                        }
                        glv.resetNormalState();
                        return true;
                    }
                }
                Toast.makeText(getApplicationContext(),"至少要连接五个点！",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    public boolean compare(List<Point> a, List<Point> b) {
        if(a.size() != b.size())
            return false;
//        Collections.sort(a);
//        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

}
