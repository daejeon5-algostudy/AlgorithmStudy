import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int to;
		int weight;
		Node link;
		
		Node(int to, int weight, Node link) {
			this.to = to;
			this.weight = weight;
			this.link = link;
		}
	}

	static int INF = 1000_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N==0 && M==0) break;
			
			Node[] adjList = new Node[N];
			int total = 0;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				adjList[u] = new Node(v, w, adjList[u]);
				adjList[v] = new Node(u, w, adjList[v]);
				
				total += w;			// 기존 모든 거리의 합
			}
			
			// prim
			int[] dist = new int[N];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
			pq.add(new int[] {0, 0});		// 정점, 최단 거리
			boolean[] visited = new boolean[N];
			int sum = 0;		// MST 비용
			int cnt = 1;
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				
				if(visited[now[0]]) continue;
				visited[now[0]] = true;
				
				sum += now[1];
				
				if(cnt++ == N) break;
				
				for(Node temp = adjList[now[0]]; temp != null; temp = temp.link) {	// 현재 정점의 이웃
					if(!visited[temp.to] && dist[temp.to] > temp.weight) {
						dist[temp.to] = temp.weight;
						pq.add(new int[] {temp.to, dist[temp.to]});
					}
				}
			}
			
			sb.append(total-sum).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();

	}

}
