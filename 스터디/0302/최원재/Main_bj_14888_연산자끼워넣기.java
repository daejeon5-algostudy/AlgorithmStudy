package practice0226;
import java.io.*;
import java.util.*;
public class Main_bj_14888_연산자끼워넣기 {
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int[] res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] op = new int[N-1];
		res = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N;i++) {
			nums[i]= Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int cnt=0;
		for(int i=0; i<4;i++) {
			int li = Integer.parseInt(st.nextToken());
			for(int j=0;j<li;j++) {
				op[cnt]=i;
				cnt++;
			}
		}
		solve(N, new boolean[N-1], 0, op, nums);
		System.out.println(max);
		System.out.println(min);
		br.close();

	}
		static void solve(int N,boolean[] v, int count,int[] op, int[] nums  ) {//연산자 수는 N-1이다 기억!!
			if(count==N-1) {
				int result = caculate(nums, res, N);
				if(result<min)min = result;
				if(result>max)max=result;
				return;
			}
			//순열 
			
			for(int i=0; i<N-1;i++) {
				if(v[i])continue;
				v[i]=true;
				res[count]=op[i];
				solve(N, v, count+1, op, nums);
				v[i]=false;
			}
			
		}
		static int caculate(int[] nums, int[] res, int N) {
			int sum = nums[0];
			for(int  i=1 ; i<N; i++ ) {
				
				if(res[i-1]==0) {
					//+
					sum= sum + nums[i];
				}else if(res[i-1]==1) {
					//-
					sum= sum - nums[i];
				}else if(res[i-1]==2) {
					//x
					sum= sum * nums[i];
				}else {
					// /
					sum= sum / nums[i];
				}
			}			
			return sum;
		}
}
