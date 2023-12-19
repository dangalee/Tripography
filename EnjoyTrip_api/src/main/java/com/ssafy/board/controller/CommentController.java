package com.ssafy.board.controller;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.CommentListDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.CommentService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러  API V1")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    //댓글 리스트 불러오기
    @ApiOperation(value = "댓글 목록", notes = "모든 댓글들의 정보를 반환한다.", response = List.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?> listComment(
            @RequestParam @ApiParam(value = "댓글 목록을 얻기위한 부가정보.", required = true) Map<String, String> map) {
        logger.info("listComments map - {}", map);
        try {
            CommentListDto commentListDto = commentService.listComments(map);
            logger.info(commentListDto.toString());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(commentListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @ApiOperation(value = "댓글 개수", notes = "총 댓글 개수를 반환한다")
    @GetMapping("/{articleno}")
    public ResponseEntity<?> totalCommentsCount(
            @PathVariable("articleno") @ApiParam(value = "기사 정보.", required = true) int articleno) {
        try {
            int totalCount = commentService.totalComments(articleno);
            return ResponseEntity.ok().body(totalCount);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    //댓글 작성하기
    @ApiOperation(value = "댓글 작성", notes = "새로운 댓글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeComment(
            @RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) {
        logger.info("writeComment commentDto - {}", commentDto);
        try {
            commentService.writeComment(commentDto);

            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

//    @ApiOperation(value = "댓글 수정", notes = "수정할 댓글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//    @PutMapping
//    public ResponseEntity<String> modifyComment(
//            @RequestBody @ApiParam(value = "수정할 댓글 정보.", required = true) CommentDto commentDto) throws Exception {
//        logger.info("modifyArticle - 호출 {}", commentDto);
//
//        commentService.modifyComment(commentDto);
//        return ResponseEntity.ok().build();
//    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable("comment_id") @ApiParam(value = "삭제할 댓글의 댓글번호.", required = true) int commentId) throws Exception {
        logger.info("deleteComment - 호출");
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();

    }

    private ResponseEntity<?> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
