package kwy.taxi.allocation.security;

import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAuthInterceptor implements HandlerInterceptor {


    private final MyAuthTokenManager myAuthTokenManager;

    public MyAuthInterceptor(MyAuthTokenManager myAuthTokenManager) {
        this.myAuthTokenManager = myAuthTokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!containAuthHeader(request)) throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        String auth = request.getHeader("Authorization");
        boolean result = myAuthTokenManager.validateToken(auth);
        String[] data = myAuthTokenManager.parseIdUserTypeFromToken(auth);
        if(!myAuthTokenManager.validateUserId(Integer.parseInt(data[0]))) throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        request.setAttribute("id", data[0]);
        request.setAttribute("userType", data[1]);
        return result;
    }

    private boolean containAuthHeader(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("Authorization") != null;
    }
}
