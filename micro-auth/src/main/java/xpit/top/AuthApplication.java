package xpit.top;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author PTJ
 * @since 2022/11/19 9:15
 */
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(AuthApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
