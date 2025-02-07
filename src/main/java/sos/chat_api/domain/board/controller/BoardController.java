package sos.chat_api.domain.board.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.board.dto.BoardInput;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @MutationMapping
    public Board createBoard(@Argument BoardInput boardInput) {
        return boardService.uploadBoard(boardInput);
    }

    @QueryMapping
    public Board getBoard(@Argument Long boardId) {
        return boardService.getBoardById(boardId);
    }
}
