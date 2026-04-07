import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class CT_5_1_3_P5 {
    public static void main(String[] args) {
        int N, G;
        int count = 0;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        G = sc.nextInt();

        // 각 사람이 가진 그룹을 의미
        List<Set<Integer>>[] p = new List[N + 1];
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            p[i] = new ArrayList<>();
        }

        // 각 사람이 가지고 있는 그룹을 입력받음
        for (int i = 1; i <= G; i++) {
            int size = sc.nextInt();
            Set<Integer> group = new HashSet<>();
            for (int j = 0; j < size; j++) {
                int person = sc.nextInt();
                p[person].add(group);
                group.add(person);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;
        count++;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Set<Integer> g : p[now]) {
                g.remove(now);
                if (g.size() == 1) {
                    Integer left = g.iterator().next();
                    // q에 없을 경우에만 집어 넣자.
                    if (!visited[left]) {
                        q.add(left);
                        visited[left] = true;
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

}
