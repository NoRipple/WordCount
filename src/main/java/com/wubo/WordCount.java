package main.java.com.wubo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classname: WordCount
 * Package: main.java.com.wubo
 * Description:
 * 程序统计文本文件（纯英文）的字符数或者单词数。空格，水平制表符，换行符均算字符。由空格或逗号分割开的都视为单词，且不做单词的有效性校验，例如：thi#,that视为用逗号隔开的2个单词。
 * （3）输入输出说明
 * 	输入： [parameter] [input_file_name]
 * 	注： [parameter]为控制参数，取”-c”和”-w”之一；[input_file_name]为要被处理的文本文件名，默认存放在执行文件目录下。
 * 	输出：根据[parameter]为以下形式之一
 * 		字符数：n
 * 		单词数：n
 * 		其中n为统计的字符数或单词数
 *
 * @Author: No_Ripple(吴波)
 * @Creat： - 16:38
 * @Version: v1.0
 */
public class WordCount {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TextStatistics [-c|-w] input_file_name");
            return;
        }//second

        String parameter = args[0];
        String inputFile = args[1];

        try {
            int count = getCount(parameter, inputFile);
            if ("c".equals(parameter)) {
                System.out.println("字符数：" + count);
            } else if ("w".equals(parameter)) {
                System.out.println("单词数：" + count);
            } else {
                System.out.println("错误的参数。请输入'-c'统计字符数或'-w'统计单词数。");
            }
        } catch (IOException e) {
            System.err.println("读取文件时发生错误：" + e.getMessage());
        }
    }

    private static int getCount(String type, String fileName) throws IOException {
        int count = 0;
        boolean isWordCount = "w".equals(type);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isWordCount) {
                    // 统计单词数，以空格或逗号作为分隔符
                    String[] words = line.split("[ ,]+");
                    count += words.length;
                } else {
                    // 统计字符数，包括空格、制表符、换行符等
                    count += line.length();
                }
            }
        }
        return count;
    }
}
