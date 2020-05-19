package ru.otus.hw15_MS.controllers.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.hw15_MS.app.common.Serializers;
import ru.otus.hw15_MS.controllers.UserController;
import ru.otus.hw15_MS.domain.User;
import ru.otus.hw15_MS.messagesystem.Message;
import ru.otus.hw15_MS.messagesystem.MessageType;
import ru.otus.hw15_MS.messagesystem.RequestHandler;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Component
public class GetUserDataResponseHandler implements RequestHandler {
  private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

  private final UserController userController;

  public GetUserDataResponseHandler(UserController userController) {
    this.userController = userController;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    logger.info("new message:{}", msg);
    try {
      switch (msg.getType()) {
        case "AllUsers":
          ArrayList allusers = Serializers.deserialize(msg.getPayload(), ArrayList.class);
          UUID sourceMessageId1 = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
          userController.takeConsumer(sourceMessageId1, ArrayList.class).ifPresent(consumer -> consumer.accept(allusers));
        case "SaveUser":
          long id = Serializers.deserialize(msg.getPayload(), long.class);
          UUID sourceMessageId2 = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
          userController.takeConsumer(sourceMessageId2, long.class).ifPresent(consumer -> consumer.accept(id));
        case "Authenticate":
          boolean authStatus = Serializers.deserialize(msg.getPayload(), boolean.class);
          UUID sourceMessageId3 = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
          userController.takeConsumer(sourceMessageId3, boolean.class).ifPresent(consumer -> consumer.accept(authStatus));
      }
      return Optional.ofNullable(msg);
    } catch (Exception ex) {
      logger.error("msg:" + msg, ex);
    }
    return Optional.empty();
  }
}
