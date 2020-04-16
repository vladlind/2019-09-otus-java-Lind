package ru.otus.hw15_MS.messagesystem;

import org.springframework.stereotype.Component;


public interface MessageSystem {

  void addClient(MsClient msClient);

  void removeClient(String clientId);

  boolean newMessage(Message msg);

  void dispose() throws InterruptedException;

  void dispose(Runnable callback) throws InterruptedException;

  void start();

  int currentQueueSize();
}

