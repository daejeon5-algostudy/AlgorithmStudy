package algoStudy.a0211;

import java.io.*;
import java.util.*;

public class Main_bj_2564_경비원 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2564"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken()); // 가로행수
		int M=Integer.parseInt(st.nextToken()); // 세로행수
		int tc=Integer.parseInt(br.readLine()); // 상점의개수

		int[][] p=new int[tc][2];
		for(int i=0; i<tc; i++) {
			st=new StringTokenizer(br.readLine());
			p[i][0]=Integer.parseInt(st.nextToken()); // 북1남2서3동4
			p[i][1]=Integer.parseInt(st.nextToken()); // 북남은 왼쪽부터 서동은 위쪽부터
		}
		
		st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken()); // 북1남2서3동4
		int myp = Integer.parseInt(st.nextToken()); // 북남은 왼쪽부터 서동은 위쪽부터
		int sum = 0;
		for(int i=0; i<tc; i++) {
			int tmp1=0, tmp2=0;
			
			if((p[i][0]==1 && d==2)||(p[i][0]==2 && d==1)|| // 건너편에 있는경우
			   (p[i][0]==3 && d==4)||(p[i][0]==4 && d==3)) {
				if(p[i][0] == 1 || p[i][0] == 2) { // 북남의 경우
					tmp1=myp+p[i][1]+M;
					tmp2=N-myp+N-p[i][1]+M;
					sum+=tmp1<tmp2 ? tmp1:tmp2;
				}
				else { // 동서의경우
					tmp1=myp+p[i][1]+N;
					tmp2=M-myp+M-p[i][1]+N;
					sum+=tmp1<tmp2 ? tmp1:tmp2;
				}
			}
			else // 건너편이 아닌경우
				if(p[i][0]==d) sum+=Math.abs(p[i][1]-myp); // 같은 방향인경우
				else // 옆방향인경우
					if(d==1) // 북의 경우
						if(p[i][0]==3) sum+=myp+p[i][1];
						else 		   sum+=N-myp+p[i][1];
					else if(d==2) // 남의경우
						if(p[i][0]==3) sum+=myp+M-p[i][1];
						else 		   sum+=N-myp+M-p[i][1];
					else if(d==3) // 서의경우
						if(p[i][0]==1) sum+=myp+p[i][1];
						else 		   sum+=M-myp+p[i][1];
					else // 동의경우
						if(p[i][0]==1) sum+=myp+N-p[i][1];
						else 		   sum+=M-myp+N-p[i][1];
		}
		System.out.println(sum);
	}

}
