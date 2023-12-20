package ru.lanit.bpm.yodata.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.SubscriptionService;
import ru.lanit.bpm.yodata.app.repo.PageRepository;
import ru.lanit.bpm.yodata.app.repo.SubscriptionRepository;
import ru.lanit.bpm.yodata.app.repo.UserRepository;
import ru.lanit.bpm.yodata.domain.Subscription;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final PageRepository pageRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    @Override
    public void addSubscription(String login, Long id) throws NotFoundException, DuplicateEntityException {
        if (userRepository.findById(login).isPresent()){
            if (pageRepository.findById(id).isPresent()){
                if (subscriptionRepository.findByUserAndPage(userRepository.findById(login).get(), pageRepository.findById(id).get()).isPresent()) {
                    throw new DuplicateEntityException("Subscription already exists");
                } else {
                    subscriptionRepository.save(new Subscription(userRepository.findById(login).get(), pageRepository.findById(id).get()));
                }
            } else {
                throw new NotFoundException("Page with id " + id + " is not available for subscription");
            }
        } else {
            throw new NotFoundException("User with login " + login + " does not exist");
        }
    }

    @Override
    public void deleteSubscription(String login, Long id) throws NotFoundException {
        if (userRepository.findById(login).isPresent()){
            if (pageRepository.findById(id).isPresent()){
                if (subscriptionRepository.findByUserAndPage(userRepository.findById(login).get(), pageRepository.findById(id).get()).isPresent()) {
                    subscriptionRepository.deleteByUserAndPage(userRepository.findById(login).get(), pageRepository.findById(id).get());
                } else {
                    throw new NotFoundException("Subscription does not exist");
                }
            } else {
                throw new NotFoundException("Page with id " + id + " is not available for subscription");
            }
        } else {
            throw new NotFoundException("User with login " + login + " does not exist");
        }

    }
}
