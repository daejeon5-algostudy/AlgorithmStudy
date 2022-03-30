package practice0322;
import java.io.*;
import java.util.*;
class comp implements Comparator<int[]>{

	@Override
	public int compare(int[] o1, int[] o2) {
		int r = o1[1]-o2[1];
		if(r==0)r=o1[0]-o2[0];
		return r;
	}
	
}
public class Main_bj_17140_이차원배열과연산 {


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[3][3];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		Comparator<int[]> co = new comp();
		int r=Integer.parseInt(st.nextToken());
		int c=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int len_i=3;
		int len_j=3;
		for(int i=0; i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
			
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int t=0;
		
		while(true) {
			if(t>100)break;
		
			if(len_i>=r&&len_j>=c) {
				if(map[r-1][c-1]==k)break;
			}
			
			t++;
			if(len_i>=len_j) {
				int max=0; // 가장 큰 행 수 구하기 위한 것 
				List<List<int[]>> lists = new ArrayList<>();
				for(int i=0; i<len_i; i++) {
					
					List<int[]> list = new ArrayList<int[]>();
					boolean[] numcheck = new boolean[101];
					for(int j=0; j<len_j;j++) {
						if(map[i][j]==0)continue;
						if(numcheck[map[i][j]]==false) {
							numcheck[map[i][j]]=true;
							list.add(new int[] {map[i][j],1});
						}else {
							
							for(int a=0; a<list.size(); a++) {
								if(list.get(a)[0]==map[i][j]) {
									list.get(a)[1]++;
								}
							}
						}
						
					}
					list.sort(co);
					if(max<list.size())max=list.size();
					lists.add(list);					
				}
				
				if(max*2<=100) {
					len_j=max*2;
				
					for(int i=0; i<len_i; i++) {
						int[] row = new int[max*2];
						for(int a=0; a<lists.get(i).size(); a++) {
							
							
							row[2*a]=lists.get(i).get(a)[0];
							row[2*a+1]=lists.get(i).get(a)[1];
						}
						map[i]=row;
					}
				}else {
					len_j=100;
					
					for(int i=0; i<len_i; i++) {
						int limit = lists.get(i).size();
						int[] row = new int[100];
						for(int a=0; a<(limit=limit<=50?limit:50) ; a++) {
							
							
							row[2*a]=lists.get(i).get(a)[0];
							row[2*a+1]=lists.get(i).get(a)[1];
						}
						map[i]=row;
					}
				}
				

			}else {
				int max=0; // 가장 큰 열 크기 구하기 위한 것 
				List<List<int[]>> lists = new ArrayList<>();
				for(int j=0; j<len_j; j++) {	
					List<int[]> list = new ArrayList<int[]>();
					boolean[] numcheck = new boolean[101];
					for(int i=0; i<len_i;i++) {
						if(map[i][j]==0)continue;
						if(numcheck[map[i][j]]==false) {
							numcheck[map[i][j]]=true;
							list.add(new int[] {map[i][j],1});
						}else {
							
							for(int a=0; a<list.size(); a++) {
								if(list.get(a)[0]==map[i][j]) {
									list.get(a)[1]++;
								}
							}
						}
						
					}
					list.sort(co);
					if(max<list.size())max=list.size();
					lists.add(list);					
				}
				if(max*2<=100) {
					len_i=max*2;
					map=new int[len_i][len_j];
					for(int j=0; j<len_j; j++) {
						
						for(int a=0; a<lists.get(j).size(); a++) {
							//System.out.println(max);
							map[2*a][j]=lists.get(j).get(a)[0];
							map[2*a+1][j]=lists.get(j).get(a)[1];
									
						}
					}
				}else {
					len_i=100;
					
					for(int j=0; j<len_j; j++) {
						int limit = lists.get(j).size();
						
						for(int a=0; a<(limit=limit<=50?limit:50) ; a++) {
							//System.out.println(max);
							map[2*a][j]=lists.get(j).get(a)[0];
							map[2*a+1][j]=lists.get(j).get(a)[1];
						}
					
					}
				}
			}
			
		}
		if(t>100)System.out.println(-1);
		else System.out.println(t);
		
		
	}

}
