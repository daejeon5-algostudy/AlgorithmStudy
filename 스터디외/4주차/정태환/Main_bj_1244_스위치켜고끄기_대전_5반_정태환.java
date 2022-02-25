package a0204;

import java.io.*;
import java.util.*;

public class Main_bj_1244_스위치켜고끄기_대전_5반_정태환 {

	public static void main(String[] args) throws Exception {
		StringBuilder sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[N+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		int time=st.countTokens();
		for(int i=1; i<=time; i++)
			arr[i]=Integer.parseInt(st.nextToken());

		int M=Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int g=Integer.parseInt(st.nextToken());
			int num=Integer.parseInt(st.nextToken());

			if (g == 1) {
				int idx=1;
				while (num*idx <= N) {
					arr[num*idx]=(arr[num*idx] == 1) ? 0:1;
					idx++;
				}
			} else {
				int idx=0;
				while (true) {
					if (idx==0) arr[num]=(arr[num] == 1) ? 0:1;
					else if (arr[num+idx] == arr[num-idx]) {
						arr[num+idx]=(arr[num+idx] == 1) ? 0:1;
						arr[num-idx]=(arr[num-idx] == 1) ? 0:1;
					} else break;
					idx++;
					if (num+idx>N || num-idx<1) break;
				}
			}
		}
		for (int i=1; i<=N; i++) {
			sb.append(arr[i]).append(" ");
			if(i%20 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}
