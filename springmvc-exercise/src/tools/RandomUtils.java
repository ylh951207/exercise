package tools;

import java.util.Random;

/**
 * @author: yanglh
 * @ className: RandomUtils
 * @ description: 生成随机工具
 * @ create 2021-03-10
 **/
public class RandomUtils {

    /**
     * 根据提供的字典库，生成固定位数的随机串
     * @param dictionary 字典库
     * @param numCount 位数
     * @return 生成的随机串
     */
    public static String generateRandomString(String dictionary, int numCount) {
        if (dictionary == null || dictionary.length() == 0) {
            throw new RuntimeException("dictionary is empty");
        }
        if (numCount <= 0 || numCount > 50) {
            throw new RuntimeException("numCount must between 1 and "+ "50");
        }
        StringBuilder sbRandom = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numCount; i++) {
            int index = random.nextInt(dictionary.length());
            char c = dictionary.charAt(index);
            sbRandom.append(c);
        }
        return sbRandom.toString();
    }

}
