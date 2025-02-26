package sos.chat_api.domain.board.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.board.dto.BoardInput;
import sos.chat_api.domain.board.dto.BoardUpdateDTO;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.repository.BoardRepository;
import sos.chat_api.domain.board.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    //글작성
    @MutationMapping
    public Board createBoard(@Argument BoardInput boardInput) {
        return boardService.uploadBoard(boardInput);
    }
    //단일 게시글 가져오기
    @QueryMapping
    public Board getBoard(@Argument Long boardId) {
        return boardService.getBoardById(boardId);
    }

    //삭제
    @MutationMapping
    public Boolean deleteBoard(@Argument Long boardId) {
        return boardService.deleteBoard(boardId);
    }
    //수정
    @MutationMapping
    public Board updateBoard(@Argument BoardUpdateDTO boardInput) {
        return boardService.updateBoard(boardInput);
    }

    //전체 게시글 가져오기
    @QueryMapping
    public Page<Board> getBoards(@Argument Long communityId,@Argument Long categoryId,@Argument Integer page, @Argument Integer size) {
        return boardService.getBoards(communityId,categoryId,page,size);
    }


}
