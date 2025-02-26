package sos.chat_api.domain.comment.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CommentInput {
    private Long boardId;
    private Long userId;
    private String commentDetail;
    private MultipartFile  commentImage;
}
