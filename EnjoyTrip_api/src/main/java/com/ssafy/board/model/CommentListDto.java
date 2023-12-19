package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommentListDto : 댓글리스트정보", description = "댓글들의 상세 정보를 나타낸다.")
public class CommentListDto {
    private List<CommentDto> comments;
    private int currentPage;
    private int totalPageCount;

    @Override
    public String toString() {
        return "CommentListDto{" +
                "comments=" + comments.size() +
                ", currentPage=" + currentPage +
                ", totalPageCount=" + totalPageCount +
                '}';
    }
}
