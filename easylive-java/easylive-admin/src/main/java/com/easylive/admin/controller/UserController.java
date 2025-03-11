package com.easylive.admin.controller;

import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserInfoService;
import com.easylive.service.VideoCommentService;
import com.easylive.service.VideoDanmuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends ABaseController{
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/loadUser")
    public ResponseVO loadUser(UserInfoQuery userInfoQuery) {
       userInfoQuery.setOrderBy("join_time desc");
        return getSuccessResponseVO(userInfoService.findListByPage(userInfoQuery));
    }
    @RequestMapping("/changeStatus")
    public ResponseVO changeStatus(String userId,Integer status) {
        userInfoService.changeUserStatus(userId,status);
        return getSuccessResponseVO(null);
    }


}
