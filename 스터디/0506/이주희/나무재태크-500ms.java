import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Kan{
		LinkedList<Integer> treeAgeQue = new LinkedList<>();
		
		int energy=5, dieEnergy, addTreeNum;
	}
	
	public static int N, M, K;
	public static Kan[][] map;
	public static int[][] A;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Kan[N][N];
		A = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) { 
				map[i][j] = new Kan();
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			
			map[r][c].treeAgeQue.add(age);
		}
		
		for(int i=0; i<K; i++) {
//			System.out.println((i+1) + "년째 기록 ===========================");
			process();
		}
		
		int total = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				total += map[i][j].treeAgeQue.size();
			}
		}

		System.out.println(total);
	}
	
	public static void process() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
			
				
				spring(i,j);
//				System.out.print(map[i][j].treeAgeQue);
				summer(i,j);
			}
//			System.out.println();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
			
				fall(i,j);
				winter(i,j);
			}
		}
		
		
	}
	
	public static void spring(int i, int j) {
		
		Kan kan = map[i][j];
		int limit = kan.treeAgeQue.size();
		
		
		int z=0;
		for(; z<limit; z++) {
			int age = kan.treeAgeQue.poll();
			
			if(kan.energy >= age) {
				// 봄
				// 양분먹고
				kan.energy -= age;
				// 나이추가
				kan.treeAgeQue.add(age+1);
				
				// 가을대비 : 번식나무수 계산
				if((age+1)%5 == 0) {
					kan.addTreeNum++;
				}
			}else {
				// 여름대 : 나무 죽이기 -> 에너지계산
				kan.dieEnergy += (age/2);
				while(true) {
					z++;
					if(limit<=z) break;
					kan.dieEnergy += (kan.treeAgeQue.poll()/2);
				}
			}
		}
	}
	public static void summer(int i, int j) {
		Kan kan = map[i][j];
		kan.energy += kan.dieEnergy;
		kan.dieEnergy = 0;
	}
	
	static int[] dy = {-1,-1,-1,0,0,1,1,1};
	static int[] dx = {-1,0,1,-1,1,-1,0,1};
	public static void fall(int i, int j) {
		
		Kan kan = map[i][j];
		if(kan.addTreeNum == 0) return;
		
		// 8방 번식
		for(int z=0; z<8; z++) {
			int zy = i + dy[z];
			int zx = j + dx[z];
			
			if(zy<0 || zx<0 || zy>=N || zx>=N) continue;
			
//			System.out.println("나무 번식 + "+kan.addTreeNum + "개의 나무생성");
			for(int k=0; k<kan.addTreeNum; k++) {
				map[zy][zx].treeAgeQue.addFirst(1);
			}
			
		}
		kan.addTreeNum = 0;
	}
	
	public static void winter(int i, int j){
		Kan kan = map[i][j];
		kan.energy += A[i][j];
	}


}

