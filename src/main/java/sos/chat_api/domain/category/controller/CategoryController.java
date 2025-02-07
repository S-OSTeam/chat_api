package sos.chat_api.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.service.CategoryService;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    //에러 캐치하는 코드 작성해야 한다.

    @QueryMapping
    public Category getCategory(@Argument long categoryId) {
        return categoryService.findByCategoryId(categoryId);
    }

    @MutationMapping
    public Category createCategory(@Argument String name) {
        return categoryService.uploadCategory(name);
    }
}
