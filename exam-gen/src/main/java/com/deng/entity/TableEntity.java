package com.deng.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by hp on 2017/4/13.
 */
public class TableEntity {
    private String tableName;
    private String engine;
    private String tableComment;
    private Date createTime;

    private List<ColumnEntity> cols;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ColumnEntity> getCols() {
        return cols;
    }

    public void setCols(List<ColumnEntity> cols) {
        this.cols = cols;
    }
}
