package ru.otus.configForScanning;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceConsole;

import java.util.Scanner;

@AppComponentsContainerConfig(order = 1)
public class ConsoleConfig {

  @AppComponent(order = 0, name = "ioService")
  public IOService ioService() {
    return new IOServiceConsole(System.out, new Scanner(System.in));
  }
}
