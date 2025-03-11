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
public class UserFocus implements Serializable {


	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String focusUserId;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date focusTime;

	private String otherNickName;
	private String otherUserId;
	private String otherPersonIntroduction;
	private String otherAvatar;
	private String focusType;

	public String getOtherNickName() {
		return otherNickName;
	}

	public void setOtherNickName(String otherNickName) {
		this.otherNickName = otherNickName;
	}

	public String getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(String otherUserId) {
		this.otherUserId = otherUserId;
	}

	public String getOtherPersonIntroduction() {
		return otherPersonIntroduction;
	}

	public void setOtherPersonIntroduction(String otherPersonIntroduction) {
		this.otherPersonIntroduction = otherPersonIntroduction;
	}

	public String getOtherAvatar() {
		return otherAvatar;
	}

	public void setOtherAvatar(String otherAvatar) {
		this.otherAvatar = otherAvatar;
	}

	public String getFocusType() {
		return focusType;
	}

	public void setFocusType(String focusType) {
		this.focusType = focusType;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setFocusUserId(String focusUserId){
		this.focusUserId = focusUserId;
	}

	public String getFocusUserId(){
		return this.focusUserId;
	}

	public void setFocusTime(Date focusTime){
		this.focusTime = focusTime;
	}

	public Date getFocusTime(){
		return this.focusTime;
	}

	@Override
	public String toString (){
		return "userId:"+(userId == null ? "空" : userId)+"，focusUserId:"+(focusUserId == null ? "空" : focusUserId)+"，focusTime:"+(focusTime == null ? "空" : DateUtil.format(focusTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
