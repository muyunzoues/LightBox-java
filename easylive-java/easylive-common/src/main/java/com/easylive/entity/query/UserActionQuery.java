package com.easylive.entity.query;

import java.util.Date;


/**
 * 用户行为 点赞，评论参数
 */
public class UserActionQuery extends BaseParam {


	/**
	 * 
	 */
	private Integer actionId;

	/**
	 * 
	 */
	private String videoId;

	private String videoIdFuzzy;

	/**
	 * 
	 */
	private String videoUserId;

	private String videoUserIdFuzzy;

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

	private String userIdFuzzy;

	/**
	 * 
	 */
	private String actionTime;

	private String actionTimeStart;

	private String actionTimeEnd;

	private Integer[] actionTypeArray;
	private Boolean queryVideoInfo;

	public Boolean getQueryVideoInfo() {
		return queryVideoInfo;
	}

	public void setQueryVideoInfo(Boolean queryVideoInfo) {
		this.queryVideoInfo = queryVideoInfo;
	}

	public Integer[] getActionTypeArray() {
		return actionTypeArray;
	}

	public void setActionTypeArray(Integer[] actionTypeArray) {
		this.actionTypeArray = actionTypeArray;
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

	public void setVideoIdFuzzy(String videoIdFuzzy){
		this.videoIdFuzzy = videoIdFuzzy;
	}

	public String getVideoIdFuzzy(){
		return this.videoIdFuzzy;
	}

	public void setVideoUserId(String videoUserId){
		this.videoUserId = videoUserId;
	}

	public String getVideoUserId(){
		return this.videoUserId;
	}

	public void setVideoUserIdFuzzy(String videoUserIdFuzzy){
		this.videoUserIdFuzzy = videoUserIdFuzzy;
	}

	public String getVideoUserIdFuzzy(){
		return this.videoUserIdFuzzy;
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

	public void setUserIdFuzzy(String userIdFuzzy){
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy(){
		return this.userIdFuzzy;
	}

	public void setActionTime(String actionTime){
		this.actionTime = actionTime;
	}

	public String getActionTime(){
		return this.actionTime;
	}

	public void setActionTimeStart(String actionTimeStart){
		this.actionTimeStart = actionTimeStart;
	}

	public String getActionTimeStart(){
		return this.actionTimeStart;
	}
	public void setActionTimeEnd(String actionTimeEnd){
		this.actionTimeEnd = actionTimeEnd;
	}

	public String getActionTimeEnd(){
		return this.actionTimeEnd;
	}

}
