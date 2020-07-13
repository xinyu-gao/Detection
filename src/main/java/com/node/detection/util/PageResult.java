package com.node.detection.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * mysql 分页请求处理
 * @param <T>
 */
@Data
public class PageResult<T> {

    /**
     * 总共有多少数据
     */
    private Long total;
    /**
     * 当前查询的结果列表
     */
    private List<T> list;
    /**
     * 是否还有下一页数据
     */
    private Boolean hasNextPage;

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
     *     "hasNextPage": true
     * }
     *
     * @param page 原来的结果
     * @param <T>  泛型类
     * @return 构建后的结果
     */
    public static <T> PageResult<T> restructure(Page<T> page) {
        long total = page.getTotalElements();
        Boolean hasNextPage = total > (page.getNumber() + page.getSize());

        return new PageResult<T>()
                .setTotal(total)
                .setList(page.getContent())
                .setHasNextPage(hasNextPage);
    }

    public PageResult<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public PageResult<T> setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
        return this;
    }
}
