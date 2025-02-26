package sos.chat_api.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.category.CategoryDTO;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.service.CategoryService;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    //에러 캐치하는 코드 작성해야 한다.
    //카테고리 한 개 불러오기
    @QueryMapping
    public Category getCategory(@Argument long categoryId) {
        return categoryService.findByCategoryId(categoryId);
    }
    //카테고리 생성
    @MutationMapping
    public Category createCategory(@Argument String name) {
        return categoryService.uploadCategory(name);
    }

    //카테고리 삭제
    @MutationMapping
    public Boolean deleteCategory(@Argument long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
    //카테고리 수정
    @MutationMapping
    public Category updateCategory(@Argument long categoryId, @Argument String name) {
        return categoryService.updateCategory(categoryId, name);
    }
    //카테고리 잘라서 가져오기
    @QueryMapping
    public CategoryDTO getCategories(@Argument int page, @Argument int size) {
        Page<Category> categoryPage = categoryService.getAllCategories(page,size);
        return new CategoryDTO(
                categoryPage.getContent(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categoryPage.getSize(),
                categoryPage.getNumber()
        );
    }
}
