package sos.chat_api.domain.community.dto;

import lombok.Getter;
import lombok.Setter;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.community.entity.Community;

import java.util.List;

public class CommunityDTO {
    List<Community> content;
    long totalPages;
    long totalElements;
    int size;
    int number;

    public CommunityDTO(List<Community> content, long totalPages, long totalElements, int size, int number) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
    }
}


