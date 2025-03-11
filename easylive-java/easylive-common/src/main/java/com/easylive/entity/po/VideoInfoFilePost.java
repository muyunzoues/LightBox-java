package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 */
public class VideoInfoFilePost implements Serializable {


	/**
	 * 
	 */
	private String fileId;

	/**
	 * 上传ID
	 */
	private String uploadId;

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
	 * 0无更新，1有更新
	 */
	private Integer updateType;

	/**
	 * 0转码中，1转码成功，2转码失败
	 */
	private Integer transferResult;

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

	public void setUploadId(String uploadId){
		this.uploadId = uploadId;
	}

	public String getUploadId(){
		return this.uploadId;
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

	public void setUpdateType(Integer updateType){
		this.updateType = updateType;
	}

	public Integer getUpdateType(){
		return this.updateType;
	}

	public void setTransferResult(Integer transferResult){
		this.transferResult = transferResult;
	}

	public Integer getTransferResult(){
		return this.transferResult;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration(){
		return this.duration;
	}

	@Override
	public String toString (){
		return "fileId:"+(fileId == null ? "空" : fileId)+"，上传ID:"+(uploadId == null ? "空" : uploadId)+"，userId:"+(userId == null ? "空" : userId)+"，videoId:"+(videoId == null ? "空" : videoId)+"，fileIndex:"+(fileIndex == null ? "空" : fileIndex)+"，fileName:"+(fileName == null ? "空" : fileName)+"，fileSize:"+(fileSize == null ? "空" : fileSize)+"，filePath:"+(filePath == null ? "空" : filePath)+"，0无更新，1有更新:"+(updateType == null ? "空" : updateType)+"，0转码中，1转码成功，2转码失败:"+(transferResult == null ? "空" : transferResult)+"，持续时间:"+(duration == null ? "空" : duration);
	}
}
