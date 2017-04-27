package com.zq.common.excel;


public class SimpleReadParameter {

    private String[] fieldsId; // 列名 英文ID

    private int startIndex = 2;// 开始行索引，默认第一行为标题，第二行为列名，从第三行开始

    public String[] getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(String[] fieldsId) {
        this.fieldsId = fieldsId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

}
