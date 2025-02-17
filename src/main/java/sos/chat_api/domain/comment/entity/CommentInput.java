package sos.chat_api.domain.comment.entity;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CommentInput {
    private Long boardId;
    private Long userId;
    private String commentDetail;
    private MultipartFile  commentImage;
}
