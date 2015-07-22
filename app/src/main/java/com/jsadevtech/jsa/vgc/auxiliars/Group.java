package com.jsadevtech.jsa.vgc.auxiliars;

import java.util.ArrayList;
import java.util.List;

public class Group {

    public String string;
    public List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}