package com.easylive.entity.query;

import java.util.Date;


/**
 * 评论参数
 */
public class VideoCommentQuery extends BaseParam {


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

	private String videoIdFuzzy;

	/**
	 * 
	 */
	private String videoUserId;

	private String videoUserIdFuzzy;

	/**
	 * 
	 */
	private String content;

	private String contentFuzzy;

	/**
	 * 
	 */
	private String imgPath;

	private String imgPathFuzzy;

	/**
	 * 
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 
	 */
	private String replyUserId;

	private String replyUserIdFuzzy;

	/**
	 * 0未置顶 1置顶
	 */
	private Integer topType;

	/**
	 * 
	 */
	private String postTime;

	private String postTimeStart;

	private String postTimeEnd;

	/**
	 * 
	 */
	private Integer likeCount;

	/**
	 * 
	 */
	private Integer hateCount;

	private Boolean loadChildren;

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

	public Boolean getLoadChildren() {
		return loadChildren;
	}

	public void setLoadChildren(Boolean loadChildren) {
		this.loadChildren = loadChildren;
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

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setContentFuzzy(String contentFuzzy){
		this.contentFuzzy = contentFuzzy;
	}

	public String getContentFuzzy(){
		return this.contentFuzzy;
	}

	public void setImgPath(String imgPath){
		this.imgPath = imgPath;
	}

	public String getImgPath(){
		return this.imgPath;
	}

	public void setImgPathFuzzy(String imgPathFuzzy){
		this.imgPathFuzzy = imgPathFuzzy;
	}

	public String getImgPathFuzzy(){
		return this.imgPathFuzzy;
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

	public void setReplyUserId(String replyUserId){
		this.replyUserId = replyUserId;
	}

	public String getReplyUserId(){
		return this.replyUserId;
	}

	public void setReplyUserIdFuzzy(String replyUserIdFuzzy){
		this.replyUserIdFuzzy = replyUserIdFuzzy;
	}

	public String getReplyUserIdFuzzy(){
		return this.replyUserIdFuzzy;
	}

	public void setTopType(Integer topType){
		this.topType = topType;
	}

	public Integer getTopType(){
		return this.topType;
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

}
