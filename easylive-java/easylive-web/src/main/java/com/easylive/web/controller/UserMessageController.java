package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UserMessageCountDto;
import com.easylive.entity.enums.MessageReadTypeEnum;
import com.easylive.entity.po.UserMessage;
import com.easylive.entity.query.UserMessageQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/message")
public class UserMessageController extends ABaseController{
    @Resource
    private UserMessageService userMessageService;

    @RequestMapping("/getNoReadCount")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO getNoReadCount(){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UserMessageQuery messageQuery=new UserMessageQuery();
        messageQuery.setUserId(tokenUserInfoDto.getUserId());
        messageQuery.setReadType(MessageReadTypeEnum.NO_READ.getType());
        Integer count=userMessageService.findCountByParam(messageQuery);
        return getSuccessResponseVO(count);
    }

    @RequestMapping("/getNoReadCountGroup")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO getNoReadCountGroup(){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        List<UserMessageCountDto> dataList=userMessageService.getMessageTypeNoReadCount(tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(dataList);
    }
    @RequestMapping("/readAll")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO readAll(@NotNull Integer messageType){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UserMessageQuery userMessageQuery=new UserMessageQuery();
        userMessageQuery.setUserId(tokenUserInfoDto.getUserId());
        userMessageQuery.setMessageType(messageType);
        UserMessage userMessage=new UserMessage();
        userMessage.setReadType(MessageReadTypeEnum.READ.getType());
        userMessageService.updateByParam(userMessage,userMessageQuery);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/loadMessage")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadMessage(@NotNull Integer messageType,Integer pageNo){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UserMessageQuery userMessageQuery=new UserMessageQuery();
        userMessageQuery.setUserId(tokenUserInfoDto.getUserId());
        userMessageQuery.setMessageType(messageType);
        userMessageQuery.setPageNo(pageNo);
        userMessageQuery.setOrderBy("message_id desc");
        PaginationResultVO resultVO=userMessageService.findListByPage(userMessageQuery);
        return getSuccessResponseVO(resultVO);
    }
    @RequestMapping("/delMessage")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delMessage(@NotNull Integer messageId){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UserMessageQuery userMessageQuery=new UserMessageQuery();
        userMessageQuery.setUserId(tokenUserInfoDto.getUserId());
        userMessageQuery.setMessageId(messageId);
        userMessageService.deleteByParam(userMessageQuery);
        return getSuccessResponseVO(null);
    }
}
