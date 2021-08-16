package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Q1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Q1 q1 = new Q1();
        System.out.println(q1.solution(br.readLine()));
        br.close();
    }

    public String solution(String str) {

        Map<String, String> dict = new HashMap<>();
        str = "1[" + str + ']';

        dfs(str.toCharArray(), dict, 1, 0, 2);

        return dict.get(str);
    }

    public int dfs(char[] chars, Map<String, String> dict, int rpCnt, int cntSrtIdx, int idx) {

        StringBuilder sb = new StringBuilder();

        while (idx < chars.length && chars[idx] != ']') {

            if (!Character.isDigit(chars[idx])) {
                sb.append(chars[idx++]);
                continue;
            }

            /*
                - 현재 문자가 숫자일 경우, dfs 실행
                - 반복할 횟수, 숫자의 시작 인덱스, 열기괄호 다음 인덱스 값을 매개변수로 넘김
                - 닫기괄호가 끝난 후 다음 인덱스 값 받아옴
             */
            int innerRpCnt = 0;
            int innerIdx = idx;
            while (innerIdx < chars.length && chars[innerIdx] != '[') {
                innerRpCnt = innerRpCnt * 10 + chars[innerIdx++] - '0';
            }

            int nextIdx = dfs(chars, dict, innerRpCnt, idx, innerIdx + 1);
            sb.append(dict.get(chars2String(chars, idx, nextIdx)));
            idx = nextIdx;
        }

        /*
            - (key, value) = (number[content], number * content) 형태로 dict에 저장
            - ex) (3[a], aaa)
         */
        dict.put(chars2String(chars, cntSrtIdx, idx + 1), repeatString(sb.toString(), rpCnt));
        return idx + 1;
    }

    public static String chars2String(char[] chars, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; ++i) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static String repeatString(String str, int cnt) {
        StringBuilder sb = new StringBuilder();
        while (cnt-- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }
}
