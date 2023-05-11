package api.utilities;
/*import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    public static XSSFRow row ;
    static Object[][] data ;
    String filePath;
    public XLUtility(String filePath)
    {
        this.filePath = filePath;
    }
    public  Object[][] setExcelFile(String sheetName) throws Exception {

        FileInputStream fis = new FileInputStream(this.filePath);
        XSSFWorkbook excelWBook = new XSSFWorkbook(fis);
        XSSFSheet excelWSheet = excelWBook.getSheet(sheetName);
        int rowCount = excelWSheet.getLastRowNum();
        System.out.println("rowCount= "+ rowCount);
        int columnCount = excelWSheet.getRow(0).getLastCellNum();
        System.out.println("columnCount =" + columnCount);
        data = new Object [rowCount][columnCount];

        for (int rowIndex = 1; rowIndex <=rowCount; rowIndex++) {
            int cellCount = excelWSheet.getRow(rowIndex).getLastCellNum();
            for (int columnIndex = 0; columnIndex < cellCount; columnIndex++) {
                String value = excelWSheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
                data[rowIndex-1][columnIndex] = value;
            }
        }
        excelWBook.close();
        return data;
    }
}*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path)
    {
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName,int rownum) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        int cellcount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data=formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }
    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    {
        File xlfile=new File(path);
        if(!xlfile.exists())
        {
            workbook=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            workbook.write(fo);
        }
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1)
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

    }


}