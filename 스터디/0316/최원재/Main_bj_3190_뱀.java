package practice0310;
import java.io.*;
import java.util.*;
public class Main_bj_3190_뱀 {
	//상우하좌
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] apple = new boolean[N][N];
		
		for(int i=0;i<K;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int ni = Integer.parseInt(st.nextToken());
			int nj = Integer.parseInt(st.nextToken());
			apple[ni-1][nj-1]=true;
		}
		int d = 1; //우
		
		int L = Integer.parseInt(br.readLine());
		int ni = 0;
		int nj = 0;
		int tailcount = 1;
	
		int cnt = 1;
		boolean finish=false;
		map[0][0]=cnt;
	
		label:for(int t=0; t<L;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int X = Integer.parseInt(st.nextToken());
			String C = st.nextToken();
			
			for(int k = cnt; k<=X ; k++ ) {
				ni+=di[d];
				nj+=dj[d];
				if(0<=ni&&ni<N&&0<=nj&&nj<N) { //이동한 곳이 격자 안이고
					if(map[ni][nj]!=0) {//자기 몸이라면 
						finish = true; // 끝났음
						break label;
					}
					else {//자기 몸이 아닌 빈칸이라면 
						//System.out.println("d");
						map[ni][nj]=(cnt+1);
						cnt++;
						//System.out.println(ni+" "+nj + " "+ map[ni][nj]);
						//이동한 곳에 사과가 없다면?
						if(!apple[ni][nj]) {
							here:for(int i=0; i<N; i++) {
								for(int j=0; j<N ; j++) {
									if(map[i][j]==tailcount) {
										map[i][j]=0;
										tailcount++;
										break here;
									}
								}
							}
						}else {
							//사과가 있다면?
							apple[ni][nj]=false;
						}
					}
				}else {
					break label;
				}
//				for(int[] a : map) System.out.println(Arrays.toString(a));
//				System.out.println();
			}
			
			
			if(d==0) {
				if(C.equals("D")) {
					d=1;
				}else {
					d=3;
				}
			}else if(d==1) {
				if(C.equals("D")) {
					d=2;
				}else {
					d=0;
				}
			}else if(d==2) {
				if(C.equals("D")) {
					d=3;
				}else {
					d=1;
				}
			}else {
				if(C.equals("D")) {
					d=0;
				}else {
					d=2;
				}
			}
		}
		if(!finish) {
			while(true) {
				ni+=di[d];
				nj+=dj[d];
				if(0<=ni&&ni<N&&0<=nj&&nj<N) { //이동한 곳이 격자 안이고
					if(map[ni][nj]!=0) {//자기 몸이라면 
						finish = true; // 끝났음
						break;
					}
					else {//자기 몸이 아닌 빈칸이라면 
						map[ni][nj]=++cnt;
						//이동한 곳에 사과가 없다면?
						if(!apple[ni][nj]) {
							for(int i=0; i<N; i++) {
								for(int j=0; j<N ; j++) {
									if(map[i][j]==tailcount) {
										map[i][j]=0;
										tailcount++;
									}
								}
							}
						}else {
							//사과가 있다면?
							apple[ni][nj]=false;
						}
					}
				}else {
					break;
				}
//				for(int[] a : map) System.out.println(Arrays.toString(a));
//				System.out.println();
			}
		}
		System.out.println(cnt);
		
		
		br.close();

	}

}
