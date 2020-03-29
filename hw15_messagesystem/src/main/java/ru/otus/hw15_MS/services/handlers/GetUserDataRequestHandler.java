package ru.otus.hw15_MS.services.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.hw15_MS.app.common.Serializers;
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
    long id = Serializers.deserialize(msg.getPayload(), Long.class);
    return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(userService.getAll())));
  }
}
