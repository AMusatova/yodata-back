package ru.lanit.bpm.yodata.fw;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration("camundaConfig")
@Getter
public class CamundaConfig {
    @Value("${ru.lanit.bpm.yodata.camunda.pageParsingInterval}")
    private String pageParsingInterval;

    @Value("${ru.lanit.bpm.yodata.camunda.pageParsingMinSendingAmount}")
    private Integer minSendingAmount;
}
