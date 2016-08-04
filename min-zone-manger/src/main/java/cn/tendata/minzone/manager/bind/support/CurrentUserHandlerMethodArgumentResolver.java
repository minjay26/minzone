package cn.tendata.minzone.manager.bind.support;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.service.UserService;

public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	     return parameter.getParameterAnnotation(CurrentUser.class)!=null&&parameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(parameter)) {
			Principal principal=webRequest.getUserPrincipal();
			User user=(User) ((Authentication)principal).getPrincipal();
			if (user!=null) {
				return userService.findById(user.getuId());
			}
		}
		return WebArgumentResolver.UNRESOLVED;
	}

}
