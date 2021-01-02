package com.sss.archetype.controller;

import com.sss.archetype.dto.FriendDTO;
import com.sss.archetype.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;
import com.sss.archetype.common.Result;
import com.sss.archetype.common.ResultGenerator;
import com.sss.archetype.pagination.Pagination;
import com.sss.archetype.pagination.PageQuery;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    @Autowired
    FriendService friendService;

    /**
     * 查询所有Friend
     *
     * @return
     */
    @GetMapping(value = "/")
    public Result listAll(FriendDTO dto) {
        List<FriendDTO> list = friendService.listAll(dto);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
    * 分页查询
    * @param page
    * @param dto
    * @return
    */
    @GetMapping(value = "/p")
    public Result<Pagination> listPage(PageQuery page, FriendDTO dto) {
        return ResultGenerator.genSuccessResult(friendService.pageList(dto, page));
    }

    /**
     * 根据id查询单个Friend
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") java.lang.String id) {
        log.info("查询id= {}的单个对象", id);
        FriendDTO dto = friendService.getById(id);
        if (dto == null) {
            log.error("没有查到id= {}的Friend.", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Friend");
        }
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 创建新的Friend
     *
     * @param
     * @return
     */
    @PostMapping(value = "/")
    public Result insert(@RequestBody FriendDTO dto) {
        log.info("创建新的Friend : {}", dto);
        friendService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据id更新单个Friend
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result updateById(@PathVariable("id") java.lang.String id, @RequestBody FriendDTO dto) {
        log.info("更新id= {}的Friend", id);

        FriendDTO currentObj = friendService.getById(id);

        if (currentObj == null) {
            log.error("无法更新，找不到id= {}的Friend", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Friend");
        }
        friendService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 根据id删除Friend
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") java.lang.String id) {
        log.info("删除id= {}的Friend", id);
        FriendDTO dto = friendService.getById(id);
        if (dto == null) {
            log.error("无法删除，找不到id= {}的对象", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Friend");
        }
        friendService.deleteById(id);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 删除所有对象
     *
     * @return
     */
    @DeleteMapping(value = "/")
    public Result deleteAll() {
        log.info("删除所有Friend");
        friendService.deleteAll();
        return ResultGenerator.genSuccessResult();
    }
}