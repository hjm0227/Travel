package com.example.travel.Service;

import com.example.travel.Dto.MemberDTO;
import com.example.travel.entity.MemberEntity;
import com.example.travel.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean save(MemberDTO memberDTO) {
        // 이메일 중복 확인
        Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (existingMember.isPresent()) {
            // 이미 존재하는 이메일이면 회원가입 실패
            return false;
        }

        // 1. DTO -> Entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // 2. 저장
        memberRepository.save(memberEntity);
        return true;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toMemberDTO(memberEntity);
            }
        }
        return null;  // 로그인 실패 시 null 반환
    }
}
