package com.fawvw.ms.voucher.baseservice.util;

import com.fawvw.ms.oa.core.exception.ServiceException;
import com.fawvw.ms.oa.core.result.ResultEnum;
import com.fawvw.ms.voucher.basedomain.constants.FileConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author guo.yin
 * @title: ExcelUtil
 * @projectName one-app-server
 * @description:
 * @date 2019/6/1810:56
 */
@Slf4j
public final class ExcelUtil {

    private ExcelUtil(){

    }

    private static final int INT_THIRTY_SIX = 36;
    private static final String REGEX = "^.+\\.(?i)(xlsx)$";
    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb, short[] colWidthAry) {
        HSSFWorkbook wbn = wb;
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wbn == null) {
            wbn = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wbn.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //for(int i=0;i<cellTitle.length;i++){
        //row.createCell(i).setCellValue(cellTitle[i]);
        //}
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wbn.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            if (colWidthAry != null) {
                sheet.setColumnWidth(i, (INT_THIRTY_SIX * colWidthAry[i]));
            }
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wbn;
    }

    private static boolean isValidTitle(String[] titles, Sheet sheet) throws Exception {
        Row row = sheet.getRow(0);
        int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
        if (titles.length != columnNum) {
            return false;
        }
        if (row == null) {
            return false;
        }
        for (int c = 0; c < columnNum; c++) {
            Cell cell = row.getCell(c);
            cell.setCellType(CellType.STRING);
            String cellData = cell.getStringCellValue();
            String title = titles[c];
            if (!cellData.equals(title)) {
                return false;
            }
        }
        return true;
    }

    public static String[][] getValuesOfHSSFWorkbook(MultipartFile file, String[] titles, Integer sheetIndex) throws Exception {
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches(REGEX)) {
            throw new ServiceException(ResultEnum.LIMITED_DEALER_FILE_NAME_ERROR);
        }
        boolean isExcel2003 = true;
        if (fileName.matches(REGEX)) {
            isExcel2003 = false;
        }
        InputStream data = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(data);
        } else {
            wb = new XSSFWorkbook(data);
        }

        Sheet sheet = wb.getSheetAt(sheetIndex);
        if (null == sheet) {
            return null;
        }

        if (!isValidTitle(titles, sheet)) {
            throw new ServiceException(ResultEnum.FILE_TITILE_ERROR);
        }

        int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
        int lastRowNum = sheet.getLastRowNum();
        String[][] results = new String[lastRowNum][columnNum];

        for (int r = 1; r <= lastRowNum; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            for (int c = 0; c < columnNum; c++) {
                Cell cell = row.getCell(c);
                if (null == cell) {
                    continue;
                }
                cell.setCellType(CellType.STRING);
                String cellData = cell.getStringCellValue();
                results[r - 1][c] = cellData;
            }
        }

        return results;
    }

    /**
     * 导出大数据集的Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        SXSSFWorkbook对象
     * @return
     */
    public static SXSSFWorkbook exportSXSSFWorkbook(String sheetName, String[] title, String[][] values, SXSSFWorkbook wb,
                                                    Integer offset, Integer sheetIndex, Boolean isNewSheet) {

        SXSSFWorkbook wbn = wb;
        // 第一步，SXSSFWorkbook，对应一个Excel文件
        if (wbn == null) {
            wbn = new SXSSFWorkbook(FileConstants.SXSSF_WINDOW_SIZE);
            //压缩临时文件大小,否则磁盘很快要被写满
            wbn.setCompressTempFiles(true);
        }
        SXSSFSheet sheet = null;
        CellStyle style = wbn.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        if (isNewSheet) {
            // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
            sheet = wbn.createSheet(sheetName + sheetIndex);
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            SXSSFRow row = sheet.createRow(0);
            //声明列对象
            SXSSFCell cell = null;
            //创建标题
            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }
        } else {
            sheet = wbn.getSheet(sheetName + sheetIndex);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            SXSSFRow row = sheet.createRow(i + offset + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wbn;
    }

    public static InputStream workbookConvertorStream(SXSSFWorkbook workbook) {
        InputStream inputStream = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            inputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            log.error("workbookConvertorStream error e={}", e);
            e.printStackTrace();
        }
        return inputStream;
    }
}
