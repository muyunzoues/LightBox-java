package com.easylive.web.controller;

import com.easylive.annotation.GlobalInterceptor;
import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadFileDto;
import com.easylive.entity.dto.VideoPlayInfoDto;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.impl.VideoInfoFileServiceImpl;
import com.easylive.utils.DateUtil;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class FileController extends ABaseController{
    @Resource
    private AppConfig appConfig;

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private FFmpegUtils fFmpegUtils;

    @Resource
    private VideoInfoFileServiceImpl videoInfoFileService;



    @RequestMapping("/getResource")
    public void getResource(HttpServletResponse response, @NotNull String sourceName) {
        if (!StringTools.pathIsOk(sourceName)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        String suffix = StringTools.getFileSuffix(sourceName);

        response.setContentType("image/" + suffix.replace(".", ""));
        response.setHeader("Cache-Control", "max-age=2592000");
        readFile(response, sourceName);
    }


    protected void readFile(HttpServletResponse response,String filePath){
        File file = new File(appConfig.getProjectFolder()+Constants.FILE_FOLDER+filePath);
        if(!file.exists()){
            return;
        }
        try (OutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(file)){
            byte[] byteData = new byte[1024];
            int len = 0;
            while ((len = in.read(byteData)) != -1) {
                out.write(byteData, 0, len);
            }
            out.flush();
        }catch (Exception e){
            log.error("读取文件异常",e);
        }
    }

    @RequestMapping("/preUploadVideo")
    public ResponseVO preUploadVideo(@NotEmpty String fileName, @NotNull Integer chunks) {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        String uploadId= redisComponent.savePreVideoFileInfo(tokenUserInfoDto.getUserId(),fileName,chunks);
        return getSuccessResponseVO(uploadId);

    }

    @RequestMapping("/uploadVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO uploadVideo(@NotNull MultipartFile chunkFile, @NotNull Integer chunkIndex,@NotEmpty String uploadId) throws IOException {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UploadFileDto fileDto=redisComponent.getUploadVideoFile(tokenUserInfoDto.getUserId(),uploadId);
        if(fileDto==null){
            throw new BusinessException("文件不存在请重新上传");
        }
        SysSettingDto sysSettingDto= redisComponent.getSysSettingDto();
        if(fileDto.getFileSize()>sysSettingDto.getVideoSize()*Constants.MB_SIZE){
            throw new BusinessException("文件超过限制");
        }
        //判断分片
        if((chunkIndex-1)>fileDto.getChunkIndex() || chunkIndex>fileDto.getChunks()-1){
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        String folder= appConfig.getProjectFolder()+Constants.FILE_FOLDER+Constants.FILE_FOLDER_TEMP+fileDto.getFilePath();
        File targetFile=new File(folder+"/"+chunkIndex);
        chunkFile.transferTo(targetFile);
        fileDto.setChunkIndex(chunkIndex);
        fileDto.setFileSize(fileDto.getFileSize()+chunkFile.getSize());
        redisComponent.updateVideoFileInfo(tokenUserInfoDto.getUserId(),fileDto);
        return getSuccessResponseVO(null);

    }


    @RequestMapping("/delUploadVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delUploadVideo(@NotEmpty String uploadId) throws IOException {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto();
        UploadFileDto fileDto=redisComponent.getUploadVideoFile(tokenUserInfoDto.getUserId(),uploadId);
        if(fileDto==null){
            throw new BusinessException("文件不存在请重新上传");
        }
       redisComponent.delVideoFileInfo(tokenUserInfoDto.getUserId(),uploadId);
        FileUtils.deleteDirectory(new File(appConfig.getProjectFolder()+Constants.FILE_FOLDER+Constants.FILE_FOLDER_TEMP+fileDto.getFilePath()));
        return getSuccessResponseVO(uploadId);
    }


    @RequestMapping("/uploadImage")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO uploadImage(@NotNull MultipartFile file,@NotNull Boolean createThumbnail) throws IOException {
        String day = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMMDD.getPattern());
        String folder= appConfig.getProjectFolder()+ Constants.FILE_FOLDER+Constants.FILE_COVER+day;
        File folderFile=new File(folder);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String fileSuffix = StringTools.getFileSuffix(fileName);
        String realFileName = StringTools.getRandomString(Constants.LENGTH_30)+fileSuffix;
        String filePath = folder+"/"+realFileName;
        file.transferTo(new File(filePath));
        if(createThumbnail){
            //生产缩略图
            fFmpegUtils.createImageThumbnail(filePath);
        }
        return getSuccessResponseVO(Constants.FILE_COVER+day+"/"+realFileName);
    }

    @RequestMapping("/videoResource/{fileId}")
    public void videoResource(HttpServletResponse response,@PathVariable @NotEmpty String fileId) {
        VideoInfoFile videoInfoFile= videoInfoFileService.getVideoInfoFileByFileId(fileId);
        String filePath= videoInfoFile.getFilePath();
        readFile(response,filePath+"/"+Constants.M3U8_NAME);
        //TODO 更新视频阅读信息
        VideoPlayInfoDto videoPlayInfoDto= new VideoPlayInfoDto();
        videoPlayInfoDto.setVideoId(videoInfoFile.getVideoId());
        videoPlayInfoDto.setFileIndex(videoInfoFile.getFileIndex());

        TokenUserInfoDto tokenUserInfoDto=getTokenInfoFromCookie();
        if(tokenUserInfoDto !=null){
            videoPlayInfoDto.setUserId(tokenUserInfoDto.getUserId());
        }
        redisComponent.addVideoPlay(videoPlayInfoDto);
    }

    @RequestMapping("/videoResource/{fileId}/{ts}")
    public void videoResourceTs(HttpServletResponse response,@PathVariable @NotEmpty String fileId,@PathVariable @NotEmpty String ts) {
        VideoInfoFile videoInfoFile= videoInfoFileService.getVideoInfoFileByFileId(fileId);
        String filePath= videoInfoFile.getFilePath();
        readFile(response,filePath+"/"+ts);
    }





}
