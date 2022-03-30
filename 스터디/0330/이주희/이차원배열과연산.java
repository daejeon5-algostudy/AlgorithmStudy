import java.io.*;
import java.util.*;

public class Main_bj_17140_이차원배열과연산 {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_17140.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		
		int[][] mat = new int[100][100];
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rcnt=3, ccnt=3;			// 3*3 으로 시작
		int ans=0;
		
		int[] cnt;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) return Integer.compare(o1[0], o2[0]);	// cnt가 같다면 수가 작은 순서대로
				return Integer.compare(o1[1], o2[1]);	// cnt가 작은 순서대로
			}
		});
			
		while(true) {
			if(mat[r][c] == k) break;
			if(ans>100) {
				ans = -1;
				break;
			}
			
			if(rcnt>=ccnt) {	// 행 정렬
				int ncnt=0;
				for(int i=0; i<rcnt; i++) {
					cnt = new int[101]; // 1~100	갯수를 셀 배열
					for(int j=0; j<ccnt; j++) {
						cnt[mat[i][j]]++;
					}
					
					for(int j=1; j<=100; j++) {
						if(cnt[j]!=0) {				// 한개라도 있다면
							pq.add(new int[] {j, cnt[j]});
						}
					}
					
					int index=0;
					while(!pq.isEmpty()) {			// 정렬된 숫자 map 에 넣기
						if(index>100) break;
						
						int[] cur = pq.poll();
						mat[i][index++] = cur[0];
						mat[i][index++] = cur[1];
					}
					for(int j=index; j<ccnt; j++) {		//기존에 있던 숫자가 남아있다면 숫자 삭제 
						mat[i][j]=0;
					}
					ncnt = Math.max(ncnt, index);		// 열의 가장 큰 사이즈
				}
				ccnt=ncnt;
			}else {		// 열 정렬
				int ncnt=0;
				for(int i=0; i<ccnt; i++) {
					cnt = new int[101]; // 1~100
					for(int j=0; j<rcnt; j++) {
						cnt[mat[j][i]]++;
					}
					for(int j=1; j<=100; j++) {
						if(cnt[j]!=0) {
							pq.add(new int[] {j, cnt[j]});
						}
					}
					int index=0;
					while(!pq.isEmpty()) {
						if(index>100) break;
						
						int[] cur = pq.poll();
						mat[index++][i] = cur[0];
						mat[index++][i] = cur[1];
					}
					for(int j=index; j<rcnt; j++) {		//기존에 남아있던 숫자 삭제 
						mat[j][i]=0;
					}
					ncnt = Math.max(ncnt, index);
				}
				rcnt=ncnt;
			}

			ans++;

		}
		
		System.out.println(ans);
		br.close();
		
		
	}

}
