package sos.chat_api.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardInput {

    private Long communityId;
    private Long categoryId;
    private Long userId;

    private String boardTitle;

    private String boardDetail;

    private MultipartFile boardImage;

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardDetail() {
        return boardDetail;
    }

    public void setBoardDetail(String boardDetail) {
        this.boardDetail = boardDetail;
    }

    public MultipartFile getBoardImage() {
        return boardImage;
    }

    public void setBoardImage(MultipartFile boardImage) {
        this.boardImage = boardImage;
    }
}
