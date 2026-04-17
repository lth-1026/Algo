import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5643_수업3 {
    static int N, adj[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(in.readLine());
            int M = Integer.parseInt(in.readLine());
            adj = new int[N + 1][N + 1];

            for(int i=0; i<=N; i++) {
                adj[i][0] = -1;
            }

            StringTokenizer st;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = 1;
            }

            int answer = 0; // 자신의 키 순서를 알 수 있는 학생 수

            for (int i = 1; i <= N; i++) {
                if(adj[i][0] == -1) gtDFS(i);
            }   //나보다 큰애들 모두 탐색이 완료됨. 간접관계가 직접관계로 다 표현됨.

            for(int i=1; i<=N; i++) {
                for(int j=1; j<= N; j++) {
                    adj[0][j] += adj[i][j];
                }
            }

            for(int i=1; i<=N; i++) {
                if(adj[i][0]+adj[0][i] == N-1) ++answer;
            }   //자신보다 큰 학생수와 작은 학생수를 더해 N-1인지 확인
            System.out.println("#" + tc + " " + answer);
        }

    }

    private static void gtDFS(int cur) {
        // 방문 처리
        // 현 정점의 인접 정점 중 미 방문 정점 따라 검색
        for (int i = 1; i <= N; i++) {
            if (adj[cur][i] == 1) {
                //나의 인접정점이 미탐색상태라면 탐색하러 가기!!!
                if(adj[i][0] == -1) gtDFS(i);

                // i정점의 탐색완료 정보를 이용해서 cur인 나의 정보를 update
                if(adj[i][0] > 0) {
                    for(int j = 1; j <= N; j++) {
                        if(adj[i][j] == 1) adj[cur][j] = 1;
                    }
                }


            }
        }

        int cnt = 0;
        for(int i=1; i<=N; i++) {
            cnt += adj[cur][i];
        }   //나보다 큰 애들 카운트(0은 덧셈에 변화 주지 않으므로 
        adj[cur][0] = cnt;
    }

}
