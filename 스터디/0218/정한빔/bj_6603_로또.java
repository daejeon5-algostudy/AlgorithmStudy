import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static int[] a, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		k = Integer.parseInt(st.nextToken());
		
		if(k==0) break;
		
		a = new int[k];
		ans = new int[6];
		for (int i = 0; i < k; i++) {
			a[i]=Integer.parseInt(st.nextToken());
		}
		
		comb(0,0);
		System.out.println();
		}
		br.close();
	}
	static void comb(int cnt, int start) {
		if(cnt==6) {
			for (int i : ans) {
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		for(int i=start; i<k; i++) {
			ans[cnt]=a[i];
			comb(cnt+1,i+1);
		}
	}
}
