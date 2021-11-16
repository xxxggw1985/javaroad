package com.javaroad.thread.twophasestop;

public class CountupThread extends Thread{
    private long counter = 0;
    private volatile  boolean shutdown = false;
    public  void shutdown(){
        shutdown = true;
        interrupt(); //触发doWork()中的Thread.sleep，让其抛出InterruptedException
        System.out.println("interrupted");
    }
    public boolean isShutdown(){
        return  shutdown;
    }
    @Override
    public final void run(){
        try{
            while (!shutdown){
                doWork();
            }
        }catch (InterruptedException ex){
            System.out.println("CountupThread InterruptedException："+ex.getMessage());
        }finally {
            doShutdown();
        }
    }
    private void doWork() throws InterruptedException{
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }
    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
    public static void main(String[] args) {
        System.out.println("main: BEGIN");
        try {
            CountupThread t = new CountupThread();
            t.start();
            Thread.sleep(10000);
            System.out.println("main: shutdownRequest");
            t.shutdown();
//            Thread.sleep(5000);
            System.out.println("main: join");
            t.join();
        } catch (InterruptedException e) {
            System.out.println("main InterruptedException " + e);

        }
        System.out.println("main: END");
    }
}
