package sos.chat_api.domain.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long chat_id;

    private String chat_name;

    private long chat_people;

    //이 부분은 키로 연결할지 아니면 그냥 여기에 값을 넣어줄지
    //방장 유저에 대한 정보임
    private Long user_id;

    private LocalDateTime deleted_at;
}
