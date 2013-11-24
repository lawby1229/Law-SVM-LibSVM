package keg.edu.mp.svm;

import java.io.IOException;

import ls.svm_predict;
import ls.svm_train;

public class MultiFeature {
	public static void main(String arg[]) {
		String filepath = "2m";
		System.out.println("........SVM运行开始..........");
		double Accuracy_sum = 0;
		String[] arg_train = { ".\\" + filepath + "\\" + "trainFile.txt",
				".\\" + filepath + "\\mode", "-t", "1", "-c", "100","-g","0.5" };

		String[] arg_predict = { ".\\" + filepath + "\\" + "testFile.txt", // 这个是存放测试数据
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
}
