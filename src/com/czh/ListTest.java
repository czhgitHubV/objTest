package com.czh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author:chenzhihua
 * @Date: 2020/12/1 16:24
 * @Deacription:
 **/
public class ListTest {
    // ArrayList
//    protected static ArrayList<Object> arrayList = new ArrayList<Object>();//不安全
//    protected static Vector<Object> arrayList = new Vector<Object>();//安全
//    protected static List<Object> arrayList = Collections.synchronizedList(new ArrayList<Object>());//安全
    protected static CopyOnWriteArrayList<Object> arrayList = new CopyOnWriteArrayList<Object>();//安全

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-start");
        ArrayListThread[] threads = new ArrayListThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new ArrayListThread();
            threads[i].start();
        }
        for (int i = 0; i <threads.length ; i++) {
            threads[i].join();
        }
        System.out.println("list的内容");
        for (int i = 0; i < threads.length; i++) {
            System.out.println(arrayList.get(i));
        }

        System.out.println(Thread.currentThread().getName()+"-end");
    }
}

class ArrayListThread extends Thread{
    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        ListTest.arrayList.add(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getName());
    }
}
