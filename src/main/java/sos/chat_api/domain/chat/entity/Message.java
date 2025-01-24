package sos.chat_api.domain.chat.entity;

import jakarta.persistence.*;
import sos.chat_api.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long message_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatRoom chatRoom;

    private LocalDateTime deleted_at;

    //이걸로 soft delete를 만듦
    private Boolean deleted_ok = false;

}
