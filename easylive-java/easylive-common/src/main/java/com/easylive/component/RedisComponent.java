package com.easylive.component;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadFileDto;
import com.easylive.entity.dto.VideoPlayInfoDto;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.redis.RedisUtils;
import com.easylive.utils.DateUtil;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;;

    @Resource
    private AppConfig appConfig;

    public String saveCheckCode(String checkCode) {
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey,checkCode,Constants.REDIS_KEY_EXPIRES_ONE_MIN*10);
        return checkCodeKey;
    }

    public String getCheckCode(String checkCodeKey) {
        return (String)redisUtils.get(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    public void cleanCheckCode(String checkCodeKey) {
        redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    public void saveTokenInfo(TokenUserInfoDto tokenUserInfoDto) {
        String token = UUID.randomUUID().toString();
        tokenUserInfoDto.setExpireAt(System.currentTimeMillis()+Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
        tokenUserInfoDto.setToken(token);
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_WEB+token,tokenUserInfoDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }
    public void updateTokenInfo(TokenUserInfoDto tokenUserInfoDto) {
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_WEB+tokenUserInfoDto.getToken(),tokenUserInfoDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }

    public void cleanToken(String token) {
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_WEB+token);
    }

    public TokenUserInfoDto getTokenInfo(String token) {
        return (TokenUserInfoDto) redisUtils.get((Constants.REDIS_KEY_TOKEN_WEB+token));
    }

    public String saveTokenForAdmin(String account) {
        String token = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_ADMIN+token,account,Constants.REDIS_KEY_EXPIRES_ONE_DAY);
        return token;
    }
    public String gatTokenForAdmin(String token) {
        return (String) redisUtils.get(Constants.REDIS_KEY_TOKEN_ADMIN+token);
    }
    public void cleanToken4Admin(String token) {
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_ADMIN+token);
    }

    public void saveCategoryList(List<CategoryInfo> categoryList) {
        redisUtils.set(Constants.REDIS_KEY_CATEGORY_LIST,categoryList);
    }
    public List<CategoryInfo> getCategoryList() {
        return (List<CategoryInfo>) redisUtils.get(Constants.REDIS_KEY_CATEGORY_LIST);
    }

    public String savePreVideoFileInfo(String userId,String fileName,Integer chunks){
        String uploadId= StringTools.getRandomString(Constants.LENGTH_15);
        UploadFileDto fileDto= new UploadFileDto();
        fileDto.setChunks(chunks);
        fileDto.setFileName(fileName);
        fileDto.setUploadId(uploadId);
        fileDto.setChunkIndex(0);
        String day= DateUtil.format(new Date(), DateTimePatternEnum.YYYYMMDD.getPattern());
        String filePath= day+"/"+userId+"/"+uploadId;
        String fileFolder=appConfig.getProjectFolder()+Constants.FILE_FOLDER+Constants.FILE_FOLDER_TEMP+filePath;
        File folderFile= new File(fileFolder);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        fileDto.setFilePath(filePath);
        redisUtils.setex(Constants.REDIS_KEY_UPLOADING_FILE+userId+uploadId,fileDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY);
        return uploadId;
    }

    public UploadFileDto getUploadVideoFile(String userId,String uploadId){
        return (UploadFileDto) redisUtils.get(Constants.REDIS_KEY_UPLOADING_FILE+userId+uploadId);

    }

    public SysSettingDto getSysSettingDto(){
        SysSettingDto sysSettingDto = (SysSettingDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        if(sysSettingDto==null){
            sysSettingDto=new SysSettingDto();
        }
        return sysSettingDto;
    }

    public void updateVideoFileInfo(String userId,UploadFileDto fileDto){
        redisUtils.setex(Constants.REDIS_KEY_UPLOADING_FILE+userId+fileDto.getUploadId(),fileDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY);
    }

    public void delVideoFileInfo(String userId,String uploadId){
        redisUtils.delete(Constants.REDIS_KEY_UPLOADING_FILE+userId+uploadId);
    }

    public void addFile2DelQueue(String videoId, List<String> filePathList){
        redisUtils.lpushAll(Constants.REDIS_KEY_FILE_DEL+videoId,filePathList,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }

    public List<String> getDelFileList(String videoId){
        return redisUtils.getQueueList(Constants.REDIS_KEY_FILE_DEL+videoId);
    }

    public void cleanDelFileList(String videoId){
        redisUtils.delete(Constants.REDIS_KEY_FILE_DEL+videoId);
    }
    public void addFile2TransferQueue(List<VideoInfoFilePost> addFileList){
        redisUtils.lpushAll(Constants.REDIS_KEY_QUEUE_TRANSFER,addFileList,0);
    }

    public void addFile2TransferQueue4Sigle(VideoInfoFilePost videoInfoFilePost){
        redisUtils.lpush(Constants.REDIS_KEY_QUEUE_TRANSFER,videoInfoFilePost,0L);
    }

    public VideoInfoFilePost getFileFromTransferQueue(){
        return (VideoInfoFilePost) redisUtils.rpop(Constants.REDIS_KEY_QUEUE_TRANSFER);
    }


    public Object reportVideoPlayOnline(String fileId, String deviceId) {
        String userPlayOnlineKey= String.format(Constants.REDIS_KEY_VIDEO_PLAY_COUNT_USER,fileId,deviceId);
        String playOnlineCountKey=String.format(Constants.REDIS_KEY_VIDEO_PLAY_COUNT_ONLINE,fileId);
        if(!redisUtils.keyExists(userPlayOnlineKey)){
            redisUtils.setex(userPlayOnlineKey,fileId,Constants.REDIS_KEY_EXPIRES_ONE_SECONDS*8);
            return redisUtils.incrementex(playOnlineCountKey,Constants.REDIS_KEY_EXPIRES_ONE_SECONDS*10).intValue();
        }
        redisUtils.expire(playOnlineCountKey,Constants.REDIS_KEY_EXPIRES_ONE_SECONDS*10);
        redisUtils.expire(userPlayOnlineKey,Constants.REDIS_KEY_EXPIRES_ONE_SECONDS*8);
        Integer count= (Integer) redisUtils.get(playOnlineCountKey);
        return count==null?1:count;
    }

    public void decrementPlayOnline(String key){
        redisUtils.decrement(key);
    }

    public void addKeywordCount(String keyword){
        redisUtils.zaddCount(Constants.REDIS_KEY_VIDEO_SEARCH_COUNT,keyword);
    }

    public List<String> getKeywordTop(Integer top){
        return redisUtils.getZSetList(Constants.REDIS_KEY_VIDEO_SEARCH_COUNT,top-1);
    }

    public void addVideoPlay(VideoPlayInfoDto videoPlayInfoDto){
        redisUtils.lpush(Constants.REDIS_KEY_QUEUE_VIDEO_PLAY,videoPlayInfoDto,null);
    }

    public VideoPlayInfoDto getVideoPlayFromVideoPlayQueue(){
        return (VideoPlayInfoDto) redisUtils.rpop(Constants.REDIS_KEY_QUEUE_VIDEO_PLAY);
    }

    public void recordVideoPlayCount(String videoId){
        String date=DateUtil.format(new Date(),DateTimePatternEnum.YYYY_MM_DD.getPattern());
        redisUtils.incrementex(Constants.REDIS_KEY_VIDEO_PLAY_COUNT+date+":"+videoId,Constants.REDIS_KEY_EXPIRES_ONE_DAY*2L);
    }

    public Map<String,Integer> getVideoPlayCount(String date){
        Map<String,Integer> videoPlayMap=redisUtils.getBatch(Constants.REDIS_KEY_VIDEO_PLAY_COUNT+date);
        return videoPlayMap;
    }
}
