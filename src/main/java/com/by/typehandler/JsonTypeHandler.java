package com.by.typehandler;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义MyBatis类型处理器，用于处理JSON格式数据与Java对象之间的转换
 * 它继承自MyBatis的BaseTypeHandler，实现了Java对象和数据库JSON字段之间的相互转换
 *
 * @param <T> 泛型参数，表示可以处理的Java对象类型
 */
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    static ObjectMapper objectMapper=new ObjectMapper();

    private JavaType javaType;

    static {
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),ObjectMapper.DefaultTyping.NON_FINAL);
    }


    public JsonTypeHandler(Class<T> clazz) {
        this.javaType = TypeFactory.defaultInstance().constructType(clazz);
    }

    @Override //java对象属性 -> mysql字段
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T paramEter, JdbcType jdbcType) throws SQLException {
        // 使用ObjectMapper将参数对象转换为JSON字符串
        // 这里包含了全类名，以便在反序列化时能够准确地还原对象类型
        String json = objectMapper.writeValueAsString(paramEter);

        // 将转换后的JSON字符串设置到PreparedStatement中
        // 参数i表示参数的索引，从1开始计数
        preparedStatement.setString(i, json);
    }

    @Override //mysql字段值 -> java对象属性值
    @SneakyThrows
    public T getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        if (resultSet.getString(columnName)==null){
            return null;
        }
        // 使用ObjectMapper将字符串形式的列值转换为指定的Java对象类型
        return objectMapper.readValue(resultSet.getString(columnName), this.javaType);
    }


    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }


    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
