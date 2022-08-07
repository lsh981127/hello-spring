package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Controller 애노테이션만 넣어줘도 스프링 컨테이너가 알아서 이 MemberController 객체를 넣어둠
public class MemberController {

    // private final MemberService memberService = new MemberService(); Nope

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 스프링이 관리하게 되면 컨테이너가 관리하기 때문에 컨테이너한테서 받아서 써야함
    // 그래서, MemberService를 쓰고 싶으면 스프링 컨테이너 안에 등록을 시킨 다음에 사용
    // 그러면, 여러개 생성할 이유 없으 그냥 1개만 계속 사용할 수 있음



}
