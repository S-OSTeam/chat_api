package sos.chat_api.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sos.chat_api.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
