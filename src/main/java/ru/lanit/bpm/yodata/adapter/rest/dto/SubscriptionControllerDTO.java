package ru.lanit.bpm.yodata.adapter.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionControllerDTO {
    private String userLogin;
    private Long pageId;
}
