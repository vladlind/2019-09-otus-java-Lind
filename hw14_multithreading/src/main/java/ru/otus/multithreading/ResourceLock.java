package ru.otus.multithreading;

class ResourceLock {

  int getFlag() {
    return flag;
  }

  void setFlag(int flag) {
    this.flag = flag;
  }

  private volatile int flag = 1;

}
