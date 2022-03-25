import java.io.*;
import java.util.*;

public class Main_bj_14950_정복자 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14950.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T  = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] mat = new ArrayList[N+1];    // 1번 나라부터 시작해서 N+1 개
		for(int i=0; i<N+1; i++) {
			mat[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			mat[a].add(new int[] {b, w});
			mat[b].add(new int[] {a, w});
		}
		
		// 비용이 적은 나라부터 나와 정복할 수 있도록
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {1, 0});
		boolean[] visited = new boolean[N+1];
		
		
		int ans=0, cnt=0, plus=0;		// 최소 비용, 정복한 나라의 갯수, 정복할때마다 더해줘야 하는 비용
		while(true) {	
			int[] cur = pq.poll();
			if(visited[cur[0]]) continue;
			
			visited[cur[0]] = true;
			ans += cur[1] + plus;
			plus = T*cnt++;			// 나라를 정복한 갯수만큼 더해줘야 하는 비용이 증가함
			if(cnt==N) break; 		// N개의 나라를 정복해야 함
			
			// 이웃들 갱신 : 정복한 나라에서 연결된 나라들만 정복이 가능
			for(int j=0; j<mat[cur[0]].size(); j++) {
				int next = mat[cur[0]].get(j)[0];
				int next_weight = mat[cur[0]].get(j)[1];
				
				if(!visited[next]) {
					pq.offer(new int[] {next, next_weight});	
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}
