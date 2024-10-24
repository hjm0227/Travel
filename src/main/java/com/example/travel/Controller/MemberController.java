package com.example.travel.Controller;

import com.example.travel.Dto.MemberDTO;
import com.example.travel.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
            session.setAttribute("memberId", loginResult.getId()); // 사용자 ID 저장
            session.setAttribute("memberName", loginResult.getMemberName()); // 사용자 이름 저장
            session.setAttribute("isLoggedIn", true);
            System.out.println("로그인 성공, 사용자 ID: " + loginResult.getId() + ", 이름: " + loginResult.getMemberName()); // 로그 추가
            return "redirect:/travel/home?loginSuccess=true";
        } else {
            return "redirect:/travel/login?loginFailed=true";
        }
    }



    @GetMapping("/travel/home")
    public String home() {
        return "home";
    }

    @GetMapping("/travel/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";  // 로그아웃 시 홈 페이지로
    }

    @GetMapping("/travel/mypage")
    public String mypageForm(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute("memberId"); // 세션에서 사용자 ID 가져오기
        model.addAttribute("memberId", memberId); // 모델에 사용자 ID 추가
        return "mypage";  // mypage.html로 이동
    }
}