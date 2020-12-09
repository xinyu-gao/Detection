package com.node.detection.util;

import com.node.detection.entity.util.PageResult;
import org.springframework.data.domain.Page;

public class PageUtil {

    /**
     * 静态方法，因为原来的结果过于复杂，而且很多用不到，
     * 所以需要重新构建结果，示例如下：
     * {
     *     "total": 4,
     *     "list": [
     *         {
     *             "id": 2,
     *             "name": null,
     *         },
     *     ],
     *     "currentPage": 1
     * }
     *
     * @param page 原来的结果
     * @param <T>  泛型类
     * @return 构建后的结果
     */
    public static <T> PageResult<T> setResult(Page<T> page, int currentPage) {
        return new PageResult<T>()
                .setTotal( page.getTotalElements())
                .setList(page.getContent())
                .setCurrentPage(currentPage);
    }
}
