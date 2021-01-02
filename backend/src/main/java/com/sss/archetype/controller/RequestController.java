package com.sss.archetype.controller;

import com.sss.archetype.dto.RequestDTO;
import com.sss.archetype.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.sss.archetype.common.Result;
import com.sss.archetype.common.ResultGenerator;
import com.sss.archetype.pagination.Pagination;
import com.sss.archetype.pagination.PageQuery;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/request")
@Slf4j
public class RequestController {

    @Autowired
    RequestService requestService;

    /**
     * 查询所有Request
     *
     * @return
     */
    @GetMapping(value = "/")
    public Result listAll(RequestDTO dto) {
        List<RequestDTO> list = requestService.listAll(dto);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
    * 分页查询
    * @param page
    * @param dto
    * @return
    */
    @GetMapping(value = "/p")
    public Result<Pagination> listPage(PageQuery page, RequestDTO dto) {
        return ResultGenerator.genSuccessResult(requestService.pageList(dto, page));
    }

    /**
     * 根据id查询单个Request
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") java.lang.String id) {
        log.info("查询id= {}的单个对象", id);
        RequestDTO dto = requestService.getById(id);
        if (dto == null) {
            log.error("没有查到id= {}的Request.", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Request");
        }
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 创建新的Request
     *
     * @param
     * @return
     */
    @PostMapping(value = "/")
    public Result insert(@RequestBody RequestDTO dto) {
        log.info("创建新的Request : {}", dto);
        requestService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据id更新单个Request
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result updateById(@PathVariable("id") java.lang.String id, @RequestBody RequestDTO dto) {
        log.info("更新id= {}的Request", id);

        RequestDTO currentObj = requestService.getById(id);

        if (currentObj == null) {
            log.error("无法更新，找不到id= {}的Request", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Request");
        }
        requestService.saveOrUpdate(dto);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 根据id删除Request
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") java.lang.String id) {
        log.info("删除id= {}的Request", id);
        RequestDTO dto = requestService.getById(id);
        if (dto == null) {
            log.error("无法删除，找不到id= {}的对象", id);
            return ResultGenerator.genFailResult("没有查到id=" + id + "的Request");
        }
        requestService.deleteById(id);
        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 删除所有对象
     *
     * @return
     */
    @DeleteMapping(value = "/")
    public Result deleteAll() {
        log.info("删除所有Request");
        requestService.deleteAll();
        return ResultGenerator.genSuccessResult();
    }
}