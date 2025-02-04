package sos.chat_api.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import sos.chat_api.domain.community.entity.Community;

@Getter
@Setter
public class BoardDTO {

    private Long communityId;
    private Long categoryId;
    private Long userId;

    private String boardTitle;

    private String boardDetail;

    private MultipartFile boardImage;

    public BoardDTO(BoardInput input){
        this.communityId = input.getCommunityId();
        this.categoryId = input.getCategoryId();
        this.userId = input.getUserId();
        this.boardTitle = input.getBoardTitle();
        this.boardDetail = input.getBoardDetail();
        this.boardImage = input.getBoardImage();
    }

}
