
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4038 {
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        long MOD1 = 1_000_000_007;
        long MOD2 = 1_000_000_009;
        long P1 = 31;
        long P2 = 37;

        for (int t = 1; t <= T; t++) {
            String text = br.readLine();
            String pattern = br.readLine();

            int n = text.length();
            int m = pattern.length();

            long patternHash1 = 0;
            long patternHash2 = 0;
            long currentHash1 = 0;
            long currentHash2 = 0;
            long power1 = 1;
            long power2 = 1;

            int count = 0;

            for (int i = 0; i < m; i++) {
                patternHash1 = (P1 * patternHash1 + pattern.charAt(i)) % MOD1;
                patternHash2 = (P2 * patternHash2 + pattern.charAt(i)) % MOD2;
                currentHash1 = (P1 * currentHash1 + text.charAt(i)) % MOD1;
                currentHash2 = (P2 * currentHash2 + text.charAt(i)) % MOD2;

                if (i < m - 1) {
                    power1 = (power1 * P1) % MOD1;
                    power2 = (power2 * P2) % MOD2;
                }
            }

            for (int i = 0; i <= n - m; i++) {
                if ((patternHash1 == currentHash1) && (patternHash2 == currentHash2)) {
                    count++;
                }

                if (i < n - m) {
                    // 1. (현재 해시 - 맨 앞 문자 해시)를 구함. 이때 음수 방지를 위해 MOD를 더함
                    long removal1 = (text.charAt(i) * power1) % MOD1;
                    long removal2 = (text.charAt(i) * power2) % MOD2;
                    currentHash1 = (currentHash1 - removal1 + MOD1) % MOD1;
                    currentHash2 = (currentHash2 - removal2 + MOD2) % MOD2;

                    // 2. 한 칸 밀고(P 곱하기) 새로운 문자 추가 후 다시 MOD
                    currentHash1 = (currentHash1 * P1 + text.charAt(i + m)) % MOD1;
                    currentHash2 = (currentHash2 * P2 + text.charAt(i + m)) % MOD2;
                }
            }

            sb.append("#").append(t).append(" ")
                    .append(count).append("\n");
        }
        System.out.println(sb);
    }
}