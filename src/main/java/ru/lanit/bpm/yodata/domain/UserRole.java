package ru.lanit.bpm.yodata.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name =  "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user_roles_id")
    @SequenceGenerator(name = "sq_user_roles_id", allocationSize = 1)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private String role;

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }
}
