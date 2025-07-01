package com.bling.common.model;

import java.io.Serializable;

public class Game implements Serializable {
    private String version;
    private String name;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
