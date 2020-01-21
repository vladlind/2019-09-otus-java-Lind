package ru.otus.multithreading;


public class OrderedThread extends Thread {

  private final ResourceLock lock;
  private int flag1;
  private int flag2;
  //emulating different execution time for different threads
  private long timeLag;

  private String order;

  OrderedThread(ResourceLock lock, String order, long timeLag) {
    this.lock = lock;
    this.order = order;
    this.timeLag = timeLag;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        threadOrder();
        while (true) {
          for (int i = 1; i < 11; i++) {
            while (lock.getFlag() != flag1) {
              lock.wait();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
            lock.setFlag(flag2);
            lock.notifyAll();
            Thread.sleep(timeLag);
          }
          for (int i = 9; i > 1; i--) {
            while (lock.getFlag() != flag1) {
              lock.wait();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
            lock.setFlag(flag2);
            lock.notifyAll();
            Thread.sleep(timeLag);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Exception e :" + e.getMessage());
    }
  }

  private void threadOrder() {
    if (this.order.equals("first")) {
      this.flag1 = 1;
      this.flag2 = 2;
    } else if (this.order.equals("second")) {
      this.flag1 = 2;
      this.flag2 = 1;
    }
  }
}
