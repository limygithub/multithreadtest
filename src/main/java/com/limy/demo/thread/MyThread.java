package com.limy.demo.thread;

import java.util.List;

/**
 * @author: Limy
 * @create: 2019/07/25 15:10
 * @description: ${description}
 */
public class MyThread implements Runnable{

    private List<Integer> num;

    public MyThread(List<Integer> num) {
        this.num = num;
    }

    @Override
    public void run() {
        StringBuffer stringBuffer = null;
        for (Integer integer : num) {
            System.out.println(integer);
            stringBuffer.append(integer);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
