package com.dzf.zxkj.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * EasyUI DataGrid模型
 *
 * @author 孙宇
 */
@Data
public class Grid<T> implements java.io.Serializable {
    private Long total = 0L;
    // 允许序列化空集合
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T rows;
    private int status;
    private boolean success;
    private String msg;
    private List<String[]> msglist;
}
