package sos.chat_api.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sos.chat_api.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
