package sos.chat_api.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.comment.entity.Comment;
import sos.chat_api.domain.comment.dto.CommentInput;
import sos.chat_api.domain.comment.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 이름
    @QueryMapping
    public Comment getComment(@Argument Long commentId){
        return commentService.findByCommentId(commentId);
    }

    //댓글 달기
    @MutationMapping
    public Comment createComment(@Argument CommentInput commentInput){
        return commentService.uploadComment(commentInput);
    }
}
