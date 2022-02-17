import java.io.*;
import java.util.*;
public class Main_bj_9934_완전이진트리 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N= Integer.parseInt(br.readLine());
		int n=(int)(Math.pow(2, N));
		int[] tree = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for(int i=1; i<n;i++) {
			tree[i]=Integer.parseInt(st.nextToken());
		}
		int center = n/2;		
		int x =1;
		int plus = (int)(Math.pow(2, N));
		
		for(int i=N; i>0; i--) {
			int stage=center;
			for(int t=0; t<x;t++) {
				sb.append(tree[stage]).append(" ");
				stage+=plus;
			}
			x*=2;
			center/=2;
			plus/=2;
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
