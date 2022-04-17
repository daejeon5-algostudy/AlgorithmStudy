package p0412;
import java.io.*;
import java.util.*;
public class Main_bj_15685_드래곤커브 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[101][101];
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int nj = Integer.parseInt(st.nextToken());
			int ni = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			map[ni][nj]=1;
			/*
			// * 0 :  0,1   -1,0 | 0,-1 -1,0 | 0,-1  1,0  0,-1 -1,0
			// * 1 : -1,0   0,-1
			// * 2 :  0,-1  1,0
			// * 3 :  1,0  |  0,1 |  -1,0  0,1 |   
			 * 
			 */
			if(dir==0) {
				int[] go = new int[2048];
				go[0]=1;
				go[1]=-1;
				for(int g=2; g<=10;g++) {
					int li = (int)Math.pow(2, g-1);
					for(int start = li; start<2*li; start++){
						if(start<li+li/2){
							go[start]=-go[start-li];
						}else {
							go[start]=go[start-li];
						}
					}
				}
				int move= (int)Math.pow(2, gen);
				for(int g=0; g<move;g++) {
					if(g%2==0) {
						ni+=0;
						nj+=go[g];
					}else {
						ni+=go[g];
						nj+=0;
					}
					map[ni][nj]=1;
				}
				
			}else if(dir==1) {
				// * 1 : -1,0   0,-1
				int[] go = new int[2048];
				go[0]=-1;
				go[1]=-1;
				for(int g=2; g<=10;g++) {
					int li = (int)Math.pow(2, g-1);
					for(int start = li; start<2*li; start++){
						if(start<li+li/2){
							go[start]=-go[start-li];
						}else {
							go[start]=go[start-li];
						}
					}
				}
				int move= (int)Math.pow(2, gen);
				for(int g=0; g<move;g++) {
					if(g%2==0) {
						ni+=go[g];
						nj+=0;
					}else {
						ni+=0;
						nj+=go[g];
					}
					map[ni][nj]=1;
				}
			}else if(dir==2) {
				// * 2 :  0,-1  1,0
				int[] go = new int[2048];
				go[0]=-1;
				go[1]=1;
				for(int g=2; g<=10;g++) {
					int li = (int)Math.pow(2, g-1);
					for(int start = li; start<2*li; start++){
						if(start<li+li/2){
							go[start]=-go[start-li];
						}else {
							go[start]=go[start-li];
						}
					}
				}
				int move= (int)Math.pow(2, gen);
				for(int g=0; g<move;g++) {
					if(g%2==0) {
						ni+=0;
						nj+=go[g];
					}else {
						ni+=go[g];
						nj+=0;
					}
					map[ni][nj]=1;
				}
				
			}else if(dir==3) {
				// * 3 :  1,0  |  0,1 |  -1,0  0,1 | 
				int[] go = new int[2048];
				go[0]=1;
				go[1]=1;
				for(int g=2; g<=10;g++) {
					int li = (int)Math.pow(2, g-1);
					for(int start = li; start<2*li; start++){
						if(start<li+li/2){
							go[start]=-go[start-li];
						}else {
							go[start]=go[start-li];
						}
					}
				}
				int move= (int)Math.pow(2, gen);
				for(int g=0; g<move;g++) {
					if(g%2==0) {
						ni+=go[g];
						nj+=0;
					}else {
						ni+=0;
						nj+=go[g];
					}
					map[ni][nj]=1;
				}
			}
		}
		int cnt=0;
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==0)continue;
				if(map[i+1][j]==0)continue;
				if(map[i][j+1]==0)continue;
				if(map[i+1][j+1]==0)continue;
				cnt++;
			}
		}
		
		System.out.println(cnt);

		br.close();

	}

}
