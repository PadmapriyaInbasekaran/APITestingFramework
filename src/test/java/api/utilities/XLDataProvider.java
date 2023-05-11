package api.utilities;
//import org.testng.annotations.DataProvider;
//public class XLDataProvider {
//    @DataProvider( name= "Data" )
//    public static Object[][] demoTestDataProvider() throws Exception
//    {
//        XLUtility readSheet = new XLUtility("C:\\Users\\Padmapriya.I\\IdeaProjects\\RestAssuredFramework\\testData\\Users.xlsx");
//        Object[][] data = readSheet.setExcelFile("Sheet1");
//        return data;
//    }
//}


import java.io.IOException;

import org.testng.annotations.DataProvider;

public class XLDataProvider {

    @DataProvider(name="Data")
    public Object[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Users.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");
        int colcount=xl.getCellCount("Sheet1",1);

        String apidata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }


    @DataProvider(name="UserId")
    public Object[] getUserId() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Users.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");

        String apidata[]=new String[rownum];
        for(int i=1;i<=rownum;i++)
        {
            apidata[i-1]=xl.getCellData("Sheet1", i, 0);
        }

        return apidata;

    }
}

