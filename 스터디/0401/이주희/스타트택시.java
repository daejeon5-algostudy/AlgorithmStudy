import java.io.*;
import java.util.*;

public class Main_bj_19238_스타트택시 {
	
	static int N;
	static int[][] dist, map;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static class Passenger implements Comparable<Passenger>{
		int si, sj, ti, tj, dist, num;

		public Passenger(int si, int sj, int ti, int tj, int dist, int num) {
			super();
			this.si = si;
			this.sj = sj;
			this.ti = ti;
			this.tj = tj;
			this.dist = dist;
			this.num = num;
		}

		@Override
		public int compareTo(Passenger o) {
			if(dist==o.dist) {
				if(si == o.si)
					return Integer.compare(sj, o.sj);
				return Integer.compare(si, o.si);
			}
			return Integer.compare(dist, o.dist);
		}
		
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_19238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int left = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j <N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] taxi = new int[2];
		for(int i=0; i<2; i++) {
			taxi[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		Passenger[] pass = new Passenger[P];
		
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int si = Integer.parseInt(st.nextToken())-1;
			int sj = Integer.parseInt(st.nextToken())-1;
			int ti = Integer.parseInt(st.nextToken())-1;
			int tj = Integer.parseInt(st.nextToken())-1;
			
			pass[i] = new Passenger(si, sj, ti, tj, 0, i);
		}
		
		PriorityQueue<Passenger> pq = new PriorityQueue<>();	//dist 작은 순으로 정렬
		boolean[] visited = new boolean[P];
		int cnt=0;
		while(true) {
			dist = new int[N][N];

			// 택시에서 거리값 계산해서 넣기
			calDist(taxi[0], taxi[1]);
			
			// 아직 방문안한 승객 중 택시까지의 거리 pq에 담기
			pq.clear();
			for(int i=0; i<P; i++) {
				if(visited[i] || dist[pass[i].si][pass[i].sj]-1 <0) continue;	// 택시가 도달할 수 없는 위치에 승객이 위치
				pq.add(new Passenger(pass[i].si, pass[i].sj, pass[i].ti, pass[i].tj, dist[pass[i].si][pass[i].sj]-1, i));
			}
			
			if(pq.isEmpty()) break;		//태울 수 있는 승객이 없음
			
			Passenger cur = pq.poll();
			left -= cur.dist;			// 손님까지 이동한 거리 빼기
			if(left<=0) break;			// 남은 연료가 없음
			
			// 손님부터 도착지까지 거리 구하기
			dist = new int[N][N];
			calDist(cur.si, cur.sj);
			
			if(dist[cur.ti][cur.tj]-1 > left || dist[cur.ti][cur.tj]-1 <0) break;	// 목적지까지 갈 수 있는 연료가 없음, 손님 출발지부터 목적지까지 갈 수 없음
			
			
			// 손님을 목적지까지 이동함
			taxi[0] = cur.ti; taxi[1] = cur.tj;
			visited[cur.num] = true;			// 승객 방문 처리
			left += dist[cur.ti][cur.tj]-1; 	// 연료충전
			
			if(++cnt==P) break;					// 승객을 다 태웠다면 break
		}
		
		if(cnt!=P) System.out.println(-1);
		else System.out.println(left);
		br.close();
	}
	
	// (si, sj) 부터 거리 값 dist에 담기
	static void calDist(int si, int sj) {
		dist[si][sj] = 1;			// 방문처리하기 위해 0이 아닌 1로 저장
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.add(new int[] {si, sj});
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur[0] + di[d];
				int nj= cur[1] + dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || dist[ni][nj]!=0 || map[ni][nj]==1) continue;	// dist가 이미 계산됐거나 장애물이 있으면
				dist[ni][nj] = dist[cur[0]][cur[1]]+1;
				que.add(new int[] {ni, nj});
			}
		}
	}

}
