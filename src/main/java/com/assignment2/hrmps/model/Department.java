package com.assignment2.hrmps.model;

import java.io.Serializable;

public class Department implements Serializable {
    private static final long serialVersionUID = 1L; //Making sure this is serializable so we can write

    private String id; //Department with two properties
    private String name;

    public Department(){}
    public Department(String id, String name) { //Auto generate constructor by intellij
        this.id = id;
        this.name = name;
    }

    //Generating getters and setters with intellij

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
