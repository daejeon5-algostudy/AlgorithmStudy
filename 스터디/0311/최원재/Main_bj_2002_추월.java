package practice0305;
import java.io.*;
import java.util.*;
public class Main_bj_2002_추월 {
//바로 앞에 거랑 비교하면 되지 않을까?
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] first = new String[N];
		String[] second = new String[N];
		for(int i=0;i<N;i++) {
			first[i]=br.readLine();
		}
		for(int i=0;i<N;i++) {
			second[i]=br.readLine();
		}
		int count=0;
		for(int i=1;i<N;i++) {
			
			String cur = first[i];
			int curidx=-1;
			for(int j=0;j<N;j++) {
				if(second[j].equals(cur)) {
					curidx = j;
					break;
				}
			}
			label:for(int j=0; j<i;j++) {//cur보다 먼저 들어온 것을 탐색 
				String ahead = first[j];
				for(int k=0; k<N;k++) {
					
					if(second[k].equals(ahead)) {//cur보다 먼저 들어온 것을 나온 차 목록에서 찾았다!
						if(k>curidx) {//그런데 cur보다 먼저 들어간 차가 나올 때는 늦게 나왔으니 추월을 한 것 
							count++;
							break label;
						}
						break;
					}
				}
			}
		}
		
		System.out.println(count);
		br.close();

	}

}
