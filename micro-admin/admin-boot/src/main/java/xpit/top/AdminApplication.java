package xpit.top;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author PTJ
 * @since 2022/11/18 22:50
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class AdminApplication {

    /**
     * 主要
     *
     * @param args
     * @author PTJ
     * @since 2022/11/18
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}