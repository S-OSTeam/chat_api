package sos.chat_api.domain.community.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.category.service.CategoryService;
import sos.chat_api.domain.community.dto.CommunityInput;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;

@Service
@AllArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CategoryRepository categoryRepository;
    //커뮤니티 이름 찾기
    public Community findByCommunityId(long communityId) {
        return communityRepository.findById(communityId).
                orElseThrow(()->new RuntimeException("Community not found"));
    }

    //커뮤니티 만들기
    public Community uploadCommunity(CommunityInput communityInput){
        if (communityRepository.existsByName(communityInput.getName())) {
            throw new IllegalArgumentException("이미 존재하는 커뮤니티입니다.");
        }

        Category category = categoryRepository.findById(communityInput.getCategory_id())
                .orElseThrow(()->new RuntimeException("Category not found"));

        Community community = Community.builder()
                .category(category)
                .name(communityInput.getName())
                .build();

        return communityRepository.save(community);
    }

}
