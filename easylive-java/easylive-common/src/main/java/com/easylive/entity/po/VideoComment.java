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
 * 评论
 */
public class VideoComment implements Serializable {


	/**
	 * 
	 */
	private Integer commentId;

	/**
	 * 
	 */
	private Integer pCommentId;

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
	private String content;

	/**
	 * 
	 */
	private String imgPath;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String replyUserId;

	/**
	 * 0未置顶 1置顶
	 */
	private Integer topType;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postTime;

	/**
	 * 
	 */
	private Integer likeCount;

	/**
	 * 
	 */
	private Integer hateCount;
	private String avatar;
	private String nickName;

	private String replyAvatar;

	private String replyNickName;

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

	private List<VideoComment> children;

	public List<VideoComment> getChildren() {
		return children;
	}

	public void setChildren(List<VideoComment> children) {
		this.children = children;
	}

	public String getReplyNickName() {
		return replyNickName;
	}

	public void setReplyNickName(String replyNickName) {
		this.replyNickName = replyNickName;
	}

	public String getReplyAvatar() {
		return replyAvatar;
	}

	public void setReplyAvatar(String replyAvatar) {
		this.replyAvatar = replyAvatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setCommentId(Integer commentId){
		this.commentId = commentId;
	}

	public Integer getCommentId(){
		return this.commentId;
	}

	public void setpCommentId(Integer pCommentId){
		this.pCommentId = pCommentId;
	}

	public Integer getpCommentId(){
		return this.pCommentId;
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

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setImgPath(String imgPath){
		this.imgPath = imgPath;
	}

	public String getImgPath(){
		return this.imgPath;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setReplyUserId(String replyUserId){
		this.replyUserId = replyUserId;
	}

	public String getReplyUserId(){
		return this.replyUserId;
	}

	public void setTopType(Integer topType){
		this.topType = topType;
	}

	public Integer getTopType(){
		return this.topType;
	}

	public void setPostTime(Date postTime){
		this.postTime = postTime;
	}

	public Date getPostTime(){
		return this.postTime;
	}

	public void setLikeCount(Integer likeCount){
		this.likeCount = likeCount;
	}

	public Integer getLikeCount(){
		return this.likeCount;
	}

	public void setHateCount(Integer hateCount){
		this.hateCount = hateCount;
	}

	public Integer getHateCount(){
		return this.hateCount;
	}

	@Override
	public String toString (){
		return "commentId:"+(commentId == null ? "空" : commentId)+"，pCommentId:"+(pCommentId == null ? "空" : pCommentId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，videoUserId:"+(videoUserId == null ? "空" : videoUserId)+"，content:"+(content == null ? "空" : content)+"，imgPath:"+(imgPath == null ? "空" : imgPath)+"，userId:"+(userId == null ? "空" : userId)+"，replyUserId:"+(replyUserId == null ? "空" : replyUserId)+"，0未置顶 1置顶:"+(topType == null ? "空" : topType)+"，postTime:"+(postTime == null ? "空" : DateUtil.format(postTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，likeCount:"+(likeCount == null ? "空" : likeCount)+"，hateCount:"+(hateCount == null ? "空" : hateCount);
	}
}
