package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoRecommendTypeEnum;
import com.easylive.entity.po.VideoDanmu;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.VideoDanmuService;
import com.easylive.service.VideoInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController("videoDanmuController")
@RequestMapping("/danmu")
public class VideoDanmuController extends ABaseController{

    @Resource
    private VideoDanmuService videoDanmuService;

    @Resource
    private VideoInfoService videoInfoService;
    @RequestMapping("/postDanmu")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO postDanmu(@NotEmpty String videoId, @NotEmpty String fileId,
                                @NotEmpty @Size(max = 200) String text, @NotNull Integer mode,
                                @NotEmpty String color, @NotNull Integer time) {
        VideoDanmu videoDanmu=new VideoDanmu();
        videoDanmu.setVideoId(videoId);
        videoDanmu.setFileId(fileId);
        videoDanmu.setText(text);
        videoDanmu.setMode(mode);
        videoDanmu.setColor(color);
        videoDanmu.setTime(time);
        videoDanmu.setUserId(getTokenUserInfoDto().getUserId());
        videoDanmu.setPostTime(new Date());
        videoDanmuService.saveVideoDanmu(videoDanmu);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/loadDanmu")
    public ResponseVO loadDanmu(@NotEmpty String fileId, @NotEmpty String videoId) {
        VideoInfo videoInfo= videoInfoService.getVideoInfoByVideoId(videoId);
        if(videoInfo.getInteraction() !=null && videoInfo.getInteraction().contains(Constants.ONE.toString())){
            return getSuccessResponseVO(new ArrayList<>());
        }
        VideoDanmuQuery videoDanmuQuery= new VideoDanmuQuery();
        videoDanmuQuery.setFileId(fileId);
        videoDanmuQuery.setOrderBy("danmu id asc");
        return getSuccessResponseVO(videoDanmuService.findListByParam(videoDanmuQuery));
    }


}
