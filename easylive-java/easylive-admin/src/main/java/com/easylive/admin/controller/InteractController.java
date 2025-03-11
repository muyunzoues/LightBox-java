package com.easylive.admin.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.annotation.RecordUserMessage;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.CommentTopTypeEnum;
import com.easylive.entity.enums.MessageTypeEnum;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoComment;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoCommentResultVO;
import com.easylive.service.UserActionService;
import com.easylive.service.VideoCommentService;
import com.easylive.service.VideoDanmuService;
import com.easylive.service.VideoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Valid
@RestController
@RequestMapping("/interact")
@Slf4j
public class InteractController extends ABaseController{
    @Resource
    private VideoCommentService videoCommentService;
    @Resource
    private VideoDanmuService  videoDanmuService;

    @RequestMapping("/loadComment")
    public ResponseVO loadComment(Integer pageNo,String videoNameFuzzy) {
        VideoCommentQuery videoCommentQuery= new VideoCommentQuery();
        videoCommentQuery.setOrderBy("comment_id desc");
        videoCommentQuery.setPageNo(pageNo);
        videoCommentQuery.setQueryVideoInfo(true);
        videoCommentQuery.setVideoNameFuzzy(videoNameFuzzy);
        PaginationResultVO resultVO=videoCommentService.findListByPage(videoCommentQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/delComment")
    public ResponseVO delComment(@NotNull Integer commentId) {
        videoCommentService.deleteComment(commentId,null);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/loadDanmu")
    public ResponseVO loadDanmu(Integer pageNo,String videoNameFuzzy) {
        VideoDanmuQuery videoDanmuQuery=new VideoDanmuQuery();
        videoDanmuQuery.setOrderBy("danmu_id desc");
        videoDanmuQuery.setPageNo(pageNo);
        videoDanmuQuery.setQueryVideoInfo(true);
        videoDanmuQuery.setVideoNameFuzzy(videoNameFuzzy);
        PaginationResultVO resultVO=videoDanmuService.findListByPage(videoDanmuQuery);
        return getSuccessResponseVO(resultVO);
    }
    @RequestMapping("/delDanmu")
    public ResponseVO delDanmu(@NotNull Integer danmuId) {
        videoDanmuService.deleteDanmu(null,danmuId);
        return getSuccessResponseVO(null);
    }
}
