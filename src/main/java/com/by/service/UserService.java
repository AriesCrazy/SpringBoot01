package com.by.service;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cn.smart.core.exception.BizException;
import com.by.config.EmailConfig;
import com.by.dao.UserDao;
import com.by.dto.LoginDTO;
import com.by.dto.UserDTO;
import com.by.dto.UserRegister;
import com.by.entity.Permissions;
import com.by.entity.Roles;
import com.by.entity.User;
import com.by.entity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private UserDao userDao;
    @Value("${cn.smart.tokenx.key}")
    private String jwtKey;

    //创建缓存，默认20秒过期
    TimedCache<String, Integer> timedCache = CacheUtil.newTimedCache(1000 * 60 / 3);

    public int sendCode(String email) {
        MailAccount account = new MailAccount();
        account.setHost(emailConfig.getHost());
        account.setPort(emailConfig.getPort());
        account.setAuth(emailConfig.isAuth());
        account.setFrom(emailConfig.getFrom());
        account.setUser(emailConfig.getUser());
        account.setPass(emailConfig.getPass());
        account.setSslEnable(emailConfig.isSslEnable());

        int code = RandomUtil.randomInt(1000, 10000);
        //缓存验证码
        timedCache.put(email, code);

        //String content = "验证码："+code;
        StringBuilder content = new StringBuilder();
        content.append("<table border=1>");
        content.append("<tr>");
        content.append("<td>");
        content.append(" 验证码：" + code);
        content.append("</td>");
        content.append("</tr>");
        content.append("</table>");

        MailUtil.send(account, CollUtil.newArrayList("3358248521@qq.com"), "验证码", content.toString(), true);
        return 0;
    }

    public int register(UserRegister userRegister) {
        //1.校验验证码
        Integer code = timedCache.get(userRegister.getEmail());
        if (code == null) {
            BizException.throwBizException(412, "请输入验证码");
        }
        if (!code.equals(userRegister.getCode())) {
            BizException.throwBizException(413, "验证码错误");
        }
        //2.校验手机号是否已经注册
        User user = userDao.check(userRegister.getTel());
        if (ObjectUtil.isNotEmpty(user)) {
            BizException.throwBizException(414, "手机号已经注册");
        }
        //3.插入用户信息
        user = new User();
        user.setTel(userRegister.getTel());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        user.setNickName(userRegister.getNickName());
        user.setLastUpdateBy("");
        return userDao.insert(user);
    }

    public Map login(LoginDTO loginDTO) {
        String tel = loginDTO.getTel();
        String password = loginDTO.getPassword();
        if (ObjectUtil.isEmpty(tel) || ObjectUtil.isEmpty(password)) {
            BizException.throwBizException(415, "手机号或密码不能为空");
        }
        if (!Validator.isMobile(tel)) {
            BizException.throwBizException(416, "手机号格式不正确");
        }
        User user = userDao.login(tel, password);
        if (ObjectUtil.isEmpty(user)) {
            BizException.throwBizException(417, "用户名或密码错误");
        }
        //生成token jwt 业务token
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", user.getId());
        map1.put("tel", user.getTel().substring(0, 3) + "****" + user.getTel().substring(7, 11));
        map1.put("nickName", user.getNickName());
        map1.put("roles", user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));//角色
        map1.put("permissions", user.getPermissions().stream().map(Permissions::getName).collect(Collectors.toList()));//权限
        //map.put("exp", System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 15);//过期时间15天
        map1.put("exp", System.currentTimeMillis() / 1000 + 60);
        String accessToken = JWTUtil.createToken(map1, jwtKey.getBytes());

        //生成token jwt 刷新token
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", user.getId());
        map2.put("exp", System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 7);
        String refreshToken = JWTUtil.createToken(map2, jwtKey.getBytes());

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);
        return map;
    }

    public List<User> select(UserQuery query) {
        return userDao.select(query);
    }

    public int update(UserDTO userDTO) {
        userDao.updateInfo(
                userDTO.getId(), userDTO.getNickName(),
                userDTO.getTel(), userDTO.getEmail(),
                userDTO.getPassword());
        List<Integer> roleids = userDTO.getRoleids();
        if (CollUtil.isNotEmpty(roleids)) {
            userDao.deleteUserRoles(userDTO.getId());
            userDao.insertUserRoles(userDTO.getId(), roleids);
        }
        return 1;
    }

    public int delete(Integer id) {
        userDao.delete1(id);
        return userDao.delete2(id);
    }

    public Map refreshToken(String refreshToken) {
        try {
            JWTValidator.of(refreshToken)
                    .validateAlgorithm(JWTSignerUtil.hs256(jwtKey.getBytes())) //验证第3部分的签名是否正确
                    .validateDate(DateUtil.date()); //验证第2部分的有效时间是否过期
        } catch (ValidateException e) {
            e.printStackTrace();
            BizException.throwBizException(407, "刷新token过期");
        } catch (Exception e) {
            e.printStackTrace();
            BizException.throwBizException(403, "刷新token不正确");
        }
        final JWT jwt = JWTUtil.parseToken(refreshToken);
        Map tokenMap = JSONUtil.toBean(jwt.getPayload().toString(), Map.class);
        Integer id = Integer.valueOf(tokenMap.get("id").toString());

        User user = userDao.loginById(id);
        if (ObjectUtil.isEmpty(user)) {
            BizException.throwBizException(417, "用户名或密码错误");
        }
        //生成token jwt 业务token
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", user.getId());
        map1.put("tel", user.getTel().substring(0, 3) + "****" + user.getTel().substring(7, 11));
        map1.put("nickName", user.getNickName());
        map1.put("roles", user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));//角色
        map1.put("permissions", user.getPermissions().stream().map(Permissions::getName).collect(Collectors.toList()));//权限
        //map.put("exp", System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 15);//过期时间15天
        map1.put("exp", System.currentTimeMillis() / 1000 + 60);
        String accessToken = JWTUtil.createToken(map1, jwtKey.getBytes());

        //生成token jwt 刷新token
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", user.getId());
        map2.put("exp", System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 7);
        String newRefreshToken = JWTUtil.createToken(map2, jwtKey.getBytes());

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", newRefreshToken);
        return map;
    }
}
