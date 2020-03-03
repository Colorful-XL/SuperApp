package com.example.supersdk.player.videoBean;

import com.example.supersdk.monitor.Monitor;
import com.example.supersdk.monitor.emevent.EMEvent;

import java.util.ArrayList;

/**
 * * @function: video‘s json value节点， 节点名字记得修改一下
 */
public class AdValue {

    public String resourceID;
    public String adid;
    public String resource;
    public String thumb;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;
    public String type;
}
