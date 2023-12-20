package ru.lanit.bpm.yodata.adapter.telegram.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserService;
import ru.lanit.bpm.yodata.domain.User;

import java.util.Optional;

@Component("updatePasswordTelegramCommandHandler")
@RequiredArgsConstructor
public class UpdatePasswordCommandHandler implements CommandHandler {
    private final UserService userService;
    @Override
    public void handleMessage(Update update, Optional<User> user, SendMessage response) {
        try {
            userService.changePassword(user.get().getLogin(), update.getMessage().getText());
            response.setText("Пароль установлен");
        } catch (NotFoundException e) {
            response.setText("Произошла ошибка. Попробуйте позже.");
        }
    }
}
