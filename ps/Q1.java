package ps;

import java.util.HashMap;
import java.util.Map;

public class Q1 {

    public static void main(String[] args) {

        String str = "3[a10[c]]f2[z]";
        System.out.println(solution(str));
    }

    public static String solution(String str) {

        Map<String, String> dict = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("1[");
        sb.append(str);
        sb.append(']');
        char[] chars = sb.toString().toCharArray();

        dfs(chars, dict, 0, 2, 1);

        return dict.get(sb.toString());
    }

    public static int dfs(char[] chars, Map<String, String> dict, int numStart, int start, int num) {

        int idx = start;
        StringBuilder sb = new StringBuilder();

        while (idx < chars.length && chars[idx] != ']') {

            if (!Character.isDigit(chars[idx])) {
                sb.append(chars[idx]);
                ++idx;
                continue;
            }

            int nextNum = 0;
            int nextStart = idx;
            while (nextStart < chars.length && chars[nextStart] != '[') {
                nextNum = nextNum * 10 + chars[nextStart] - '0';
                ++nextStart;
            }

            int next = dfs(chars, dict, idx, nextStart + 1, nextNum);
            sb.append(dict.get(chars2String(chars, idx, next)));
            idx = next;
        }

        // num[문자열] : num * 문자열
        String repeated = repeatString(num, sb.toString());
        dict.put(chars2String(chars, numStart, idx + 1), repeated);
        return idx + 1;
    }

    public static String chars2String(char[] chars, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; ++i) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static String repeatString(int cnt, String str) {
        StringBuilder sb = new StringBuilder();
        while(cnt-- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }
}
