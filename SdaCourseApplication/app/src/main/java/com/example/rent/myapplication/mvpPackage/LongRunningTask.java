package com.example.rent.myapplication.mvpPackage;

/**
 * Created by RENT on 2017-03-04.
 */

public class LongRunningTask {

    public String execute(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "It worked out";
    }



}
