package sos.chat_api.domain.board.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.user.entity.User;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    private String board_title;

    private String board_detail;

    private LocalDateTime deleted_at;

    private Boolean deleted_ok = false;

    private Long recommend = 0L;

    private Long recommend_bad = 0L;

    private String image_url;

    private String image_name;

    private Boolean softDelete(){
         deleted_ok  = true;
         return true;
    }

    //한 사람이 하나의 게시물에 recommend에 대해서 업하거나 내리거나만 가능하다.
    //중복추천을 방지하기 위한 것을 만든다.

    private Boolean plusRecommend(){
        recommend += 1;
        return true;
    }

    private Boolean minusRecommend(){
        recommend -= 1;
        if(recommend < 0)
            return false;
        return true;
    }
}
