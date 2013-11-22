package keg.edu.mp.svm;

import java.io.IOException;

import ls.svm_predict;
import ls.svm_train;

public class MultiFeature {
	public static void main(String arg[]) {
		String filepath = "3b_P800_N808";
		// String filepath = "2b_P462_N321";
		// String filepath = "1b_P600_N540";
		int times = 4;
		FileCleaning fc = new FileCleaning(filepath, "posFile.txt",
				"negFile.txt", "trainFile.txt", "testFile.txt", 600, 100, times);
		fc.Generate();// 生成文件
		System.out.println("........SVM运行开始..........");
		double Accuracy_sum = 0;
		for (int s = 0; s < times; s++) {

			String[] arg_train = {
					".\\" + filepath + "\\" + s + "_trainFile.txt",
					".\\" + filepath + "\\mode" + s, "-t", "4", "-c", "1" };

			String[] arg_predict = {
					".\\" + filepath + "\\" + s + "_testFile.txt", // 这个是存放测试数据
					".\\" + filepath + "\\mode" + s, // 调用的是训练以后的模型
					".\\" + filepath + "\\predict" + s }; // 生成的结果的文件的路径

			try {

				svm_train.main(arg_train);
				Accuracy_sum += svm_predict.main(arg_predict);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n最后准确率：" + Accuracy_sum / times);
	}
}
