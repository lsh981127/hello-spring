package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean   // 스프링 빈으로 등록하라는 뜻
    public MemberService memberService() {
        return new MemberService(memberRepository());
        // MemberService가 MemberRepository를 엮어줘야 하기 때문에 위처럼 안에 넣어주는거야
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
