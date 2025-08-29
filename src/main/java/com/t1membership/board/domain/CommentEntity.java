package com.t1membership.board.domain;

<<<<<<< HEAD
public class CommentEntity {
=======
import com.t1membership.member.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t1_comment")
public class CommentEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private MemberEntity member; // member

    @Column(name = "comment_writer", nullable = false)
    private String cWriter;

    @Id
    @Column(name = "comment_no", nullable = false)
    private Long cno;

    @Column(name = "comment_content", nullable = false)
    private String cContent;

    @Column(name = "comment_likeCount", nullable = false)
    private int cLikeCount = 0;
>>>>>>> board
}
