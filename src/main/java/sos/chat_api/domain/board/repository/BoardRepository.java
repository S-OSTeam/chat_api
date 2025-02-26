package sos.chat_api.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.community.entity.Community;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board>  findByCommunityAndCategory(Community community, Category category, Pageable pageable);
}
