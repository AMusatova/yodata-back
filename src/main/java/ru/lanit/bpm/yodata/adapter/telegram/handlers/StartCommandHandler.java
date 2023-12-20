package ru.lanit.bpm.yodata.adapter.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lanit.bpm.yodata.domain.User;

import java.util.Optional;

@Component("startTelegramCommandHandler")
public class StartCommandHandler implements CommandHandler {
    @Override
    public void handleMessage(Update update, Optional<User> user, SendMessage response) {
        if (user.isPresent()){
            response.setText("Вы уже зарегестрированы, вы можете обновить пароль");
        } else {
            response.setText("Добро пожаловать! Введите логин:");
        }
    }
}
