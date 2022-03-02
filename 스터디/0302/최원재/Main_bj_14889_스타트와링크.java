package practice0226;
import java.io.*;
import java.util.*;
public class Main_bj_14889_스타트와링크 {
	static int[] team;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[][] ability = new int[N][N];
		team = new int[N/2];
		for(int i=0; i<N; i++) {
			numbers[i]=i;// {0 1 2 3}
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				ability[i][j]=Integer.parseInt(st.nextToken());
				
			}
		}
		
		solve(N, 0, 0, numbers, ability);
		System.out.println(min);
		br.close();

	}
	static void solve(int N, int start, int count, int[] numbers, int[][] ability) {
		//조합
		if(count == N/2) {
			int sumA=0;
			int sumB=0;
			for(int i=0; i<N/2-1;i++) {
				for(int j=i+1;j<N/2;j++) {
				sumA+=(ability[team[i]][team[j]]+ability[team[j]][team[i]]);
				}
			}
			for(int i=0; i<N-1; i++) {
				if(numbers[i]==-1)continue;
				for(int j=i+1; j<N; j++) {
					if(numbers[j]==-1)continue;
					sumB+=(ability[numbers[i]][numbers[j]]+ability[numbers[j]][numbers[i]]);
				}
			}
			if(Math.abs(sumA-sumB)<min)min=Math.abs(sumA-sumB);
			return;
		}
		for(int i=start; i<N; i++) {
			team[count]=i;
			numbers[i]=-1;
			solve(N, i+1, count+1, numbers,ability);
			numbers[i]=i;
		}
	}
}
