import java.io.*;
import java.util.*;

public class Main_bj_17471_게리멘더링 {

	static int N, ans;
	static int[] num;
	static LinkedList<Integer>[] mat;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new int[N+1];				// 각 구역의 인구수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		mat = new LinkedList[N+1];
		for(int i=1; i<=N; i++) {
			mat[i] = new LinkedList<>();
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int E = Integer.parseInt(st.nextToken());
			for(int j=0; j<E; j++) {
				int V = Integer.parseInt(st.nextToken());
				
				mat[i].offer(V);			// 연결되어 있음
			}
		}
		
		// N개중에 N/2개를 뽑는 경우에서 인구수의 최솟값으로 갱신 (N/2 인 이유 : nC1 = nCn-1)
		ans = Integer.MAX_VALUE;
		for(int i=1, size=N/2; i<=size; i++) {
			combi(1, 0, i, new boolean[N+1]);
		}
		
		if(ans>=Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		br.close();
	}
	
	static void combi(int idx, int cnt, int target, boolean[] selected) {	// N개중에 target 만큼 뽑음
		if(cnt==target) {
			
			boolean ok = true;			// 뽑힌 애들끼리와 안뽑힌 애들끼리의 연결 유무
			int sumA=0, sumB=0;
			for(int i=1; i<=N; i++) {
				if(selected[i] && sumA==0) {
					sumA=isConnected(i, target, selected, true);
					if(sumA==-1)
						ok = false;
				}
				else if(!selected[i] && sumB==0){
					sumB=isConnected(i, N-target, selected, false);
					if(sumB==-1)
						ok = false;
				} else if(sumA!=0 && sumB!=0) break;
			}
			
			if(ok)
				ans = Math.min(ans, Math.abs(sumA-sumB));
			
			return;
		}
		
		for(int i=idx; i<=N; i++) {
			selected[i] = true;					// 뽑은 것은 true
			combi(i+1, cnt+1, target, selected);
			selected[i] = false;
		}
	}
	
	static int isConnected(int start, int target_cnt, boolean[] selected, boolean isSelect) {
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(start);
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		int cnt=0, sum=0;
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			cnt++;
			sum += num[cur];
			if(cnt == target_cnt) return sum;
			
			for(int v : mat[cur]) {
				if(!visited[v] && selected[v]==isSelect) {
					visited[v] = true;
					que.offer(v);
				}
			}
		}
		
		return -1;
	}

}
