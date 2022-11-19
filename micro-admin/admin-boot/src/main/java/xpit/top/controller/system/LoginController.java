package xpit.top.controller.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.result.Result;

/**
 * 登录测试 
 */
@RestController
@RequestMapping("/user/")
public class LoginController {


    @GetMapping("info")
    public Result info() {
        return Result.okResult("info");
    }

}
