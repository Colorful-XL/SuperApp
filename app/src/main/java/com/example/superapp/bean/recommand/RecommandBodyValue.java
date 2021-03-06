package com.example.superapp.bean.recommand;



import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.superapp.bean.BaseModel;
import com.example.supersdk.monitor.Monitor;
import com.example.supersdk.monitor.emevent.EMEvent;


import java.util.ArrayList;

/**
 * @文件名称：RecommandValue.java
 * @文件描述：搜索实体
 */
public class RecommandBodyValue extends BaseModel implements MultiItemEntity {

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    //视频
    public String thumb;
    public String resource;
    public String resourceID;
    public String adid;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;

    @Override
    public int getItemType() {
        return type;
    }
}
