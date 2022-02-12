import java.io.*;
import java.util.*;

public class Main_bj_2628_종이자르기 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2628.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> widths = new LinkedList<>();
		LinkedList<Integer> heights = new LinkedList<>();
		
		widths.add(0); widths.add(W);
		heights.add(0); heights.add(H);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int row = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			if(row==0) {
				heights.add(val);
			}else {
				widths.add(val);
			}
		}
		
		Collections.sort(widths);
		Collections.sort(heights);
	
		int maxW=0 ,maxH=0;
		
		for(int i=1; i<widths.size(); i++) {
			maxW = Math.max(maxW, widths.get(i) - widths.get(i-1));
		}
		
		for(int i=1; i<heights.size(); i++) {
			maxH = Math.max(maxH, heights.get(i) - heights.get(i-1));
		}
		
		System.out.println(maxW*maxH);

	}

}
