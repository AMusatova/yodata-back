package ru.lanit.bpm.yodata.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table (name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_subscriptions_id")
    @SequenceGenerator(name = "sq_subscriptions_id", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Subscription(User user, Page page) {
        this.user = user;
        this.page = page;
    }
}
