package ru.lanit.bpm.yodata.adapter.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lanit.bpm.yodata.domain.User;

import java.util.Optional;


public interface CommandHandler {
    void handleMessage(Update update, Optional<User> user, SendMessage response);
}
