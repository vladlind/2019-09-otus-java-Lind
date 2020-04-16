package ru.otus.configForScanning;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.services.*;

@AppComponentsContainerConfig(order = 2)
public class PlayerGameConfig {

  @AppComponent(order = 0, name = "playerService")
  public PlayerService playerService(IOService ioService) {
    return new PlayerServiceImpl(ioService);
  }

  @AppComponent(order = 1, name = "gameProcessor")
  public GameProcessor gameProcessor(IOService ioService,
                                     PlayerService playerService,
                                     EquationPreparer equationPreparer) {
    return new GameProcessorImpl(ioService, equationPreparer, playerService);
  }
}
