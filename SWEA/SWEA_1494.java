
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1494 {
    static boolean [] from;
    static int[][] positions;
    static int N;
    static long minSize;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            positions = new int[N][2];
            from = new boolean[N];
            minSize = Long.MAX_VALUE;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                positions[i][0] = x;
                positions[i][1] = y;
            }

            //10 개씩 지렁이를 선택한다.
            dfs(0, 0);
            sb.append("#").append(t).append(" ")
            .append(minSize).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int count, int start) {
        if(count == N/2) {
            //백터 크기 더하기
            long x = 0;
            long y = 0;
            for(int i=0; i<N; i++) {
                if(from[i]) {
                    x -= positions[i][0];
                    y -= positions[i][1];
                } else {
                    x += positions[i][0];
                    y += positions[i][1];
                }
            }
            long size = x*x + y*y;
            minSize = Math.min(size, minSize);
            return;
        }

        for(int i=start; i<N; i++) {
            from[i] = true;
            dfs(count+1, i+1);
            from[i] = false;
        }
    }
}
