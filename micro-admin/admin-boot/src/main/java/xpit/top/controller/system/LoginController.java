package xpit.top.controller.system;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.enums.HttpCodeEnum;
import xpit.top.result.Result;

/**
 * 登录测试 
 */
@RestController
@RequestMapping("/user/")
public class LoginController {

    // 测试登录  ---- http://localhost:8081/user/doLogin?name=zhang&pwd=123456
    @RequestMapping("doLogin")
    public Result doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return Result.okResult();
        }
        return Result.errorResult(HttpCodeEnum.LOGIN_ERROR);
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public Result isLogin() {
        return Result.okResult("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public Result tokenInfo() {
        return Result.okResult(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public Result logout() {
        StpUtil.logout();
        return Result.okResult();
    }

}
