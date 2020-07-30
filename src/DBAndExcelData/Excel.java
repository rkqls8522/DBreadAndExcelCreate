package DBAndExcelData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Excel {

	void create() {

		XSSFWorkbook xssfWb;
		XSSFSheet xssfSheet;
		XSSFRow xssfRow;
		XSSFCell xssfCell;

		try {
			// 워크북 생성
			xssfWb = new XSSFWorkbook();
			xssfSheet = xssfWb.createSheet("유저리스트"); // 워크시트 이름
			// 셀병합
			xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); // 첫행, 마지막행, 첫열, 마지막열  (0번째 행의 0~4번째 컬럼을 병합한다)
			// 타이틀 생성
			xssfRow = xssfSheet.createRow(0); // 행 객체 추가
			xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
			xssfCell.setCellValue("회원 목록입니다."); // 데이터 입력

			for (int i = 0; i < 8; i++) {
				// 넓이 정하기
				xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i)) + (short) 2048);
				//주소부분만 넓이를 넓게 줌
				if(i == 5)
					xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i)) + (short) 9192);
			}

			xssfRow = xssfSheet.createRow(1); // 헤더 01
			xssfCell = xssfRow.createCell((short) 0);
			xssfCell.setCellValue("id");
			xssfCell = xssfRow.createCell((short) 1);
			xssfCell.setCellValue("password");
			xssfCell = xssfRow.createCell((short) 2);
			xssfCell.setCellValue("name");
			xssfCell = xssfRow.createCell((short) 3);
			xssfCell.setCellValue("sex");
			xssfCell = xssfRow.createCell((short) 4);
			xssfCell.setCellValue("age");
			xssfCell = xssfRow.createCell((short) 5);
			xssfCell.setCellValue("juso");
			xssfCell = xssfRow.createCell((short) 6);
			xssfCell.setCellValue("number");
			xssfCell = xssfRow.createCell((short) 7);
			xssfCell.setCellValue("e_mail");
			
			String localFile = "D:\\노가빈\\" + "userlist" + ".xlsx";

			File file = new File(localFile);
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			xssfWb.write(fos);

			if (xssfWb != null)
				xssfWb.close();
			if (fos != null)
				fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void insert(String id, String password, String name, String sex, String age, String juso, String number, String e_mail,int num) {
		
		try {
			//파일읽기
			InputStream inp = new FileInputStream("D:\\노가빈\\userlist.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			sheet.createRow(num);
			Row row = sheet.getRow(num);
			
			//셀마다 값 넣기
			row.createCell(0).setCellValue(id);
			row.createCell(1).setCellValue(password);
			row.createCell(2).setCellValue(name);
			row.createCell(3).setCellValue(sex);
			row.createCell(4).setCellValue(age);
			row.createCell(5).setCellValue(juso);
			row.createCell(6).setCellValue(number);
			row.createCell(7).setCellValue(e_mail);

			FileOutputStream fileOut = new FileOutputStream("D:\\노가빈\\userlist.xlsx");
			wb.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

	void read() {

		try {
			FileInputStream fis = new FileInputStream("D:\\노가빈\\userlist.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수
			int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
			for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex); // 각 행을 읽어온다
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (int columnIndex = 0; columnIndex < cells; columnIndex++) {
						System.out.printf("%d행의 %d번째 셀 : ", rowIndex, columnIndex);
						System.out.println(row.getCell(columnIndex));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
