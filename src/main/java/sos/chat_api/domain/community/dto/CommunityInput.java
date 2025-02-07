package sos.chat_api.domain.community.dto;

import lombok.Getter;

@Getter
public class CommunityInput {
    private String name;
    private Long category_id;
}
