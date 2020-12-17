package com.idguowx.utils;

public class PoolObj {
    private int id;
    private String name;

    public PoolObj(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public PoolObj(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
