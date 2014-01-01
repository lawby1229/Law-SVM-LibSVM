package keg.edu.mp.svm;

import java.io.IOException;

import ls.svm_predict;
import ls.svm_train;

public class MultiFeature {
	public static void SVM(String folder)
	{
		String filepath = folder;
		System.out.println("........SVM运行开始..........");
		double Accuracy_sum = 0;
		String[] arg_train = { ".\\" + filepath + "\\" + "trainFile.txt",
				".\\" + filepath + "\\mode", "-t", "2" };

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
	public static void main(String arg[]) {
//		MultiFeature.SVM("multi2_behavior_C3");
//		MultiFeature.SVM("multi2_behavior_C4");
		MultiFeature.SVM("multi2_behavior_C5");
		MultiFeature.SVM("multi2_behavior_C6");
	}
}
