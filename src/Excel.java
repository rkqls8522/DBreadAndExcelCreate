
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	void create(String id, String password, String name, String sex, String age, String juso) {

		XSSFWorkbook xssfWb;
		XSSFSheet xssfSheet;
		XSSFRow xssfRow;
		XSSFCell xssfCell;

		try {
			int rowNo = 0; // 행 갯수
			// 워크북 생성
			xssfWb = new XSSFWorkbook();
			xssfSheet = xssfWb.createSheet("엑셀 테스트"); // 워크시트 이름
			
			//행의 개수 구하기
			FileInputStream fis = new FileInputStream("D:\\노가빈\\userlist.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수
			int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
			System.out.println("위쪽 rows : " + rows);

			// 셀병합
			xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4)); // 첫행, 마지막행, 첫열, 마지막열  (0번째 행의 0~4번째 컬럼을 병합한다)
			// 타이틀 생성
			xssfRow = xssfSheet.createRow(0); // 행 객체 추가
			xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
			xssfCell.setCellValue("여자 회원 목록입니다."); // 데이터 입력

			for (int i = 0; i < 4; i++) {
				// 넓이 정하기
				xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i)) + (short) 2048);
			}
			//주소부분만 넓이를 넓게 줌
			xssfSheet.setColumnWidth(4, (xssfSheet.getColumnWidth(4)) + (short) 9192);

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
			
			xssfRow = xssfSheet.createRow(rows); // 헤더 02
			xssfCell = xssfRow.createCell((short) 0);
			xssfCell.setCellValue(id);
			xssfCell = xssfRow.createCell((short) 1);
			xssfCell.setCellValue(password);
			xssfCell = xssfRow.createCell((short) 2);
			xssfCell.setCellValue(name);
			xssfCell = xssfRow.createCell((short) 3);
			xssfCell.setCellValue(sex);
			xssfCell = xssfRow.createCell((short) 4);
			xssfCell.setCellValue(age);
			xssfCell = xssfRow.createCell((short) 5);
			xssfCell.setCellValue(juso);
			rows = sheet.getPhysicalNumberOfRows();
			System.out.println("id : " + id + "일 때 rows : " + rows);
			
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
