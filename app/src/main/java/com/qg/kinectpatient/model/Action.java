package com.qg.kinectpatient.model;

/**
 * Created by HWF on 2018/5/13..
 */
public class Action {
    private int id;
    private String name;
    private String fileName;//动作对应的文件名字

    public Action(){};

    public Action(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
