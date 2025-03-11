package com.easylive.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.CountInfoDto;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UserCountInfoDto;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.UserSexEnum;
import com.easylive.entity.enums.UserStatusEnum;
import com.easylive.entity.po.UserFocus;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.UserFocusQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.UserFocusMapper;
import com.easylive.mappers.VideoInfoMapper;
import com.easylive.utils.CopyTools;
import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.UserInfoMapper;
import com.easylive.service.UserInfoService;
import com.easylive.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户信息 业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	@Resource
	private RedisComponent redisComponent;

	@Resource
	private UserFocusMapper<UserFocus, UserFocusQuery>userFocusMapper;

	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery>videoInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserInfo> findListByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserInfo> list = this.findListByParam(param);
		PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserInfo bean) {
		return this.userInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserInfo bean, UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserInfo getUserInfoByUserId(String userId) {
		return this.userInfoMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoByUserId(UserInfo bean, String userId) {
		return this.userInfoMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoByUserId(String userId) {
		return this.userInfoMapper.deleteByUserId(userId);
	}

	/**
	 * 根据Email获取对象
	 */
	@Override
	public UserInfo getUserInfoByEmail(String email) {
		return this.userInfoMapper.selectByEmail(email);
	}

	/**
	 * 根据Email修改
	 */
	@Override
	public Integer updateUserInfoByEmail(UserInfo bean, String email) {
		return this.userInfoMapper.updateByEmail(bean, email);
	}

	/**
	 * 根据Email删除
	 */
	@Override
	public Integer deleteUserInfoByEmail(String email) {
		return this.userInfoMapper.deleteByEmail(email);
	}

	/**
	 * 根据NickName获取对象
	 */
	@Override
	public UserInfo getUserInfoByNickName(String nickName) {
		return this.userInfoMapper.selectByNickName(nickName);
	}

	/**
	 * 根据NickName修改
	 */
	@Override
	public Integer updateUserInfoByNickName(UserInfo bean, String nickName) {
		return this.userInfoMapper.updateByNickName(bean, nickName);
	}

	/**
	 * 根据NickName删除
	 */
	@Override
	public Integer deleteUserInfoByNickName(String nickName) {
		return this.userInfoMapper.deleteByNickName(nickName);
	}

	@Override
	public void register(String email, String registerPassword, String nickName) {
		UserInfo userInfo= this.userInfoMapper.selectByEmail(email);
		if(userInfo!=null){
			throw new BusinessException("邮箱账号已存在");
		}
		UserInfo NickNameUser= this.userInfoMapper.selectByNickName(nickName);
		if(NickNameUser!=null){
			throw new BusinessException("昵称已存在");
		}
		userInfo= new UserInfo();
		String userId= StringTools.getRandomNumber(Constants.LENGTH_10);
		userInfo.setUserId(userId);
		userInfo.setNickName(nickName);
		userInfo.setEmail(email);
		userInfo.setPassword(StringTools.encodeByMd5(registerPassword));
		userInfo.setJoinTime(new Date());
		userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
		userInfo.setSex(UserSexEnum.SECRECY.getType());
		userInfo.setTheme(Constants.ONE);
		//TODO 初始化用户硬币
		SysSettingDto sysSettingDto=redisComponent.getSysSettingDto();
		userInfo.setCurrentCoinCount(sysSettingDto.getRegisterCoinCount());
		userInfo.setTotalCoinCount(sysSettingDto.getRegisterCoinCount());
		this.userInfoMapper.insert(userInfo);
	}

	@Override
	public TokenUserInfoDto login(String email, String password, String ip) {
		UserInfo userInfo= this.userInfoMapper.selectByEmail(email);
		if(userInfo==null){
			throw new BusinessException("账号不存在");
		}
		if(!userInfo.getPassword().equals(StringTools.encodeByMd5(password))){
			throw new BusinessException("密码错误");
		}
		if(UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())){
			throw new BusinessException("账号已禁用");
		}
		UserInfo updateInfo= new UserInfo();
		updateInfo.setLastLoginTime(new Date());
		userInfo.setLastLoginIp(ip);
		this.userInfoMapper.updateByUserId(updateInfo,userInfo.getUserId());
		TokenUserInfoDto tokenUserInfoDto= CopyTools.copy(userInfo,TokenUserInfoDto.class);
		redisComponent.saveTokenInfo(tokenUserInfoDto);
		return tokenUserInfoDto;

	}

	@Override
	public UserInfo getUserDetailInfo(String currentUserId, String userId) {
		UserInfo userInfo=getUserInfoByUserId(userId);
		if(null==userInfo){
			throw new BusinessException(ResponseCodeEnum.CODE_404);
		}
		// 获赞数，播放量
		CountInfoDto countInfoDto=videoInfoMapper.selectSumCountInfo(userId);
		CopyTools.copyProperties(countInfoDto,userInfo);

		Integer fansCount= userFocusMapper.selectFansCount(userId);
		Integer focusCount= userFocusMapper.selectFocusCount(userId);
		userInfo.setFansCount(fansCount);
		userInfo.setFocusCount(focusCount);

		if(currentUserId == null){
			userInfo.setHavaFocus(false);
		}
		else{
			UserFocus userFocus=userFocusMapper.selectByUserIdAndFocusUserId(currentUserId,userId);
			userInfo.setHavaFocus(userFocus== null? false:true);
		}
		return userInfo;
	}

	@Override
	@Transactional
	public void updateUserInfo(UserInfo userInfo, TokenUserInfoDto tokenUserInfoDto) {
		UserInfo dbInfo=this.userInfoMapper.selectByUserId(userInfo.getUserId());
		if(!dbInfo.getNickName().equals(userInfo.getNickName()) &&dbInfo.getCurrentCoinCount()<Constants.UPDATE_NICK_NAME_COIN){
			throw new BusinessException("硬币不足，无法修改昵称");
		}
		if(!dbInfo.getNickName().equals(userInfo.getNickName())){
			Integer count=this.userInfoMapper.updateCoinCountInfo(userInfo.getUserId(),-Constants.UPDATE_NICK_NAME_COIN);
			if(count==0){
				throw new BusinessException("硬币不足，无法修改昵称");
			}
		}
		this.userInfoMapper.updateByUserId(userInfo, userInfo.getUserId());
		Boolean updateTokenInfo= false;
		if(!userInfo.getAvatar().equals(tokenUserInfoDto.getAvatar())){
			tokenUserInfoDto.setAvatar(userInfo.getAvatar());
			updateTokenInfo=true;
		}
		if(!tokenUserInfoDto.getNickName().equals(userInfo.getNickName())){
			tokenUserInfoDto.setNickName(userInfo.getNickName());
			updateTokenInfo=true;
		}
		if(updateTokenInfo){
			redisComponent.updateTokenInfo(tokenUserInfoDto);
		}
	}

	@Override
	public UserCountInfoDto getUserCountInfo(String userId) {
		UserInfo userInfo=getUserInfoByUserId(userId);
		Integer fansCount=userFocusMapper.selectFansCount(userId);
		Integer focusCount=userFocusMapper.selectFocusCount(userId);
		UserCountInfoDto countInfoDto=new UserCountInfoDto();
		countInfoDto.setFansCount(fansCount);
		countInfoDto.setFocusCount(focusCount);
		countInfoDto.setCurrentCoinCount(userInfo.getCurrentCoinCount());
		return countInfoDto;
	}

	@Override
	public void changeUserStatus(String userId, Integer status) {
		UserInfo userInfo=new UserInfo();
		userInfo.setStatus(status);
		this.userInfoMapper.updateByUserId(userInfo,userId);
	}
}