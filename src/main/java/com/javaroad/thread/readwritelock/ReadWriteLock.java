package com.javaroad.thread.readwritelock;

public final  class ReadWriteLock {
    private int readingReaders = 0 ;//正在读取线程的数量
    private int writingWriters = 0 ; //正在写入线程的数量
    public synchronized  void readLock() throws InterruptedException{
        while(writingWriters > 0){
            wait();
        }
        readingReaders ++ ;
    }

    public synchronized void readUnlock() {
        readingReaders --;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while(writingWriters > 0 || readingReaders >0){
            wait();
        }
        notifyAll();
    }

    public synchronized void writeUnlock() {
        writingWriters --;
        notifyAll();
    }
}
