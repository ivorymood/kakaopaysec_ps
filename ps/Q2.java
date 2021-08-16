package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");

        int[][] map = new int[y + 1][x + 1];
        for (int i = 1; i <= y; ++i) {
            for (int j = 1; j <= x; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(map, x, y));
        br.close();
    }


    public static int solution(final int[][] map, final int X, final int Y) {

        // 각 행의 (l, r)위치 누적합을 저장하기 위한 메모이제이션
        int[][][] memoSum = new int[Y + 1][X + 1][X + 1];
        boolean[][][] visited = new boolean[Y + 1][X + 1][X + 1];
        visited[0][1][X] = true;

        for (int depth = 1; depth <= Y; ++depth) {
            for (int l = 1; l <= Math.min(depth, X); ++l) {
                for (int r = Math.max(1, X - depth + 1); r <= X; ++r) {

                    // [depth][l][r] 위치에서의 누적합 = 이전 depth에서 이동할 수 있는 위치의 누적합 중 최대값 + 현재 위치 (l, r) 값의 합
                    int maxBefore = getMaxBefore(memoSum, visited, l, r, X, depth);
                    int nowSum = (l == r) ? map[depth][l] : map[depth][l] + map[depth][r];
                    memoSum[depth][l][r] = maxBefore + nowSum;
                    visited[depth][l][r] = true;
                }
            }
        }

        return getMaxCurrent(memoSum, visited, X, Y);
    }

    public static int getMaxCurrent(final int[][][] memoSum, final boolean[][][] visited, final int X, final int depth) {
        int max = Integer.MIN_VALUE;
        for (int l = 1; l <= X; ++l) {
            for (int r = 1; r <= X; ++r) {
                if (visited[depth][l][r]) {
                    max = Math.max(max, memoSum[depth][l][r]);
                }
            }
        }
        return max;
    }

    public static int getMaxBefore(final int[][][] memoSum, final boolean[][][] visited, final int L, final int R, final int X, final int depth) {
        int max = Integer.MIN_VALUE;
        for (int bfL = L - 1; bfL <= L + 1; ++bfL) {

            if (!checkRange(1, X, bfL)) {
                continue;
            }
            for (int bfR = R - 1; bfR <= R + 1; ++bfR) {
                if (!(checkRange(1, X, bfR) && visited[depth - 1][bfL][bfR])) {
                    continue;
                }
                max = Math.max(max, memoSum[depth - 1][bfL][bfR]);
            }
        }
        return max;
    }

    public static boolean checkRange(final int leftLimit, final int rightLimit, final int idx) {
        return leftLimit <= idx && idx <= rightLimit;
    }
}
