package sos.chat_api.domain.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sos.chat_api.domain.community.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
