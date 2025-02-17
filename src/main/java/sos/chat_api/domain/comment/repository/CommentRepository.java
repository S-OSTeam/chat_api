package sos.chat_api.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sos.chat_api.domain.comment.entity.Comment;
import sos.chat_api.domain.community.entity.Community;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByCommunity(Community community);
}
