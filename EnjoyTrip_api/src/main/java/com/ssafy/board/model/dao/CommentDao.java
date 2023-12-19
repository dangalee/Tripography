package com.ssafy.board.model.dao;

import com.ssafy.board.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentDao {

    List<CommentDto> listComments(Map<String, Object> param) throws SQLException;
    void writeComment(CommentDto commentDto) throws SQLException;
    void modifyComment(CommentDto commentDto) throws SQLException;
    void deleteComment(int comment_id) throws SQLException;

    int getTotalCommentCount(int param) throws SQLException;
}
