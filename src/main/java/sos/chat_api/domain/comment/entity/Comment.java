package sos.chat_api.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="comnunity_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String comment_detail;

    private String comment_url;

    private LocalDateTime deleted_at;

    private Boolean deleted_ok;

    private Long recommend;

    private Long recommend_bad;

}
