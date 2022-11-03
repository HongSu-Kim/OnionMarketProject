package com.youprice.onion.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ImageUtil {

	// 이미지 저장
	public static String store(MultipartFile multipartFile, String imageFolder) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}

		String path = getFilePath() + imageFolder;

		String originalFilename = multipartFile.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

		File file;
		String storeFileName;

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
			log.info("폴더 생성 : " + path);
		}

		do {
			storeFileName = UUID.randomUUID().toString() + ext;
			log.info(storeFileName);
			file = new File(path, storeFileName);
		} while (file.exists());

		FileCopyUtils.copy(multipartFile.getBytes(), file);
		log.info("이미지 저장 완료");

		return storeFileName;
	}

	public static List<String> store(List<MultipartFile> multipartFileList, String imageFolder) throws IOException {

		List<String> list = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFileList) {
			String storeFileName = store(multipartFile, imageFolder);

			if (storeFileName == null) {
				continue;
			}

			list.add(storeFileName);
		}

		return list;
	}

	// 이미지 삭제
	public static void delete(String imageName, String imageFolder) {
		File file = new File(getFilePath() + imageFolder + "/" + imageName);

		if (file.exists()) {
			file.delete();
		}
	}

	public static void delete(List<String> imageNameList, String imageFolder) {
		for (String s : imageNameList) {
			delete(s, imageFolder);
		}
	}

	public static Boolean existsImage(String imageName, String imageFolder ) {
		File file = new File(getFilePath() + imageFolder + "/" + imageName);
		return file.exists();
	}

	// 이미지 저장 경로
	private static String getFilePath() {
		return System.getProperty("user.dir") + "/src/img/";
	}

}
