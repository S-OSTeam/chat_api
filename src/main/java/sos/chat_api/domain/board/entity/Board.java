package sos.chat_api.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.community.entity.Community;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String board_title;

    private String board_detail;

    private LocalDateTime deleted_at;

    private Boolean deleted_ok;

    private Long recommend;

    private Long recommend_bad;

}
