package com.by.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.smart.core.model.UserSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//禁用CSRF保护
        http.sessionManagement().disable();//禁用Session管理
        http.formLogin().disable();//禁用表单登录
        http.logout().disable();//禁用注销功能
        http.authorizeRequests()
                .antMatchers( "/api/user/**").permitAll() //允许访问这些路径
                .anyRequest().authenticated(); //其他请求需要认证

        // 在UsernamePasswordAuthenticationFilter之前添加添加JWT认证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    private class JwtAuthenticationFilter extends BasicAuthenticationFilter {
        public JwtAuthenticationFilter() throws Exception {
            super(authenticationManager());
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            //super.doFilterInternal(request, response, chain);

            String token = request.getHeader("token");

            if (ObjectUtil.isNotEmpty(token)){
                final JWT jwt = JWTUtil.parseToken(token);//解析JWT令牌
                UserSession userSession = JSONUtil.toBean(jwt.getPayload().toString(), UserSession.class);

                List<GrantedAuthority> authorities = new ArrayList<>();

                List<String> roles = userSession.getRoles();
                roles.forEach(role->{
                    authorities.add(new SimpleGrantedAuthority(role));
                });
                List<String> permissions = userSession.getPermissions();
                permissions.forEach(permission->{
                    authorities.add(new SimpleGrantedAuthority(permission));
                });

                //把角色和权限添加到权限列表中
                UsernamePasswordAuthenticationToken toke=new UsernamePasswordAuthenticationToken(userSession.getId(),"", authorities);
                //把用户的这个我们自己设置的token 放到Security上下文
                SecurityContextHolder.getContext().setAuthentication(toke);
            }

            chain.doFilter(request, response);//继续过滤链
        }
    }

}
