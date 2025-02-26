package sos.chat_api.domain.board.service;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.board.dto.BoardDTO;
import sos.chat_api.domain.board.dto.BoardInput;
import sos.chat_api.domain.board.dto.BoardUpdateDTO;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.repository.BoardRepository;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;
import sos.chat_api.domain.s3code.AwsFileService;
import sos.chat_api.domain.user.entity.User;
import sos.chat_api.domain.user.repository.UserRepository;
import sos.chat_api.global.exception.BaseResponse;
import sos.chat_api.global.exception.BaseResponseServiceImpl;
import sos.chat_api.global.exception.BaseResponseStatus;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommunityRepository communityRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AwsFileService awsFileService;
//    private final BaseResponseServiceImpl baseResponseService;

    //글 작성
    public Board uploadBoard(BoardInput boardDTO) {

        Community community = communityRepository.findById(boardDTO.getCommunityId())
                .orElseThrow(()->new RuntimeException("Community not found"));

        Category category = categoryRepository.findById(boardDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));

        User userEntity = userRepository.findById(boardDTO.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        Board board;
        if(boardDTO.getBoardImage()!=null) {
                String imageUrl = awsFileService.upload(boardDTO.getBoardImage());
                board = new Board(community,category,userEntity,boardDTO.getBoardTitle(),boardDTO.getBoardDetail());
                board.setImage_url(imageUrl);
                board.setImage_name(boardDTO.getBoardImage().getName());
            }else{
            board = new Board(community,category,userEntity,boardDTO.getBoardTitle(),boardDTO.getBoardDetail());
        }
        boardRepository.save(board);
        return board;
    }

    //글 자세히 보기
    //pageAble로 잘라서 넣기
    public Board getBoardById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(()->new RuntimeException("Board not found"));
    }
    //삭제
    public Boolean deleteBoard(Long boardId){
        if(boardRepository.existsById(boardId)) {
            boardRepository.deleteById(boardId);
            return true;
        }else {
            return false;
        }
    }
    //변경
    public Board updateBoard(BoardUpdateDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getBoardId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 게시글입니다."));
        if(!Objects.equals(board.getUser().getUser_id(), boardDTO.getUserId())) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다.");
        }

        board.setBoard_detail(boardDTO.getBoardDetail());
        board.setBoard_title(boardDTO.getBoardTitle());
        if(boardDTO.getBoardImage()!=null) {
            String imageUrl = awsFileService.upload(boardDTO.getBoardImage());
            board.setImage_url(imageUrl);
            board.setImage_name(boardDTO.getBoardImage().getName());
        }
        return boardRepository.save(board);
    }

    //필터링해서 가져오는 정보들 설정
    //카테고리별, 커뮤니티별 게시글들 가져오기
    public Page<Board> getBoards(Long communityId,Long categoryId,int page,int size){
        //같은 내용의 community와 카테고리가 있을 수 있으니까
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 커뮤니티입니다."));

        PageRequest pageRequest = PageRequest.of(page, size);
        return boardRepository.findByCommunityAndCategory(community,category,pageRequest);
    }

    //필터링 기능 추가해야해
}
