package com.javaroad.thread.readwritelock;


public class Data {
    private final char[]  buffer;
    private final ReadWriteLock lock = new ReadWriteLock();
    public Data(int size){
        this.buffer = new char[size];
        for(int i = 0; i < buffer.length; i++){
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException{
        lock.readLock();
        try {
            return doRead();
        }finally {
            lock.readUnlock();
        }
    }

    public void write(char c)throws  InterruptedException{
        lock.writeLock();
        try{
            doWrite(c);
        }finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(1000);
        }
    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for(int i = 0; i < buffer.length; i++){
            newbuf[i] = buffer[i];
        }
        slowly(50);
        return newbuf;
    }

    private void slowly(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
