package com.sss.archetype.controller;

import com.sss.archetype.dto.UserDTO;
import com.sss.archetype.entity.User;
import com.sss.archetype.service.UserService;
import com.sss.archetype.util.JSONResult;
import com.sss.archetype.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.sss.archetype.common.Result;
import com.sss.archetype.common.ResultGenerator;
import com.sss.archetype.pagination.Pagination;
import com.sss.archetype.pagination.PageQuery;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    /**
     * @Description: 用户注册/登录
     */
    @PostMapping("/registOrLogin")
    public JSONResult registOrLogin(@RequestBody User user) throws Exception {

        // 0. 判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return JSONResult.errorMsg("用户名或密码不能为空...");
        }

        // 1. 判断用户名是否存在，如果存在就登录，如果不存在则注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        User userResult = null;
        if (usernameIsExist) {
            // 1.1 登录
            userResult = userService.queryUserForLogin(user.getUsername(),
                    MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return JSONResult.errorMsg("用户名或密码不正确...");
            }
        } else {
            // 1.2 注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }

        UserDTO userVO = new UserDTO();
        BeanUtils.copyProperties(userResult, userVO);
        return JSONResult.ok(userVO);
    }



    /**
     * 查询所有User
     *
     * @return
     */
    @GetMapping(value = "/")
    public Result listAll(UserDTO dto) {
        List<UserDTO> list = userService.listAll(dto);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
    * 分页查询
    * @param page
    * @param dto
    * @return
    */
    @GetMapping(value = "/p")
    public Result<Pagination> listPage(PageQuery page, UserDTO dto) {
        return ResultGenerator.genSuccessResult(userService.pageList(dto, page));
    }

    /**
     * 根据id查询单个User
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") java.lang.String id) {
        log.info("查询id= {}的单个对象", id);
        UserDTO dto = userService.getById(id);
        if (dto == null) {
            log.error("没有查到id= {}的User.", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的User");
        }
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 创建新的User
     *
     * @param
     * @return
     */
    @PostMapping(value = "/")
    public Result insert(@RequestBody UserDTO dto) {
        log.info("创建新的User : {}", dto);
        userService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据id更新单个User
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result updateById(@PathVariable("id") java.lang.String id, @RequestBody UserDTO dto) {
        log.info("更新id= {}的User", id);

        UserDTO currentObj = userService.getById(id);

        if (currentObj == null) {
            log.error("无法更新，找不到id= {}的User", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的User");
        }
        userService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 根据id删除User
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") java.lang.String id) {
        log.info("删除id= {}的User", id);
        UserDTO dto = userService.getById(id);
        if (dto == null) {
            log.error("无法删除，找不到id= {}的对象", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的User");
        }
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 删除所有对象
     *
     * @return
     */
    @DeleteMapping(value = "/")
    public Result deleteAll() {
        log.info("删除所有User");
        userService.deleteAll();
        return ResultGenerator.genSuccessResult();
    }
}