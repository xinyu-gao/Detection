package com.node.detection.entity.ws;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Data
public class WsNode {

    /**
     * 命令
     */
    private String command;

    /**
     * 接收方
     */
    private String dest;

    /**
     * 发送方
     */
    private String source;

    /**
     * 密码
     */
    private String password;

    /**
     * 当前帧
     */
    @Field(name = "current_row")
    private int currentRow;

    /**
     * 总帧数
     */
    @Field(name = "total_rows")
    private int totalRows;


    /**
     * 消息
     */
    private String value;

    /**
     * 数据
     */
    private List<MyData> data;

    @Data
    public static class MyData {

        /**
         * 数据类型
         */
        private String type;

        /**
         * 数据值
         */
        private String value;

        /**
         * 数据名
         */
        private String name;

        /**
         * 数据所占大小
         */
        private int size;

        /**
         * 别名
         */
        @Field(name = "other_name")
        private String otherName;

        /**
         * 可读写，"read"/"write"
         */
        private String wr;
    }

}