package org.example.threads;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task21 {

    public void printListToConsole(List<int []> inputList) {
        inputList.forEach(i -> {
            System.out.println(i);
            for (int j = 0; j < i.length; j++) {
                System.out.println(i[j]);
            }
        });

    }

    class WriteCurrentArrayToFile implements Runnable {
        @Override
        public void run() {
            try {
                File file = new File(fileName);
                System.out.println("file`s absolute path: " + file.getAbsolutePath());
                FileOutputStream os = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(os);
                for (int i = 0; i < inputArray.length; i++) {
                    pw.print(inputArray[i]);
                }
                System.out.println("array number " + this.threadNumberCount + " written to file" + fileName);

                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        int [] inputArray;
        String fileName;
        String threadName;
        int threadNumberCount;

        public WriteCurrentArrayToFile(int [] inputArray, String fileName, String threadName, int count) {
            this.inputArray = inputArray;
            this.fileName = fileName;
            this.threadName = threadName;
        }
    }

    class ReadCurrentArrayFromFile implements Runnable {

        public ReadCurrentArrayFromFile() {}
        @Override
        public void run() {

        }
    }

    public void startThreads(List<int []> inputList,String inputFileName) {
        for (int i = 0; i < inputList.size(); i++) {
            String currentThreadName = "t" + i;
            String currentFileName = "f" + i + ".txt";
            int [] currentArray = inputList.get(i);
            WriteCurrentArrayToFile currentThread =
                    new WriteCurrentArrayToFile(currentArray,currentFileName,currentThreadName, i);
            Thread thread = new Thread(currentThread);
            thread.start();
        }
        System.out.println("work done");
    }


    public static void main(String[] args) {
        List<int[]> inputList = new ArrayList<int[]>();
        int [] array1 = {1,2,3};
        int [] array2 = {4,5,6};
        int [] array3 = {7,8,9};
        inputList.addAll(Arrays.asList(array1,array2,array3));
        Task21 task21 = new Task21();
//        task21.printListToConsole(inputList);
        task21.startThreads(inputList,"file");
    }
}
