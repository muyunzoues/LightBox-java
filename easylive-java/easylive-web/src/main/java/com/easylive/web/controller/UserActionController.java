package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.annotation.RecordUserMessage;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.MessageTypeEnum;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoRecommendTypeEnum;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoInfoResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserActionService;
import com.easylive.service.VideoInfoFileService;
import com.easylive.service.VideoInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Valid
@RestController
@RequestMapping("/userAction")
public class UserActionController extends ABaseController{
    @Resource
    private UserActionService userActionService;

    @RequestMapping("/doAction")
    @GlobalInterceptor(checkLogin = true)
    @RecordUserMessage(messageType = MessageTypeEnum.LIKE)
    public ResponseVO doAction(@NotEmpty String videoId,
                               @NotNull Integer actionType,
                               @Max(2) @Min(1) Integer actionCount,
                               Integer commentId) {
        UserAction userAction=new UserAction();
        userAction.setVideoId(videoId);
        userAction.setUserId(getTokenUserInfoDto().getUserId());
        userAction.setActionType(actionType);
        actionCount=actionCount==null? Constants.ONE:actionCount;
        userAction.setActionCount(actionCount);
        commentId=commentId==null?Constants.ZERO:commentId;
        userAction.setCommentId(commentId);
        userActionService.saveAction(userAction);
        return getSuccessResponseVO(null);
    }



}
