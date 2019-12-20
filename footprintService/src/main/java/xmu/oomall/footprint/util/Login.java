package xmu.oomall.footprint.util;

import javax.servlet.http.HttpServletRequest;

public class Login {
    public static Integer getUserId(HttpServletRequest request)
    {
        String userIdStr=request.getHeader("userId");
        if(userIdStr==null)
        {
            return null;
        }
        return Integer.valueOf(userIdStr);
    }
}
