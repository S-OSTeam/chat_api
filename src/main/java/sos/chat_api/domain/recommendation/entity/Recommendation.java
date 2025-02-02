package sos.chat_api.domain.recommendation.entity;

import jakarta.persistence.*;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.user.entity.User;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","board_id"}))
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recommendation_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;
}
