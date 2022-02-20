import java.io.*;
import java.util.*;

class Tower implements Comparable<Tower>{
	int point;
	int height;
	
	Tower(int point, int height){
		this.point = point;
		this.height = height;
	}
	
	@Override
	public int compareTo(Tower o) {
		return point - o.point;
	};
	
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Tower[] tower = new Tower [N];
		
		int max=0, big_point=0;
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int point = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			tower[i] = new Tower(point, height);
			if(max <= height) {
				max = height;
				big_point = point;
			}
		}
		
		Arrays.sort(tower);
		
		max = 0;
		int sum=0;
		int befPoint=-1;
		
		for(int i=0; i<N; i++) {
			if(max <= tower[i].height) {        // 전까지의 넓이 계산해서 더함
				sum += max * (tower[i].point - befPoint);
				max = tower[i].height;
				befPoint = tower[i].point;
			}
			if(befPoint == big_point) {
				sum += tower[i].height;
				break;
			}
		}
		
		max = 0;
		befPoint = 1001;
		for(int i=N-1; i>=0; i--) {
			if(max <= tower[i].height) {
				sum += max * (befPoint - tower[i].point);
				max = tower[i].height;
				befPoint = tower[i].point;
			}
			
			if(befPoint == big_point) break;
		}
		System.out.println(sum);
	}

}
