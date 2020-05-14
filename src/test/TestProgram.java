package test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

public class TestProgram {
    public static void main(String[] args) {
        try {
            final int THRESHOLD1 = 10000;
            final int THRESHOLD1_1 = THRESHOLD1 - 1;

            final int THRESHOLD2 =10000;
            final int THRESHOLD2_1 = THRESHOLD2 - 1;

            FileWriter fileWriter = new FileWriter("G:\\Masters CS\\Study\\Fall 2019\\Homeworks\\Artificial Intelligence\\Homework1\\src\\input.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("A*");
            printWriter.println(THRESHOLD1 + " " + THRESHOLD2);
//            printWriter.println("0 0");
            printWriter.println("0 0");
            printWriter.println("5");

//            int a = 3;
//            int b = 4;

//            int a = THRESHOLD1;
//            int b = THRESHOLD2;
//            int numberoftargets = a * b;
//            printWriter.println("" + numberoftargets);
//            for (int x = 0; x < a; x++) {
//                for (int y = 0; y < b; y++) {
//                    printWriter.println(x + " " + y);
//                }
//            }
//

            printWriter.println("1");
//            printWriter.println(THRESHOLD1_1 + " " + THRESHOLD2_1);
            printWriter.println(9999 + " " + 9999);
//            printWriter.println(1 + " " + 3);
//            printWriter.println(1 + " " + 4);
//            printWriter.println(1 + " " + 5);
            for (int i = 0; i < THRESHOLD2; i++) {
                if (i != 0) {
                    printWriter.print("\n");
                }
                for (int j = 0; j < THRESHOLD1; j++) {
                    if (j == THRESHOLD1_1) {
//                        printWriter.print(new Random().ints(-7, 7).findAny().getAsInt());
                        printWriter.print("0");
                    } else {
//                        printWriter.print(new Random().ints(-7, 7).findAny().getAsInt() + " ");
                        printWriter.print("0 ");
                    }
                }
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}