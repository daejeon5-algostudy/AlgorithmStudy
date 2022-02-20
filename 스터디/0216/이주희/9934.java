import java.io.*;
import java.util.*;

public class Main_bj_9934_완전이진트리 {
	static int N;
	static Queue<Integer>[] tree;
	static Queue<Integer> input;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_9934.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		N = (1<<K)-1;							// 노드의 갯수 (완전이진트리이기 때문)
		input = new ArrayDeque<Integer>();		// 중위순회
		tree = new Queue[K];					// 자료형이 큐인 배열
		
		for(int i=0; i<K; i++) {
			tree[i] = new ArrayDeque<Integer>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			input.offer(Integer.parseInt(st.nextToken()));
		}
		
		inorder(1, 0);
		
		for(int i=0; i<K; i++) {			// depth 별로 출력
			while(!tree[i].isEmpty())
				System.out.print(tree[i].poll() + " ");
			System.out.println();
		}
	}
	
	// 중위순회를 돌림
	static void inorder(int node, int depth) {
		if(node > N) {
			return;
		}
		
		inorder(node*2, depth+1);			// 왼쪽 자식 노드, depth 1 증가
		tree[depth].offer(input.poll());	// tree[depth] 에 삽입
		inorder(node*2+1, depth+1);			// 오른쪽 자식 노드, depth 1 증가
	}

}
