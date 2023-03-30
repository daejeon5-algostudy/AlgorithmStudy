import java.io.*;
import java.util.*;

public class Main {
	
	static int L, C;
	static char[] selected, alphabet;
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		selected = new char[L];
		alphabet = new char[C];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
		combi(0, 0);
		
		System.out.println(ans.toString());
		br.close();
	}
	
	static void combi(int start, int cnt) {
		if(cnt == L) {
			int num1=0, num2=0;
			StringBuilder br = new StringBuilder();
			for(int i=0; i<L; i++) {
				br.append(selected[i]);
				if(selected[i] == 'a' || selected[i] == 'e' ||
				   selected[i] == 'i' || selected[i] == 'o' ||
				   selected[i] == 'u')
					num1++;
				else num2++;
			}
			
			if(num1<1 || num2<2) return;
			
			ans.append(br.toString()).append("\n");
			return;
		}
		
		for(int i=start; i<C; i++) {
			selected[cnt] = alphabet[i];
			combi(i+1, cnt+1);
		}
	}

}
