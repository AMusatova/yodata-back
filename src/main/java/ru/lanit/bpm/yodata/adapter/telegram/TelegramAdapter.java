package ru.lanit.bpm.yodata.adapter.telegram;

/**
 * todo Document type TelegramAdapter
 */
public interface TelegramAdapter {
    void notifyUser(Long telegramId, String message);
}
