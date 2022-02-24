import java.util.*;
import java.io.*;
/* 조합
 * 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
 * */
public class Main {
	static ArrayList<Integer> num;
	static ArrayList<Character> op;
	static int L,C;
	static char[] a, ans;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		L=Integer.parseInt(st.nextToken()); //암호길이
		C=Integer.parseInt(st.nextToken()); //주어진 문자 개수
		
		ans=new char[L];
		a=new char[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			a[i]=st.nextToken().charAt(0);
		}
		Arrays.sort(a);
		//System.out.println(Arrays.toString(a));
		comb(0,0);
		
		br.close();
	}
	
	static void comb(int cnt, int start) {
		if(cnt==L) {
			//System.out.println(Arrays.toString(ans));
			int c =0;
			int v = 0;
			for(int i=0; i<L;i++) {
				if(ans[i]=='a' || ans[i]=='e' || ans[i]=='i' || ans[i]=='o' || ans[i]=='u') c++;
				else v++;
			}
			if(v>=2 && c>=1) {
				for (char d : ans) {
					System.out.print(d);
				}
				System.out.println();
			}
			return;
		}
		//유도파트
		for(int i=start; i<C;i++) {
			ans[cnt]=a[i];
			comb(cnt+1,i+1);
		}
	}
	
}
