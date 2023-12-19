package com.ssafy.board.model.service;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.CommentListDto;
import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        super();
        this.commentDao = commentDao;
    }


    @Override
    public void writeComment(CommentDto commentDto) throws SQLException {
        commentDao.writeComment(commentDto);
    }

    @Override
    public void modifyComment(CommentDto commentDto) throws SQLException {
        commentDao.modifyComment(commentDto);
    }

    @Override
    public void deleteComment(int comment_id) throws SQLException {
        commentDao.deleteComment(comment_id);
    }

    @Override
    public CommentListDto listComments(Map<String, String> map) throws SQLException {
        Map<String, Object> param = new HashMap<>();
        param.put("articleNo", map.get("articleNo"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));

        int start = currentPage * sizePerPage - sizePerPage;
        int totalCommentsCount = commentDao.getTotalCommentCount(Integer.parseInt(map.get("articleNo")));
        int totalPageCount = (totalCommentsCount - 1) /sizePerPage + 1;
        param.put("start", start);
        param.put("listsize", sizePerPage);


        List<CommentDto> list = commentDao.listComments(param);

        CommentListDto commentListDto = new CommentListDto();
        commentListDto.setComments(list);
        commentListDto.setCurrentPage(currentPage);
        commentListDto.setTotalPageCount(totalPageCount);

        return commentListDto;

    }

    @Override
    public int totalComments(int article_no) throws SQLException {
        return commentDao.getTotalCommentCount(article_no);
    }
}
