import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_5212.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] sea = new char[R+2][C+2];	// 범위가 벗어난 지역도 바다
		for(int i=1; i<=R; i++) {
			String s = br.readLine();
			for(int j=1; j<=C; j++) {
				sea[i][j] = s.charAt(j-1);
			}
		}
//		System.out.println("초기값");
//		System.out.println(sea[0][0]);
//		
//		System.out.println("초기");
//		for(int i=1; i<=R; i++) {
//			for(int j=1; j<=C; j++) {
//				System.out.print(sea[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		int[] di = {-1, 0, 1, 0};
		int[] dj = {0, 1, 0, -1};
		
		int row[] = new int[2];	//[0] : start [1] : end
		int col[] = new int[2]; //[0] : start [1] : end
		col[0] = C+2; // 처음값을 가장 큰 값으로 지정
		
		char[][] tmpSea = new char[R+2][C+2];
		for(int i=0; i<+R+2; i++) {
			tmpSea[i] = Arrays.copyOf(sea[i], C+2);
		}
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(sea[i][j]=='X') {
					
					// 3면 또는 4면이 바다인지 체크
					int cnt=0;
					for(int k=0; k<4; k++) {
						int ni = i+di[k];
						int nj = j+dj[k];
						
						if(ni<0 || ni>=R+2 || nj<0 || nj>C+2 || sea[ni][nj]=='X') continue;
						cnt++;
					}
					
					if(cnt>=3) {	// 3면 또는 4면이 바다
						tmpSea[i][j]='.';
					}
					else {						
						if(row[0]==0) {
							row[0] = i;
							row[1] = i;
						}
						else row[1] = i;
						
						if(col[0]>j) col[0] = j;
						if(col[1]<j) col[1] = j;
						
					}
				}
			}
		}
		
//		System.out.println("후");
//		for(int i=1; i<=R; i++) {
//			for(int j=1; j<=C; j++) {
//				System.out.print(tmpSea[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		System.out.println(row[0]+", "+row[1]+", " + col[0] + ", " + col[1]);
		StringBuffer sb = new StringBuffer();
		for(int i=row[0]; i<=row[1]; i++) {
			for(int j=col[0]; j<=col[1]; j++) {
				sb.append(tmpSea[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
