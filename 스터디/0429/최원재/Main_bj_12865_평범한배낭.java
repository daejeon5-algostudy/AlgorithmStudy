package p0424;
import java.io.*;
import java.util.*;

public class Main_bj_12865_평범한배낭 {

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] bag = new int[N+1][K+1];
		for(int i=1; i<=N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int wei = Integer.parseInt(st.nextToken());
			int val=Integer.parseInt(st.nextToken());
			
			for(int k=1;k<=K;k++) {
				if(k>=wei) {
					if(val+bag[i-1][k-wei]>bag[i-1][k]) {
						bag[i][k]=val+bag[i-1][k-wei];
					}else {
						bag[i][k]=bag[i-1][k];
					}
				}else {
					bag[i][k]=bag[i-1][k];
				}
			}
		}
		//for(int[] a: bag)System.out.println(Arrays.toString(a));
		System.out.println(bag[N][K]);
		
		
		
		br.close();

	}

}
