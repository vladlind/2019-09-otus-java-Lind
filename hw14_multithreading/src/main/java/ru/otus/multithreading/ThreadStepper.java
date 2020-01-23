package ru.otus.multithreading;
/*
Based on this article:
http://javabypatel.blogspot.com/2017/06/how-to-run-two-thread-one-after-another-in-java.html
*/

public class ThreadStepper {

  public static void main(String[] args) throws InterruptedException {
    ThreadStepper threadStepper = new ThreadStepper();
    threadStepper.go();
  }

  private void go() throws InterruptedException {
    ResourceLock lock = new ResourceLock();
    Thread thread1 = new OrderedThread(lock, "first", 100);
    Thread thread2 = new OrderedThread(lock, "second", 1000);
    thread1.setName("Thread-1");
    thread2.setName("Thread-2");

    thread1.start();
    thread2.start();

  }
}
