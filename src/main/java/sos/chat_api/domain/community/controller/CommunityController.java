package sos.chat_api.domain.community.controller;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.service.CategoryService;
import sos.chat_api.domain.community.dto.CommunityInput;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.service.CommunityService;

@Controller
@AllArgsConstructor
public class CommunityController {

    private final CommunityService communityService;


    @QueryMapping
    public Community getCommunity(@Argument long communityId) {
        return communityService.findByCommunityId(communityId);
    }

    @MutationMapping
    public Community createCategory(@Argument CommunityInput  communityInput) {
        return communityService.uploadCommunity(communityInput);
    }
}
