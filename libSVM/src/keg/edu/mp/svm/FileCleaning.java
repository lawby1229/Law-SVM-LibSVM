package keg.edu.mp.svm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * 将两个格子正负例的文件生成若干个不同的文件
 * 
 * @author Law
 * 
 */
public class FileCleaning {
	String folder;
	String posFileName, negFileName, outTrainName, outTestName;
	int trainNum, testNum, fileNum;
	int posLineNum, negLineNum;
	LineNumberReader lnrPos, lnrNeg;
	FileWriter fwTrain;
	FileWriter fwTest;

	public FileCleaning(String folder, String posFileName, String negFileName,
			String outTrainName, String outTestName, int trainNum, int testNum,
			int fileNum) {
		this.folder = folder;
		this.posFileName = posFileName;
		this.negFileName = negFileName;
		this.outTrainName = outTrainName;
		this.outTestName = outTestName;
		this.trainNum = trainNum;
		this.testNum = testNum;
		this.fileNum = fileNum;
		this.posLineNum = FileStaticFunction.getLineNum(folder + "/"
				+ posFileName);
		this.negLineNum = FileStaticFunction.getLineNum(folder + "/"
				+ negFileName);
	}

	public void Generate() {
		// lnrPos = FileStaticFunction.getLNR(posFileName);
		// lnrNeg = FileStaticFunction.getLNR(negFileName);

		try {
			int k = 0;
			int testStart = 0;
			while (k < fileNum) {
				lnrPos = FileStaticFunction.getLNR(folder + "/" + posFileName);
				lnrNeg = FileStaticFunction.getLNR(folder + "/" + negFileName);

				fwTrain = FileStaticFunction.getFW(folder + "/" + k + "_"
						+ outTrainName);
				fwTest = FileStaticFunction.getFW(folder + "/" + k + "_"
						+ outTestName);
				String line = lnrPos.readLine();
				int i = 0;
				while (line != null) {
					if ((testStart + testNum <= posLineNum && i >= testStart && i < testStart
							+ testNum)
							|| (testStart + testNum > posLineNum && (i > testStart || i < (testStart + testNum)
									% posLineNum)))
						FileStaticFunction.writeString(fwTest, line + "\n");
					else
						FileStaticFunction.writeString(fwTrain, line + "\n");
					line = lnrPos.readLine();
					i++;
				}
				i = 0;
				line = lnrNeg.readLine();
				while (line != null) {
					if ((testStart + testNum <= negLineNum && i >= testStart && i < testStart
							+ testNum)
							|| (testStart + testNum > negLineNum && (i > testStart || i < (testStart + testNum)
									% negLineNum)))
						FileStaticFunction.writeString(fwTest, line + "\n");
					else
						FileStaticFunction.writeString(fwTrain, line + "\n");
					line = lnrNeg.readLine();
					i++;
				}

				FileStaticFunction.closeLNR(lnrPos);
				FileStaticFunction.closeLNR(lnrNeg);
				FileStaticFunction.closeFW(fwTrain);
				FileStaticFunction.closeFW(fwTest);
				testStart += testNum;
				k++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
