package com.by;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.by.dao")
@EnableTransactionManagement
@Slf4j
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy = true)
public class App {
    public static void main(String[] args) {

        //Logger logger = LoggerFactory.getLogger("App");
        /*Logger logger = LoggerFactory.getLogger(App.class);
        logger.debug("Spring Boot Application 开始运行...");*/

        //集成ai
        /*OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://openrouter.ai/api/v1") // OpenRouter 的 API 地址
                .apiKey("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")        // 用你的 OpenRouter key 替换
                .modelName("deepseek/deepseek-chat-v3-0324:free")     // 选用 OpenRouter 支持的模型
                .build();

        String answer = model.generate("你是什么模型");
        System.out.println(answer);*/


        SpringApplication.run(App.class, args);
        log.debug("Spring Boot Application 开始运行...");


        log.error("我是error");
        log.warn("我是warn");
        log.info("我是info");
        log.debug("我是debug");
        log.trace("我是trace");

        /*while (true){
            ThreadUtil.safeSleep(10);
            log.debug("湖人队的东契奇得到39分、8个篮板和7次助攻，里夫斯得到23分、7个篮板和6次助攻，八村塁得到16分和5个篮板，史密斯得到18分，勒布朗得到14分、4个篮板和8次助攻，布朗尼得到3分、1个篮板和1次助攻。火箭队的惠特莫尔得到34分和8个篮板，谢帕德得到14分和5次助攻，威廉姆斯得到12分，霍勒迪得到11分和4次助攻。");
        }*/


    }
}
