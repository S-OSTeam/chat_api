package sos.chat_api.domain.category.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    List<Category> content;
    long totalPages;
    long totalElements;
    int size;
    int number;

    public CategoryDTO(List<Category> content, long totalPages, long totalElements, int size, int number) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
    }
}
