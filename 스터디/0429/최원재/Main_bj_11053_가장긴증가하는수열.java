package p0427;
import java.io.*;
import java.util.*;

public class Main_bj_11053_가장긴증가하는수열 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[1000];
		int len=0;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		nums[0]=Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<N; j++) {
				if(nums[j]>num) {
					if(j==0)nums[j]=num;
					else {
						if(nums[j-1]<num)nums[j]=num;
					}
					break;
				}
				else if(nums[j]==0) {
					if(nums[j-1]<num)nums[j]=num;
					break;
				}
			}
			
			//System.out.println(Arrays.toString(nums));
		}
		
		int cnt=0;
		for(int i=0; i<1000; i++) {
			if(nums[i]==0)break;
			else cnt++;
		}
		System.out.println(cnt);
		br.close();

	}

}
