package sos.chat_api.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //카테고리 이름 찾기
    public Category findByCategoryId(long categoryId) {
        return categoryRepository.findById(categoryId).
                orElseThrow(()->new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
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

    //카테고리 삭제하기
    public boolean deleteCategory(long categoryId) {
        if(!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다.");
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }


    public Category updateCategory(long categoryId,String name){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다"));
        category.setName(name);
        return categoryRepository.save(category);
    }

    //category를 pagealbe로 보여주게끔 만드는 것도 필요하다
    public Page<Category> getAllCategories(int page, int size){
        PageRequest pagerequest = PageRequest.of(page, size);
        return categoryRepository.findAll(pagerequest);
    }

}
