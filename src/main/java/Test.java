import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 类名称：Test
 * ***********************
 * <p>
 * 类描述：
 *
 * @author deng on 2021/1/10 01:48
 */
public class Test {
    public static void main(String[] args) {
//        boolean test = test(12121);
//        System.out.println(test);
        int test1 = test1("pwwkew");
        System.out.println(test1);
    }

    private static boolean test(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return Objects.equals(x + "", reversedStr);
    }

    private static int test1(String s ) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            set.add(s.charAt(i));
        }
        return set.size();
    }
}