package com.by.interceptors;

import cn.hutool.core.util.ObjectUtil;
import cn.smart.core.util.UserContext;
import com.by.entity.BaseEntity;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
@Intercepts(@Signature(type = ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class}))
//1.实现Interceptor
//2.指定拦截类型 以及方法和参数 @Intercepts(@Signature(
public class ParameterInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        Object parameterObject = parameterHandler.getParameterObject();
        //第一种方案,使用反射 缺点:慢,性能低
        /*Field lastUpdatebyField = ReflectUtil.getField(parameterObject.getClass(), "lastUpdateby");
        if(ObjUtil.isNotEmpty(lastUpdatebyField)){
            UserSession userSession = UserContext.get();
            ReflectUtil.setFieldValue(parameterObject, lastUpdatebyField, userSession.getNickName());
        }*/
        //第二种方案,性能高
        if (parameterObject instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) parameterObject;
            if (ObjectUtil.isNotEmpty(UserContext.get())){
                baseEntity.setLastUpdateBy(UserContext.get().getNickName());
            }
        }
        return invocation.proceed();//继续执行
    }
}
