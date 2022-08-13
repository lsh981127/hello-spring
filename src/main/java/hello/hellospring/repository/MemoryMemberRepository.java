package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제때문에 Conquorer 데시벨?로 써야함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) { // member를 받을 때 이미 name은 받은 상태로 들어옴
        member.setId(++sequence);   //
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // 값이 없거나 Null이 반활될 수도 있어서 Optional로 감싸서 반환함. 그러면 클라이언트에서 뭐라도 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {   // 람다를 쓴다고 했는데 다시 공부하기
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny(); // 하나라도 찾는 거
        // 루프로 돌림
        // 같은 name을 가진 것을 찾아내고 찾은 아무거나 출력하는 방식
        // 끝까지 찾아봤지만 없으면 Optional에 Null 이 포함되서 나가는 거야
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 value 들이 Member인거야
    }

    public void clearStore() {
        store.clear();
    }
}
