package ru.lanit.bpm.yodata.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name =  "users")
public class User {
    @Id
    private String login;
    @ToString.Exclude
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "telegram_id")
    private Long telegramId;
}
