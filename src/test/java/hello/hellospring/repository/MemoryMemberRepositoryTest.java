package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test 가 끝날때마다 저장된 객체를 깔끔하게 지워주는 메소드를 만들어줘야함
    @AfterEach  // 어떤 메소드가 끝날때마다 이 메소드가 동작하도록 해주는 애노테이션
    public void afterEach() {
        repository.clearStore();
        // 테스트가 끝날때마다 Repository에 다 저장된 걸 지움
        // 그럼 테스트하는 순서는 상관없어짐
        // 테스트하는 순서에 따라서 갑자기 에러가 발생할 수 도 있었음

    }


    @Test   // Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();  // retunr 타입이 optional이라서 .get()으로 출력
        assertThat(member).isEqualTo(result);
        // 같은 값인지 확인해줄 수 있는 거
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //cltr + alt + v

        assertThat(result.size()).isEqualTo(2); // Total = 2, else, false
    }


}
