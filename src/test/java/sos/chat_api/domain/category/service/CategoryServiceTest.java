package sos.chat_api.domain.category.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sos.chat_api.domain.category.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void findByCategoryId() {

    }

    @Test
    void uploadCategory() {
        //given
        String categoryName = "리그오브레전드";
        categoryService.uploadCategory(categoryName);

        //when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.uploadCategory(categoryName);
        });

        Assertions.assertThat(thrown.getMessage()).isEqualTo("이미 존재하는 카테고리입니다.");


    }
}