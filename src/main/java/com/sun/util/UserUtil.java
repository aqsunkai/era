package com.sun.util;

import com.sun.permission.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by sun on 2017-4-9.
 */
public class UserUtil {

    /**
     * 获得当前用户
     * @return
     */
    public static User getCurrentUser(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (User) session.getAttribute("currentUser");
    }

    /**
     * 获得当前用户ID
     * @return
     */
    public static Integer getCurrentUserId(){
        return getCurrentUser().getId();
    }

}
