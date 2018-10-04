package com.hypeofpipe.sprinters.hypeengine.utils;

/**
 * Created by Volodymyr on 10/29/2017.
 */

public class Observable extends java.util.Observable {

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

}
