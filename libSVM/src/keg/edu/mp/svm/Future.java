package keg.edu.mp.svm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import keg.edu.mp.tools.FileCleaning;

import ls.*;
import ls.libsvm.*;

public class Future {
	public static void predictChangbleBrand(String folder) {
		String filepath = folder;
		// String filepath = "2b_P462_N321";
		// String filepath = "1b_P600_N540";
		int times = 4;
		FileCleaning fc = new FileCleaning(filepath, "posFile.txt",
				"negFile.txt", "trainFile.txt", "testFile.txt", 600, 100, times);
		fc.Generate();// 生成文件
		System.out.println("........SVM运行开始..........");
		double Accuracy_sum = 0;
		for (int s = 0; s < times; s++) {
			String[] arg_scale_train = { "-l", "0", "-u", "1", "-s", "range1",
					".\\" + filepath + "\\" + s + "_trainFile.txt" };

			String[] arg_scale_test = { "-l", "0", "-u", "1", "-r", "range1",
					".\\" + filepath + "\\" + s + "_testFile.txt" };

			String[] arg_train = {
					".\\" + filepath + "\\" + s + "_trainFile.txt",
					".\\" + filepath + "\\mode" + s, "-t", "4", "-c", "1" };

			String[] arg_predict = {
					".\\" + filepath + "\\" + s + "_testFile.txt", // 这个是存放测试数据
					".\\" + filepath + "\\mode" + s, // 调用的是训练以后的模型
					".\\" + filepath + "\\predict" + s }; // 生成的结果的文件的路径

			String[] arg_train_scale = {
					".\\" + filepath + "\\" + s + "_trainFile.txt.scale",
					".\\" + filepath + "\\mode" + s, "-t", "1" };
			String[] arg_predict_scale = {
					".\\" + filepath + "\\" + s + "_testFile.txt.scale", // 这个是存放测试数据
					".\\" + filepath + "\\mode" + s, // 调用的是训练以后的模型
					".\\" + filepath + "\\predict" + s }; // 生成的结果的文件的路径

			try {
				// svm_scale.main(arg_scale_train);
				// svm_scale.main(arg_scale_test);

				svm_train.main(arg_train);
				Accuracy_sum += svm_predict.main(arg_predict);

				// svm_train.main(arg_train_scale);
				// svm_predict.main(arg_predict_scale);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n最后准确率：" + Accuracy_sum / times);
	}

	public static void predictChangeWitchBrand(String folder) {
		System.out.println("........SVM运行开始..........");
		String filepath = folder;
		double Accuracy_sum = 0;
		String[] arg_train = { ".\\" + filepath + "\\" + "trainFile.txt",
				".\\" + filepath + "\\mode", "-t", "0", "-c", "1" };

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

	// *******************************************************************
	// String[] arg = { ".\\heart_scale", // 存放SVM训练模型用的数据的路径
	// ".\\mode", "-t", "0" }; // 存放SVM通过训练数据训练出来的模型的路径 -t是核函数
	//
	// String[] parg = { ".\\heart_scale", // 这个是存放测试数据
	// ".\\mode", // 调用的是训练以后的模型
	// ".\\predict" }; // 生成的结果的文件的路径
	// *****************************************************************************
	// ---------------------------------------------------------------------------------------------------

	public static void main(String law[]) {
		// 预测是否换手机
		Future.predictChangbleBrand("3b_P800_N808");
		// 预测换什么品牌的手机
		// svm_train.main(arg1);
		// svm_train.main(arg2);

	}

}
