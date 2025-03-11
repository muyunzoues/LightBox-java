package com.easylive.admin.controller;

import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
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
import java.util.HashMap;
import java.util.Map;

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

	@Resource
	private AppConfig appConfig;

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


	@RequestMapping("/login")
	public ResponseVO login(HttpServletRequest request,
							HttpServletResponse response,
							@NotEmpty String account,
							@NotEmpty String password,
							@NotEmpty String checkCodeKey,
							@NotEmpty String checkCode) {

		try {
			if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
				throw new BusinessException("图片验证码不正确");
			}
			if(!account.equals(appConfig.getAdminAccount()) || !password.equals(appConfig.getAdminPassword())){
				throw new BusinessException("账号或密码错误");
			}
			String token= redisComponent.saveTokenForAdmin(account);
			saveTokenCookie(response, token);
			return getSuccessResponseVO(account);
		}finally {
			//将验证码删除，更安全
			redisComponent.cleanCheckCode(checkCodeKey);
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String token = null;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(Constants.TOKEN_Admin)) {
						token = cookie.getValue();
					}
				}
				if (!StringTools.isEmpty(token)) {
					redisComponent.cleanToken4Admin(token);
				}
			}

		}
	}



	@RequestMapping("/logout")
	public ResponseVO logout(HttpServletResponse response) {
		cleanCookie(response);
		return getSuccessResponseVO(null);
	}


}