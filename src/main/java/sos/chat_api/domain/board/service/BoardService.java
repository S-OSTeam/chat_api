package sos.chat_api.domain.board.service;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.board.dto.BoardDTO;
import sos.chat_api.domain.board.dto.BoardInput;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.repository.BoardRepository;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;
import sos.chat_api.domain.user.entity.User;
import sos.chat_api.domain.user.repository.UserRepository;
import sos.chat_api.global.exception.BaseResponse;
import sos.chat_api.global.exception.BaseResponseServiceImpl;
import sos.chat_api.global.exception.BaseResponseStatus;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommunityRepository communityRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final BaseResponseServiceImpl baseResponseService;

    //글 작성
    @MutationMapping
    public Board uploadBoard(@Argument BoardInput boardDTO) {

        Community community = communityRepository.findById(boardDTO.getCommunityId())
                .orElseThrow(()->new RuntimeException("Community not found"));

        Category category = categoryRepository.findById(boardDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));

        User userEntity = userRepository.findById(boardDTO.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        Board board = new Board(community,category,userEntity,boardDTO.getBoardTitle(),boardDTO.getBoardDetail());

        //이미지 설정하는 부분 필요
        boardRepository.save(board);
        return board;
    }

    //글 자세히 보기
    //pageAble로 잘라서 넣기
    @QueryMapping
    public Board getBoard(@Argument Long boardId){
        return boardRepository.findById(boardId).orElseThrow(()->new RuntimeException("Board not found"));
    }

    //이미지 올리기
    //S3를 사용할건지 어떻게 할지

}
