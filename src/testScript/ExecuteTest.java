package testScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ExecuteTest {

	@SuppressWarnings({ "unchecked", "rawtypes", "resource", "incomplete-switch" })
	@Parameters({"browser"})
	@Test
	public void runLoginTest(String browser) throws IOException, InterruptedException {
		Keywords keywords = new Keywords();
		List data = new ArrayList();
		FileInputStream fis = new FileInputStream(".\\src\\testCases\\OrangeHrm.xlsx");
		XSSFWorkbook wbk = new XSSFWorkbook(fis);
		XSSFSheet sheet = wbk.getSheetAt(0);

		Iterator row = sheet.iterator();
		while (row.hasNext()) {
			Row rowitr = (Row) row.next();

			Iterator cell = rowitr.cellIterator();
			while (cell.hasNext()) {
				Cell cellitr = (Cell) cell.next();

				switch (cellitr.getCellType()) {

				case STRING:
					data.add(cellitr.getStringCellValue());
					break;

				case NUMERIC:
					data.add(cellitr.getNumericCellValue());
					break;

				case BOOLEAN:
					data.add(cellitr.getBooleanCellValue());
					break;

				case FORMULA:
					data.add(cellitr.getArrayFormulaRange());
					break;
				}

			}
		}

		System.out.println(data);

		for (int i = 0; i <= data.size(); i++) {
			if (data.get(i).equals("Open browser")) {
				String keyWord = (String) data.get(i);
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				System.out.println(keyWord);
				System.out.println(testData);
				System.out.println(objectName);
				keywords.openBrowser(browser);
			}

			if (data.get(i).equals("Navigate")) {
				String keyWord = (String) data.get(i);
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				System.out.println(keyWord);
				System.out.println(testData);
				System.out.println(objectName);
				keywords.navigate(testData);
			}

			if (data.get(i).equals("UsernameField")) {
				String keyWord = (String) data.get(i);
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				System.out.println(keyWord);
				System.out.println(testData);
				System.out.println(objectName);
				keywords.inputUserName(testData, objectName);
			}

			if (data.get(i).equals("PasswordField")) {
				String keyWord = (String) data.get(i);
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				System.out.println(keyWord);
				System.out.println(testData);
				System.out.println(objectName);
				keywords.inputPassword(testData, objectName);
			}

			if (data.get(i).equals("LoginBtn")) {
				String keyWord = (String) data.get(i);
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				System.out.println(keyWord);
				System.out.println(testData);
				System.out.println(objectName);
				keywords.clickLogin(objectName);
			}

		}

	}

}
