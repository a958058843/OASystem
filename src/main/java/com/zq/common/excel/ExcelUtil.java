package com.zq.common.excel ;

import java.text.DecimalFormat ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import org.apache.poi.ss.usermodel.Cell ;
import org.apache.poi.ss.usermodel.CellStyle ;
import org.apache.poi.ss.usermodel.DateUtil ;
import org.apache.poi.ss.usermodel.Font ;
import org.apache.poi.ss.usermodel.Row ;
import org.apache.poi.ss.usermodel.Sheet ;
import org.apache.poi.ss.usermodel.Workbook ;
import org.apache.poi.ss.util.CellRangeAddress ;

 
/**
 * 导入导出工具类
 * @author liliting
 *
 */
public class ExcelUtil {
	
	//excel文件的后缀 = .xls
	public static final String EXCEL_FILE_SUFFIX = ".xls" ;

	//默认的excel列宽=20
	public static final int DEFAULT_COLUMN_WIDTH = 250 ;
	
	/**
	 * 读取Excel列表  --->List
	 * Excel 每一行-----》Map<String,Object>
	 * 所有行--->list集合中
	 * list集合---->数据库
	 * @param wsheet  表格对象
	 * @param startIndex
	 * @param fields
	 * @return
	 */
	public List< Map< String , Object >> readSimple ( Sheet wsheet , int startIndex , String[] fields ) {
		List< Map< String , Object >> dataList = new ArrayList< Map< String , Object >> () ;
		//row.getRowNum =0,1,2,3
		for ( Row row : wsheet ) {
			// 从数据行开始读、
			//row.getRowNum() 得到行的下标  第二行开始
			if ( row.getRowNum () >= startIndex ) {
				
				Map< String , Object > rowMap = new HashMap< String , Object > () ;
				//startIndex为2，row为视觉第三行时，第二个for循环完成时rowMap里的数据
				for ( Cell cell : row ) {//得到每一行的单元格
					// fields代表的列以后的数据忽略  fields.length=6
					//cell.getColumnIndex()得到列的下标
					if ( cell.getColumnIndex () < fields.length ) {
						Object value = getValue ( cell );//得到该单元格的值
						//cell.getColumnIndex ()得到单元格的下标
						String key = fields[cell.getColumnIndex ()];
						//fields[cell.getColumnIndex ()]:  单元格的英文名称
						rowMap.put ( key , value ) ;
						//rowMap.put("userCHname","千锋1");
					}
				}
				dataList.add ( rowMap ) ;
			}
		}
		return dataList ;
	}
	
	/**
	 * 得到Excel中单元格的值
	 * @param cell
	 * @return
	 * @see
	 * @since
	 */
	private Object getValue ( Cell cell ) {
		Object value = null ;
		DecimalFormat df = new DecimalFormat ( "#.####" ) ;
		//  getCellType();得到单元格的类型
		switch ( cell.getCellType () ) {
			case Cell.CELL_TYPE_STRING ://String类型
				value = cell.getRichStringCellValue ().getString () ;
				break ;
			case Cell.CELL_TYPE_NUMERIC ://日期或者数值类型
				if ( DateUtil.isCellDateFormatted ( cell ) ) {//日期
					value = CalendarHelper.formatDatetime ( cell.getDateCellValue () ) ;
				} else {//数值
					value = df.format ( cell.getNumericCellValue () ) ;
				}
				break ;
			case Cell.CELL_TYPE_BOOLEAN ://boolean类型
				value = cell.getBooleanCellValue () ;
				break ;
			case Cell.CELL_TYPE_FORMULA :
				value = cell.getCellFormula () ;
				break ;
			default :
				value = cell.getStringCellValue () ;
		}
		return value ;
	}
	
	/**
	 * 导出excel格式的文件
	 * @param wsheet
	 * @param parameters
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @see
	 * @since
	 */
	public void simpleExport ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		//     千锋员工信息
		//名称  组织名称  性别  电话  …………  
		fillHeaders ( wwb , wsheet , parameters ) ;//填充excel的标题部分
		
