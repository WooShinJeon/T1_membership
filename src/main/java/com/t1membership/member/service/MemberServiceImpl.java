package com.t1membership.member.service;

import com.t1membership.member.domain.MemberEntity;
import com.t1membership.member.dto.deleteMember.DeleteMemberReq;
import com.t1membership.member.dto.deleteMember.DeleteMemberRes;
import com.t1membership.member.dto.joinMember.JoinMemberReq;
import com.t1membership.member.dto.joinMember.JoinMemberRes;
import com.t1membership.member.dto.modifyMember.ModifyMemberReq;
import com.t1membership.member.dto.modifyMember.ModifyMemberRes;
import com.t1membership.member.dto.readAllMember.ReadAllMemberRes;
import com.t1membership.member.dto.readOneMember.ReadOneMemberReq;
import com.t1membership.member.dto.readOneMember.ReadOneMemberRes;
import com.t1membership.member.reporitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public JoinMemberRes joinMember(JoinMemberReq joinMemberReq) {
        String memberId = joinMemberReq.getMemberEmail();
        memberRepository.save(new MemberEntity());

        return null;
    }
    @Override
    public ReadAllMemberRes readAllMember() {
        return null;
    }
    @Override
    public ReadOneMemberRes readOneMember(ReadOneMemberReq readOneMemberReq) {
        return null;
    }
    @Override
    public ModifyMemberRes modifyMember(ModifyMemberReq modifyMemberReq) {
        return null;
    }
    @Override
    public DeleteMemberRes deleteMember(DeleteMemberReq deleteMemberReq) {
        return null;
    }

}
