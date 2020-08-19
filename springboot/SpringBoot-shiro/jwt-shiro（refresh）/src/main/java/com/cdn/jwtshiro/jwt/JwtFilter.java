package com.cdn.jwtshiro.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cdn.jwtshiro.constant.CommonConstant;
import com.cdn.jwtshiro.constant.DefContants;
import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.service.ISysUserService;
import com.cdn.jwtshiro.utils.BeanUtils;
import com.cdn.jwtshiro.utils.JwtUtil;
import com.cdn.jwtshiro.utils.RedisUtil;
import com.cdn.jwtshiro.utils.oConvertUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Description: 鉴权登录拦截器
 * @Author: Scott
 * @Date: 2018/10/7
 **/
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {


	/**
	 * 执行登录认证
	 *
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		try {
			executeLogin(request, response);
			return true;
		} catch (Exception e) {
			throw new AuthenticationException("Token失效，请重新登录", e);
		}
	}

	/**
	 *
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(DefContants.X_ACCESS_TOKEN);
		if (token == null) {
			log.error("————————身份认证失败——————————IP地址:  ");
			throw new AuthenticationException("token为空!");
		}
		checkUserTokenIsEffect( token,request);

//		JwtToken jwtToken = new JwtToken(token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
//		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	/**
	 * 校验token的有效性
	 *
	 * @param token
	 */
	public void checkUserTokenIsEffect(String token,ServletRequest request) throws AuthenticationException {
		log.debug("———校验token是否有效————checkUserTokenIsEffect——————— " + token);
		// 解密获得username，用于和数据库进行对比
		String username = JwtUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token非法无效!");
		}

		// 校验token是否超时失效 & 或者账号密码是否错误
		if (!jwtTokenRefresh(token, username,request)) {
			throw new AuthenticationException("Token失效，请重新登录!");
		}

	}

	/**
	 * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
	 * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
	 * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
	 * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
	 * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
	 * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
	 * 用户过期时间 = Jwt有效时间 * 2。
	 *
	 * @param userName
	 * @param
	 * @return
	 */
	public boolean jwtTokenRefresh(String token, String userName,ServletRequest request) {
		RedisUtil redisUtil = BeanUtils.getBean(RedisUtil.class, request);
		String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
		if (oConvertUtils.isNotEmpty(cacheToken)) {
			// 校验token有效性
			if (!JwtUtil.verify(cacheToken, userName)) {
				String newAuthorization = JwtUtil.sign(userName);
				// 设置超时时间
				redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
				redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
				log.info("——————————用户在线操作，更新token保证不掉线—————————jwtTokenRefresh——————— " + token);
			}
			return true;
		}
		return false;
	}



	/**
	 * 对跨域提供支持
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
		// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			return false;
		}
		//update-begin-author:taoyan date:20200708 for:多租户用到
//		String tenant_id = httpServletRequest.getHeader(DefContants.TENANT_ID);
//		TenantContext.setTenant(tenant_id);
		//update-end-author:taoyan date:20200708 for:多租户用到
		return super.preHandle(request, response);
	}
}
