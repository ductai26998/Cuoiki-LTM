package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUpdateUtil {
	private static final String UPLOAD_DIR = "resource/files";

	@SuppressWarnings("resource")
	public static ArrayList<String> saveImage(HttpServletRequest request) {
		// file info: [name, type, size, url]
		ArrayList<String> fileInfo = new ArrayList<String>();
		try {
			String applicationPath = request.getServletContext().getRealPath("");
			Part filePart = request.getPart("file");

			String fileName = getFileName(filePart);
			String filePath = applicationPath + UPLOAD_DIR + File.separator + fileName;

			InputStream inputStream = null;
			OutputStream outputStream = null;

			File outputFilePath = new File(filePath);
			inputStream = filePart.getInputStream();
			outputStream = new FileOutputStream(outputFilePath);
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			fileInfo.add(fileName);
			fileInfo.add(filePart.getContentType());
			fileInfo.add(String.valueOf(filePart.getSize() / 1024));
			fileInfo.add(UPLOAD_DIR + File.separator + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileInfo;
	}

	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "";
	}
}
