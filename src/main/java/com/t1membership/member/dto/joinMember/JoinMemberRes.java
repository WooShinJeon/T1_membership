package com.t1membership.member.dto.joinMember;

import com.t1membership.member.domain.MemberEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberRes {
    private String memberEmail;//id
    private String memberPw;
    private String memberName;
    private String memberNickName;
    private String memberBirthY;
    private String memberPhone;
    private String memberAddress;

    public static JoinMemberRes form(MemberEntity memberEntity) {
        return JoinMemberRes.builder()
                .memberEmail(memberEntity.getMemberEmail())
                .memberPw(memberEntity.getMemberPw())
                .memberName(memberEntity.getMemberName())
                .memberNickName(memberEntity.getMemberNickName())
                .memberPhone(memberEntity.getMemberPhone())
                .memberAddress(memberEntity.getMemberAddress())
                .build();
    }
}
