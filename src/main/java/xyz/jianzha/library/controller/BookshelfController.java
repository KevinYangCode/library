package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Bookshelf;
import xyz.jianzha.library.service.BookshelfService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 书架表(Bookshelf)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("bookshelf")
public class BookshelfController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BookshelfService bookshelfService;

    /**
     * 查询所有数据
     *
     * @param bookshelf 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Bookshelf bookshelf) {
        return success(this.bookshelfService.list(new QueryWrapper<>(bookshelf)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.bookshelfService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bookshelf 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Bookshelf bookshelf) {
        return success(this.bookshelfService.save(bookshelf));
    }

    /**
     * 修改数据
     *
     * @param bookshelf 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Bookshelf bookshelf) {
        return success(this.bookshelfService.updateById(bookshelf));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.bookshelfService.removeByIds(idList));
    }
}