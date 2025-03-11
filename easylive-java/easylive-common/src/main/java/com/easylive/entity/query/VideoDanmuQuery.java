package com.easylive.entity.query;

import java.util.Date;


/**
 * 参数
 */
public class VideoDanmuQuery extends BaseParam {


	/**
	 * 
	 */
	private Integer danmuId;

	/**
	 * 
	 */
	private String videoId;

	private String videoIdFuzzy;

	/**
	 * 
	 */
	private String fileId;

	private String fileIdFuzzy;

	/**
	 * 
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 
	 */
	private String postTime;

	private String postTimeStart;

	private String postTimeEnd;

	/**
	 * 
	 */
	private String text;

	private String textFuzzy;

	/**
	 * 
	 */
	private Integer mode;

	/**
	 * 
	 */
	private String color;

	private String colorFuzzy;

	/**
	 * 
	 */
	private Integer time;


	private String videoUserId;

	private Boolean queryVideoInfo;

	private String videoNameFuzzy;

	public String getVideoNameFuzzy() {
		return videoNameFuzzy;
	}

	public void setVideoNameFuzzy(String videoNameFuzzy) {
		this.videoNameFuzzy = videoNameFuzzy;
	}

	public Boolean getQueryVideoInfo() {
		return queryVideoInfo;
	}

	public void setQueryVideoInfo(Boolean queryVideoInfo) {
		this.queryVideoInfo = queryVideoInfo;
	}

	public String getVideoUserId() {
		return videoUserId;
	}

	public void setVideoUserId(String videoUserId) {
		this.videoUserId = videoUserId;
	}

	public void setDanmuId(Integer danmuId){
		this.danmuId = danmuId;
	}

	public Integer getDanmuId(){
		return this.danmuId;
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

	public void setFileId(String fileId){
		this.fileId = fileId;
	}

	public String getFileId(){
		return this.fileId;
	}

	public void setFileIdFuzzy(String fileIdFuzzy){
		this.fileIdFuzzy = fileIdFuzzy;
	}

	public String getFileIdFuzzy(){
		return this.fileIdFuzzy;
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

	public void setPostTime(String postTime){
		this.postTime = postTime;
	}

	public String getPostTime(){
		return this.postTime;
	}

	public void setPostTimeStart(String postTimeStart){
		this.postTimeStart = postTimeStart;
	}

	public String getPostTimeStart(){
		return this.postTimeStart;
	}
	public void setPostTimeEnd(String postTimeEnd){
		this.postTimeEnd = postTimeEnd;
	}

	public String getPostTimeEnd(){
		return this.postTimeEnd;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return this.text;
	}

	public void setTextFuzzy(String textFuzzy){
		this.textFuzzy = textFuzzy;
	}

	public String getTextFuzzy(){
		return this.textFuzzy;
	}

	public void setMode(Integer mode){
		this.mode = mode;
	}

	public Integer getMode(){
		return this.mode;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return this.color;
	}

	public void setColorFuzzy(String colorFuzzy){
		this.colorFuzzy = colorFuzzy;
	}

	public String getColorFuzzy(){
		return this.colorFuzzy;
	}

	public void setTime(Integer time){
		this.time = time;
	}

	public Integer getTime(){
		return this.time;
	}

}
