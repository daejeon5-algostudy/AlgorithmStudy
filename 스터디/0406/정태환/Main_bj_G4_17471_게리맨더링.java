package algoStudy.a0406;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17471_게리맨더링 {

	static int N, ans=Integer.MAX_VALUE;
	static int[] nums;
	static int[][] g;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17471"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N=Integer.parseInt(br.readLine()); // 선거구역
		nums=new int[N+1];
		st=new StringTokenizer(br.readLine()," ");
		for(int i=1; i<=N; i++) 
			nums[i]=Integer.parseInt(st.nextToken());
		
		g=new int[N+1][];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			g[i]=new int[Integer.parseInt(st.nextToken())];
			for(int j=0; j<g[i].length; j++) 
				g[i][j]=Integer.parseInt(st.nextToken());
		}
		
		subset(1, 0);
		if(ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
		br.close();
	}
	
	static void subset(int cnt, int bit) {
		if(cnt==N) {
			bfs(bit);
			return;
		}
		subset(cnt+1, bit); // 미선택
		bit=(1<<cnt) | bit;
		subset(cnt+1, bit); // 선택
	}
	
	static void bfs(int bit) {
		ArrayList<Integer> t1=new ArrayList<>();
		ArrayList<Integer> t2=new ArrayList<>();
		for(int i=1; i<=N; i++)
			if(((1<<i)&bit)>0) t1.add(i);
			else t2.add(i);
		if(t1.size()==0 || t2.size()==0) return;
		ArrayDeque<Integer> q=new ArrayDeque<>();
		boolean[] v=new boolean[N+1];
		// 선택팀 연결여부, 인구수구하기
		int sum1=0;
		q.offer(t1.get(0));
		v[t1.get(0)]=true;
		while(!q.isEmpty()) {
			int now=q.poll();
			sum1+=nums[now];
			for(int next:g[now]) {
				if(!v[next] && t1.contains(next)) {
					v[next]=true;
					q.offer(next);
				}
			}
		}
		// 모두 선택되지 않았다면 리턴
		for(int i=0; i<t1.size(); i++)
			if(!v[t1.get(i)]) return;
		
		// 미선택팀 연결여부, 인구수구하기
		v=new boolean[N+1];
		int sum2=0;
		q.offer(t2.get(0));
		v[t2.get(0)]=true;
		while(!q.isEmpty()) {
			int now=q.poll();
			sum2+=nums[now];
			for(int next:g[now]) {
				if(!v[next] && t2.contains(next)) {
					v[next]=true;
					q.offer(next);
				}
			}
		}
		// 모두 선택되지 않았다면 리턴
		for(int i=0; i<t2.size(); i++)
			if(!v[t2.get(i)]) return;
		ans=Math.min(ans, Math.abs(sum1-sum2));
	}

}
