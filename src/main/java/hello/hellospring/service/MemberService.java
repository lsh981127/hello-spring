package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {   // 직접 new 하는게 아니라 외부에서 넣어주도록 만들어 주기
        this.memberRepository = memberRepository;
    }

    /**
     *
     * 회원가입
     */
    public Long join(Member member) {
        // Prohibit to register duplicate members with same name

        validateDuplicateMember(member);    // check duplicate member
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //but 더 깔끔한 버전
        memberRepository.findByName(member.getName()) // 찾은 결화는 Option의 멤버니까
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
        // 이렇게 로직이 쭉 나온 경우 메소드로 뽑는 게 좋음. 메소드로 만들 사안을 그래그 한 다음 단축기 : ctrl + Shift + Alt + T => Extract Method
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
