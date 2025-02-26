package sos.chat_api.domain.community.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.category.service.CategoryService;
import sos.chat_api.domain.community.dto.CommunityInput;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;

@Service
@RequiredArgsConstructor
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
    //커뮤니티 삭제
    public Boolean deleteCommunity(long communityId){
        if(!communityRepository.existsById(communityId)){
//            throw new IllegalArgumentException("존재하지 않는 커뮤니티입니다.");
            return false;
        }
        categoryRepository.deleteById(communityId);
        return true;
    }

    //커뮤니티 업데이트
    public Community updateCommunity(Long communityId, String name){
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new RuntimeException("존재하지 않는 커뮤니티입니다."));
        community.setName(name);
        return communityRepository.save(community);
    }

    //커뮤니티 pageable로 제공
    public Page<Community> getAllCommunities(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return communityRepository.findAll(pageRequest);
    }


}
