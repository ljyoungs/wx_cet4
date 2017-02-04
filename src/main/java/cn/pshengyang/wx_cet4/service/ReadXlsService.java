package cn.chenmuxin.wx_cet4.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chenmuxin.wx_cet4.entity.Listen;
import cn.chenmuxin.wx_cet4.repository.ListenRepository;

@Service
public class ReadXlsService {

	@Autowired
	private ListenRepository listenRepository;

	/**
	 * excel读取动作定义
	 * 
	 */
	private static final String LISTEN = "listen";
	private static final String WRITING = "writing";
	private static final String READ = "read";
	private static final String WORD = "word";

	/**
	 * 
	 * 批量读取excel文件并根据动作类型保存在相关数据库，返回错误信息文本列表
	 * 
	 * @param files
	 * @param action
	 * @param action
	 * @throws IOException
	 */
	public String getDataFromExcel(List<String> files, String action) throws IOException {
		StringBuffer errorMsg = new StringBuffer();
		int curFile = 0;

		System.out.println(files);

		for (String filePath : files) {

			// 定位错误文件信息
			String errorFile = "、" + new File(filePath).getName();

			if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
				errorMsg.append(++curFile + errorFile + "：非excel文件已被忽略\n");
				continue;
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
			try {
				fis = new FileInputStream(new File(filePath));
			} catch (Exception e) {
				continue;
			}
			if (filePath.endsWith(".xls")) {
				wookbook = new HSSFWorkbook(fis);
			} else {
				wookbook = new XSSFWorkbook(fis);
			}

			// 得到一个工作表
			Sheet sheet = wookbook.getSheetAt(0);

			// 导入听力
			if (action == "listen") {
				readListen(sheet);
			}

			wookbook.close();
			fis.close();
			// 读取完成删除缓存文件
			File file = new File(filePath);
			System.out.println("删除缓存文件：" + file.delete());
		}

		if (errorMsg.length() == 0) {
			return "导入所有信息成功，没有任何错误";
		}
		return errorMsg.toString();
	}

	public Boolean readListen(Sheet sheet) {

		// 获得表头
		Row rowHead = sheet.getRow(0);

		// 导入问答
		// 要获得属性

		String core = "";
		String content = "";
		String optionA = "";
		String optionB = "";
		String optionC = "";
		String optionD = "";
		String answer = "";
		String type = "";
		List<String> options = new ArrayList<>();

		// 获得所有数据
		for (int i = 1; true; i++) {

			Listen listenEntity = new Listen();

			// 获得第i行对象
			Row row = sheet.getRow(i);
			// 读到行不存在时跳出循环，主要是因为sheet.getLastRowNum()数据不准确，无法只通过它来判断循环次数
			if (row == null) {
				break;
			}
			// 第一列听力 内容

			Cell cell = row.getCell((short) 0);
			if (null != cell) {
				String tempContent = cell.getStringCellValue();
				content = tempContent;
				listenEntity.setContent(content);
			}

			// 第二列 分数
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			cell = row.getCell((short) 1);

			if (null != cell) {
				String tempCore = cell.getStringCellValue();
				core = tempCore;
				listenEntity.setCore(core);
			}

			// 第三列 答案
			cell = row.getCell((short) 2);
			if (null != cell) {
				String tempAnswer = cell.getStringCellValue();
				answer = tempAnswer;
				listenEntity.setAnswer(answer);
			}

			// 第四列 选项
			cell = row.getCell((short) 3);
			if (null != cell) {
				String tempOption = cell.getStringCellValue();
				optionA = tempOption;
				listenEntity.setOptionA(optionA);
			}

			cell = row.getCell((short) 4);
			if (null != cell) {
				String tempOption = cell.getStringCellValue();
				optionB = tempOption;
				listenEntity.setOptionB(optionB);
			}

			cell = row.getCell((short) 5);
			if (null != cell) {
				String tempOption = cell.getStringCellValue();
				optionC = tempOption;
				listenEntity.setOptionC(optionC);
			}

			cell = row.getCell((short) 6);
			if (null != cell) {
				String tempOption = cell.getStringCellValue();
				optionD = tempOption;
				listenEntity.setOptionD(optionD);
			}

			// 第八列 类型
			cell = row.getCell((short) 7);
			if (null != cell) {
				String tempOption = cell.getStringCellValue();
				type = tempOption;
				listenEntity.setType(type);
			}

			if (null != listenEntity) {
				listenRepository.save(listenEntity);
			}

		}

		return false;

	}

}
