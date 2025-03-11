package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.enums.VideoOrderTypeEnum;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.UserFocusQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.UserInfoVO;
import com.easylive.service.UserActionService;
import com.easylive.service.UserFocusService;
import com.easylive.service.UserInfoService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.CopyTools;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/uhome")
@Validated
public class UHomeController extends ABaseController{
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private VideoInfoService videoInfoService;
    @Resource
    private UserFocusService userFocusService;
    @Resource
    private UserActionService userActionService;

    @RequestMapping("/getUserInfo")
    public ResponseVO getUserInfo(@NotEmpty String userId) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        UserInfo userInfo=userInfoService.getUserDetailInfo(tokenUserInfoDto==null? null:tokenUserInfoDto.getUserId(),userId);
        UserInfoVO userInfoVO= CopyTools.copy(userInfo,UserInfoVO.class);
        return getSuccessResponseVO(userInfoVO);
    }
    @RequestMapping("/updateUserInfo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO updateUserInfo(@NotEmpty @Size(max = 20) String nickName,
                                     @NotEmpty @Size(max = 100) String avatar,
                                     @NotNull Integer sex,
                                     @NotEmpty @Size(max = 10) String birthday,
                                     @NotEmpty @Size(max = 150) String school,
                                     @NotEmpty @Size(max = 80) String personIntroduction,
                                     @NotEmpty @Size(max = 300) String noticeInfo) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        UserInfo userInfo= new UserInfo();
        userInfo.setUserId(tokenUserInfoDto.getUserId());
        userInfo.setNickName(nickName);
        userInfo.setAvatar(avatar);
        userInfo.setSex(sex);
        userInfo.setBirthday(birthday);
        userInfo.setSchool(school);
        userInfo.setPersonIntroduction(personIntroduction);
        userInfo.setNoticeInfo(noticeInfo);
        userInfoService.updateUserInfo(userInfo,tokenUserInfoDto);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/saveTheme")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO saveTheme(@Min(1) @Max(10) @NotNull Integer theme) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        UserInfo userInfo= new UserInfo();
        userInfo.setTheme(theme);
        userInfoService.updateUserInfoByUserId(userInfo,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/focus")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO focus(@NotEmpty String focusUserId) {
        userFocusService.focusUser(getTokenUserInfoDto().getUserId(),focusUserId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/cancelFocus")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO cancelFocus(@NotEmpty String focusUserId) {
        userFocusService.cancelFocus(getTokenUserInfoDto().getUserId(),focusUserId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/loadFocusList")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadFocusList(Integer pageNo) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        UserFocusQuery focusQuery=new UserFocusQuery();
        focusQuery.setUserId(tokenUserInfoDto.getUserId());
        focusQuery.setPageNo(pageNo);
        focusQuery.setOrderBy("focus_time desc");
        focusQuery.setQueryType(Constants.ZERO);
        PaginationResultVO resultVO= userFocusService.findListByPage(focusQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/loadFansList")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadFansList(Integer pageNo) {
        TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
        UserFocusQuery focusQuery=new UserFocusQuery();
        focusQuery.setFocusUserId(tokenUserInfoDto.getUserId());
        focusQuery.setPageNo(pageNo);
        focusQuery.setOrderBy("focus_time desc");
        focusQuery.setQueryType(Constants.ONE);
        PaginationResultVO resultVO= userFocusService.findListByPage(focusQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/loadVideoList")
    public ResponseVO loadVideoList(@NotEmpty String userId,Integer type,Integer pageNo,String videoName,Integer orderType) {
        VideoInfoQuery infoQuery=new VideoInfoQuery();
        if(type!=null){
            infoQuery.setPageSize(PageSize.SIZE10.getSize());
        }
        VideoOrderTypeEnum videoOrderTypeEnum=VideoOrderTypeEnum.getByType(orderType);
        if(videoOrderTypeEnum==null){
            videoOrderTypeEnum=VideoOrderTypeEnum.CREATE_TIME;
        }
        infoQuery.setOrderBy(videoOrderTypeEnum.getField()+"desc");
        infoQuery.setVideoNameFuzzy(videoName);
        infoQuery.setPageNo(pageNo);
        infoQuery.setUserId(userId);
        PaginationResultVO resultVO=videoInfoService.findListByPage(infoQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/loadUserCollection")
    public ResponseVO loadUserCollection(@NotEmpty String userId,Integer pageNo) {
        UserActionQuery actionQuery= new UserActionQuery();
        actionQuery.setActionType(UserActionTypeEnum.VIDEO_COLLECT.getType());
        actionQuery.setUserId(userId);
        actionQuery.setPageNo(pageNo);
        actionQuery.setOrderBy("action_time desc");
        actionQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO=userActionService.findListByPage(actionQuery);
        return getSuccessResponseVO(resultVO);
    }



}
