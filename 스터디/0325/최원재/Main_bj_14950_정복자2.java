package algorithm;
import java.io.*;
import java.util.*;

public class Main_bj_14950_정복자2 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> g = new ArrayList<>();
		for(int i=0; i<=N;i++) {
			g.add(new ArrayList<>());
		}
		//System.out.println(g.size());
		for(int i=0; i<M;i++) {
			st= new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			g.get(start).add(new int[] {end, cost});
			g.get(end).add(new int[] {start, cost});
		}
		PriorityQueue<info> pq = new PriorityQueue<>();

		
		int cost=0;
		int count=-1;
		
		boolean[] v = new boolean[N+1];
		pq.add(new info(0, 1,0));
		while(!pq.isEmpty()) {
			info cur = pq.poll();
			//System.out.println(cur.from+"  "+cur.to+"  "+cur.cost);
			if(v[cur.to]) continue;
			//System.out.println(cur.from+"  "+cur.to+"  "+cur.cost);
			v[cur.to]=true;
			//System.out.println(cur.to+" "+cur.cost);
			if(count!=-1) {
				cost+=cur.cost+t*count;
			}
			count++;
			for(int i=0; i<g.get(cur.to).size();i++) {
				if(!v[g.get(cur.to).get(i)[0]]) {
					pq.add(new info(cur.to,g.get(cur.to).get(i)[0], g.get(cur.to).get(i)[1]));
				}
			}

			if(count==N-1)break;
			
		}
		//System.out.println(count);
		System.out.println(cost);

	}
	

}
class info implements Comparable<info>{
	int from;
	int to;
	int cost;
	public info( int from, int to, int cost) {
		
		this.from=from;
		this.to = to;
		this.cost = cost;
	}
	@Override
	public int compareTo(info o) {
		
		return this.cost-o.cost;
	}
	
	
}
