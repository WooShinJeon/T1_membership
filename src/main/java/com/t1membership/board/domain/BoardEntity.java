package com.t1membership.board.domain;

import com.t1membership.board.constant.BoardType;
import com.t1membership.coredomain.BaseEntity;
import com.t1membership.member.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t1_board")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no", nullable = false)
    private Long bno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private MemberEntity member; // member

    @Column(name = "board_writer", nullable = false)
    private String bWriter; // member

    @Column(name = "board_title", nullable = false)
    private String bTitle;

    @Lob
    @Column(name = "board_content", nullable = false, length = 2000)
    private String bContent;

    @Column(name = "board_likeCount", nullable = false)
    private int bLikeCount = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    @Builder.Default
    private boolean notice = false;

    @Column(nullable = false)
    private boolean isSecret = false;
}
