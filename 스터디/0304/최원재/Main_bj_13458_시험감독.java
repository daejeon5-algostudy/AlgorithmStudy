import java.io.*;
import java.util.*;

public class Main_bj_13458_시험감독 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//시험장 수
		long count=0;
		int[] stu = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			stu[i]=Integer.parseInt(st.nextToken());
		}
		st =new StringTokenizer(br.readLine()," ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		for(int i=0; i<N;i++) {
			long n=stu[i]-B;
			if(n<=0)continue;
			if(n%C==0) {
				count=count+n/C;
			}else {
				count+=n/C+1;
			}
		}
		System.out.println(count+N);
		br.close();

	}

}
