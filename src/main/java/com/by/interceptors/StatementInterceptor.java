package com.by.interceptors;

import cn.hutool.core.util.ObjectUtil;
import cn.smart.core.util.UserContext;
import com.by.config.MonitorTableConfig;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Intercepts(@Signature(type = StatementHandler.class,
        method = "update",
        args = {Statement.class}))
//1.实现Interceptor
//2.指定拦截类型 以及方法和参数 @Intercepts(@Signature(
@Slf4j
public class StatementInterceptor implements Interceptor {

    //要监控的表
    //private static final List<String> MONITOR_TABLE = CollUtil.newArrayList("product");
    //要监控的表中的字段
    //private static final Map<String, List<String>> MONITOR_MAP = new HashMap<>();

    /*static {
        MONITOR_MAP.put("product", CollUtil.newArrayList("name","brief"));
    }*/

    @Autowired
    private MonitorTableConfig monitorTableConfig;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        net.sf.jsqlparser.statement.Statement parse = CCJSqlParserUtil.parse(sql);
        if (parse instanceof Insert) {
            insertHandler((Insert) parse);
        } else if (parse instanceof Update) {
            updateHandler((Update) parse);
        } else {
            deleteHandler((Delete) parse);
        }

        return invocation.proceed();//继续执行
    }

    private void updateHandler(Update update) {
        Table table = update.getTable();

        Map<String, List<String>> tableMap = monitorTableConfig.getTables();
        Set<String> tables = tableMap.keySet();


        if (tables.contains(table.getName())) {
            List<String> columnList = tableMap.get(table.getName());
            List<UpdateSet> updateSets = update.getUpdateSets();
            for (UpdateSet updateSet : updateSets) {
                String columnName = updateSet.getColumns().get(0).getColumnName();
                if (columnList.contains(columnName) && ObjectUtil.isNotEmpty(UserContext.get())) {
                    log.info("{}对表:{}中的字段:{}进行了修改操作", UserContext.get().getNickName(), table.getName(), columnName);
                }
            }
            /*String nickName = UserContext.get().getNickName();
            log.info("{}对表{}进行了修改操作", nickName, table.getName());*/
        }
    }

    private void deleteHandler(Delete delete) {
        Table table = delete.getTable();

        Map<String, List<String>> tableMap = monitorTableConfig.getTables();
        Set<String> tables = tableMap.keySet();


        if (tables.contains(table.getName())&& ObjectUtil.isNotEmpty(UserContext.get())) {
            String nickName = UserContext.get().getNickName();
            log.info("{}对表{}进行了删除操作", nickName, table.getName());
        }
    }

    private void insertHandler(Insert insert) {
        Table table = insert.getTable();

        Map<String, List<String>> tableMap = monitorTableConfig.getTables();
        Set<String> tables = tableMap.keySet();


        if (tables.contains(table.getName())) {
            if (ObjectUtil.isNotEmpty(UserContext.get())) {
                String nickName = UserContext.get().getNickName();
                log.info("{}对表{}进行了插入操作", nickName, table.getName());
            }
        }
    }
}
