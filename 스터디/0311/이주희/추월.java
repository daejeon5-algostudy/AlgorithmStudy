import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_2002.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Queue<String> carOrder = new LinkedList<String>();
		Map<String, String> passed = new HashMap<String, String>(); 
		
		for(int i=0; i<N; i++) {
			String car = br.readLine();
			carOrder.offer(car);
			passed.put(car, "false");
		}		
		
		int ans=0;
		while(!carOrder.isEmpty()) {
			String cur = carOrder.poll();
			if(passed.get(cur).equals("true")) continue;
			
			while(true) {
				String car = br.readLine();
				if(cur.equals(car) == false) {
					ans++;
				}else break;
				
				passed.replace(car, "true");
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}
