package sos.chat_api.domain.comment.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.repository.BoardRepository;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.comment.entity.Comment;
import sos.chat_api.domain.comment.entity.CommentInput;
import sos.chat_api.domain.comment.repository.CommentRepository;
import sos.chat_api.domain.community.dto.CommunityInput;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.s3code.AwsFileService;
import sos.chat_api.domain.user.entity.User;
import sos.chat_api.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final AwsFileService awsFileService;
    //댓글 이름 찾기
    public Comment findByCommentId(long commentId) {
        return commentRepository.findById(commentId).
                orElseThrow(()->new RuntimeException("Comment not found"));
    }

    //댓글 달기
    public Comment uploadComment(CommentInput commentInput){

        Board board = boardRepository.findById(commentInput.getBoardId())
                .orElseThrow(()->new RuntimeException("Board not found"));

        Category category = board.getCategory(); //얘네들에 대한 예외처리를 해야하나 ? 이미 존재하는 것인데

        Community community = board.getCommunity();

        User user = userRepository.findById(commentInput.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));


        Comment comment;
        if(!commentInput.getCommentImage().isEmpty()) {
            String imageUrl = awsFileService.upload(commentInput.getCommentImage());
            comment = Comment.builder()
                    .board(board)
                    .community(community)
                    .category(category)
                    .user(user)
                    .comment_detail(commentInput.getCommentDetail())
                    .comment_url(imageUrl).build();
        }else{
             comment = Comment.builder()
                    .board(board)
                    .community(community)
                    .category(category)
                    .user(user)
                    .comment_detail(commentInput.getCommentDetail())
                    .build();
        }
        return commentRepository.save(comment);
    }


}
