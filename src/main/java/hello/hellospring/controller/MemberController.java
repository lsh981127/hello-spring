package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Controller 애노테이션만 넣어줘도 스프링 컨테이너가 알아서 이 MemberController 객체를 넣어둠
public class MemberController {


    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 스프링이 관리하게 되면 컨테이너가 관리하기 때문에 컨테이너한테서 받아서 써야함
    // 그래서, MemberService를 쓰고 싶으면 스프링 컨테이너 안에 등록을 시킨 다음에 사용
    // 그러면, 여러개 생성할 이유 없으 그냥 1개만 계속 사용할 수 있음

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";    // 회원가입 끝나면 홈화면으로 보내는거
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }

}
