package com.oceam.controller.utils;

import java.io.File;
import java.security.MessageDigest;

/**
 * 文件操作相关工具
 * 
 * @author SYF
 * @creationtime 2014-8-7下午1:33:51
 */
public class FileUtil {

	/******************************** MD5相关 ********************************/
	private static MessageDigest MD5 = null;

	/**
	 * 删除指定文件（文件夹）
	 * 
	 * @param file
	 */
	public static void deleteDir(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					deleteDir(files[i]);// 把每个文件用这个方法进行迭代
				}
			}
			delete(file);
		} else {
		}
	}
	
	/**
	 * 删除指定文件（文件夹）
	 * 
	 * @param file
	 */
	public static void deleteAppointDir(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					if(files[i].toString().endsWith("region.json")){ //除地区信息文件其他全部删除
						
					}else{
						delete(files[i]);// 把每个文件用这个方法进行迭代
					}
				}
			}
		} else {
			
		}
	}

	/**
	 * 文件或文件夹的删除 如果是文件则直接删除 不是则先删除子文件或文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static boolean delete(String path) {
		File file = new File(path);
		if (file != null && file.exists()) {
			return delete(file);
		}
		return true;
	}

	/**
	 * 文件或文件夹的删除 如果是文件则直接删除 不是则先删除子文件或文件夹
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delete(File file) {
		if (file.exists()) {
			try {
				if (file.isDirectory()) {

					// 是文件夹则遍历先删除子文件及文件夹
					File[] files = file.listFiles();
					if (files != null && files.length > 0) {
						for (File f : files) {
							if (f.isDirectory()) {
								// 再次遍历子文件删除
								delete(f);
							} else {
								// 直接删除
								f.delete();
							}
						}
					}
					file.delete();
				} else {
					// 是文件则直接删除
					file.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
