package com.t1membership.board.dto.createComment;

<<<<<<< HEAD
public class CreateCommentRes {
=======
import com.t1membership.board.domain.CommentEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRes {

    private Long cno;
    private String cWriter;
    private String cContent;
    private int cLikeCount;

    public static CreateCommentRes from(CommentEntity commentEntity) {
        return CreateCommentRes.builder()
                .cno(commentEntity.getCno())
                .cWriter(commentEntity.getMember().getMemberNickName())
                .cContent(commentEntity.getCContent())
                .cLikeCount(commentEntity.getCLikeCount())
                .build();
    }
>>>>>>> board
}
