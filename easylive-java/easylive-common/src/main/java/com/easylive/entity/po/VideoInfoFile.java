package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 */
public class VideoInfoFile implements Serializable {


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
	private String videoId;

	/**
	 * 
	 */
	private Integer fileIndex;

	/**
	 * 
	 */
	private String fileName;

	/**
	 * 
	 */
	private Long fileSize;

	/**
	 * 
	 */
	private String filePath;

	/**
	 * 持续时间
	 */
	private Integer duration;


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

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setFileIndex(Integer fileIndex){
		this.fileIndex = fileIndex;
	}

	public Integer getFileIndex(){
		return this.fileIndex;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}

	public Long getFileSize(){
		return this.fileSize;
	}

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	public String getFilePath(){
		return this.filePath;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration(){
		return this.duration;
	}

	@Override
	public String toString (){
		return "fileId:"+(fileId == null ? "空" : fileId)+"，userId:"+(userId == null ? "空" : userId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，fileIndex:"+(fileIndex == null ? "空" : fileIndex)+"，fileName:"+(fileName == null ? "空" : fileName)+"，fileSize:"+(fileSize == null ? "空" : fileSize)+"，filePath:"+(filePath == null ? "空" : filePath)+"，持续时间:"+(duration == null ? "空" : duration);
	}
}
