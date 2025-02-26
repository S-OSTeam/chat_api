package sos.chat_api.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoardUpdateDTO {
    private Long boardId;
    private Long userId;
    private String boardTitle;
    private String boardDetail;
    private MultipartFile boardImage;

}
