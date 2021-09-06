package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class exer {
    public static void main(String[] args) {
        ticket t = new ticket();
        System.out.println("****************");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    t.saleticket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    t.saleticket();
//                }
//            }
//        }, "B").start();

        new Thread(() -> {for (int i = 0; i < 40; i++) {t.saleticket();}},"A").start();

        new Thread(() ->{for (int i = 0; i < 40; i++) {t.saleticket();}},"B").start();
    }
}
class ticket{
    private int number = 30 ;
    public  void saleticket(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+ "号卖的第" + number-- + "，还剩" + number + "张票！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}