import java.io.*;
import java.util.*;
/* 좌 하 우 상
 * 범위를 벗어나면 ans+= 모래의양
 * alpha자리에 오는 값 유의!
 * */
public class Main {
	static int[] di= {0,1,0,-1}; //좌 하 우 상
	static int[] dj= {-1,0,1,0}; //좌 하 우 상
	static int[][] tornado_i = {
								{-1,1,-2,2,0,-1,1,-1,1,0}, //좌
								{-1,-1,0,0,2,0,0,1,1,1}, //하
								{-1,1,-2,2,0,-1,1,-1,1,0}, //우
								{1,1,0,0,-2,0,0,-1,-1,-1} //상
								};
	static int[][] tornado_j = {
								{1,1,0,0,-2,0,0,-1,-1,-1}, //좌
								{-1,1,-2,2,0,-1,1,-1,1,0}, //하
								{-1,-1,0,0,2,0,0,1,1,1}, //우
								{-1,1,-2,2,0,-1,1,-1,1,0} //상
							    };
	static double[]percent= {0.01,0.01,0.02,0.02,0.05,0.07,0.07,0.1,0.1,0.55};
	static int[][] map;
	static int N, size;
	static int sand_out;
	static Queue<Integer> q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); //map
		map = new int[N][N];
		size=N*N;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//tornado(N/2,N/2,1,0,1);
		int i=N/2, j=N/2, cnt=1, dir=0, rep=1;
		
		while(cnt<size) {
			for (int k = 0; k < rep; k++) {
				if(i==0 && j==0)break;
				i+=di[dir];
				j+=dj[dir];
				//System.out.println("cnt="+cnt+"에서"+rep+"만큼 "+dir+"방향 움직여서 "+"("+i+","+j+")");
				int sand_total=0;
				int sand_original=map[i][j];
					for(int l=0; l<9; l++) {
						//모래가 옮겨질 좌표
						int si=i+tornado_i[dir][l];
						int sj=j+tornado_j[dir][l];
						
						
						int sand = (int)(map[i][j]*percent[l]);
						//System.out.println(l+"번째 "+sand);
						if(si<0|| sj<0|| si>=N || sj>=N) {
							//System.out.println("밖으로 나오는 모래"+sand);
							sand_out+=sand;
						}
						else map[si][sj]+=sand; //밖으로 안나가면 그 좌표에 더해줌 
						sand_total+=sand;
					}
					//알파
					int si=i+tornado_i[dir][9];
					int sj=j+tornado_j[dir][9];
					int alphaSand = sand_original-sand_total;
                    if(si<0 || si>=N || sj<0|| sj>=N){
                        sand_out +=alphaSand;
                    }
                    else{
                        map[si][sj] +=alphaSand;
                    }
				
//				for (int[] a : map) {
//					System.out.println(Arrays.toString(a));
//				}
				cnt++;
			}
			
			if(dir==1) {
				rep++;
			}else if(dir==3) {
				rep++;
			}
			dir=(dir+1)%4;
		}
		System.out.println(sand_out);
		br.close();
	}
	
}

