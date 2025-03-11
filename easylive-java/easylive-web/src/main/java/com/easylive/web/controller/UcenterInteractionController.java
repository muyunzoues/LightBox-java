package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoStatusEnum;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.*;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoPostEditInfoVO;
import com.easylive.entity.vo.VideoStatusCountInfoVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.*;
import com.easylive.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/ucenter")
@Validated
@Slf4j
public class UcenterInteractionController extends ABaseController{
//    @Resource
//    private VideoInfoFilePostService videoInfoFilePostService;
//    @Resource
//    private VideoInfoPostService videoInfoPostService;
    @Resource
    private VideoInfoService videoInfoService;

    @Resource
    private VideoCommentService videoCommentService;

    @Resource
    private VideoDanmuService videoDanmuService;


    @RequestMapping("/loadAllVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadAllVideo() {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
        videoInfoQuery.setUserId(tokenUserInfoDto.getUserId());
        videoInfoQuery.setOrderBy("create_time desc");
        List<VideoInfo> videoInfoList= videoInfoService.findListByParam(videoInfoQuery);
        return getSuccessResponseVO(videoInfoList);
    }
    @RequestMapping("/loadComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadComment(Integer pageNo,String videoId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        VideoCommentQuery videoCommentQuery= new VideoCommentQuery();
        videoCommentQuery.setVideoId(videoId);
        videoCommentQuery.setVideoUserId(tokenUserInfoDto.getUserId());
        videoCommentQuery.setOrderBy("comment_id desc");
        videoCommentQuery.setPageSize(pageNo);
        videoCommentQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO= videoCommentService.findListByPage(videoCommentQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/delComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delComment(@NotNull Integer commentId) {
        videoCommentService.deleteComment(commentId,getTokenUserInfoDto().getUserId());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/loadDammu")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadDammu(Integer pageNo,String videoId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        VideoDanmuQuery videoDanmuQuery= new VideoDanmuQuery();
        videoDanmuQuery.setVideoId(videoId);
        videoDanmuQuery.setVideoUserId(tokenUserInfoDto.getUserId());
        videoDanmuQuery.setOrderBy("danmu_id desc");
        videoDanmuQuery.setPageNo(pageNo);
        videoDanmuQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO= videoDanmuService.findListByPage(videoDanmuQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/delDanmu")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delDanmu(@NotNull Integer danmuId) {
        videoDanmuService.deleteDanmu(getTokenUserInfoDto().getUserId(),danmuId);
        return getSuccessResponseVO(null);
    }

}
