package algoStudy.a0427;

import java.io.*;
import java.util.*;

public class Main_bj_G2_20061_모노미노도미노2 {

	static int[] delx= {-1, 0, 1, 0}; // 상우하좌
	static int[] dely= { 0, 1, 0,-1};
	static boolean[][] blue, green;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20061"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		int[][] data=new int[N][3];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			data[i][0]=Integer.parseInt(st.nextToken());
			data[i][1]=Integer.parseInt(st.nextToken());
			data[i][2]=Integer.parseInt(st.nextToken());
		}
		
		blue=new boolean[4][6];
		green=new boolean[6][4];
		
		int ans=0;
		for(int i=0; i<N; i++) {
			int[] now=data[i]; // 0: 블록의 모양, 1: x좌표, 2: y좌표
			
			// now의 정보에 따라 블럭을 추가한다
			addBlock(now);
			
			// 블루는 열, 그린은 행에 한줄완성된것이 있는지 확인하고 있으면 삭제, 점수추가
			ans+=calcAndremove();
			
			// 오버된 부분이 있으면 블럭들 밑으로 내리기
			deleteBlock();
		}
		int cnt=0;
		for(boolean[] ba:blue) for(boolean b:ba) if(b) cnt++;
		for(boolean[] ba:green) for(boolean b:ba) if(b) cnt++;
		System.out.println(ans);
		System.out.println(cnt);
		br.close();
	}

	static void deleteBlock() {
		// 그린 검사
		for(int i=0; i<2; i++) {
			boolean ch=false;
			for(int j=0; j<4; j++) {
				if(green[1][j]) {
					ch=true;
				}
			}
			// 한줄내리기
			if(ch) {
				for(int k=5; k>0; k--) {
					green[k]=green[k-1].clone();
				}
				Arrays.fill(green[0], false);
			}
		}
		
		// 블루 검사
		for(int i=0; i<2; i++) {
			boolean ch=false;
			for(int j=0; j<4; j++) {
				if(blue[j][1]) {
					ch=true;
				}
			}
			// 한줄우측이동
			if(ch) {
				for(int j=5; j>0; j--) {
					for(int k=0; k<4; k++) {
						blue[k][j]=blue[k][j-1];
					}
				}
				for(int k=0; k<4; k++) {
					blue[k][0]=false;
				}
			}
		}
		
		
	}

	static int calcAndremove() {
		int cnt=0;
		// 그린 검사
		for(int i=5; i>=0; i--) {
			// 행검사
			boolean ch=true; // 검사여부
			for(int j=0; j<4; j++) {
				if(!green[i][j]) {
					ch=false;
					break;
				}
			}
			// 삭제하면 상위층 전체적으로 내리면서 cnt++
			if(ch) {
				cnt++;
				for(int j=i; j>0; j--) {
					green[j]=green[j-1].clone();
				}
				Arrays.fill(green[0], false);
				i++;
			}
		}
		
		// 블루 검사
		for(int i=5; i>=0; i--) {
			// 행검사
			boolean ch=true; // 검사여부
			for(int j=0; j<4; j++) {
				if(!blue[j][i]) {
					ch=false;
					break;
				}
			}
			// 삭제하면 상위층 전체적으로 내리면서 cnt++
			if(ch) {
				cnt++;
				for(int j=i; j>0; j--) {
					for(int k=0; k<4; k++) {
						blue[k][j]=blue[k][j-1];
					}
				}
				for(int j=0; j<4; j++) {
					blue[j][0]=false;
				}
				i++;
			}
		}
		
		return cnt;
	}

	static void addBlock(int[] now) {
		int shape=now[0], x=now[1], y=now[2];
		if(shape==1) { // 1칸
			// 초록보드: x좌표만 변경
			for(int i=1; i<6; i++) {
				if(i==5 || green[i+1][y]) {
					green[i][y]=true;
					break;
				}
			}
			// 파란보드: y좌표만 변경
			for(int i=1; i<6; i++) {
				if(i==5 || blue[x][i+1]) {
					blue[x][i]=true;
					break;
				}
			}
			
		} 
		else if(shape==2) { // 수평 2칸
			// 초록보드: x좌표만 변경
			for(int i=0; i<6; i++) {
				if(i==5 || green[i+1][y] || green[i+1][y+1]) {
					green[i][y]=true;
					green[i][y+1]=true;
					break;
				}
			}
			// 파란보드: y좌표만 변경
			for(int i=0; i<5; i++) {
				if(i==4 || blue[x][i+2]) {
					blue[x][i]=true;
					blue[x][i+1]=true;
					break;
				}
			}
		} 
		else { // 수직 2칸
			// 초록보드: x좌표만 변경
			for(int i=0; i<5; i++) {
				if(i==4 || green[i+2][y]) {
					green[i][y]=true;
					green[i+1][y]=true;
					break;
				}
			}
			// 파란보드: y좌표만 변경
			for(int i=0; i<6; i++) {
				if(i==5 || blue[x][i+1] || blue[x+1][i+1]) {
					blue[x][i]=true;
					blue[x+1][i]=true;
					break;
				}
			}
		}
	}
	
}
