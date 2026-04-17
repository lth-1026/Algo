
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5643 {
    final static int INF = 100000;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            int[][] dp = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = INF;
                }
                dp[i][i] = 0;
            }

            StringTokenizer st;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                dp[a][b] = 1;
            }

            for (int k = 1; k <= N; k++) {  //경유 학생 1
                for (int i = 1; i <= N; i++) {  //출발 학생 1
                    if(i==k || dp[i][k] == 0) continue;
                    for (int j = 1; j <= N; j++) {
                        if(dp[i][j] == 1) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                        // dp[i][j] = dp[i][k] & dp[k][j];
                    }
                }
            }

            int totalCount = 0;
            for (int i = 1; i <= N; i++) {
                int connected = 0;
                for (int j = 1; j <= N; j++) {
                    if (dp[i][j] != INF || dp[j][i] != INF) {
                        connected++;
                    }
                }
                if (connected == N)
                    totalCount++;
            }

            sb.append("#").append(t).append(" ")
                    .append(totalCount).append("\n");
        }
        System.out.println(sb);
    }
}
