import java.io.*;
import java.util.*;

public class Main_bj_23290_마법사상어와복제_sol2 {
	
	final static int N = 4;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] ddx = {-1, 0, 1, 0};	// 상 좌 하 우
	static int[] ddy = {0, -1, 0, 1};
	static List[][] map;
	static Fish shark;
	static int[][] smell;
	static List<Fish> rootFishes;
	static List<Fish> fishes;
	
	static class Fish {
		int x, y, dir;
		
		Fish(int x, int y, int dir) {
			this.x = x; 
			this.y = y;
			this.dir = dir;
		}
	}
	
	static class Dir implements Comparable<Dir>{
		int cnt;
		int[] direction;
		int num;
		
		Dir(int cnt, int[] direction, int num) {
			this.cnt = cnt;
			this.direction = direction;
			this.num = num;
		}
		
		@Override
		public int compareTo(Dir o) {
			if(cnt == o.cnt) {
				return this.num - o.num;		// 작은순서
			}
			return o.cnt - this.cnt;			// 큰 순서
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_23290.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());	// 물고기의 수
		int S = Integer.parseInt(st.nextToken()); 	// 연습한 횟수
		
		fishes = new ArrayList<>();		// 현재 있는 물고기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			fishes.add(new Fish(x, y, d));
		}
		st = new StringTokenizer(br.readLine(), " ");
		shark = new Fish(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, -1);
		
		rootFishes = new ArrayList<>();	// 복제되는 물고기
		smell = new int[N][N];	// 냄새
		for(int s=1; s<=S; s++) {
			// 복제, 물고기 이동
			copyAndMoveFish();
			
			// 상어 이동
			moveShark();
			
			// 냄새 제거
			removeSmell();
 			
			// 복제 완료
			magicSum();
		}
		
		System.out.println(fishes.size());
		br.close();
	}
	
	static void copyAndMoveFish() {
		rootFishes.clear();
		// 물고기 이동
		map = new List[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(Fish fish : fishes) {
			// 복제 -> 여기서 rootFishes에 fish 를 그대로 담아서 틀렸다. 아래에서 fish.dir를 변경하기 때문
			rootFishes.add(new Fish(fish.x, fish.y, fish.dir));
			
			boolean isMoved = false;
			int oriDir = fish.dir;
			
			do {
				int nx = fish.x + dx[fish.dir];
				int ny = fish.y + dy[fish.dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || smell[nx][ny] > 0 || (nx==shark.x) && (ny==shark.y)) {
					fish.dir = (fish.dir-1+8)%8;
					continue;
				}
				
				map[nx][ny].add(fish.dir);
				isMoved = true;
				break;
			} while(oriDir != fish.dir);
			
			if(!isMoved) {
				map[fish.x][fish.y].add(fish.dir); 
			}
		}
	}
	
	static void moveShark() {
		// 상어 이동
		List<Dir> directions = new ArrayList<>();
		outer: for(int i=0; i<(int)(Math.pow(4, 3)); i++) {
			int[] dir = getQuaternary(i);
			int cnt = 0;		// 먹는 물고기의 수
			int nx = shark.x, ny = shark.y;
			boolean[][] visited = new boolean[N][N];
			for(int j=0; j<3; j++) {
				nx += ddx[dir[j]];
				ny += ddy[dir[j]];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue outer;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				cnt += map[nx][ny].size();
			}
			
			directions.add(new Dir(cnt, dir, i));
		}
		Collections.sort(directions);
		
		Dir dir = directions.get(0);
		int nx = shark.x, ny = shark.y;
		for(int i=0; i<3; i++) {
			nx += ddx[dir.direction[i]];
			ny += ddy[dir.direction[i]];
			
			if(!map[nx][ny].isEmpty()) {
				smell[nx][ny] = 3;		// 냄새 남김
				map[nx][ny].clear();	// 모두 제거
			}
		}
		
		shark.x = nx; shark.y = ny;
	}
	
	static void removeSmell() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(smell[i][j]!=0) {
					smell[i][j]--;
				}
			}
		}
	}
	
	static void magicSum() {
		fishes.clear();
		for(Fish fish : rootFishes) {
			fishes.add(fish);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].isEmpty()) continue;
				for(int k=0; k<map[i][j].size(); k++) {
					fishes.add(new Fish(i, j, (int)map[i][j].get(k)));
				}
			}
		}
	}

	
	static int[] getQuaternary(int num) {
		int[] res = new int[3];
		int idx=2;
		while(num > 0) {
			res[idx--] = num%4;
			num/=4;
		}
		
		return res;
	}
	

}
