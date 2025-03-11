package com.easylive.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easylive.component.RedisComponent;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserInfoService;
import com.easylive.utils.StringTools;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

/**
 * 用户信息 Controller
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController extends ABaseController{

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private RedisComponent redisComponent;

	@RequestMapping("/checkCode")
	public ResponseVO checkCode() {
		try {
			// 初始化 ArithmeticCaptcha（指定宽高）
			ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(100, 42);
			// 获取验证码的文本值（捕获异常）
			String code = arithmeticCaptcha.text();
			if (code == null || code.isEmpty()) {
				throw new RuntimeException("Captcha generation failed: Code is empty.");
			}
			// 将验证码存储到 Redis 并生成唯一 key
			String checkCodeKey = redisComponent.saveCheckCode(code);
			// 将验证码转换为 Base64 格式
			String checkCodeBase64 = arithmeticCaptcha.toBase64();
			// 构造返回结果
			Map<String, String> result = new HashMap<>();
			result.put("checkCode", checkCodeBase64);
			result.put("checkCodeKey", checkCodeKey);
			// 返回成功响应
			return getSuccessResponseVO(result);
		} catch (Exception e) {
			// 记录日志并返回错误响应
			log.error("Error generating captcha: ", e);
			return getServerErrorResponseVO("验证码生成失败，请稍后重试");
		}
	}

	@RequestMapping("/register")
	public ResponseVO register(@NotNull @Email @Size(max = 150) String email,
							   @NotEmpty @Pattern(regexp = Constants.REGEX_PASSWORD) String registerPassword,
							   @NotEmpty @Size(max = 20) String nickName,
							   @NotEmpty String checkCodeKey,
							   @NotEmpty String checkCode) {

		try {
			if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
				throw new BusinessException("图片验证码不正确");
			}
			userInfoService.register(email, registerPassword, nickName);
			return getSuccessResponseVO(null);
		}finally {
			//将验证码删除，更安全
			redisComponent.cleanCheckCode(checkCodeKey);
		}
	}

	@RequestMapping("/login")
	public ResponseVO login(HttpServletRequest request,HttpServletResponse response,
							@NotEmpty @Email String email,
							@NotEmpty @Pattern(regexp = Constants.REGEX_PASSWORD) String password,
							@NotEmpty String checkCodeKey,
							@NotEmpty String checkCode) {

		try {
			if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
				throw new BusinessException("图片验证码不正确");
			}
			String ip= getIpAddr();
			TokenUserInfoDto tokenUserInfoDto= userInfoService.login(email, password, ip);
			saveTokenCookie(response,tokenUserInfoDto.getToken());
			return getSuccessResponseVO(tokenUserInfoDto);
		}finally {
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				String token = null;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(Constants.TOKEN_WEB)) {
						token = cookie.getValue();
					}
				}
				if(!StringTools.isEmpty(token)){
					redisComponent.cleanToken(token);
				}
			}
			//将验证码删除，更安全
			redisComponent.cleanCheckCode(checkCodeKey);
		}
	}

	@RequestMapping("/autoLogin")
	public ResponseVO autoLogin(HttpServletResponse response) {
		TokenUserInfoDto tokenUserInfoDto= getTokenUserInfoDto();
		if(tokenUserInfoDto== null){
			return getSuccessResponseVO(null);
		}
		if(tokenUserInfoDto.getExpireAt()-System.currentTimeMillis() < Constants.REDIS_KEY_EXPIRES_ONE_DAY){
			redisComponent.saveTokenInfo(tokenUserInfoDto);
			saveTokenCookie(response,tokenUserInfoDto.getToken());
		}
		saveTokenCookie(response,tokenUserInfoDto.getToken());
		return getSuccessResponseVO(tokenUserInfoDto);
	}

	@RequestMapping("/logout")
	public ResponseVO logout(HttpServletResponse response) {
		cleanCookie(response);
		return getSuccessResponseVO(null);
	}
	@RequestMapping("/getUserCountInfo")
	public ResponseVO getUserCountInfo() {
		return getSuccessResponseVO(null);
	}

}