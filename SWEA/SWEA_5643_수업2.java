import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5643_수업2 {
    static int N, adj[][], rAdj[][];
    static int cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(in.readLine());
            int M = Integer.parseInt(in.readLine());
            adj = new int[N + 1][N + 1];
            rAdj = new int[N + 1][N + 1];

            StringTokenizer st;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = 1;
                rAdj[b][a] = 1;
            }

            int answer = 0; // 자신의 키 순서를 알 수 있는 학생 수

            for (int i = 1; i <= N; i++) {
                cnt = 0;
                boolean[] visited = new boolean[N + 1];
                dfs(i, adj, visited);
                dfs(i, rAdj, visited);
                if (cnt == N - 1) {
                    ++answer;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

    }

    private static void dfs(int cur, int[][] adj, boolean[] visited) {
        // 방문 처리
        visited[cur] = true;
        // 현 정점의 인접 정점 중 미 방문 정점 따라 검색
        for (int i = 1; i <= N; i++) {
            if (adj[cur][i] == 1 && !visited[i]) {
                ++cnt;
                dfs(i, adj, visited);
            }
        }
    }
}
