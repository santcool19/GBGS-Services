package com.gbgs.edu.application.programm.thread;

public class GenericBasic {
    public static void main(String args[]) {
        GenericBasic obj = new GenericBasic();
        System.out.println("Starting hello");
        new Thread(() -> {
            obj.hello1();
        }).start();

        System.out.println("Starting hello1");
        new Thread(() -> {
            obj.hello();
        }).start();
    }

    private void hello() {
        try {
            synchronized (this) {
                Thread.sleep(5000);
                System.out.println("hello");
            }
        } catch (Exception ex) {

        } finally {
        }
    }

    private void hello1() {
        try {
            synchronized (this) {
                Thread.sleep(2000);
                System.out.println("hello1");
            }
        } catch (Exception ex) {

        } finally {
        }
    }
}
