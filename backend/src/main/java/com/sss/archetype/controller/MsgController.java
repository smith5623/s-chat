package com.sss.archetype.controller;

import com.sss.archetype.dto.MsgDTO;
import com.sss.archetype.service.MsgService;
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
@RequestMapping("/msg")
@Slf4j
public class MsgController {

    @Autowired
    MsgService msgService;

    /**
     * 查询所有Msg
     *
     * @return
     */
    @GetMapping(value = "/")
    public Result listAll(MsgDTO dto) {
        List<MsgDTO> list = msgService.listAll(dto);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
    * 分页查询
    * @param page
    * @param dto
    * @return
    */
    @GetMapping(value = "/p")
    public Result<Pagination> listPage(PageQuery page, MsgDTO dto) {
        return ResultGenerator.genSuccessResult(msgService.pageList(dto, page));
    }

    /**
     * 根据id查询单个Msg
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") java.lang.String id) {
        log.info("查询id= {}的单个对象", id);
        MsgDTO dto = msgService.getById(id);
        if (dto == null) {
            log.error("没有查到id= {}的Msg.", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Msg");
        }
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 创建新的Msg
     *
     * @param
     * @return
     */
    @PostMapping(value = "/")
    public Result insert(@RequestBody MsgDTO dto) {
        log.info("创建新的Msg : {}", dto);
        msgService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据id更新单个Msg
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result updateById(@PathVariable("id") java.lang.String id, @RequestBody MsgDTO dto) {
        log.info("更新id= {}的Msg", id);

        MsgDTO currentObj = msgService.getById(id);

        if (currentObj == null) {
            log.error("无法更新，找不到id= {}的Msg", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Msg");
        }
        msgService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 根据id删除Msg
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") java.lang.String id) {
        log.info("删除id= {}的Msg", id);
        MsgDTO dto = msgService.getById(id);
        if (dto == null) {
            log.error("无法删除，找不到id= {}的对象", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Msg");
        }
        msgService.deleteById(id);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 删除所有对象
     *
     * @return
     */
    @DeleteMapping(value = "/")
    public Result deleteAll() {
        log.info("删除所有Msg");
        msgService.deleteAll();
        return ResultGenerator.genSuccessResult();
    }
}