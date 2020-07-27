
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

	void create() {

		XSSFWorkbook xssfWb;
		XSSFSheet xssfSheet;
		XSSFRow xssfRow;
		XSSFCell xssfCell;

		try {
			int rowNo = 0; // �� ����
			// ��ũ�� ����
			xssfWb = new XSSFWorkbook();
			xssfSheet = xssfWb.createSheet("���� �׽�Ʈ"); // ��ũ��Ʈ �̸�

			// ������
			xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4)); // ù��, ��������, ù��, ��������( 0��° ���� 0~8��° �÷��� �����Ѵ�)
			// Ÿ��Ʋ ����
			xssfRow = xssfSheet.createRow(rowNo++); // �� ��ü �߰�
			xssfCell = xssfRow.createCell((short) 0); // �߰��� �࿡ �� ��ü �߰�
			xssfCell.setCellValue("���� ȸ�� ����Դϴ�."); // ������ �Է�

			for (int i = 0; i < 4; i++) {
				// ���� ���ϱ�
				xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i)) + (short) 2048);
			}

			xssfSheet.setColumnWidth(4, (xssfSheet.getColumnWidth(4)) + (short) 9192);

			xssfRow = xssfSheet.createRow(rowNo++); // ��� 01
			xssfCell = xssfRow.createCell((short) 0);
			xssfCell.setCellValue("�̸�");
			xssfCell = xssfRow.createCell((short) 1);
			xssfCell.setCellValue("����");
			xssfCell = xssfRow.createCell((short) 2);
			xssfCell.setCellValue("����");
			xssfCell = xssfRow.createCell((short) 3);
			xssfCell.setCellValue("������");
			xssfCell = xssfRow.createCell((short) 4);
			xssfCell.setCellValue("�ּ�");

			xssfRow = xssfSheet.createRow(rowNo++); // ��� 02

			String localFile = "D:\\�밡��\\" + "userlist" + ".xlsx";

			File file = new File(localFile);
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			xssfWb.write(fos);

			if (xssfWb != null)
				xssfWb.close();
			if (fos != null)
				fos.close();
			System.out.println("�Ϸ� : " + localFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void read() {

		try {
			FileInputStream fis = new FileInputStream("D:\\�밡��\\userlist.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // �ش� ���������� ��Ʈ(Sheet) ��
			int rows = sheet.getPhysicalNumberOfRows(); // �ش� ��Ʈ�� ���� ����
			for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex); // �� ���� �о�´�
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (int columnIndex = 0; columnIndex < cells; columnIndex++) {
						System.out.printf("%d���� %d��° �� : ", rowIndex, columnIndex);
						System.out.println(row.getCell(columnIndex));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
