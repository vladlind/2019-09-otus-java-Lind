package ru.otus;

import ru.otus.appcontainer.AppComponentsContainerImpl;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.config.AppConfig;
import ru.otus.configForScanning.ConsoleConfig;
import ru.otus.configForScanning.EquationConfig;
import ru.otus.configForScanning.PlayerGameConfig;
import ru.otus.services.GameProcessor;

import java.lang.reflect.InvocationTargetException;

/*
В классе AppComponentsContainerImpl реализовать обработку, полученной в конструкторе конфигурации,
основываясь на разметке аннотациями из пакета appcontainer. Так же необходимо реализовать методы getAppComponent.
В итоге должно получиться работающее приложение. Менять можно только класс AppComponentsContainerImpl.

PS Приложение представляет из себя тренажер таблицы умножения)
*/

public class App {

  public static void main(String[] args) throws Exception {
    appComponentStartByClass();
//    appComponentStartByName();
//    appComponentStartMultiConfigClasses();
//    appComponentStartPackageScanning();

  }

  private static void appComponentStartByClass() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);
    GameProcessor gameProcessor = container.getAppComponent(GameProcessor.class);
    gameProcessor.startGame();
  }

  private static void appComponentStartByName() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);
    GameProcessor gameProcessor = container.getAppComponent("gameProcessor");
    gameProcessor.startGame();
  }

  private static void appComponentStartMultiConfigClasses() {
    AppComponentsContainer container = new AppComponentsContainerImpl(EquationConfig.class, PlayerGameConfig.class, ConsoleConfig.class);
    GameProcessor gameProcessor = container.getAppComponent("gameProcessor");
    gameProcessor.startGame();
  }

  private static void appComponentStartPackageScanning() {
    AppComponentsContainer container = new AppComponentsContainerImpl("ru.otus.configForScanning");
    GameProcessor gameProcessor = container.getAppComponent("gameProcessor");
    gameProcessor.startGame();
  }
}
