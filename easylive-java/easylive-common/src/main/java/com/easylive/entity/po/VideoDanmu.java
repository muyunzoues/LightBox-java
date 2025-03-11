package com.easylive.entity.po;

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
public class VideoDanmu implements Serializable {


	/**
	 * 
	 */
	private Integer danmuId;

	/**
	 * 
	 */
	private String videoId;

	/**
	 * 
	 */
	private String fileId;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postTime;

	/**
	 * 
	 */
	private String text;

	/**
	 * 
	 */
	private Integer mode;

	/**
	 * 
	 */
	private String color;

	/**
	 * 
	 */
	private Integer time;

	private String videoName;
	private String videoCover;
	private String nickName;

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoCover() {
		return videoCover;
	}

	public void setVideoCover(String videoCover) {
		this.videoCover = videoCover;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public void setFileId(String fileId){
		this.fileId = fileId;
	}

	public String getFileId(){
		return this.fileId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setPostTime(Date postTime){
		this.postTime = postTime;
	}

	public Date getPostTime(){
		return this.postTime;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return this.text;
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

	public void setTime(Integer time){
		this.time = time;
	}

	public Integer getTime(){
		return this.time;
	}

	@Override
	public String toString (){
		return "danmuId:"+(danmuId == null ? "空" : danmuId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，fileId:"+(fileId == null ? "空" : fileId)+"，userId:"+(userId == null ? "空" : userId)+"，postTime:"+(postTime == null ? "空" : DateUtil.format(postTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，text:"+(text == null ? "空" : text)+"，mode:"+(mode == null ? "空" : mode)+"，color:"+(color == null ? "空" : color)+"，time:"+(time == null ? "空" : time);
	}
}
