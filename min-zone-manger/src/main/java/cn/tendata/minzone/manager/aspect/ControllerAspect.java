package cn.tendata.minzone.manager.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class ControllerAspect {
   
	@Before("execution(* cn.tendata.minzone.manager.controllers.*Controller.*(..))")
	public void logAccess() throws Throwable{
		//System.out.println("complete:"+joinPoint);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())  
                .getRequest(); 
		String remoteAddr = getIpAddress(request);  
        String userAgent = request.getHeader("user-agent");  
        String requestUri = request.getRequestURI();  
        String method = request.getMethod(); 
        Map<String, String[]> paramMap = request.getParameterMap();  
        StringBuffer params = new StringBuffer();  
        if (paramMap != null && !paramMap.isEmpty()) {  
            for (Map.Entry<String, String[]> param : paramMap.entrySet()) {  
                if ("".equals(params.toString())) {  
                    params.append(param.getKey() + "=");  
                } else {  
                    params.append("&" + param.getKey() + "=");  
                }  
  
                String paramValue = "";  
                if (param.getValue() != null && param.getValue().length > 0) {  
                    paramValue = param.getValue()[0];  
                }  
  
                // 屏蔽密码获取  
                if (!"password".equals(param.getKey())) {  
                    params.append(paramValue);  
                }  
            }
        }
        System.out.println("ip address:" + remoteAddr);  
        System.out.println("useragent:" + userAgent);  
        System.out.println("url:" + requestUri);  
        System.out.println("request method:" + method);  
        System.out.println("method params:" + params.toString());  
        System.out.println("---------------------------------");  
     
	}
	
	public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个ip值,第一个ip才是真实ip  
            int index = ip.indexOf(",");  
            if (index != -1) {  
                return ip.substring(0, index);  
            } else {  
                return ip;  
            }  
        }  
        ip = request.getHeader("X-Real-IP");  
        if (StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {  
            return ip;  
        }  
        return request.getRemoteAddr();  
    }  
}
