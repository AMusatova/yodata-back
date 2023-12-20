package ru.lanit.bpm.yodata.app.api;

/**
 * todo Document type SubscriptionService
 */
public interface SubscriptionService {
    void addSubscription(String login, Long id) throws NotFoundException, DuplicateEntityException;

    void deleteSubscription(String login, Long id) throws NotFoundException;
}
