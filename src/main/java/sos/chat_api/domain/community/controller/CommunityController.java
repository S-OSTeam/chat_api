package sos.chat_api.domain.community.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.service.CategoryService;
import sos.chat_api.domain.community.dto.CommunityDTO;
import sos.chat_api.domain.community.dto.CommunityInput;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.service.CommunityService;

@Controller
@AllArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    //커뮤니티 하나 불러오기
    @QueryMapping
    public Community getCommunity(@Argument long communityId) {
        return communityService.findByCommunityId(communityId);
    }
    //커뮤니티 생성
    @MutationMapping
    public Community createCommunity(@Argument CommunityInput  communityInput) {
        return communityService.uploadCommunity(communityInput);
    }

    //커뮤니티 삭제
    @MutationMapping
    public Boolean deleteCommunity(@Argument long communityId) {
        return communityService.deleteCommunity(communityId);
    }

    //커뮤니티 업데이트
    @MutationMapping
    public Community updateCommunity(@Argument long communityId, @Argument String name) {
        return communityService.updateCommunity(communityId, name);
    }
    //커뮤니티 목록 제공
    @QueryMapping
    public CommunityDTO getCommunities(@Argument int page, @Argument int size) {
        Page<Community> communities = communityService.getAllCommunities(page,size);
        return new CommunityDTO(
                communities.getContent(),
                communities.getTotalPages(),
                communities.getTotalElements(),
                communities.getSize(),
                communities.getNumber()
        );
    }
}
