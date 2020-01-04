package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Lend;
import xyz.jianzha.library.service.LendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 借阅信息表(Lend)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("lend")
public class LendController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LendService lendService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param lend 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Lend> page, Lend lend) {
        return success(this.lendService.page(page, new QueryWrapper<>(lend)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.lendService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lend 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Lend lend) {
        return success(this.lendService.save(lend));
    }

    /**
     * 修改数据
     *
     * @param lend 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Lend lend) {
        return success(this.lendService.updateById(lend));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.lendService.removeByIds(idList));
    }
}