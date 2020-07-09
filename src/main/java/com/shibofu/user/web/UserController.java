package com.shibofu.user.web;

import com.shibofu.common.exception.BusinessException;
import com.shibofu.common.response.ResponseBody;
import com.shibofu.user.model.User;
import com.shibofu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author potter.fu
 * @date 2018-12-12 8:42
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param openId 用户openId
     * @return com.shibofu.user.model.User
     * @author potter.fu
     * @date 2018-12-12 08:43
     */
    @GetMapping(value = "/get/{openId}")
    public String getUser(@PathVariable String openId) {
        User user = userService.getUser(openId);
        ResponseBody<User> body = new ResponseBody<>();
        body.init(user);
        return body.toResponse();
    }

    /**
     * 更新用户昵称
     *
     * @param nickName 用户昵称
     * @return java.lang.String
     * @author potter.fu
     * @date 2018-12-28 17:29
     */
    @PutMapping(value = "/updateNickName/{nickName}")
    public String updateNickName(@PathVariable String nickName) {
        userService.updateNickName(nickName);
        ResponseBody<Object> body = new ResponseBody<>();
        body.setMsg("更新成功");
        return body.toResponse();
    }

    /**
     * 更新用户性别
     *
     * @param gender 0未知 1男 2女
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-03 09:29
     */
    @PutMapping(value = "/updateGender/{gender}")
    public String updateGender(@PathVariable Integer gender) {
        if (gender != 0 && gender != 1 && gender != 2) {
            throw new BusinessException("性别参数错误！");
        }
        userService.updateGender(gender);
        ResponseBody<Object> body = new ResponseBody<>();
        body.setMsg("更新成功");
        return body.toResponse();
    }

    /**
     * 更新生日
     *
     * @param birthday 生日
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-04 16:31
     */
    @PutMapping(value = "updateBirthday/{birthday}")
    public String updateBirthday(@PathVariable Date birthday) {
        userService.updateBirthday(birthday);
        ResponseBody<Object> body = new ResponseBody<>();
        body.setMsg("更新成功");
        return body.toResponse();
    }

    /**
     * 更新个性签名
     *
     * @param personalSignature 个性签名
     * @return java.lang.String
     * @author potter.fu
     * @date 2019-01-04 22:04
     */
    @PutMapping(value = "updatePersonalSignature")
    public String updatePersonalSignature(@RequestBody String personalSignature) {
        userService.updatePersonalSignature(personalSignature);
        ResponseBody<Object> body = new ResponseBody<>();
        body.setMsg("更新成功");
        return body.toResponse();
    }
}
