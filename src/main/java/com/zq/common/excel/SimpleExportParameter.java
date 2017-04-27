package com.zq.common.excel;

import java.util.List;
import java.util.Map;

/**
 * 封装导出参数
 * @author liliting
 *
 */
public class SimpleExportParameter {
    private String fileName = "";//导出的excel文件名
    private String sheetName = "";//
    private String title = ""; // 标题
    private String[] fieldsId; // 列名 英文ID
    private String[] fieldsName; // 列名 中文名
    private String[] widths; // 列宽
    private int startIndex = 2;// 开始行索引，默认第一行为标题，第二行为列名，从第三行开始
    private List<Map<String, Object>> dataList; // 列数据  导出的数据

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

   

    public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String[] fieldsName) {
        this.fieldsName = fieldsName;
    }

    public String[] getWidths() {
        return widths;
    }

    public void setWidths(String[] widths) {
        this.widths = widths;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String[] getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(String[] fieldsId) {
        this.fieldsId = fieldsId;
    }

}