		//填充内容 ： 数据库数据---》excel表格中
		fillContent ( wwb , wsheet , parameters ) ;
	}
	
	/**
	 * 填充excel的标题栏 一级列的标题栏
	 * @param wwb excel文件
	 * @param wsheet  表格的sheet对象
	 * @param parameters  参数对象
	 */
	
	private void fillHeaders ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		
		//合并单元格
		//parameters.getFieldsName ().length: 得到导出的excel文件的列的长度
		//0--7 0 1 2 3 4 5 6 7
		wsheet.addMergedRegion ( new CellRangeAddress ( 0 , 0 , 0 , parameters.getFieldsName ().length - 1 ) ) ;
		
		Row titleRow = wsheet.createRow ( 0 ) ;// 标题行
		
		Cell titileCell = titleRow.createCell ( 0 ) ;//单元格
		//excel文件-->sheet表格--->Row行 ---->cell 列  js
		
		//标题的文字
		Font titleFont = wwb.createFont () ;// 标题字体
		titleFont.setFontHeightInPoints ( ( short ) 30 ) ;//高度
		titleFont.setFontName ( "Courier New" ) ;
		titleFont.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;//字体加粗
		
		CellStyle titileStyle = wwb.createCellStyle () ;// 标题单元格格式：居中，底对齐
		titileStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		titileStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;
		titileStyle.setFont ( titleFont ) ;
		
		//给标题的单元格设置上述样式
		titileCell.setCellStyle ( titileStyle ) ;
		//设置单元格内容
		titileCell.setCellValue ( parameters.getTitle () ) ;
	 //设置的是标题栏的宽度
	  wsheet.setDefaultColumnWidth ( 20 * DEFAULT_COLUMN_WIDTH )  ;
	  
	  //设置的是列标题的宽度
	  if ( parameters.getWidths () != null ) {
		   for ( int i = 0 ; i < parameters.getWidths ().length ; ++i ) {
				wsheet.setColumnWidth ( i , Integer.parseInt ( parameters.getWidths ()[i] ) * DEFAULT_COLUMN_WIDTH ) ;
			}
		}

		//创建第二行
		Row columnRow = wsheet.createRow ( 1 ) ;// 列标题行
		
		Font font = wwb.createFont () ;// 列标题行字体
		font.setFontHeightInPoints ( ( short ) 15 ) ;
		font.setFontName ( "Courier New" ) ;
		font.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;
		
		CellStyle cellStyle = wwb.createCellStyle () ;// 列标题行单元格格式：居中，底对齐
		cellStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		cellStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;
		cellStyle.setFont ( font ) ;
		
		//创建第二行的单元格
		for ( int i = 0 ; i < parameters.getFieldsName ().length ; ++i ) {
			Cell cell = columnRow.createCell ( i ) ;
			cell.setCellStyle ( cellStyle ) ;
			cell.setCellValue ( parameters.getFieldsName ()[i] ) ;
		}
		
	}
	
	/**
	 * 把数据库内容---》excel文件中
	 * @param wwb
	 * @param wsheet
	 * @param parameters
	 */
	private void fillContent ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		//数据库数据
		List< Map< String , Object >> list = parameters.getDataList () ;

		String value = "" ;
		String[] field = parameters.getFieldsId () ;//得到列的英文名--
		
		for ( int i = 0 ; i < list.size () ; i++ ) {
			//把每一行的数据取出来
			Map< String , Object > originMap = list.get ( i );
			//创建excel 一行
			Row row = wsheet.createRow ( i + 2 ) ;//从第二行开始  因为标题  列标题占了两行
			for ( int j = 0 ; j < field.length ; j++ ) {
				
				Object origin = originMap.get ( field[j] ) ;//得到该行的每一列的值 原始数据
				//创建单元格
				Cell cell = row.createCell ( j ) ;
				
				if ( origin instanceof java.sql.Date ) {
					java.sql.Date d = ( java.sql.Date ) origin ;
					value = CalendarHelper.formatDatetime ( d ) ;
				} else {
					value = String.valueOf ( origin ) ;//转成String
				}
				if ( value == null || value.equalsIgnoreCase ( "null" ) ) {
					value = "" ;
				}
		
				cell.setCellValue ( value ) ;//给单元格赋值
			}
		}
	}
}
