package p0405;
import java.io.*;
import java.util.*;

public class Main_bj_17471_게리맨더링_대전_5반_최원재 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		
		int[] population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			g.add(new ArrayList<Integer>());
		}
		
		for(int i=1; i<=N; i++) {
			population[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				g.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		//int [] district = new int[N];
		boolean[] district = new boolean[N+1];
		solve( district , N, 1, 0, g, population);
		if(min==Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(min);
		
		
		
		br.close();

	}
	
	static void solve(boolean[] district, int N, int start, int redcount, ArrayList<ArrayList<Integer>> g, int[] population) {
		if(start>  N) {
			if(check(district, g, N)){
				int red=0;
				int blue=0;
				for(int i=1; i<=N;i++) {
					if(district[i]) {//빨간
						red+=population[i];
					}else {
						blue+=population[i];
					}
				}
				int diff = Math.abs(red-blue);
				if(min>diff)min=diff;
				
			}
			return;
		}
		district[start]=true;
		solve( district, N, start+1,redcount+1, g, population);
		district[start]=false;
		solve(district, N, start+1,redcount, g, population);
	}
	static boolean check(boolean[] district, ArrayList<ArrayList<Integer>> g, int N) {
		boolean[] redv = new boolean[N+1];
		boolean[] bluev = new boolean[N+1];
		//빨간 구역 찾기
		Queue<Integer> que = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) {
			if(district[i]) {
				que.add(i);
				redv[i]=true;
				break;
			}
		}
		//bfs로 연결된 모든 레드 구역 찾기 
		while(!que.isEmpty()) {
			int cur = que.poll();
			for(int i=0; i<g.get(cur).size(); i++) {
				if(district[g.get(cur).get(i)]&&!redv[g.get(cur).get(i)]) { //빨간색 영역이고 아직 방문하지 않았다면 ?
					//트루면 빨간 구역
					que.add(g.get(cur).get(i));
					//방문 처리
					redv[g.get(cur).get(i)]=true;
					
					
				}
			}
		}
		//모든 구역 방문 했는지 확인
		for(int i=1; i<=N; i++) {
			if(redv[i]!=district[i]) {
				return false;
			}
		}		
		//파란 구역 찾기 
		que = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) {
			if(!district[i]) {
				que.add(i);
				bluev[i]=true;
				break;
			}
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			for(int i=0; i<g.get(cur).size(); i++) {
				if(!district[g.get(cur).get(i)]&&!bluev[g.get(cur).get(i)]) { //파란색 영역이고 아직 방문하지 않았다면 ?
					//false면 파란 구역
					que.add(g.get(cur).get(i));
					//방문 처리
					bluev[g.get(cur).get(i)]=true;		
				}
			}
		}
		for(int i=1; i<=N; i++) {
			if(bluev[i]==district[i]) {
				return false;
			}
		}
		return true;
	}

}
