package p0412;
import java.io.*;
import java.util.*;
public class Main_bj_6497_전력난 {
	static class node implements Comparable<node>{
		int node;
		int cost;
		public node(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(node o) {
			
			return this.cost-o.cost;
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int m= Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());
			if(m==0)break;
			ArrayList<ArrayList<node>> g = new ArrayList<>();
			for(int i=0; i<m;i++) {
				g.add(new ArrayList<node>());
			}
			int full = 0;
			for(int i=0; i<n;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				full+=cost;
				g.get(from).add(new node(to, cost));
				g.get(to).add(new node(from, cost));
			}
			PriorityQueue<node> pq = new PriorityQueue<>();
			pq.offer(new node(0, 0));
			int cnt=0;
			int sum=0;
			boolean[] v = new boolean[m]; 
			while(!pq.isEmpty()) {
				node cur = pq.poll();
				if(v[cur.node])continue;
				v[cur.node]=true;
				sum+=cur.cost;
				cnt++;
				if(cnt==m)break;
				for(int i=0; i<g.get(cur.node).size(); i++) {
					if(v[g.get(cur.node).get(i).node]) continue;
					pq.offer(g.get(cur.node).get(i));
				}
			}
			sb.append(full-sum).append("\n");
			
		}
		System.out.println(sb.toString());
			
		br.close();

	}

}
