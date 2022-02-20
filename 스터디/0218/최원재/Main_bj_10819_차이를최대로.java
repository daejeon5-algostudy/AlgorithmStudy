import java.io.*;
import java.util.*;
public class Main_bj_10819_차이를최대로 {
	static int[] out;
	static int max = -1;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int input[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		out = new int[N];
		for(int i=0; i<N; i++ ) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		boolean[] visited= new boolean[N];
		solve(0, visited, input, N);
		System.out.println(max);
		br.close();

	}
	
	static void solve(int cnt, boolean[] visited, int[] input, int N) {
		
		if(cnt==N) {
			int sum = 0;

			for(int i=0; i<N-1;i++) {
				sum+=Math.abs(out[i]-out[i+1]);
				
			}
			if(sum>max) max = sum;
			return;
		}
		
		for(int i=0; i<N;i++) {
			if(visited[i])continue;
			visited[i]=true;
			out[cnt]=input[i];
			solve(cnt+1,visited, input, N);
			visited[i]=false;
		}
	}

}
