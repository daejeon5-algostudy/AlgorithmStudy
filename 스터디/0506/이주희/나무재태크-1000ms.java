import java.io.*;
import java.util.*;

public class Main_bj_16235_나무재테크 {

	static class Tree implements Comparable<Tree>{
		int x, y, age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_16235.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] plusLand = new int[N][N];
		int[][] land = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				plusLand[i][j] = Integer.parseInt(st.nextToken());
				land[i][j] = 5;
			}
		}
		
		Queue<Tree> trees = new LinkedList<>();		// 나무를 담는 배열
	
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			trees.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		// 입력 종료
		
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
		
		
		while(K-- > 0) {
			// 봄
			Collections.sort((List<Tree>)trees);		// 나이가 적은 나무 순서대로 정렬
			
			List<Tree> dieTree = new ArrayList<>();		// 죽은 나무 배열
			List<Tree> fiveYear = new ArrayList<>();	// 나이가 5의 배수인 나무 배열
			
			int size = trees.size();
			while(size-- > 0) {
				Tree tree = trees.poll();
				
				if(land[tree.x][tree.y] >= tree.age) {
					land[tree.x][tree.y] -= tree.age;
					tree.age += 1;
					trees.add(tree);
					
					if(tree.age%5==0) fiveYear.add(tree);
				} else {
					dieTree.add(tree);
				}
			}
				
			if(trees.size()==0) break;
			
			// 여름
			
			for(int i=0; i<dieTree.size(); i++) {
				Tree tree = dieTree.get(i);
				
				land[tree.x][tree.y] += tree.age/2;
			}
			
			// 가을
			for(int i=0; i<fiveYear.size(); i++) {
				Tree tree = fiveYear.get(i);
				
				for(int d=0; d<8; d++) {
					int nx = tree.x + dx[d];
					int ny = tree.y + dy[d];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					trees.add(new Tree(nx, ny, 1));
				}
			}
			
			// 겨울
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					land[i][j] += plusLand[i][j];
				}
			}
			
		}
		
		System.out.println(trees.size());
		br.close();
	}

}
