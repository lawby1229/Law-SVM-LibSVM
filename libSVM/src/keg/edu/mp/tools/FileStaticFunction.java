package keg.edu.mp.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class FileStaticFunction {
	/**
	 * 帮助方法,通过f_b写入Content内容
	 * 
	 * @param f_b
	 * @param Content
	 */
	public static void writeString(FileWriter fw, String Content) {
		try {

			fw.write(Content);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}// TODO add your handling code here:
	}

	/**
	 * 帮助方法,读取文件流的封装
	 * 
	 * @param Filename
	 * @return
	 */
	public static LineNumberReader getLNR(String Filename) {
		File infile = new File(Filename);
		FileInputStream f;
		LineNumberReader f_b = null;
		try {
			f = new FileInputStream(infile);
			f_b = new LineNumberReader(new InputStreamReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return f_b;
	}

	public static void closeLNR(LineNumberReader lr) {
		try {
			lr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 帮助方法,输出数据流的封装
	 * 
	 * @param Filename
	 * @return
	 */
	public static FileWriter getFW(String Filename) {

		FileWriter fw = null;
		try {
			fw = new FileWriter(Filename, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fw;
	}

	public static void closeFW(FileWriter fw) {

		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getLineNum(String fileName) {
		LineNumberReader lnr = FileStaticFunction.getLNR(fileName);
		int i = 0;
		String s;
		try {
			s = lnr.readLine();
			while (s != null) {
				i++;
				s = lnr.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			FileStaticFunction.closeLNR(lnr);
		}

		return i;
	}
}
