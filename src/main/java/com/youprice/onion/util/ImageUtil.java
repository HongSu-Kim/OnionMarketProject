package com.youprice.onion.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ImageUtil {

	// 이미지 저장
	public static String store(MultipartFile multipartFile, String path) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}

		String filePath = getFilePath() + path;

		String originalFilename = multipartFile.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		log.error(ext);

		File file;
		String storeFileName;

		do {
			storeFileName = UUID.randomUUID().toString() + ext;
			log.error(storeFileName);
			file = new File(filePath, storeFileName);
		} while (file.exists());

		multipartFile.transferTo(file);

		return storeFileName;
	}

	public static List<String> store(List<MultipartFile> multipartFileList, String path) throws IOException {

		List<String> list = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFileList) {
			String storeFileName = store(multipartFile, path);

			if (storeFileName == null) {
				continue;
			}

			list.add(storeFileName);
		}

		return list;
	}

	// 이미지 삭제
	public static void delete(String imageName, String path) {
		File file = new File(getFilePath() + path + "\\" + imageName);

		if (file.exists()) {
			file.delete();
		}
	}

	public static void delete(List<String> imageNameList, String path) {
		for (String s : imageNameList) {
			delete(s, path);
		}
	}

	public static Boolean existsImage(String imageName, String path ) {
		File file = new File(getFilePath() + path + "\\" + imageName);
		return file.exists();
	}

	// 이미지 저장 경로
	private static String getFilePath() {
		return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
	}

}
