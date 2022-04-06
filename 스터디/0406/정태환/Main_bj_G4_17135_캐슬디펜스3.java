package algoStudy.a0406;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17135_캐슬디펜스3 {
    static int N,M,D,ans=0;
    static int[][] map;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
    	System.setIn(new FileInputStream("res/input_bj_17135"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());
 
        map=new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
 
        comb(1, 0, new int[3]);
        System.out.println(ans);
        br.close();
    }
 
    public static void comb(int start, int cnt, int[] pick) {
        if (cnt==3) {
            defense(pick);
            return;
        }
 
        for (int i=start; i<=M; i++) {
            pick[cnt]=i;
            comb(i+1, cnt+1, pick);
        }
    }
 
    public static void defense(int[] pick) {
    	int[][] map2=new int[N+1][M+1];
		for(int i=1;i<=N; i++) 
			map2[i]=map[i].clone();
    	
        int cnt=0;
        for (int c=0; c<N; c++) {
            boolean[][] v=new boolean[N+1][M+1];
            for (int p=0; p<3; p++) {
                int now=pick[p]; 
                int min=Integer.MAX_VALUE, minx=Integer.MAX_VALUE, miny=Integer.MAX_VALUE;
 
                for (int i=1; i<=N; i++)
                    for (int j=1; j<=M; j++)
                        if (map2[i][j]==1 && min>=getDist(N+1,now,i,j))
                            if (min>getDist(N+1,now,i,j)) {
                                min=getDist(N+1,now,i,j);
                                minx=i; miny=j;
                            } else
                                if (miny>j) {
                                    minx=i; miny=j;
                                }
 
                if (min<=D) v[minx][miny] = true;
            }
 
            for(int i=1; i<=N; i++)
                for(int j=1; j<=M; j++)
                    if (v[i][j]) {
                        map2[i][j]=0;
                        cnt++;
                    }
 
            for (int i=N; i>=1; i--) 
            	map2[i]=map2[i-1].clone();
        }
        ans = Math.max(ans, cnt);
    }
 
    static int getDist(int ax, int ay, int bx, int by) {
		return Math.abs(ax-bx)+Math.abs(ay-by);
	}
}
