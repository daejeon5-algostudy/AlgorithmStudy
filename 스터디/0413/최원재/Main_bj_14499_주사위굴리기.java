package p0411;
import java.io.*;
import java.util.*;
public class Main_bj_14499_주사위굴리기 {
	//동서북남 
	static int[] di= {0,0,0,-1,1};
	static int[] dj= {0,1,-1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dice = new int[7];
		for(int i=0; i<N; i++ ) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int ni=x;
		int nj=y;
		st=new StringTokenizer(br.readLine()," ");
		for(int t=0; t<K ;t++) {
			int order = Integer.parseInt(st.nextToken());
			//1이 위 
			//남 1->5 5->6 6->2 2->1  3 4 그대로 
			//북 5>1 6>5  2>6 1>2     3 4 그대로 
			//동 3>6 6>4 4>1 1>3      2 5 그대로
			//서 3>1 1>4 4>6 6>3      2 5 그대로
			if(order==1) { //동
				ni +=di[1];
				nj+=dj[1];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					int tmp = dice[6];
					int tmp2= dice[4];
					dice[6]=dice[3];//3->6
					dice[4]=tmp;//dice[6] 6->4
					tmp=dice[1];
					dice[1]=tmp2; //4->1
					dice[3]=tmp; //1->3
					if(map[ni][nj]==0) {
						map[ni][nj]=dice[6];
					}else {
						dice[6]=map[ni][nj];
						map[ni][nj]=0;
					}
					sb.append(dice[1]).append("\n");
					
				}else {
					ni -=di[1];
					nj -=dj[1];
				}
			}else if(order==2) {
				ni +=di[2];
				nj+=dj[2];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					//서 3>1 1>4 4>6 6>3      2 5 그대로
					int tmp = dice[1];
					int tmp2= dice[4];
					dice[1]=dice[3];//3->1
					dice[4]=tmp;//dice[1] 1->4
					tmp=dice[6];
					dice[6]=tmp2; //4->6
					dice[3]=tmp; //6->3
					if(map[ni][nj]==0) {
						map[ni][nj]=dice[6];
					}else {
						dice[6]=map[ni][nj];
						map[ni][nj]=0;
					}
					sb.append(dice[1]).append("\n");
				}else {
					ni -=di[2];
					nj -=dj[2];
				}
			}else if(order==3) {
				//북
				ni +=di[3];
				nj+=dj[3];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					//북 5>1 1>2  2>6  6>5       3 4 그대로 
					int tmp = dice[1];
					int tmp2= dice[2];
					dice[1]=dice[5];//5->1
					dice[2]=tmp;//dice[1] 1->2
					tmp=dice[6];
					dice[6]=tmp2; //2->6
					dice[5]=tmp; //6->5
					if(map[ni][nj]==0) {
						map[ni][nj]=dice[6];
					}else {
						dice[6]=map[ni][nj];
						map[ni][nj]=0;
					}
					sb.append(dice[1]).append("\n");
				}else {
					ni -=di[3];
					nj -=dj[3];
				}
			}else {
				ni +=di[4];
				nj+=dj[4];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					//남 1->5 5->6 6->2 2->1  3 4 그대로 
					int tmp = dice[5];
					int tmp2= dice[6];
					dice[5]=dice[1];//1->5
					dice[6]=tmp;//dice[1] 5->6
					tmp=dice[2];
					dice[2]=tmp2; //6->2
					dice[1]=tmp; //6->5
					if(map[ni][nj]==0) {
						map[ni][nj]=dice[6];
					}else {
						dice[6]=map[ni][nj];
						map[ni][nj]=0;
					}
					sb.append(dice[1]).append("\n");
				}else {
					ni -=di[4];
					nj -=dj[4];
				}
			}
		}
		
		System.out.println(sb.toString());
		
		
		br.close();

	}

}
