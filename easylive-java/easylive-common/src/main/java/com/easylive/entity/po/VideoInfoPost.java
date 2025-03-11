package com.easylive.entity.po;

import com.easylive.entity.enums.VideoStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 */
public class VideoInfoPost extends VideoInfo implements Serializable {


	/**
	 * 
	 */
	private String videoId;

	/**
	 * 视频封面
	 */
	private String videoCover;

	/**
	 * 
	 */
	private String videoName;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;

	/**
	 * 父级分类ID
	 */
	private Integer pCategoryId;

	/**
	 * 
	 */
	private Integer categoryId;

	/**
	 * 0转码中，1转码失败，2待审核，3审核成功，4审核失败
	 */
	private Integer status;

	/**
	 * 0自制作，1转载
	 */
	private Integer postType;

	/**
	 * 原资源说明
	 */
	private String originInfo;

	/**
	 * 标签
	 */
	private String tags;

	/**
	 * 
	 */
	private String introduction;

	/**
	 * 互动设置
	 */
	private String interaction;

	/**
	 * 持续时间
	 */
	private Integer duration;

	private String statusName;

	public String getStatusName() {
		VideoStatusEnum videoStatusEnum= VideoStatusEnum.getByStatus(status);
		return videoStatusEnum==null?"":videoStatusEnum.getDesc();
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setVideoCover(String videoCover){
		this.videoCover = videoCover;
	}

	public String getVideoCover(){
		return this.videoCover;
	}

	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getVideoName(){
		return this.videoName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	public void setpCategoryId(Integer pCategoryId){
		this.pCategoryId = pCategoryId;
	}

	public Integer getpCategoryId(){
		return this.pCategoryId;
	}

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Integer getCategoryId(){
		return this.categoryId;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setPostType(Integer postType){
		this.postType = postType;
	}

	public Integer getPostType(){
		return this.postType;
	}

	public void setOriginInfo(String originInfo){
		this.originInfo = originInfo;
	}

	public String getOriginInfo(){
		return this.originInfo;
	}

	public void setTags(String tags){
		this.tags = tags;
	}

	public String getTags(){
		return this.tags;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
	}

	public void setInteraction(String interaction){
		this.interaction = interaction;
	}

	public String getInteraction(){
		return this.interaction;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration(){
		return this.duration;
	}

	@Override
	public String toString (){
		return "videoId:"+(videoId == null ? "空" : videoId)+"，视频封面:"+(videoCover == null ? "空" : videoCover)+"，videoName:"+(videoName == null ? "空" : videoName)+"，userId:"+(userId == null ? "空" : userId)+"，createTime:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，lastUpdateTime:"+(lastUpdateTime == null ? "空" : DateUtil.format(lastUpdateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，父级分类ID:"+(pCategoryId == null ? "空" : pCategoryId)+"，categoryId:"+(categoryId == null ? "空" : categoryId)+"，0转码中，1转码失败，2待审核，3审核成功，4审核失败:"+(status == null ? "空" : status)+"，0自制作，1转载:"+(postType == null ? "空" : postType)+"，原资源说明:"+(originInfo == null ? "空" : originInfo)+"，标签:"+(tags == null ? "空" : tags)+"，introduction:"+(introduction == null ? "空" : introduction)+"，互动设置:"+(interaction == null ? "空" : interaction)+"，持续时间:"+(duration == null ? "空" : duration);
	}
}
