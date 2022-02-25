import java.io.*;
import java.util.*;
public class Main_bj_20057_마법사상어와토네이도 {
	//좌하우상
	static int[] di = {0,1,0,-1};
	static int[] dj = {-1,0,1,0};
	static int[][] outi = {{-1,-1,-2,-1, 0, 1, 1, 2, 1},
						   {-1, 0, 0, 1, 2, 1, 0, 0,-1},
						   {1, 1, 2, 1, 0, -1,-1,-2,-1},
						   {1, 0, 0,-1,-2,-1, 0, 0, 1}};
	                    // |    |    |    |    |    |    |    |    |    |
	static int[][] outj = { 
			                {1,    0,   0,  -1,  -2,  -1,   0,   0,   1},
			               {-1,    -1,  -2, -1,   0,  1,    1,   2,    1},
			               {-1,    0,   0,    1,  2,  1,    0,    0,   -1},
			               {1,     1,   2,    1,  0, -1,   -1,    -2,   -1}			               
	};
	static double[] percent = { 0.01, 0.07, 0.02 , 0.1 , 0.05, 0.1, 0.07, 0.02, 0.01};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [][] map = new int[N][N];
		int total=0;
		for(int i=0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				total+=n;
				map[i][j]=n;
			}
		}
		int sh_i = N/2;
		int sh_j = N/2;
		int d = 0;
		int step = 1; // my_Step과 step이 같으면 count하나 증가 
		int my_step=0; // 한방향으로 나아간 횟수 
		int count =0; //카운트가 2가 되면 step 1 증가하고, 
		while(true) {
			if(sh_i==0&&sh_j==0)break;
			if(my_step == step) {
				count++;
				my_step=0;
				d=(d+1)%4;
			}
			if(count==2) {
				step++;
				count=0;				
			}
			int sum=0;//퍼져 나가는 것 저장
			sh_i = sh_i+di[d];
			sh_j = sh_j+dj[d];
			//System.out.println(sh_i+" "+sh_j);
			if(map[sh_i][sh_j]>0) {
				for(int s=0;s<9;s++) {
					//System.out.println(d+" "+s);
					int si = sh_i+outi[d][s];
					int sj = sh_j+outj[d][s];
					//먼지 날아 가는 곳이 격자 안이라면?
					if(0<=si&&si<N&&0<=sj&&sj<N) {
						map[si][sj]+=map[sh_i][sh_j]*percent[s];
						sum+=map[sh_i][sh_j]*percent[s];
					}else {//격자 밖이라면?
						//out+=map[sh_i][sh_j]*percent[s];
						sum+=map[sh_i][sh_j]*percent[s];						
					}
				}				
				//남은 것 다음 칸으로 보내기 
				if(0<=sh_i+di[d]&&sh_i+di[d]<N&&0<=sh_j+dj[d]&&sh_j+dj[d]<N) {
					map[sh_i+di[d]][sh_j+dj[d]]+=(map[sh_i][sh_j]-sum);
				}
				map[sh_i][sh_j]=0;//있던 거 다 날림
				
			}
			//for(int[] a : map )System.out.println(Arrays.toString(a));
			my_step++; //한번 나아 감 
						
		}
		int left=0;
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				left+=map[i][j];				
			}
		}
		System.out.println(total-left);
		br.close();
	}
}
