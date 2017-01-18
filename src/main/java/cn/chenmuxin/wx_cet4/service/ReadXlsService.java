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
import cn.chenmuxin.wx_cet4.util.OtherUtil;

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
	 * @throws IOException
	 */
	public String getDataFromExcel(List<String> files) throws IOException {
		StringBuffer errorMsg = new StringBuffer();
		int curFile = 0;
		
		System.out.println(files);
		
		for (String filePath : files) {

			System.out.println("能进入第一层");
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

//			// 导入问答
//			if (action == LOADANSWER) {} else if (action == LOADUSER) {}
			
			Listen listenEntity = new Listen();
			// 获得表头
			Row rowHead = sheet.getRow(0);

			// 导入问答
				// 要获得属性
			
				String core = "";
				String question = "";
				String content = "";
				String isMultiple = "";
				String answer = "";
				List<String> options = new ArrayList<>();
				
				
				// 获得所有数据
				for (int i = 1; true; i++) {
					
					System.out.println("能进入第二层");
					
					// 获得第i行对象
					Row row = sheet.getRow(i);
					// 读到行不存在时跳出循环，主要是因为sheet.getLastRowNum()数据不准确，无法只通过它来判断循环次数
					if (row == null) {
						break;
					}
					// 第一列 内容
					
					Cell cell = row.getCell((short) 0);
					if (null!=cell) {
						String tempContent = cell.getStringCellValue();
						content = tempContent;
						listenEntity.setContent(content);
					}
					
					
					System.out.println("能进入第层");
					
					
					
//					// 第二列 分数
//					cell = row.getCell((short) 1);
//					String tempCore = cell.getStringCellValue();
//					if (OtherUtil.isNotEmpty(tempCore)) {
//						core = tempCore;
//						listenEntity.setCore(Integer.valueOf(core));
//					}
				
					
					
//					// 第三列 问题
//					cell = row.getCell((short) 2);
//					String	tempQuestion = cell.getStringCellValue();
//					if (OtherUtil.isNotEmpty(tempQuestion)) {
//						question = tempQuestion;
//						listenEntity.setQuestion(question);
//					}
//					
					
					// 第四列 是否多选
//					cell = row.getCell((short) 3);
//					String	tempIsMultiple = cell.getStringCellValue();
//					if (OtherUtil.isNotEmpty(tempIsMultiple)) {
//						isMultiple = tempIsMultiple;
//					}
					
					// 第五列 答案
//					cell = row.getCell((short) 3);
//					String	tempAnswer = cell.getStringCellValue();
//					if (OtherUtil.isNotEmpty(tempAnswer)) {
//						answer = tempAnswer;
//					}
					
					if (null!=listenEntity) {
						listenRepository.save(listenEntity);
					}
				
					
//					for (int j = 1; true; j++){
//						// 第六列 选项
//						cell = row.getCell((short) j);
//						String	tempOption = cell.getStringCellValue();
//						if (OtherUtil.isNotEmpty(tempOption)) {
//							options.add(tempOption);
//						}
//					}
					
				}
				
				System.out.println("能进入第三层");
			
			
			wookbook.close();
			fis.close();
			//读取完成删除缓存文件
			File file = new File(filePath);
			System.out.println("删除缓存文件："+file.delete());
		}
		
		if(errorMsg.length() == 0){
			return "导入所有信息成功，没有任何错误";
		}
		return errorMsg.toString();
	}
	
	
	
	
//	public Boolean readListen(Sheet sheet){
//		
//		
//		Listen listenEntity = new Listen();
//		// 获得表头
//		Row rowHead = sheet.getRow(0);
//
//		// 导入问答
//			// 要获得属性
//		
//			String core = "";
//			String question = "";
//			String content = "";
//			String isMultiple = "";
//			String answer = "";
//			List<String> options = new ArrayList<>();
//			
//			// 获得所有数据
//			for (int i = 1; true; i++) {
//				
//				// 获得第i行对象
//				Row row = sheet.getRow(i);
//				// 读到行不存在时跳出循环，主要是因为sheet.getLastRowNum()数据不准确，无法只通过它来判断循环次数
//				if (row == null) {
//					break;
//				}
//				// 第一列 内容
//				Cell cell = row.getCell((short) 0);
//				String tempContent = cell.getStringCellValue();
//				if (OtherUtil.isNotEmpty(tempContent)) {
//					content = tempContent;
//				}
//				
//				listenEntity.setContent(content);
//				
//				// 第二列 分数
//				cell = row.getCell((short) 1);
//				String tempCore = cell.getStringCellValue();
//				if (OtherUtil.isNotEmpty(tempCore)) {
//					core = tempCore;
//				}
////				listenEntity.setCore();
//				
//				// 第三列 问题
//				cell = row.getCell((short) 2);
//				String	tempQuestion = cell.getStringCellValue();
//				if (OtherUtil.isNotEmpty(tempQuestion)) {
//					question = tempQuestion;
//				}
////				listenEntity.setQuestions(question);
//				
//				// 第四列 是否多选
//				cell = row.getCell((short) 3);
//				String	tempIsMultiple = cell.getStringCellValue();
//				if (OtherUtil.isNotEmpty(tempIsMultiple)) {
//					isMultiple = tempIsMultiple;
//				}
//				
//				// 第五列 答案
//				cell = row.getCell((short) 3);
//				String	tempAnswer = cell.getStringCellValue();
//				if (OtherUtil.isNotEmpty(tempAnswer)) {
//					answer = tempAnswer;
//				}
//				
//				listenRepository.save(listenEntity);
//				
//				for (int j = 1; true; j++){
//					// 第六列 选项
//					cell = row.getCell((short) j);
//					String	tempOption = cell.getStringCellValue();
//					if (OtherUtil.isNotEmpty(tempOption)) {
//						options.add(tempOption);
//					}
//				}
//				
//				
//			}
//			
//			
//		return false;
//		
//	}

}
