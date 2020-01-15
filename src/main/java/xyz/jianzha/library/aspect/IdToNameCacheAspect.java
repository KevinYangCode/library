package xyz.jianzha.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.entity.Bookshelf;
import xyz.jianzha.library.entity.Classify;

import java.util.HashMap;
import java.util.Map;

/**
 * ID转名字的缓存 切面
 *
 * @author Y_Kevin
 * @date 2020-01-05 21:35
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class IdToNameCacheAspect {
    /**
     * 缓存对象
     */
    private Map<String, Object> cache = new HashMap<>();

    /**
     * 缓存前缀
     */
    private static String CLASSIFY_CACHE_PREFIX = "classify:";
    private static String BOOKSHELF_CACHE_PREFIX = "bookshelf:";
    private static String BOOK_CACHE_PREFIX = "book:";

    @Pointcut("execution(* xyz.jianzha.library.mapper.ClassifyMapper.selectById(..))")
    public void pcClassify() {
    }

    @Pointcut("execution(* xyz.jianzha.library.mapper.BookshelfMapper.selectById(..))")
    public void pcBookshelf() {
    }

    @Pointcut("execution(* xyz.jianzha.library.mapper.BookMapper.selectById(..))")
    public void pcBook() {
    }

    @Around(value = "pcClassify()")
    public Object cacheClassifyName(ProceedingJoinPoint point) throws Throwable {
        // 得到目标方法的参数
        Object[] args = point.getArgs();
        // 取出ID(我们这里的切入方法getById()只有一个参数，所以就写0)
        Integer classId = (Integer) args[0];
        // 从缓存里面取出对象
        Object obj = cache.get(CLASSIFY_CACHE_PREFIX + classId);
        if (obj != null) {
            // 说明缓存里面有数据
            return obj;
        } else {
            // 说明缓存里面没有   放行目标方法   查询数据库
            Classify res = (Classify) point.proceed();
            // 放入缓存
            cache.put(CLASSIFY_CACHE_PREFIX + res.getClassId(), res);
            return res;
        }
    }

    @Around(value = "pcBookshelf()")
    public Object cacheLocationName(ProceedingJoinPoint point) throws Throwable {
        // 得到目标方法的参数
        Object[] args = point.getArgs();
        // 取出ID(我们这里的切入方法getById()只有一个参数，所以就写0)
        Integer bookshelfId = (Integer) args[0];
        // 从缓存里面取出对象
        Object obj = cache.get(BOOKSHELF_CACHE_PREFIX + bookshelfId);
        if (obj != null) {
            // 说明缓存里面有数据
            return obj;
        } else {
            // 说明缓存里面没有   放行目标方法   查询数据库
            Bookshelf res = (Bookshelf) point.proceed();
            // 放入缓存
            cache.put(BOOKSHELF_CACHE_PREFIX + res.getBsId(), res);
            return res;
        }
    }

    @Around(value = "pcBook()")
    public Object cacheBookName(ProceedingJoinPoint point) throws Throwable {
        // 得到目标方法的参数
        Object[] args = point.getArgs();
        // 取出ID(我们这里的切入方法selectById()只有一个参数，所以就写0)
        Object bookId = (Object) args[0];
        // 从缓存里面取出对象
        Object obj = cache.get(BOOK_CACHE_PREFIX + bookId);
        if (obj != null) {
            // 说明缓存里面有数据
            return obj;
        } else {
            // 说明缓存里面没有   放行目标方法   查询数据库
            Book res = (Book) point.proceed();
            // 放入缓存
            cache.put(BOOK_CACHE_PREFIX + res.getBookId(), res);
            return res;
        }
    }

}
