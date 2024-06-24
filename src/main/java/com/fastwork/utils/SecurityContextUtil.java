package com.fastwork.utils;

import com.fastwork.configs.CustomAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {
    public static Long getCurrentUserId() {
        CustomAuthentication authentication = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getId();
    }
}
