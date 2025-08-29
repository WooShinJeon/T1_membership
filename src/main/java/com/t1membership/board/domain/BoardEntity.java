package com.t1membership.board.domain;

import com.t1membership.board.constant.BoardType;
<<<<<<< HEAD
=======
import com.t1membership.coredomain.BaseEntity;
import com.t1membership.member.domain.MemberEntity;
>>>>>>> board
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
<<<<<<< HEAD
@Setter
=======
>>>>>>> board
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t1_board")
<<<<<<< HEAD
public class BoardEntity {
=======
public class BoardEntity extends BaseEntity {
>>>>>>> board

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no", nullable = false)
    private Long bno;

<<<<<<< HEAD
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private MemberEntity member; // member

>>>>>>> board
    @Column(name = "board_writer", nullable = false)
    private String bWriter; // member

    @Column(name = "board_title", nullable = false)
    private String bTitle;

    @Lob
    @Column(name = "board_content", nullable = false, length = 2000)
    private String bContent;

<<<<<<< HEAD
    @Column(name = "board_viewCount", nullable = false)
    private int bViewCount = 0;

=======
>>>>>>> board
    @Column(name = "board_likeCount", nullable = false)
    private int bLikeCount = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

<<<<<<< HEAD

=======
    @Column(nullable = false)
    @Builder.Default
    private boolean notice = false;

    @Column(nullable = false)
    private boolean isSecret = false;
>>>>>>> board
}
