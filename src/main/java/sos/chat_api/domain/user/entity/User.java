package sos.chat_api.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    //생성자에는 이렇게 return 값이 붙지 않는다.
    public User(Long user_id) {
        this.user_id = user_id;
    }

}
