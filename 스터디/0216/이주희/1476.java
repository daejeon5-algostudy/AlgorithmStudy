import java.io.*;
import java.util.*;

public class Main_bj_1476_날짜계산 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int year = 1, e=1, s=1, m=1;
		while(true) {
			if(e==E && s==S && m==M) break;
			
			year++;
			e++;
			s++;
			m++;
			
			if(e==16) e=1;
			if(s==29) s=1;
			if(m==20) m=1;
		}
		
		System.out.println(year);
		br.close();
	}

}


//1 16 16
//16

// 15 28 19
