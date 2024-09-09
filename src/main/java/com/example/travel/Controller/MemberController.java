package com.example.travel.Controller;

import com.example.travel.Dto.MemberDTO;
import com.example.travel.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("travel/save")
    public  String saveForm(){
        return "save";
    }
    @GetMapping("/travel/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/travel/save") //여기서 서비스로 넘겨주고, 서비스에서 repository로 넘기고 repository에서 데이터베이스로 넘김
    public String save(@ModelAttribute MemberDTO memberDTO) { //MemberEmail,MemberPassword,MemberName 다 받아와야하는데 dto에서 스프링이 다 setter처리해줘서 일일이 다 안써도 됨
        System.out.println("MemberController.save");
        System.out.println("memberEmail = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @PostMapping("/travel/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("isLoggedIn", true); // 로그인 상태 추가
            return "home";
        } else {
            // 로그인 실패, 실패하면 loginFailed라는 플래그 설정
            return "redirect:/travel/login?loginFailed=true";
        }
    }

    @GetMapping("/travel/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "home"; // 로그인 페이지로 리다이렉트
    }

    @PostMapping("/travel/submit-duration")
    public String submitDuration(@RequestParam String duration, HttpSession session) {
        // 선택한 여행 기간을 세션에 저장하거나, 다른 비즈니스 로직을 수행할 수 있습니다.
        session.setAttribute("selectedDuration", duration);  // 선택된 기간 세션에 저장

        // recommend 페이지로 이동
        return "recommend";  // recommend.html로 이동
    }
}