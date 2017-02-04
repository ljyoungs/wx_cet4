package cn.pengshengyang.wx_cet4.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.pengshengyang.wx_cet4.service.ReadXlsService;

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

	@RequestMapping(value = "/file/upload", method = { RequestMethod.POST })
	@ResponseBody
	public String uploadFile(MultipartHttpServletRequest request,@RequestParam("action") String action) throws IOException {
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
		// 在这一步已经拿到上传文件列表
		return readXlsService.getDataFromExcel(tempFiles,action);
	
	}

}
