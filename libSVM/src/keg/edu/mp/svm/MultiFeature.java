package keg.edu.mp.svm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import keg.edu.mp.tools.FileStaticFunction;

import ls.svm_predict;
import ls.svm_train;

public class MultiFeature {
	/**
	 * 删除folder目录下fileName文件中的所有标签是label的数据
	 * 
	 * @param folder
	 * @param fileName
	 * @param newFileName
	 * @param label
	 */
	public static void deleteLabel(String folder, String fileName,
			String newFileName, String label) {
		LineNumberReader lnr = FileStaticFunction.getLNR(folder + "/"
				+ fileName);
		FileWriter fw = FileStaticFunction.getFW(folder + "/" + newFileName);

		try {
			String line = lnr.readLine();
			while (line != null) {
				if (!line.split(" ")[0].equals(label)) {
					fw.write(line + "\n");
				}
				line = lnr.readLine();
			}
			fw.flush();
			fw.close();
			lnr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void SVM(String folder) {
		String filepath = folder;
		System.out.println("........SVM运行开始..........");
		double Accuracy_sum = 0;
		String[] arg_train = { ".\\" + filepath + "\\" + "trainFile1.libsvm",
				".\\" + filepath + "\\mode", "-t", "2" };

		String[] arg_predict = { ".\\" + filepath + "\\" + "testFile1.libsvm", // 这个是存放测试数据
				".\\" + filepath + "\\mode", // 调用的是训练以后的模型
				".\\" + filepath + "\\predict" }; // 生成的结果的文件的路径

		try {
			svm_train.main(arg_train);
			Accuracy_sum += svm_predict.main(arg_predict);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n最后准确率：" + Accuracy_sum);
	}

	public static void main(String arg[]) {
		// MultiFeature.SVM("multi2_behavior_C3");
		// MultiFeature.SVM("multi2_behavior_C4");
		// MultiFeature.SVM("multi2_behavior_C5");
		// MultiFeature.deleteLabel("multi_X_host_C11", "trainFile2.txt",
		// "trainFile3.txt", "8");
		// MultiFeature.deleteLabel("multi_X_host_C11", "testFile2.txt",
		// "testFile3.txt", "8");
		MultiFeature.SVM("./x8_c4-c15_top3k_g500_beh5_1001/c8");
	}
}
