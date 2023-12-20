package ru.lanit.bpm.yodata.adapter.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserControllerDTO {
    private String login;
    private String password;
}