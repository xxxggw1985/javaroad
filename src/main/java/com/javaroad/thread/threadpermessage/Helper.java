package com.javaroad.thread.threadpermessage;

public class Helper {
    public void handler(int count, char c) {
        System.out.println("handle ("+ count+"," + c +") begin");
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println("  ");
        System.out.println("handle ("+ count+"," + c +") end");
    }
    private void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        Helper[] test = new Helper[5];
        System.out.println(test.length);
    }
}
