```Java
import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {											// 테스트 케이스 만큼 반복
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");		// N, M 받아오기
			int N = Integer.parseInt(token.nextToken());
			int M = Integer.parseInt(token.nextToken());
			
			int[][] arr = new int[N][N];											// N * N 배열 생성
			
			for (int i = 0; i < N; i++) {											// 한 줄씩 배열에 입력
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++)
					arr[i][j] = Integer.parseInt(token.nextToken());		
			}

			int max = 0;
			for (int i = 0; i <= N - M; i++) {				// 고정 행 인덱스 위치
				for (int j = 0; j <= N - M; j++) {			// 고정 열 인덱스 위치
					int sum = 0;							// M * M 배열의 연산값 저장 변수
					for (int k = i; k < i + M; k++) {		// 변동 행 인덱스
						for (int l = j; l < j + M; l++)		// 변동 열 인덱스
							sum += arr[k][l];				// 현재 인덱스에서 M * M 크기 만큼 덧셈 
					}
					max = max > sum ? max : sum;			// 이전 최대값과 현재 연산값 중 큰값사용
				}
			}
			sb.append("#").append(tc).append(" ")			// 테스트 케이스 번호 출력
			  .append(max).append("\n");					// 결과값 버퍼에 삽입
		}
		System.out.println(sb);
		br.close();
	}
}
```
