package com.ssafy.board.model.service;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.CommentListDto;

import java.sql.SQLException;
import java.util.Map;

public interface CommentService {
    void writeComment(CommentDto commentDto) throws SQLException;
    void modifyComment(CommentDto commentDto) throws SQLException;
    void deleteComment(int comment_id) throws SQLException;
    CommentListDto listComments(Map<String, String> map) throws SQLException;
    int totalComments(int article_no) throws SQLException;
}
