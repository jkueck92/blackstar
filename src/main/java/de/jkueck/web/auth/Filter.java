package de.jkueck.web.auth;

import de.jkueck.PermissionDto;
import de.jkueck.RoleDto;
import de.jkueck.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class Filter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean check(String servletPath, String page, boolean hasPermission) {
        if (servletPath.contains(page) && hasPermission) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private boolean checkPermission(String permissionName, RoleDto role) {
        for (PermissionDto permission : role.getPermissions()) {
            if (permission.getName().equals(permissionName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
