package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoStatusEnum;
import com.easylive.entity.po.StatisticsInfo;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.StatisticsInfoQuery;
import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.query.VideoInfoPostQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoPostEditInfoVO;
import com.easylive.entity.vo.VideoStatusCountInfoVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.StatisticsInfoService;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.service.VideoInfoPostService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.DateUtil;
import com.easylive.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ucenter")
@Validated
@Slf4j
public class UcenterStatisticsPostController extends ABaseController{
    @Resource
    private StatisticsInfoService statisticsInfoService;

    @RequestMapping("/getActualTimeStatisticsInfo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO getActualTimeStatisticsInfo() {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        String preData= DateUtil.getBeforeDate(1);
        StatisticsInfoQuery param=new StatisticsInfoQuery();
        param.setStatisticsDate(preData);
        param.setUserId(tokenUserInfoDto.getUserId());

        List<StatisticsInfo> preDayData=statisticsInfoService.findListByParam(param);
        Map<Integer,Integer> preDayDataMap=preDayData.stream().collect(Collectors.toMap(StatisticsInfo::getDataType,StatisticsInfo::getStatisticsCount,
                (item1,item2)->item2));
        Map<String,Integer> totalCountInfo=statisticsInfoService.getStatisticInfoActualTime(tokenUserInfoDto.getUserId());
        Map<String,Object> result=new HashMap<>();
        result.put("preDayData",preDayDataMap);
        result.put("totalCountInfo",totalCountInfo);
        return getSuccessResponseVO(result);
    }
    @RequestMapping("/getWeekStatisticsInfo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO getWeekStatisticsInfo(Integer dataType) {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();

        List<String> dateList=DateUtil.getBeforeDates(7);

        StatisticsInfoQuery param=new StatisticsInfoQuery();
        param.setDataType(dataType);
        param.setUserId(tokenUserInfoDto.getUserId());
        param.setStatisticsDateStart(dateList.get(0));
        param.setStatisticsDateEnd(dateList.get(dateList.size()-1));
        param.setOrderBy("statistics_date asc");
        List<StatisticsInfo> statisticsInfoList=statisticsInfoService.findListByParam(param);

        Map<String,StatisticsInfo> dataMap=statisticsInfoList.stream().collect(Collectors.toMap(item->item.getStatisticsDate(), Function.identity(),
                (data1,data2)->data2));
        List<StatisticsInfo> resultDataList=new ArrayList<>();
        for(String date:dateList){
            StatisticsInfo dataItem=dataMap.get(date);
            if(dataItem==null){
                dataItem=new StatisticsInfo();
                dataItem.setStatisticsCount(0);
                dataItem.setStatisticsDate(date);
            }
            resultDataList.add(dataItem);
        }
        return getSuccessResponseVO(resultDataList);
    }

}
