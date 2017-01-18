package cn.chenmuxin.wx_cet4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.chenmuxin.wx_cet4.service.ReadXlsService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * excel批量导入上传入口
 * 
 * @author Administrator
 *
 */
@Controller
public class FileUploadController {

	/**
	 * 上传动作定义
	 * 
	 * LOADUSER 批量导入用户 LOADANSWER 批量导入问答
	 * 
	 */

	@Autowired
	private ReadXlsService readXlsService;

//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	@ResponseBody
//	public List<File> uploadFile(MultipartHttpServletRequest request) throws IOException {
//		// 首先定义了一个List<File>用于保存所有文件上传后所在位置，以便后续做出一系列处理。
//		List<File> uploadFileList = new ArrayList<File>();
//		// 拿到name为files的文件上传表单内容（支持多文件）
//		List<MultipartFile> files = request.getFiles("files");
//		// 拿到name为test的表单项
//		String test = request.getParameter("test");
//		// 获取项目真实路径
//		String path = request.getSession().getServletContext().getRealPath("");
//		// 指定temp文件夹
//		String tmpPath = path + File.separator + "temp";
//		File filePath = new File(tmpPath);
//		if (!filePath.exists() || !filePath.isDirectory()) {
//			filePath.mkdirs();
//		}
//		// 上传等同于输入输出流的正常操作，拿到输入流的bytes往输出流送
//		for (MultipartFile multipartFile : files) {
//			if (!multipartFile.isEmpty()) {
//				File tempFile = new File(filePath + File.separator + multipartFile.getOriginalFilename());
//				FileOutputStream fos = new FileOutputStream(tempFile);
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				bos.write(multipartFile.getBytes());
//				bos.close();
//				fos.close();
//				uploadFileList.add(tempFile);
//			}
//		}
//		System.out.println(uploadFileList);
//		return uploadFileList;
//	}

	@RequestMapping(value = "/file/upload", method = { RequestMethod.POST })
	@ResponseBody
	public String uploadFile(MultipartHttpServletRequest request) throws IOException {
		List<MultipartFile> files = request.getFiles("files");
		List<String> tempFiles = new ArrayList<>();
		// 拿到项目目录下的缓存路径
		String uploadPath = request.getSession().getServletContext().getRealPath("");
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists() || !uploadFile.isDirectory()) {
			uploadFile.mkdirs();
		}
		for (int i = 0; i < files.size(); ++i) {
			MultipartFile file = files.get(i);
			String name = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					File tempFile = new File(uploadPath, name);
					byte[] bytes = file.getBytes();
					FileOutputStream fos = new FileOutputStream(tempFile);
					BufferedOutputStream stream = new BufferedOutputStream(fos);
					stream.write(bytes);
					tempFiles.add(tempFile.toString());
					stream.close();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			} else {
				break;
			}
		}
		System.out.println(files);
		System.out.println(tempFiles);
		// 在这一步已经拿到上传文件列表
		return readXlsService.getDataFromExcel(tempFiles);
	
	}

}
