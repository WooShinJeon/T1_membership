package com.t1membership.board.dto.createBoard;

<<<<<<< HEAD
public class CreateBoardRes {
=======
import com.t1membership.board.constant.BoardType;
import com.t1membership.board.domain.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardRes {

    private Long bno;
    private String bTitle;
    private String bWriter;
    private String bContent;
    private int bLikeCount;
    private BoardType boardType;
    private boolean notice;
    private boolean isSecret;

    private LocalDateTime createDate;
    private LocalDateTime latestDate;

    public static CreateBoardRes from(BoardEntity boardEntity) {
        return CreateBoardRes.builder()
                .bno(boardEntity.getBno())
                .bTitle(boardEntity.getBTitle())
                .bWriter(boardEntity.getMember().getMemberNickName())
                .bContent(boardEntity.getBContent())
                .bLikeCount(boardEntity.getBLikeCount())
                .notice(boardEntity.isNotice())
                .isSecret(boardEntity.isSecret())
                .createDate(boardEntity.getCreateDate())
                .latestDate(boardEntity.getLatestDate())
                .build();
    }
>>>>>>> board
}
