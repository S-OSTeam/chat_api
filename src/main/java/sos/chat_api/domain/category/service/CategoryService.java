package sos.chat_api.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //카테고리 이름 찾기
    public Category findByCategoryId(long categoryId) {
        return categoryRepository.findById(categoryId).
                orElseThrow(()->new RuntimeException("Category not found"));
    }

    //카테고리 만들기
    public Category uploadCategory(String name){
        if (categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        Category category = Category.builder()
                .name(name)
                .build();

        return categoryRepository.save(category);
    }


}
