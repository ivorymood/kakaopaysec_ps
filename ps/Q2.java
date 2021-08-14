package ps;

import java.util.StringTokenizer;

public class Q2 {

    /*
    2. 가로가 x, 세로가 y이고 각 칸에는 숫자 s가 적혀 있는 말판이 있습니다.
    네오와 프로도는 자기 말을 각각 왼쪽 상단과 오른쪽 상단에 두고 게임을 시작하려고 합니다.
    말은 자기의 위치에서 아래 3칸(왼쪽아래, 아래, 오른쪽아래)중에 한곳으로만 이동할 수 있고,
    두 말이 이동한 위치에 적힌 숫자의 총합이 게임의 점수가 됩니다.
    각 칸에 점수가 적힌 말판이 주어질 때, 네오와 프로도가 얻을 수 있는 가장 높은 점수를 구하는 코드를 작성해주세요.

    규칙
    - 말판의 가로 x, 세로 y의 범위는 3 < x, y < 50,
    - 각 칸의 점수 s의 범위는 -100 < s < 100
    - 입력은 2차원 배열로 주어집니다.
    - 말은 말판 밖으로 나갈 수 없습니다.
    - 두 말이 같은 칸으로 이동할 수 있지만, 점수는 한명에게만 주어집니다.
    - 입력처리
     첫번째 입력 : 가로 x 값
     두번째 입력 : 세로 y 값
     세번째 입력 : 맵 데이터 , 로 구분

    예제 입출력
    입력 :
    3
    4
    3,1,1,2,5,1,1,5,5,2,1,1
    출력 : 24
    입력 :
    7
    5
    1,0,0,0,0,0,1,2,0,0,0,0,3,0,2,0,9,0,0,0,0,0,3,0,5,4,0,0,1,0,2,3,0,0,6
    출력 : 28
    */
    public static void main(String[] args) {

        int x = 4;
        int y = 4;
        String nums = "-10,-10,-10,-10,-20,-20,-20,-20,-30,-30,-30,-30,-40,-40,-40,-40";

        StringTokenizer st = new StringTokenizer(nums, ",");

        int[][] map = new int[y + 1][x + 1];
        for (int i = 1; i <= y; ++i) {
            for (int j = 1; j <= x; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(x, y, map));
    }

    public static int solution(final int X, final int Y, int[][] map) {

        // dp, 메모이제이션
        int[][][] memoSum = new int[Y + 1][X + 1][X + 1];
        boolean[][][] visited = new boolean[Y + 1][X + 1][X + 1];
        visited[0][1][X] = true;

        for (int depth = 1; depth <= Y; ++depth) {
            for (int l = 1; l <= Math.min(depth, X); ++l) {
                for (int r = Math.max(1, X - depth + 1); r <= X; ++r) {

                    int maxBefore = getMaxBefore(memoSum, visited, l, r, X, depth);
                    int nowSum = (l == r) ? map[depth][l] : map[depth][l] + map[depth][r];
                    memoSum[depth][l][r] = maxBefore + nowSum;
                    visited[depth][l][r] = true;
                }
            }
        }

        return getMax(memoSum, visited, X, Y);
    }

    public static int getMax(int[][][] memoSum, boolean[][][] visited, final int X, int depth) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= X; ++i) {
            for (int j = 1; j <= X; ++j) {
                if (visited[depth][i][j]) {
                    max = Math.max(max, memoSum[depth][i][j]);
                }
            }
        }
        return max;
    }

    public static int getMaxBefore(int[][][] memoSum, boolean[][][] visited, final int L, final int R, int X, int depth) {
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
    
    public static boolean checkRange(int leftLimit, int rightLimit, int idx) {
        return leftLimit <= idx && idx <= rightLimit;
    }
}
