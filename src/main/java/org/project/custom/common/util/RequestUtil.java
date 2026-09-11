package org.project.custom.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public final class RequestUtil {

    private RequestUtil() {
        throw new AssertionError("requestUtil not instance!!");
    }

    public static String clientIp( HttpServletRequest request ) {
        String xff = request.getHeader("X-Forwarded-For");
        if( StringUtils.hasText(xff) ){
            return xff.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    public static String contextUrl(HttpServletRequest request, String path) {
        String contextPath = request.getContextPath();
        String safePath = StringCheck.nullToDefault(path, "");
        return StringCheck.isEmpty(contextPath) ? safePath : contextPath + safePath;
    }
}
