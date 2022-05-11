import java.io.*;
import java.util.*;

public class Main_bj_G4_16235_나무재테크 {
	
	static class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		
	}
	
	static int[] delx= {-1,-1, 0, 1, 1, 1, 0,-1}; // 상부터 시계방향
	static int[] dely= { 0, 1, 1, 1, 0,-1,-1,-1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16235"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		int N=Integer.parseInt(st.nextToken()); // 땅크기
		int M=Integer.parseInt(st.nextToken()); // 나무의 수
		int K=Integer.parseInt(st.nextToken()); // K년후
		
		int[][] map=new int[N][N]; // 맵의 양분
		int[][] S2D2=new int[N][N]; // 겨울에 추가되는 양분
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				S2D2[i][j]=Integer.parseInt(st.nextToken());
				map[i][j]=5;
			}
		}
		
		ArrayDeque<Tree> tree=new ArrayDeque<>();
		ArrayDeque<Tree> dead=new ArrayDeque<>();
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int age=Integer.parseInt(st.nextToken());
			tree.add(new Tree(x, y, age));
		}
		for(int year=0; year<K; year++) {
			// 봄: 자신의 나이만큼 양분을 먹고 나이1증가, 나무가 여러개면 어린나무부터먹고, 양분 부족하면 죽음
			ArrayDeque<Tree> tmp=new ArrayDeque<>();
			while(!tree.isEmpty()) {
				Tree t=tree.poll();
				int x=t.x, y=t.y, age=t.age;
				if(map[x][y]>=age) {
					map[x][y]-=age;
					t.age+=1;
					tmp.offer(t);
				} else {
					dead.offer(t);
				}
			}
			tree=tmp;
			
			// 여름: 죽은 나무가 양분으로 전환, 나이/2 만큼 추가 소수점아래는 제거
			while(!dead.isEmpty()) {
				Tree t=dead.poll();
				map[t.x][t.y]+=t.age/2;
			}
			
			// 가을: 5의 배수의 나이인 나무 번식, 8방에 1살 나무 추가
			ArrayDeque<Tree> newTree=new ArrayDeque<>();
			while(!tree.isEmpty()) {
				Tree t=tree.poll();
				int x=t.x, y=t.y;
				if(t.age%5==0) {
					for(int d=0; d<8; d++) {
						int dx=x+delx[d], dy=y+dely[d];
						if(dx<0||dx>=N||dy<0||dy>=N) continue;
						newTree.offerFirst(new Tree(dx, dy, 1));
					}
				}
				newTree.offer(t);
			}
			tree=newTree;
			
			// 겨울: S2D2가 땅에 양분추가
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					map[i][j]+=S2D2[i][j];
		}
		
		// 살아있는 나무의 개수
		System.out.println(tree.size());
		
		br.close();
	}
	
}
