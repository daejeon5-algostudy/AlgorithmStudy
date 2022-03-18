package practice0317;
import java.io.*;
import java.util.*;
public class Main_bj_6987_월드컵 {
	static boolean check ; //트루면 가능 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		label:for(int t=0; t<4; t++) {
			//System.out.println("------------------------------");
			//한번 뜬 팀이랑은 다시 안 뜬다.
			boolean[][] v = new boolean[6][6];
			
			check = false;
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int[][] result = new int[6][3];
			for(int i=0; i<6; i++) {
				for(int j=0; j<3; j++) {
					result[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//for(int[] a: result) System.out.println(Arrays.toString(a));
			for(int i=0; i<6; i++) {
				int sum=0;
					
				for(int j=0; j<3; j++) {
					sum+=result[i][j];
				}			
				if(sum!=5) {
					//System.out.println("d");
					sb.append(0+" ");
					continue label;
				}
			}
			solve(result, 0, v);
			if(check) {
				sb.append(1+" ");
			}else sb.append(0+" "); 
			
		}
		System.out.println(sb.toString());
	}
	static void solve(int[][] result , int cnt, boolean[][] v ) {
		
		if(check)return;
		//for(int[] a: result) System.out.println(Arrays.toString(a));
		//System.out.println("-------------------------------------");
		if(cnt==5) {
			//System.out.println(1);
			int sum=0;
			for(int i=0; i<3; i++) {
				sum+=result[5][i];
			}
			if(sum==0) {
				//for(int[] a: result) System.out.println(Arrays.toString(a));
				//System.out.println("성공");
				check = true; 
			}
			return;
		}
		
		if(result[cnt][0]==0) {//승리 없음 
			if(result[cnt][1]==0) {
				if(result[cnt][2]==0) { //모두다 소진 
					solve(result, cnt+1, v);
					
				}else {//패배만 남은 경우
					for(int i=cnt+1; i<6;i++) {
						if(result[i][0]>0 && !v[cnt][i]) {
							v[cnt][i]=true;
							v[i][cnt]=true;
							result[i][0]--;//승 감소
							result[cnt][2]--;//패 감소
							solve(result, cnt, v);
							result[i][0]++;
							result[cnt][2]++;
							v[cnt][i]=false;
							v[i][cnt]=false;
						}						
					}										
				}
			}else {//승리는 없고 무승부만 남은 경우
				for(int i=cnt+1; i<6;i++) {
					if(result[i][1]>0 && !v[cnt][i]) {
						v[cnt][i]=true;
						v[i][cnt]=true;
						result[i][1]--;//무승부 감소 
						result[cnt][1]--;//자신의 무승부 감소
						solve(result, cnt, v);
						v[cnt][i]=false;
						v[i][cnt]=false;
						result[i][1]++; 
						result[cnt][1]++;
					}
				}
				
			}
		}else {//승리가 남았다
			for(int i=cnt+1; i<6;i++) {
				if(result[i][2]>0 && !v[cnt][i]) {
					v[cnt][i]=true;
					v[i][cnt]=true;
					result[i][2]--;// 
					result[cnt][0]--;//
					solve(result, cnt, v);
					result[i][2]++; 
					result[cnt][0]++;
					v[cnt][i]=false;
					v[i][cnt]=false;
				}
			}
			
		}
		
	}
}
