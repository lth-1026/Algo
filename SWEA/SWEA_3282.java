
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] bags = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                bags[i][0] = v;
                bags[i][1] = c;
            }

            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int k = 1; k <= K; k++) {
                    if (bags[i][0] > k) {
                        dp[i][k] = dp[i - 1][k];
                    } else {
                        dp[i][k] = Math.max(bags[i][1] + dp[i - 1][k - bags[i][0]], dp[i - 1][k]);
                    }
                }
            }

            sb.append("#").append(t).append(" ")
            .append(dp[N][K]).append("\n");
        }
        System.out.println(sb);
    }
}
