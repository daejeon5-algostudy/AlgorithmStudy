package practice0305;
import java.io.*;
import java.util.*;
public class Main_bj_5212_지구온난화 {
	static int[] di = {-1,0,1,0};
	static int[] dj= {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int R=Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		char[][] copy = new char[R][C];
		
		for(int i=0; i<R;i++) {
			String str = br.readLine();
			for(int j=0; j<C;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		
		for(int i=0;i<R ;i++) {
			for(int j=0; j<C;j++) {
				if(map[i][j]=='X') {
					if(bfs(i,j,R,C,map)) {
						copy[i][j]='.';
					}else {
						copy[i][j]='X';
					}
				}else {
					copy[i][j]='.';
				}
			}
		}
		int starti=R;
		int startj=C;
		int lasti=0;
		int lastj=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C;j++) {
				if(copy[i][j]=='X') {
					if(i<starti)starti=i;
					if(j<startj)startj=j;
					if(i>lasti)lasti=i;
					if(j>lastj)lastj=j;
				}
			}
		}
		//for(char[]a: copy)System.out.println(Arrays.toString(a));
		for(int i=starti;i<=lasti;i++) {
			for(int j=startj;j<=lastj;j++) {
				sb.append(copy[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();

	}
	static boolean bfs(int i, int j, int R, int C,char[][] map) {
		int ni=i;
		int nj=j;
		int cnt=0;
		
		for(int d=0; d<4;d++) {
			ni=i+di[d];
			nj=j+dj[d];
			if(0<=ni&&ni<R&&0<=nj&&nj<C) {
				if(map[ni][nj]=='.')cnt++;
			}else {
				cnt++;
			}
		}
		if(cnt>2)return true;
			
		else return false;
	}

}
