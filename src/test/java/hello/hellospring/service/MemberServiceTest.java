package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // 굳이 다른 객체의 인스턴스? 리포지토리를 만들었는데 이게 좋지 않을 수 있음
    // MemberService 내부에도 memorymemberpepository가 있거든
    //MemberRepository에서 만든 new MemoryMemberRepository() 와 TestCase 내부에서 만든 new MemoryMemberRepository()는 다른거야

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // MemberService 클래스에서 선언한 것처럼 넣어줌, 즉, 의존성 주입!! Dependency Injection
    }

    @AfterEach  // 어떤 메소드가 끝날때마다 이 메소드가 동작하도록 해주는 애노테이션
    public void afterEach() {
        memberRepository.clearStore();
        // 테스트가 끝날때마다 Repository에 다 저장된 걸 지움
        // 그럼 테스트하는 순서는 상관없어짐
        // 테스트하는 순서에 따라서 갑자기 에러가 발생할 수 도 있었음

    }

    @Test
    void 회원가입() {   // Test에는 한국어 적어도 괜찮
        // given(뭐를 줬을떄)
        Member member = new Member();
        member.setName("spring");

        // when(언제)
        Long saveId = memberService.join(member);

        // then(어떤 결과가 나와야함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();  // member2는 member1이랑 Name이 중복되니까 join할때 에러가 나와야해
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 예외 터져야함, IllegalStateException 예외가 터져야해

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);    // 예외가 나와야함. But, 잘 작동하는 것처럼 보임
            fail();
        }  catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            // MemberService의 validateDuplicateMember 메소드가 던진 예외랑 일치해야 작동
            // 불일치시 에러 발생

        }*/

        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}