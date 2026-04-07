import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117 {
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int map[][] = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxCount = 0;

            //각 위치마다 확인 
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //각 위치와 집간의 거리를 확인
                    int[] dis = new int[2*N];

                    for(int r=0; r<N; r++) {
                        for(int c=0; c<N; c++) {
                            //빈 곳이면 무시
                            if(map[r][c] == 0) continue;

                            int distance = Math.abs(i-r) + Math.abs(j-c);
                            dis[distance]++;
                        }
                    }
                    
                    int accum = 0;
                    for(int k=1; k<2*N; k++) {
                        int cost = -1 * (k * k + (k-1) * (k-1));
                        accum += dis[k-1];
                        int profit = cost + M*accum;

                        //손해면 무시
                        if(profit < 0) continue;

                        maxCount = Math.max(maxCount, accum);
                    }
                }
            }

            sb.append("#").append(t).append(" ")
            .append(maxCount).append("\n");
        }
        System.out.println(sb);
    }
}
