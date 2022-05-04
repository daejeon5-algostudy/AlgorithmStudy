import java.io.*;
import java.util.*;

//위상 정렬

public class Main_bj_1516_게임개발 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1516.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] inputCnt = new int[N];
		int[] time = new int[N];
		ArrayList<Integer>[] mat = new ArrayList[N];
		for(int i=0; i<N; i++) {
			mat[i] = new ArrayList<>();
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());				// 소요 시간
			
			while(true) {
				int node = Integer.parseInt(st.nextToken());
				if(node==-1) break;
				
				node--;					// 0번 노드부터 시작
				mat[node].add(i);
				inputCnt[i]++;
			}
			
			if(inputCnt[i]==0) {
				que.offer(new int[] {i, time[i]});
			}
		}
		
		int[] answer = new int[N];
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			if(answer[cur[0]] != 0) continue;
			
			answer[cur[0]] = cur[1];
			
			for(int next : mat[cur[0]]) {
				inputCnt[next]--;
				if(inputCnt[next]==0 && answer[next]==0)
					que.offer(new int[] {next, cur[1] + time[next]});
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.println(answer[i]);
		}
		br.close();
			
	}

}
