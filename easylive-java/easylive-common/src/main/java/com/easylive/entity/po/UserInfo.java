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
public class UserInfo implements Serializable {


	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String nickName;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private Integer sex;

	/**
	 * 
	 */
	private String birthday;

	/**
	 * 
	 */
	private String school;

	/**
	 * 
	 */
	private String personIntroduction;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 
	 */
	private String lastLoginIp;

	/**
	 * 
	 */
	private Integer status;

	/**
	 * 
	 */
	private String noticeInfo;

	/**
	 * 
	 */
	private Integer totalCoinCount;

	/**
	 * 
	 */
	private Integer currentCoinCount;

	/**
	 * 
	 */
	private Integer theme;

	/**
	 * 
	 */
	private String avatar;

	private Integer fansCount;
	private Integer focusCount;
	private Integer likeCount;
	private Integer playCount;
	private Boolean havaFocus;

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Boolean getHavaFocus() {
		return havaFocus;
	}

	public void setHavaFocus(Boolean havaFocus) {
		this.havaFocus = havaFocus;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return this.birthday;
	}

	public void setSchool(String school){
		this.school = school;
	}

	public String getSchool(){
		return this.school;
	}

	public void setPersonIntroduction(String personIntroduction){
		this.personIntroduction = personIntroduction;
	}

	public String getPersonIntroduction(){
		return this.personIntroduction;
	}

	public void setJoinTime(Date joinTime){
		this.joinTime = joinTime;
	}

	public Date getJoinTime(){
		return this.joinTime;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setLastLoginIp(String lastLoginIp){
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginIp(){
		return this.lastLoginIp;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setNoticeInfo(String noticeInfo){
		this.noticeInfo = noticeInfo;
	}

	public String getNoticeInfo(){
		return this.noticeInfo;
	}

	public void setTotalCoinCount(Integer totalCoinCount){
		this.totalCoinCount = totalCoinCount;
	}

	public Integer getTotalCoinCount(){
		return this.totalCoinCount;
	}

	public void setCurrentCoinCount(Integer currentCoinCount){
		this.currentCoinCount = currentCoinCount;
	}

	public Integer getCurrentCoinCount(){
		return this.currentCoinCount;
	}

	public void setTheme(Integer theme){
		this.theme = theme;
	}

	public Integer getTheme(){
		return this.theme;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	@Override
	public String toString (){
		return "userId:"+(userId == null ? "空" : userId)+"，nickName:"+(nickName == null ? "空" : nickName)+"，email:"+(email == null ? "空" : email)+"，password:"+(password == null ? "空" : password)+"，sex:"+(sex == null ? "空" : sex)+"，birthday:"+(birthday == null ? "空" : birthday)+"，school:"+(school == null ? "空" : school)+"，personIntroduction:"+(personIntroduction == null ? "空" : personIntroduction)+"，joinTime:"+(joinTime == null ? "空" : DateUtil.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，lastLoginTime:"+(lastLoginTime == null ? "空" : DateUtil.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，lastLoginIp:"+(lastLoginIp == null ? "空" : lastLoginIp)+"，status:"+(status == null ? "空" : status)+"，noticeInfo:"+(noticeInfo == null ? "空" : noticeInfo)+"，totalCoinCount:"+(totalCoinCount == null ? "空" : totalCoinCount)+"，currentCoinCount:"+(currentCoinCount == null ? "空" : currentCoinCount)+"，theme:"+(theme == null ? "空" : theme)+"，avatar:"+(avatar == null ? "空" : avatar);
	}
}
