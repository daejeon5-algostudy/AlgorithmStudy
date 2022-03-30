package practice0322;
import java.io.*;
import java.util.*;
public class Main_bj_16234_인구이동 {
	static int[] di= {-1,0,1,0};
	static int[] dj= {0,1,0,-1};
	static int sum;
	static boolean ismove;
	static boolean check; //그 칸에서 이동여부 확인 
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int day=0;
		while(true) {
			boolean[][] v = new boolean[N][N];
			ismove=false;
			for(int i=0;i<N;i++) {
				for(int j=0; j<N; j++) {
					if(v[i][j])continue;
					
					check=false;
					count=0;
					sum=0;
					List<int[]> list = new ArrayList<int[]>();
					solve(map, i,j, v, list, N, L, R);
					if(list.size()>1) {
						for(int t=0; t<list.size();t++) {
							map[list.get(t)[0]][list.get(t)[1]]=sum/count;
						}
					}
				}
				
			}
			if(!ismove) {
				break;
			}
			//for(int[] a:map)System.out.println(Arrays.toString(a));
			day++;
		}
		System.out.println(day);
		br.close();

	}
	public static void solve(int map[][], int i, int j, boolean[][] v, List<int[]> list, int N,int L, int R) {
		v[i][j]=true;
		list.add(new int[] {i, j});
		count++;
		sum+=map[i][j];
		
		for(int d=0; d<4; d++) {
			int ni = i+di[d];
			int nj= j+dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<N) {
				if(!v[ni][nj]) {
					double diff = Math.abs(map[i][j]-map[ni][nj]);
					if(L<=diff&& diff<=R) {
						//System.out.println(1);
						ismove=true;
						check=true;
						solve(map, ni, nj, v, list, N, L, R);
					}
				}
			}
		}
	}

}
