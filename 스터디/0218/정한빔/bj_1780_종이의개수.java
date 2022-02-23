import java.io.*;
import java.util.*;
/* 00 01 02
 * 10 11 12
 * 20 21 22
 * */
public class Main_bj_1780_종이의개수 {
	static int zero=0, positive=0, negative=0;
	static int[][] paper;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine()); //종이의 수
		paper=new int[N][N];
		
		//종이입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				paper[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		cut(N,0,0);
		System.out.println(negative);
		System.out.println(zero);
		System.out.println(positive);
	}
	static void cut(int size, int r, int c) {		
			int index = paper[r][c];
			boolean chk = false; 
			
			label : for (int i = r; i < r+size; i++) { //label을 안걸어주니까 for문 하나만 break된다
				for (int j = c; j < c+size; j++) {
					if(paper[i][j]!=index) {
						chk=true;
						break label;
						}
				}
			}
			if(!chk) {
				if(index==1) positive++;
				else if(index==0)zero++;
				else if(index==-1) negative++;
			}
			else if(chk) {
        int new_size=size/3;
        for(int i=1; i<=3; i++){
          for(int j=1; j<=3; j++){
            cut(new_size,r+new_size*i,c+new_size*j)
          }
        }
        /*
				cut(size/3,r	  	   ,c	        ); //00
				cut(size/3,r	  	   ,c+size/3	); //01
				cut(size/3,r	       ,c+(size/3*2)); //02
				cut(size/3,r+size/3    ,c	        ); //10
				cut(size/3,r+size/3	   ,c+size/3    ); //11
				cut(size/3,r+size/3    ,c+(size/3*2)); //12
				cut(size/3,r+(size/3*2),c	        ); //20
				cut(size/3,r+(size/3*2),c+size/3    ); //21
				cut(size/3,r+(size/3*2),c+(size/3*2)); //22
        */
			}
				
	}
}
