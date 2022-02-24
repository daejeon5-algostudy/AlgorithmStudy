import java.io.*;
import java.util.*;
public class Main_bj_1759_암호만들기_대전_5반_최원재 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int L = Integer.parseInt(st.nextToken());//C중에 ㅣ
		
		int C = Integer.parseInt(st.nextToken());
		String[] alpha = new String[C];//전체 주어지는 알파벳 넣기 
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C;i++) {
			alpha[i]=st.nextToken();
		}
		String[] out = new String[L];
		Arrays.sort(alpha);
		
		combination(0,0,0,0,alpha,out,C,L);
		System.out.println(sb.toString());
		br.close();

	}
	static void combination(int cnt, int start, int mocount, int jacount, String[] alpha, String[] out,
			int C, int L) {
		if(cnt==L) {
			
			if(mocount>=1&&jacount>=2) {
			
				for(int i=0; i<L;i++) {
					sb.append(out[i]);
				}
				sb.append("\n");
				return;
			}
			return;
		}
		for(int i=start;i<C; i++ ) {
			if(alpha[i].equals("a")||alpha[i].equals("e")||alpha[i].equals("i")||alpha[i].equals("o")||alpha[i].equals("u")) {
				out[cnt]=alpha[i];
				combination(cnt+1, i+1, mocount+1, jacount, alpha, out, C, L);
			}else {
				out[cnt]=alpha[i];
				combination(cnt+1, i+1, mocount, jacount+1, alpha, out, C, L);
			}
		}
	}
	
}
