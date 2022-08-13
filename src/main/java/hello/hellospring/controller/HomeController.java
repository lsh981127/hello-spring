package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")    // 도메인 localhost:8080 딱 치면 나오는 제일 처음 페이지
    public String home() {
        return "home";      // home 템플릿 호출
    }
}
