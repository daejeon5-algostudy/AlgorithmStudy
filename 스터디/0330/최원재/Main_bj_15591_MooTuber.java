package practice0322;
import java.io.*;
import java.util.*;
public class Main_bj_15591_MooTuber {
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st  = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> g = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			g.add(new ArrayList<int[]>()); 
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int relation = Integer.parseInt(st.nextToken());
			g.get(from).add(new int[] {to, relation});
			g.get(to).add(new int[] {from, relation});
		}
		
		for(int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine()," ");
			boolean[] visited = new boolean[N+1];
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			count=0;
			visited[v]=true;
//			System.out.println(k+" "+v);
			solve(v, k, g, Integer.MAX_VALUE, visited);
			sb.append(count).append("\n");
//			System.out.println("--------------------------------------");
		}
		System.out.println(sb.toString());
		br.close();

	}
	static void solve(int start, int k, ArrayList<ArrayList<int[]>>g, int min, boolean[] visited) {
		//System.out.println(start+" "+" | "+min+" "+k);
		if(min!=Integer.MAX_VALUE&&min>=k) {
			//System.out.println(start+" "+min);
			count++;
		}
		for(int i=0; i<g.get(start).size();i++) {
			if(visited[g.get(start).get(i)[0]])continue;
			visited[g.get(start).get(i)[0]]=true;
			int tmp =min;
			if(g.get(start).get(i)[1]<min)min=g.get(start).get(i)[1];
			solve(g.get(start).get(i)[0], k, g, min, visited);
			min = tmp;
		}
	}

}
