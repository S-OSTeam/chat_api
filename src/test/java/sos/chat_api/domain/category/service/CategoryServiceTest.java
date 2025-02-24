package sos.chat_api.domain.category.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@Transactional

class CategoryServiceTest {
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryServiceMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;

    private Long categoryId;
    private String name;
    private Category category;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
        //필요한 변수와 그 값에 대한 설정을 진행하면 된다.

        categoryId = 1L;
        name = "Test Category";
        category = new Category();
        category.setCategory_id(1L);
        category.setName(name);

    }

    @Test
    void findByCategoryId() {
        //given
        when(categoryRepositoryMock.findById(1L)).thenReturn(Optional.of(category));

        //when
        Category category1 = categoryServiceMock.findByCategoryId(1L);
        //then
        assertThat(category1).isNotNull();
        assertThat(category1.getName()).isEqualTo(name);
    }

    @Test
    void 카테고리_생성(){
        //when
        when(categoryRepositoryMock.existsByName(name)).thenReturn(false); // 존재하지 않는다고 가정
        when(categoryRepositoryMock.save(ArgumentMatchers.any(Category.class))).thenAnswer(invocation -> {
            Category savedCategory = invocation.getArgument(0);
            savedCategory.setCategory_id(1L); // 임의의 ID 설정 (DB 저장 시 자동 생성되는 것처럼)
            return savedCategory;
        });

        Category category1 = categoryServiceMock.uploadCategory(name);

        assertThat(category1).isNotNull();
        assertThat(category1.getName()).isEqualTo(name);

        //save가 몇번 호출되었는지 확인
        verify(categoryRepositoryMock, times(1)).save(Mockito.any(Category.class));
    }

//    @Test
//    void uploadCategory() {
//        //given
//        String categoryName = "리그오브레전드";
//        categoryService.uploadCategory(categoryName);
//
//        //when
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            categoryService.uploadCategory(categoryName);
//        });
//
//        Assertions.assertThat(thrown.getMessage()).isEqualTo("이미 존재하는 카테고리입니다.");
//    }


}