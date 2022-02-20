import java.io.*;
import java.util.*;
public class Main_bj_6603_로또 {
	static int[] out;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
	while(true) {
		String str = br.readLine();
		if(str.equals("0"))break;
	
	
		StringTokenizer st = new StringTokenizer(str, " ");
		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		out = new int[6];
		for(int i=0;i<N;i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		//Arrays.sort(input);
		solve(0,0,input,N);
		sb.append("\n");
	}
		System.out.println(sb.toString());
		br.close();

	}
	static void solve(int cnt, int start,int[] input,int len ) {
		if(cnt == 6) {
			for(int i=0; i<6;i++) {
				sb.append(out[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=start;i<len;i++) {
			out[cnt]=input[i];
			solve(cnt+1, i+1, input, len );
		}
	}

}
