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

import ls.*;
import ls.libsvm.*;

public class Future {

	public static void main(String law[]) {

		// *******************************************************************
		// String[] arg = { ".\\heart_scale", // 存放SVM训练模型用的数据的路径
		// ".\\mode", "-t", "0" }; // 存放SVM通过训练数据训练出来的模型的路径 -t是核函数
		//
		// String[] parg = { ".\\heart_scale", // 这个是存放测试数据
		// ".\\mode", // 调用的是训练以后的模型
		// ".\\predict" }; // 生成的结果的文件的路径
		// *****************************************************************************
		// Future f = new Future("D:\\移动原始数据\\移动\\校园用户用户整体清单_no.csv", 3);

		// String[] arg1 = { ".\\train_file1", // 存放SVM训练模型用的数据的路径
		// ".\\mode1" }; // 存放SVM通过训练数据训练出来的模型的路径
		// String[] arg2 = { ".\\train_file2", // 存放SVM训练模型用的数据的路径
		// ".\\mode2" }; // 存放SVM通过训练数据训练出来的模型的路径

		// String[] arg01 = { ".\\train_file0", ".\\mode0_l" };
		// String[] arg12 = { ".\\train_file1", ".\\mode1_l" };
		// String[] arg02 = { ".\\train_file2", ".\\mode2_l" };
		// ---------------------------------------------------------------------------------------------------

		// String[] parg0_2 = { ".\\train_file2", // 这个是存放测试数据
		// ".\\mode0", // 调用的是训练以后的模型
		// ".\\predict0_2" }; // 生成的结果的文件的路径
		//
		// String[] parg1_0 = { ".\\train_file0", // 这个是存放测试数据
		// ".\\mode1", // 调用的是训练以后的模型
		// ".\\predict1_0" }; // 生成的结果的文件的路径
		// String[] parg1_2 = { ".\\train_file2", // 这个是存放测试数据
		// ".\\mode1", // 调用的是训练以后的模型
		// ".\\predict1_2" }; // 生成的结果的文件的路径
		//
		// String[] parg2_0 = { ".\\train_file0", // 这个是存放测试数据
		// ".\\mode2", // 调用的是训练以后的模型
		// ".\\predict2_0" }; // 生成的结果的文件的路径
		// String[] parg2_1 = { ".\\train_file1", // 这个是存放测试数据
		// ".\\mode2", // 调用的是训练以后的模型
		// ".\\predict2_1" }; // 生成的结果的文件的路径

		// String[] parg01_2 = { ".\\train_file2", ".\\mode01", ".\\predict01_2"
		// };
		// String[] parg12_0 = { ".\\train_file0", ".\\mode12", ".\\predict12_0"
		// };
		// String[] parg02_1 = { ".\\train_file1", ".\\mode02", ".\\predict02_1"
		// };

		// String filepath = "2b_P462_N321";
		String filepath = "1b_P600_N540";
		int times = 5;
		FileCleaning fc = new FileCleaning(filepath, "posFile.txt",
				"negFile.txt", "trainFile.txt", "testFile.txt", 400, 100, times);
		fc.Generate();// 生成文件
		System.out.println("........SVM运行开始..........");

		for (int s = 0; s < times; s++) {
			String[] arg_scale_train = { "-l", "0", "-u", "1", "-s", "range1",
					".\\" + filepath + "\\" + s + "_trainFile.txt" };

			String[] arg_scale_test = { "-l", "0", "-u", "1", "-r", "range1",
					".\\" + filepath + "\\" + s + "_testFile.txt" };

			String[] arg_train = {
					".\\" + filepath + "\\" + s + "_trainFile.txt",
					".\\" + filepath + "\\mode" + s, "-t", "1" };

			String[] arg_predict = {
					".\\" + filepath + "\\" + s + "_testFile.txt", // 这个是存放测试数据
					".\\" + filepath + "\\mode" + s, // 调用的是训练以后的模型
					".\\" + filepath + "\\predict" + s }; // 生成的结果的文件的路径

			try {
				// svm_scale.main(arg_scale_train);
				// svm_scale.main(arg_scale_test);
				svm_train.main(arg_train);
				svm_predict.main(arg_predict);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// svm_train.main(arg1);
		// svm_train.main(arg2);

		// svm_predict.main(parg0_1);
		// svm_predict.main(parg0_2);
		// svm_predict.main(parg1_0);
		// svm_predict.main(parg1_2);
		// svm_predict.main(parg2_0);
		// svm_predict.main(parg2_1);
		// svm_train.main(arg01);
		// svm_train.main(arg12);
		// svm_train.main(arg02);
		// svm_predict.main(parg01_2);
		// svm_predict.main(parg12_0);
		// svm_predict.main(parg02_1);

	}
}
