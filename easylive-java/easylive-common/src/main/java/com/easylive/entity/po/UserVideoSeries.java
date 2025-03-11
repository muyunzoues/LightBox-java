package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;


/**
 * 
 */
public class UserVideoSeries implements Serializable {


	/**
	 * 
	 */
	private Integer seriesId;

	/**
	 * 
	 */
	private String seriesName;

	/**
	 * 
	 */
	private String seriesDescription;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private Integer sort;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String cover;

	private List<VideoInfo> videoInfoList;

	public List<VideoInfo> getVideoInfoList() {
		return videoInfoList;
	}

	public void setVideoInfoList(List<VideoInfo> videoInfoList) {
		this.videoInfoList = videoInfoList;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setSeriesId(Integer seriesId){
		this.seriesId = seriesId;
	}

	public Integer getSeriesId(){
		return this.seriesId;
	}

	public void setSeriesName(String seriesName){
		this.seriesName = seriesName;
	}

	public String getSeriesName(){
		return this.seriesName;
	}

	public void setSeriesDescription(String seriesDescription){
		this.seriesDescription = seriesDescription;
	}

	public String getSeriesDescription(){
		return this.seriesDescription;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	@Override
	public String toString (){
		return "seriesId:"+(seriesId == null ? "空" : seriesId)+"，seriesName:"+(seriesName == null ? "空" : seriesName)+"，seriesDescription:"+(seriesDescription == null ? "空" : seriesDescription)+"，userId:"+(userId == null ? "空" : userId)+"，sort:"+(sort == null ? "空" : sort)+"，updateTime:"+(updateTime == null ? "空" : DateUtil.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
