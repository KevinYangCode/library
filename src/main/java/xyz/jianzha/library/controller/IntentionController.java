package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.service.IntentionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 新书采购意向表(Intention)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@CrossOrigin
@RestController
@RequestMapping("intention")
public class IntentionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private IntentionService intentionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param intention 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Intention> page, Intention intention) {
        return success(this.intentionService.page(page, new QueryWrapper<>(intention)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.intentionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param intention 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Intention intention) {
        return success(this.intentionService.save(intention));
    }

    /**
     * 修改数据
     *
     * @param intention 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Intention intention) {
        return success(this.intentionService.updateById(intention));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.intentionService.removeByIds(idList));
    }
}