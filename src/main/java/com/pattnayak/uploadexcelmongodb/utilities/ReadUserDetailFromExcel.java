package com.pattnayak.uploadexcelmongodb.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.pattnayak.uploadexcelmongodb.entity.Employee;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ReadUserDetailFromExcel {

	private List<Employee> employee;

	/** The excel sheet of campus minds detail */
	// public static final String Campus_Minds_Excel_Data =
	// "excel/CampusMindData_Nov_2017.xlsx";
	// public static final String Campus_Minds_Excel_Data =
	// "excel/CampusMindData_Feb_2018.xlsx";
	public static final String Campus_Minds_Excel_Data = "excel/June_2018/Data_June_2018_v6.xlsx";

	public List<Employee> getEmployeeDetails() {
		return employee;
	}

	public void setEmployeeDetails(List<Employee> employee) {
		this.employee = employee;
	}

	private XSSFWorkbook getWorkbook(final FileInputStream fileStream, final String excelFilePath) throws IOException {
		XSSFWorkbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fileStream);
		}
		/*
		 * else if (excelFilePath.endsWith("xls")) { workbook = new
		 * HSSFWorkbook(fileStream); }
		 */
		else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
	
	private XSSFWorkbook getWorkbook1(final MultipartFile uploadFile) throws IOException, InvalidFormatException {
		XSSFWorkbook workbook = null;
		
		log.info("filename - "+uploadFile.getOriginalFilename()+"\n"+uploadFile.getName());

		if (uploadFile.getOriginalFilename().endsWith("xlsx")) {
			workbook = new XSSFWorkbook(ConvertMultipartFileToFile.convert(uploadFile));
		}
		/*
		 * else if (excelFilePath.endsWith("xls")) { workbook = new
		 * HSSFWorkbook(fileStream); }
		 */
		else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public void readUsersDataFromExcel(MultipartFile uploadFile) throws IOException, InvalidFormatException {
		
		log.info("Started reading users data from excel...");
		List<Employee> userDetailsData = new ArrayList<Employee>();
//		FileInputStream fis = new FileInputStream(
//				new File(this.getClass().getClassLoader().getResource(excelSheet).getFile()));

		XSSFWorkbook workbook = getWorkbook1(uploadFile);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (nextRow.getRowNum() > 0) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Employee userDetails = new Employee();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 0) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setMid(cell.getStringCellValue());
						}
					} else if (cell.getColumnIndex() == 1) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setName(cell.getStringCellValue());
						}
					} else if (cell.getColumnIndex() == 2) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setDescription(cell.getStringCellValue());
						}
					} else if (cell.getColumnIndex() == 3) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setEmail(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 4) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setDisplayName(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 5) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setStatus(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 6) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setGender(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 7) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setLocale(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 8) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setInstitute(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 9) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setCity(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 10) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setState(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 11) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setRegion(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 12) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setQualification(cell.getStringCellValue());
						}

					} else if (cell.getColumnIndex() == 13) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							userDetails.setSpecialization(cell.getStringCellValue());
						}

					}
					
					
					
				}
				
				log.info("Details - "+userDetails);
				userDetailsData.add(userDetails);
				setEmployeeDetails(userDetailsData);
			}

			workbook.close();
			//fis.close();

		}
		
		log.info("Finished reading data from excel. Number of records  - "+this.getEmployeeDetails().size());

	}

}
