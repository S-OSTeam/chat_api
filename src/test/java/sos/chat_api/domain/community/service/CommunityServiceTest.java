package sos.chat_api.domain.community.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


class CommunityServiceTest {

    @InjectMocks
    private CommunityService communityService;

    @Mock
    private CommunityRepository communityRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private String name;
    private Community community;
    private Category category;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        name = "testName";
        category = new Category();
        category.setName("testCategory");
        category.setCategory_id(1L);

        community= new Community();
        community.setName(name);
        community.setCommunityId(1L);
    }

    @Test
    void findByCommunityId() {
        //given when
        when(communityRepository.findById(1L)).thenReturn(Optional.of(community));

        //then
        assertThat(community).isNotNull();
        assertThat(community.getName()).isEqualTo(name);
    }

    @Test
    void uploadCommunity() {

    }

    @Test
    void deleteCommunity() {
    }

    @Test
    void updateCommunity() {
    }
}