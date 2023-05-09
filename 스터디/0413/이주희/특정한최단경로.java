import java.io.*;
import java.util.*;

/*
 * u-v 를 꼭 포함하는 최단경로의 거리
 * sol)
 * 1->u->v->N
 */

public class Main {

	static int N, E;
	static int INF = 100_000_000;
	static Node[] nodeList;
	
	static class Node {
		int v;
		int weight;
		Node link;
		
		Node(int v, int weight, Node link) {
			this.v = v;
			this.weight = weight;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		nodeList = new Node[N+1];
		int a, b, c;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			nodeList[a] = new Node(b, c, nodeList[a]);
			nodeList[b] = new Node(a, c, nodeList[b]);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int res1 = dijkstra(1, u) + dijkstra(u, v) + dijkstra(v, N);
		int res2 = dijkstra(1, v) + dijkstra(v, u) + dijkstra(u, N);
		
		int ans = Math.min(res1, res2);
		if(ans >= INF) System.out.println(-1);
		else System.out.println(ans);
		br.close();
	}
	
	static int dijkstra(int s, int e) {
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[s] = 0;
		
		boolean[] visited = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
		pq.add(new int[] {s, 0});
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if(visited[now[0]]) continue;
			visited[now[0]] = true;
			
			if(now[0] == e) break;
			
			for(Node temp = nodeList[now[0]]; temp != null; temp=temp.link) {
				if(!visited[temp.v] && distance[temp.v] > distance[now[0]] + temp.weight) {
					distance[temp.v] = distance[now[0]] + temp.weight;
					pq.add(new int[] {temp.v, distance[temp.v]});
				}
			}
		}
		
		return distance[e];
	}

}
