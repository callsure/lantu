package org.lantu.utils.file;

import org.lantu.utils.json.GsonUtil;

import java.io.*;

/**
 * Created by runshu.lin on 2018/12/22.
 */
public class FileUtil {

	private FileUtil() {
	}

	/**
	 * 读取文件
	 *
	 * @param path 文件路径
	 * @return
	 */
	public static String readFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			try (InputStream infile = new FileInputStream(file)) {
				return readFile(infile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 读取文件流
	 *
	 * @param is 文件流
	 * @return
	 */
	public static String readFile(InputStream is) {
		try (
				InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
		) {
			String str = null;
			StringBuilder sb = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存文件
	 *
	 * @param path
	 * @param object
	 * @return false失败，true成功
	 */
	public static boolean saveFile(String path, Object object) {
		File saveFile = new File(path);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile), "UTF-8"))) {
			writer.write(GsonUtil.gsonString(object));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除一个文件。
	 *
	 * @param filename
	 * @throws IOException
	 */
	public static void deleteFile(String filename) throws IOException {
		File file = new File(filename);
		if (file.isDirectory()) {
			throw new IOException("IOException -> BadInputException: not a file.");
		}
		if (!file.exists()) {
			throw new IOException("IOException -> BadInputException: file is not exist.");
		}
		if (!file.delete()) {
			throw new IOException("Cannot delete file. filename = " + filename);
		}
	}

	/**
	 * 复制文件
	 *
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public static void copyFile(File src, File dst) throws Exception {
		int BUFFER_SIZE = 4096;
		try (
				InputStream in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				OutputStream out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE)
		) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 判断指定的文件是否存在。
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileName) {
		return new File(fileName).isFile();
	}

	/**
	 * 从文件路径得到文件名。
	 *
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}

	/**
	 * 从文件名得到文件绝对路径。
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFilePath(String fileName) {
		File file = new File(fileName);
		return file.getAbsolutePath();
	}

	/**
	 * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
	 *
	 * @param filePath
	 * @return
	 */
	public static String toUNIXpath(String filePath) {
		return filePath.replace("", "/");
	}

	/**
	 * 得到文件后缀名
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int point = fileName.lastIndexOf('.');
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			return "";
		} else {
			return fileName.substring(point + 1, length);
		}
	}

	/**
	 * 将文件名中的类型部分去掉。
	 *
	 * @param filename
	 * @return
	 */
	public static String removeFileExt(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return filename.substring(0, index);
		} else {
			return filename;
		}
	}

}
