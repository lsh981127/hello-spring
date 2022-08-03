package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 없으면 Null이 반환될 수 있지 때문에 Optional
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
