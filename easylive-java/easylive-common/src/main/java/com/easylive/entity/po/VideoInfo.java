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
public class VideoInfo implements Serializable {


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

	/**
	 * 
	 */
	private Integer playCount;

	/**
	 * 
	 */
	private Integer likeCount;

	/**
	 * 弹幕数量
	 */
	private Integer danmuCount;

	/**
	 * 评论数量
	 */
	private Integer commentCount;

	/**
	 * 投币数量
	 */
	private Integer coinCount;

	/**
	 * 收藏数量
	 */
	private Integer collectCount;

	/**
	 * 0未推荐，1已推荐
	 */
	private Integer recommendType;

	/**
	 * 最后播放时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPlayTime;

	private String nickName;
	private String avatar;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public void setPlayCount(Integer playCount){
		this.playCount = playCount;
	}

	public Integer getPlayCount(){
		return this.playCount;
	}

	public void setLikeCount(Integer likeCount){
		this.likeCount = likeCount;
	}

	public Integer getLikeCount(){
		return this.likeCount;
	}

	public void setDanmuCount(Integer danmuCount){
		this.danmuCount = danmuCount;
	}

	public Integer getDanmuCount(){
		return this.danmuCount;
	}

	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}

	public Integer getCommentCount(){
		return this.commentCount;
	}

	public void setCoinCount(Integer coinCount){
		this.coinCount = coinCount;
	}

	public Integer getCoinCount(){
		return this.coinCount;
	}

	public void setCollectCount(Integer collectCount){
		this.collectCount = collectCount;
	}

	public Integer getCollectCount(){
		return this.collectCount;
	}

	public void setRecommendType(Integer recommendType){
		this.recommendType = recommendType;
	}

	public Integer getRecommendType(){
		return this.recommendType;
	}

	public void setLastPlayTime(Date lastPlayTime){
		this.lastPlayTime = lastPlayTime;
	}

	public Date getLastPlayTime(){
		return this.lastPlayTime;
	}

	@Override
	public String toString (){
		return "videoId:"+(videoId == null ? "空" : videoId)+"，视频封面:"+(videoCover == null ? "空" : videoCover)+"，videoName:"+(videoName == null ? "空" : videoName)+"，userId:"+(userId == null ? "空" : userId)+"，createTime:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，lastUpdateTime:"+(lastUpdateTime == null ? "空" : DateUtil.format(lastUpdateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，父级分类ID:"+(pCategoryId == null ? "空" : pCategoryId)+"，categoryId:"+(categoryId == null ? "空" : categoryId)+"，0转码中，1转码失败，2待审核，3审核成功，4审核失败:"+(status == null ? "空" : status)+"，0自制作，1转载:"+(postType == null ? "空" : postType)+"，原资源说明:"+(originInfo == null ? "空" : originInfo)+"，标签:"+(tags == null ? "空" : tags)+"，introduction:"+(introduction == null ? "空" : introduction)+"，互动设置:"+(interaction == null ? "空" : interaction)+"，持续时间:"+(duration == null ? "空" : duration)+"，playCount:"+(playCount == null ? "空" : playCount)+"，likeCount:"+(likeCount == null ? "空" : likeCount)+"，弹幕数量:"+(danmuCount == null ? "空" : danmuCount)+"，评论数量:"+(commentCount == null ? "空" : commentCount)+"，投币数量:"+(coinCount == null ? "空" : coinCount)+"，收藏数量:"+(collectCount == null ? "空" : collectCount)+"，0未推荐，1已推荐:"+(recommendType == null ? "空" : recommendType)+"，最后播放时间:"+(lastPlayTime == null ? "空" : DateUtil.format(lastPlayTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
