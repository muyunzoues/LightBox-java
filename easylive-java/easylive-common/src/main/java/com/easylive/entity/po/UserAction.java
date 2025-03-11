package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 用户行为 点赞，评论
 */
public class UserAction implements Serializable {


	/**
	 * 
	 */
	private Integer actionId;

	/**
	 * 
	 */
	private String videoId;

	/**
	 * 
	 */
	private String videoUserId;

	/**
	 * 
	 */
	private Integer commentId;

	/**
	 * 0:评论喜欢点赞 1讨厌评论 2视频点赞 3视频收藏 4视频投币
	 */
	private Integer actionType;

	/**
	 * 
	 */
	private Integer actionCount;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date actionTime;

	private String videoCover;
	private String videoName;

	public String getVideoCover() {
		return videoCover;
	}

	public void setVideoCover(String videoCover) {
		this.videoCover = videoCover;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public void setActionId(Integer actionId){
		this.actionId = actionId;
	}

	public Integer getActionId(){
		return this.actionId;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setVideoUserId(String videoUserId){
		this.videoUserId = videoUserId;
	}

	public String getVideoUserId(){
		return this.videoUserId;
	}

	public void setCommentId(Integer commentId){
		this.commentId = commentId;
	}

	public Integer getCommentId(){
		return this.commentId;
	}

	public void setActionType(Integer actionType){
		this.actionType = actionType;
	}

	public Integer getActionType(){
		return this.actionType;
	}

	public void setActionCount(Integer actionCount){
		this.actionCount = actionCount;
	}

	public Integer getActionCount(){
		return this.actionCount;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setActionTime(Date actionTime){
		this.actionTime = actionTime;
	}

	public Date getActionTime(){
		return this.actionTime;
	}

	@Override
	public String toString (){
		return "actionId:"+(actionId == null ? "空" : actionId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，videoUserId:"+(videoUserId == null ? "空" : videoUserId)+"，commentId:"+(commentId == null ? "空" : commentId)+"，0:评论喜欢点赞 1讨厌评论 2视频点赞 3视频收藏 4视频投币:"+(actionType == null ? "空" : actionType)+"，actionCount:"+(actionCount == null ? "空" : actionCount)+"，userId:"+(userId == null ? "空" : userId)+"，actionTime:"+(actionTime == null ? "空" : DateUtil.format(actionTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
