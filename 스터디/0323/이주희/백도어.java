import java.io.*;
import java.util.*;

public class Main_bj_17396_백도어 {
	
	static class Info implements Comparable<Info>{
		int to;
		long weight;
		
		
		public Info(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Info o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17396.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		boolean[] isVisible = new boolean[N];		// 보이는 지점인지 아닌지 
		 
		ArrayList<Info>[] mat = new ArrayList[N];   // 인접리스트
		
		for(int i=0; i<N; i++) {
			if(st.nextToken().equals("1")) isVisible[i] = true; 	// 1이라면 보이는 지점
			mat[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long w = Integer.parseInt(st.nextToken());
			
			if((!isVisible[a] && !isVisible[b]) || a==N-1 || b==N-1) {	// 보이지 않는 지점이고, 마지막 지점이라면
				mat[a].add(new Info(b, w));
				mat[b].add(new Info(a, w));
			}
		}
		
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);		// 최댓값으로 초기화
		
		dist[0] = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0));	// node 번호, dist
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			
			if(cur.to==N-1) break;
			if(dist[cur.to] < cur.weight) continue;		// 이미 방문한 지점
			
			// 이웃된 노드들 update
			for(int i=0; i<mat[cur.to].size(); i++) {
				int next = mat[cur.to].get(i).to;
				long next_weight = mat[cur.to].get(i).weight;
				
				if(dist[next] > dist[cur.to] + next_weight) {
					dist[next] = dist[cur.to] + next_weight;
					pq.offer(new Info(next, dist[next]));
				}
			}
		}
		
		if(dist[N-1]>=Long.MAX_VALUE) System.out.println(-1);
		else System.out.println(dist[N-1]);
		
		br.close();
	}

}
