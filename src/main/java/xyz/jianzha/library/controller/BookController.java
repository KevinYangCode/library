package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.service.BookService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;
import xyz.jianzha.library.utils.Tools;
import xyz.jianzha.library.vo.BookVo;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 图书书目表(Book)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("book")
public class BookController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    /**
     * 查询所有数据
     *
     * @param bookVo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(BookVo bookVo) {
        // MybatisPlus条件构造器
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Tools.isNotEmpty(bookVo.getName()), "name", bookVo.getName());
        queryWrapper.eq(Tools.isNotEmpty(bookVo.getClassId()), "class_id", bookVo.getClassId());
        // 判断是否参数是否带有分页条件
        if (Tools.isEmpty(bookVo.getCurrent()) || Tools.isEmpty(bookVo.getSize())) {
            // 不分页查询
            List<Book> list = bookService.list(queryWrapper);
            // 调用方法（根据ID查找书架名和分类名）
            bookService.idToName(list);
            return ResponseData.success(list, list.size(), "执行成功！");
        } else {
            // 分页查询
            IPage<Book> bookPage = new Page<>(bookVo.getCurrent(), bookVo.getSize());
            bookPage = bookService.page(bookPage, queryWrapper);
            // 调用方法（根据ID查找书架名和分类名）
            bookService.idToName(bookPage.getRecords());
            return ResponseData.success(bookPage.getRecords(), bookPage.getTotal(), "执行成功！");
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseData selectOne(@PathVariable Serializable id) {
        return ResponseData.success(this.bookService.getById(id), "执行成功！");
    }

    /**
     * 新增数据
     *
     * @param book 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody Book book) {
        // 判断是不是管理员
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.bookService.save(book), "添加成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }

    /**
     * 修改数据
     *
     * @param book 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody Book book) {
        // 判断是借阅还是管理员修改数据
        // 借阅时只传bookId和status
        // 管理员修改数据会传表单数据进来
        if (StringUtils.isNotEmpty(book.getName())) {
            if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
                return ResponseData.success(this.bookService.updateById(book), "执行成功！");
            } else {
                return ResponseData.fail("没有权限！");
            }
        } else {
            // 借阅操作
            return ResponseData.success(this.bookService.updateById(book), "执行成功！");
        }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseData delete(@RequestParam("idList") List<Long> idList) {
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.bookService.removeByIds(idList), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}