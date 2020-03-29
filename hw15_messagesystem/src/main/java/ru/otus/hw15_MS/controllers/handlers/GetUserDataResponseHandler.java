package ru.otus.hw15_MS.controllers.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.hw15_MS.app.common.Serializers;
import ru.otus.hw15_MS.controllers.UserController;
import ru.otus.hw15_MS.messagesystem.Message;
import ru.otus.hw15_MS.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetUserDataResponseHandler implements RequestHandler {
  private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

  private final UserController userController;

  public GetUserDataResponseHandler(UserController userController) {
    this.userController= userController;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    logger.info("new message:{}", msg);
    try {
      String userData = Serializers.deserialize(msg.getPayload(), String.class);
      UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
      userController.takeConsumer(sourceMessageId, String.class).ifPresent(consumer -> consumer.accept(userData));

    } catch (Exception ex) {
      logger.error("msg:" + msg, ex);
    }
    return Optional.empty();
  }
}
