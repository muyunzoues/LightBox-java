package com.easylive.web.controller;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.*;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
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
import java.util.stream.Collectors;

@Valid
@RestController
@RequestMapping("/video")
public class VideoController extends ABaseController{
    @Resource
    private VideoInfoFileService videoInfoFileService;
    @Resource
    private VideoInfoService videoInfoService;

    @Resource
    private UserActionService userActionService;

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private EsSearchComponent esSearchComponent;

    @RequestMapping("/loadRecommendVideo")
    public ResponseVO loadRecommendVideo() {
        VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
        videoInfoQuery.setQueryUserInfo(true);
        videoInfoQuery.setOrderBy("create_time desc");
        videoInfoQuery.setRecommendType(VideoRecommendTypeEnum.RECOMMEND.getType());
        List<VideoInfo> recommendVideoList= videoInfoService.findListByParam(videoInfoQuery);
        return getSuccessResponseVO(recommendVideoList);
    }

    @RequestMapping("/loadVideo")
    public ResponseVO loadVideo(Integer pCategoryId,Integer categoryId,Integer pageNo) {
        VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
        videoInfoQuery.setCategoryId(categoryId);
        videoInfoQuery.setpCategoryId(pCategoryId);
        videoInfoQuery.setPageNo(pageNo);
        videoInfoQuery.setQueryUserInfo(true);
        videoInfoQuery.setOrderBy("create_time desc");
        videoInfoQuery.setRecommendType(VideoRecommendTypeEnum.RECOMMEND.getType());
        List<VideoInfo> recommendVideoList= videoInfoService.findListByParam(videoInfoQuery);
        return getSuccessResponseVO(recommendVideoList);
    }
    @RequestMapping("/getVideoInfo")
    public ResponseVO getVideoInfo(@NotEmpty String videoId) {
        VideoInfo videoInfo=videoInfoService.getVideoInfoByVideoId(videoId);
        if(videoInfo == null){
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
        TokenUserInfoDto userInfoDto= getTokenUserInfoDto();
        List<UserAction> userActionList= new ArrayList<>();
        if(userInfoDto!=null){
            UserActionQuery actionQuery= new UserActionQuery();
            actionQuery.setVideoId(videoId);
            actionQuery.setUserId(userInfoDto.getUserId());
            actionQuery.setActionTypeArray(new Integer[]{UserActionTypeEnum.VIDEO_LIKE.getType(),UserActionTypeEnum.VIDEO_COLLECT.getType(),
            UserActionTypeEnum.VIDEO_COIN.getType()});
            userActionList=userActionService.findListByParam(actionQuery);
        }
        VideoInfoResultVO resultVO= new VideoInfoResultVO(videoInfo,userActionList);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/loadVideoPList")
    public ResponseVO loadVideoPList(@NotEmpty String videoId) {
        VideoInfoFileQuery videoInfoFileQuery=new VideoInfoFileQuery();
        videoInfoFileQuery.setVideoId(videoId);
        videoInfoFileQuery.setOrderBy("file_index asc");
        List<VideoInfoFile> fileList= videoInfoFileService.findListByParam(videoInfoFileQuery);
        return getSuccessResponseVO(fileList);
    }

    @RequestMapping("/reportVideoPlayOnline")
    public ResponseVO reportVideoPlayOnline(@NotEmpty String fileId,@NotEmpty String deviceId) {
        return getSuccessResponseVO(redisComponent.reportVideoPlayOnline(fileId,deviceId));
    }
    @RequestMapping("/search")
    public ResponseVO search(@NotEmpty String keyword,Integer orderType,Integer pageNo) {
        // 记录搜索热词
        redisComponent.addKeywordCount(keyword);
        PaginationResultVO resultVO= esSearchComponent.search(true,keyword,orderType,pageNo, PageSize.SIZE30.getSize());
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/getVideoRecommend")
    public ResponseVO getVideoRecommend(@NotEmpty String keyword,@NotEmpty String videoId) {
        List<VideoInfo> videoInfoList= esSearchComponent.search(false,keyword, SearchOrderTypeEnum.VIDEO_PLAY.getType(),1,PageSize.SIZE10.getSize()).getList();
        videoInfoList=videoInfoList.stream().filter(item -> !item.getVideoId().equals(videoId)).collect(Collectors.toList());
        return getSuccessResponseVO(videoInfoList);
    }
    @RequestMapping("/getSearchKeywordTop")
    public ResponseVO getSearchKeywordTop() {
        List<String> keywordList= redisComponent.getKeywordTop(Constants.LENGTH_10);
        return getSuccessResponseVO(keywordList);
    }

    @RequestMapping("/loadHotVideoList")
    public ResponseVO loadHotVideoList(Integer pageNo) {
       VideoInfoQuery videoInfoQuery=new VideoInfoQuery();
       videoInfoQuery.setPageNo(pageNo);
       videoInfoQuery.setQueryUserInfo(true);
       videoInfoQuery.setOrderBy("play_count desc");
       videoInfoQuery.setLastPlayHour(Constants.HOUR_24);
       PaginationResultVO resultVO=videoInfoService.findListByPage(videoInfoQuery);
       return getSuccessResponseVO(redisComponent);
    }

}
