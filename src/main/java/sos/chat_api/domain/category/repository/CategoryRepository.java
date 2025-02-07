package sos.chat_api.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sos.chat_api.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name); // 중복 방지용
}
