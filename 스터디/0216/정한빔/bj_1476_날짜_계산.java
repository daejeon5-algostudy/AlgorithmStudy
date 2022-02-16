import java.util.*;
import java.io.*;
/*1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19
 * year = 15*x+E
 * year = 28*y+S
 * year = 19*z+M
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();// 지구
		int S = sc.nextInt();// 태양
		int M = sc.nextInt();// 달
		int year = 1;
		while(true) {
			if((year-E)%15==0&&(year-S)%28==0&&(year-M)%19==0)break;
			year++;
		}
		System.out.println(year);
		sc.close();
	}
}
