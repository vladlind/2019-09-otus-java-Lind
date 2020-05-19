package ru.otus.hw15_MS.services.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.hw15_MS.app.common.Serializers;
import ru.otus.hw15_MS.domain.User;
import ru.otus.hw15_MS.messagesystem.Message;
import ru.otus.hw15_MS.messagesystem.MessageType;
import ru.otus.hw15_MS.messagesystem.RequestHandler;
import ru.otus.hw15_MS.services.UserService;


import java.util.Optional;

@Component
public class GetUserDataRequestHandler implements RequestHandler {
  private final UserService userService;

  public GetUserDataRequestHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    switch (msg.getType()) {
      case "ALL_USERS":
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ALL_USERS.getValue(), Serializers.serialize(userService.getAll())));
      case "FIND_USER":
        User user = Serializers.deserialize(msg.getPayload(), User.class);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.SAVE_USER.getValue(), Serializers.serialize(userService.saveUser(user))));
      case "Authenticate":
        String credentials = Serializers.deserialize(msg.getPayload(), String.class);
        String[] credentialsArray = credentials.split(":");
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.AUTH_USER.getValue(),
                Serializers.serialize(userService.authenticateUser(credentialsArray[0], credentialsArray[1]))));
    }
    return Optional.ofNullable(msg);
  }
}
