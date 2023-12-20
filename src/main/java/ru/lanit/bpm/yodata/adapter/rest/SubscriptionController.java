package ru.lanit.bpm.yodata.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lanit.bpm.yodata.adapter.rest.dto.SubscriptionControllerDTO;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/yodata/admin/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionControllerDTO subscriptionControllerDTO){
        try {
           subscriptionService.addSubscription(subscriptionControllerDTO.getUserLogin(), subscriptionControllerDTO.getPageId());
            return ResponseEntity.ok("Created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @Transactional
    public ResponseEntity<String> deleteSubscription(@RequestBody SubscriptionControllerDTO subscriptionControllerDTO){
        try {
            subscriptionService.deleteSubscription(subscriptionControllerDTO.getUserLogin(), subscriptionControllerDTO.getPageId());
            return  ResponseEntity.ok("Deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
