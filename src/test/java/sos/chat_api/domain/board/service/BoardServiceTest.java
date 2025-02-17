package sos.chat_api.domain.board.service;




import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import sos.chat_api.domain.board.dto.BoardInput;
import sos.chat_api.domain.board.entity.Board;
import sos.chat_api.domain.board.repository.BoardRepository;
import sos.chat_api.domain.category.entity.Category;
import sos.chat_api.domain.category.repository.CategoryRepository;
import sos.chat_api.domain.community.entity.Community;
import sos.chat_api.domain.community.repository.CommunityRepository;
import sos.chat_api.domain.s3code.AwsFileService;
import sos.chat_api.domain.user.entity.User;
import sos.chat_api.domain.user.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//@ExtendWith(MockitoExtension.class) // Mockito를 JUnit5와 함께 사용
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService; // 실제 테스트할 객체

    @Mock
    private CommunityRepository communityRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private AwsFileService awsFileService;

    private BoardInput boardInput;
    private Community community;
    private Category category;
    private User user;

    @BeforeEach
    void setUp() {
     MockitoAnnotations.openMocks(this); // Mock 객체 초기화

        boardInput = new BoardInput();
        boardInput.setCommunityId(1L);
        boardInput.setCategoryId(1L);
        boardInput.setUserId(1L);
        boardInput.setBoardTitle("테스트 제목");
        boardInput.setBoardDetail("테스트 내용");

        category = new Category(1L, "테스트 카테고리");
        community = new Community(1L,category,"테스트");
        user = new User(1L);
    }

    @Test
    void uploadBoard_정상동작() {
        // given (Mock 설정)
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when
        Board result = boardService.uploadBoard(boardInput);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getBoard_title()).isEqualTo("테스트 제목");
        assertThat(result.getBoard_detail()).isEqualTo("테스트 내용");

        // repository의 save()가 한번 호출되었는지 검증
        verify(boardRepository, times(1)).save(Mockito.any(Board.class));
    }
}
//class BoardServiceTest {
//    private BoardService boardService;
//    private CommunityRepository communityRepository;
//    private CategoryRepository categoryRepository;
//    private UserRepository userRepository;
//    private BoardRepository boardRepository;
//    private AwsFileService awsFileService;
//
//    @BeforeEach
//    void setUp() {
//        // 실제 DB 연동 객체 초기화 (Spring 없이 테스트하기 어려움)
//        communityRepository = new CommunityRepository();
//        categoryRepository = new CategoryRepository();
//        userRepository = new UserRepository();
//        boardRepository = new BoardRepository();
//        awsFileService = new AwsFileService();
//
//        boardService = new BoardService(communityRepository, categoryRepository, userRepository, boardRepository, awsFileService);
//    }
//
//    @Test
//    void uploadBoard() {
//        //given
//        BoardInput boardInput = new BoardInput();
//        boardInput.setCategoryId(1L);
//        boardInput.setCommunityId(1L);
//        boardInput.setUserId(1L);
//        boardInput.setBoardDetail("테스트용");
//        boardInput.setBoardTitle("테스트");
//        //when
//        Board board = boardService.uploadBoard(boardInput);
////        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
////            boardService.uploadBoard(boardInput);
////        });
//        //then
////        Assertions.assertThat(thrown.getMessage()).isEqualTo("이미 존재하는 게시글입니다.");
//        Assertions.assertThat(board).isNotNull();
//    }
//
//    @Test
//    void getBoard() {
//
//    }
//}