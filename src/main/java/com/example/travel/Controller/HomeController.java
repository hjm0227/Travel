package com.example.travel.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //이 클래스가 MVC(Model, View, Controller / 설계 패턴)의 컨트롤러라는 것을 나타냄 / 주로 HTML페이지를 변환
public class HomeController {

    @RequestMapping("/")
    public String home(Model model)
    {
        return "home";
    }

}
