package sos.chat_api.domain.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long file_id;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

    private String file_url;

    //만들어서 설정해서 넣어주면 된다.
    private UUID file_name;
}
