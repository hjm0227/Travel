package com.example.travel.Controller;

import com.example.travel.Dto.MemberDTO;
import com.example.travel.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("travel/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/travel/save")
    public String save(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        boolean saveResult = memberService.save(memberDTO);
        if (saveResult) {
            return "redirect:/travel/login?SaveSuccess=true";  // 회원가입 성공 시 로그인 페이지로 이동
        } else {
            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다.");  // 회원가입 실패 시 메시지 전달
            return "save";  // 회원가입 페이지로 다시 이동
        }
    }

    @GetMapping("/travel/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/travel/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("isLoggedIn", true);
            return "redirect:/travel/home?loginSuccess=true";  // 로그인 성공 시 홈 페이지로 이동
        } else {
            return "redirect:/travel/login?loginFailed=true";  // 로그인 실패 시 다시 로그인 페이지로
        }
    }
    @GetMapping("/travel/home")
    public String home() {
        return "home";  // "home"은 src/main/resources/templates 폴더에 위치해야 합니다.
    }


    @GetMapping("/travel/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";  // 로그아웃 시 홈 페이지로
    }
}
