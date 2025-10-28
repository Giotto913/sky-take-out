package com.sky.controller.admin;

/**
 * 分类管理
 */

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类相关接口")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @ApiOperation("分类分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO  categoryPageQueryDTO){
        log.info("分类中的分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult =categoryService.page(categoryPageQueryDTO);
        return Result.success(pageResult);

    }

    @ApiOperation("新增分类")
    @PostMapping
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类");
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @ApiOperation("修改分类状态")
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("菜品状态禁用/启用，要修改成的状态是：{}，修改的员工id是：{}",status,id);
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    @ApiOperation("修改分类")
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改的内容为：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @ApiOperation("删除分类")
    @DeleteMapping
    public Result delete(Long id){
        log.info("删除的id为：{}",id);
        categoryService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据类型查询分类")
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }



}
