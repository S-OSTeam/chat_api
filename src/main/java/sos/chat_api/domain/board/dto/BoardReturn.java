package sos.chat_api.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.category.entity.Category;

import java.util.List;

@Getter
@Setter
public class BoardReturn {
    List<Board> content;
    long totalPages;
    long totalElements;
    int size;
    int number;

    public BoardReturn(List<Board> content, long totalPages, long totalElements, int size, int number) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
    }
}
