package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.annotation.RecordUserMessage;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.*;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoComment;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoCommentResultVO;
import com.easylive.entity.vo.VideoInfoResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserActionService;
import com.easylive.service.VideoCommentService;
import com.easylive.service.VideoInfoFileService;
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
@RequestMapping("/comment")
@Slf4j
public class VideoCommentController extends ABaseController{
    @Resource
    private VideoCommentService videoCommentService;
    @Resource
    private VideoInfoService videoInfoService;

    @Resource
    private UserActionService userActionService;

    @RequestMapping("/postComment")
    @GlobalInterceptor(checkLogin = true)
    @RecordUserMessage(messageType = MessageTypeEnum.COMMENT)
    public ResponseVO postComment(@NotEmpty String videoId,@NotEmpty @Size(max = 500) String content,
                                  Integer replyCommentId,@Size(max = 50) String imgPath) {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        VideoComment comment= new VideoComment();
        comment.setUserId(tokenUserInfoDto.getUserId());
        comment.setAvatar(tokenUserInfoDto.getAvatar());
        comment.setNickName(tokenUserInfoDto.getNickName());
        comment.setVideoId(videoId);
        comment.setContent(content);
        comment.setImgPath(imgPath);
        videoCommentService.postComment(comment,replyCommentId);
        return getSuccessResponseVO(comment);
    }

    @RequestMapping("/loadComment")
    public ResponseVO loadComment(@NotEmpty String videoId,Integer pageNo,
                                  Integer orderType) {
       VideoInfo videoInfo= videoInfoService.getVideoInfoByVideoId(videoId);
       if(videoInfo.getInteraction() != null && videoInfo.getInteraction().contains(Constants.ZERO.toString())){
           return getSuccessResponseVO(new ArrayList<>());
       }
        VideoCommentQuery commentQuery= new VideoCommentQuery();
       commentQuery.setVideoId(videoId);
       commentQuery.setLoadChildren(true);
       commentQuery.setPageNo(pageNo);
       commentQuery.setPageSize(PageSize.SIZE15.getSize());
       commentQuery.setpCommentId(0);
       String orderBy= orderType==null || orderType==0 ? "like_count desc,comment_id desc":"comment_id desc";
       commentQuery.setOrderBy(orderBy);

        PaginationResultVO<VideoComment> commentData= videoCommentService.findListByPage(commentQuery);

        if(pageNo==null){
            List<VideoComment> topCommentList= topComment(videoId);
            if(!topCommentList.isEmpty()){
                List<VideoComment> commentList=commentData.getList().stream().filter(item -> !item.getCommentId().equals(topCommentList.get(0).getCommentId())).collect(Collectors.toList());
                commentList.addAll(0,topCommentList);
                commentData.setList(commentList);
            }
        }

        VideoCommentResultVO resultVO=new VideoCommentResultVO();
        resultVO.setCommentData(commentData);

        List<UserAction> userActionList= new ArrayList<>();
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        if(tokenUserInfoDto != null){
            UserActionQuery actionQuery= new UserActionQuery();
            actionQuery.setVideoId(videoId);
            actionQuery.setUserId(tokenUserInfoDto.getUserId());
            actionQuery.setActionTypeArray(new Integer[]{UserActionTypeEnum.COMMENT_LIKE.getType(),UserActionTypeEnum.COMMENT_HATE.getType()});
            userActionList=userActionService.findListByParam(actionQuery);
        }
        resultVO.setUserActionList(userActionList);
        return getSuccessResponseVO(resultVO);
    }

    private List<VideoComment> topComment(String videoId){
        VideoCommentQuery commentQuery= new VideoCommentQuery();
        commentQuery.setVideoId(videoId);
        commentQuery.setTopType(CommentTopTypeEnum.TOP.getType());
        commentQuery.setLoadChildren(true);
        List<VideoComment> videoCommentList=videoCommentService.findListByParam(commentQuery);
        return videoCommentList;
    }

    @RequestMapping("/topComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO topComment(@NotNull Integer commentId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        videoCommentService.topComment(commentId,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/cancelTopComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO cancelTopComment(@NotNull Integer commentId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        videoCommentService.cancelTopComment(commentId,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/userDelComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO userDelComment(@NotNull Integer commentId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        videoCommentService.deleteComment(commentId,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }



}
