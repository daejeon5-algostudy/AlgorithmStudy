import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> Lis = new ArrayList<>();
		Lis.add(0);
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(Lis.get(Lis.size()-1) < num) {
				Lis.add(num);
			} else {
				// num 이 들어갈 자리 찾기 (num 과 대소관계가 같은 곳과 교체 (대소관계는 유지되는데 더 작은 수로 교체되는 것이므로 다음에 들어올 수가 더 많아짐))
				// 이분탐색
				int start = 0;
				int end = Lis.size()-1;
				int ans = 0;
				while(start <= end) {
					int mid = (start+end)/2;
					
					if(Lis.get(mid) < num) {
						start = mid+1;
					} else {
						ans = mid;
						end = mid-1;
					}
				}
				
				Lis.set(ans, num);
			}
		}
		
		System.out.println(Lis.size()-1);	// 처음에 넣어준 0 빼기
		br.close();
	}

}
