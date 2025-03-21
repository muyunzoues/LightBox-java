package com.easylive.entity.po;

import com.easylive.entity.dto.UserMessageExtendDto;
import com.easylive.utils.JsonUtils;
import com.easylive.utils.StringTools;
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
public class UserMessage implements Serializable {


	/**
	 * 
	 */
	private Integer messageId;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String videoId;

	/**
	 * 
	 */
	private Integer messageType;

	/**
	 * 
	 */
	private String sendUserId;

	/**
	 * 0:未读 1：已读
	 */
	private Integer readType;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 扩展信息
	 */
	private String extendJson;
	private String sendUserAvatar;
	private String sendUserName;
	private String videoName;
	private String videoCover;

	private UserMessageExtendDto extendDto;

	public UserMessageExtendDto getExtendDto() {
		return StringTools.isEmpty(extendJson)?new UserMessageExtendDto(): JsonUtils.convertJson2Obj(extendJson, UserMessageExtendDto.class);
	}

	public void setExtendDto(UserMessageExtendDto extendDto) {
		this.extendDto = extendDto;
	}

	public String getSendUserAvatar() {
		return sendUserAvatar;
	}

	public void setSendUserAvatar(String sendUserAvatar) {
		this.sendUserAvatar = sendUserAvatar;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

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

	public void setMessageId(Integer messageId){
		this.messageId = messageId;
	}

	public Integer getMessageId(){
		return this.messageId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setMessageType(Integer messageType){
		this.messageType = messageType;
	}

	public Integer getMessageType(){
		return this.messageType;
	}

	public void setSendUserId(String sendUserId){
		this.sendUserId = sendUserId;
	}

	public String getSendUserId(){
		return this.sendUserId;
	}

	public void setReadType(Integer readType){
		this.readType = readType;
	}

	public Integer getReadType(){
		return this.readType;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setExtendJson(String extendJson){
		this.extendJson = extendJson;
	}

	public String getExtendJson(){
		return this.extendJson;
	}

	@Override
	public String toString (){
		return "messageId:"+(messageId == null ? "空" : messageId)+"，userId:"+(userId == null ? "空" : userId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，messageType:"+(messageType == null ? "空" : messageType)+"，sendUserId:"+(sendUserId == null ? "空" : sendUserId)+"，0:未读 1：已读:"+(readType == null ? "空" : readType)+"，createTime:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，扩展信息:"+(extendJson == null ? "空" : extendJson);
	}
}
