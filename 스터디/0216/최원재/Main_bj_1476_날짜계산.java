import java.io.*;
import java.util.*;
public class Main_bj_1476_날짜계산 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int e=1,s=1,m=1;
		int cnt=1;
		while(true) {			
			if(e==E&&s==S&&m==M) {
				break;
			}
			if(++e==16)e=1;
			if(++s==29)s=1;
			if(++m==20)m=1;
			cnt++;
		}
		System.out.println(cnt);
		br.close();
	}
}
