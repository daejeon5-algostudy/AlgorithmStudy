import java.util.*;
import java.io.*;

/* 1. 중위순회한 결과
 * 2. DFS로 넣었습니당
 * - 중간값 루트 노드 인덱스를 저장
 */
public class Main_bj_9934_완전이진트리 {
	static int K, size; // K줄
	static int[] input;
	static List<Integer>[] nodes;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		K = sc.nextInt();
		size = (int) (Math.pow(2, K) - 1);

		input = new int[size];

		for (int i = 0; i < size; i++) {
			input[i] = sc.nextInt();
		}

		nodes = new ArrayList[K];
		for (int i = 0; i < K; i++) { // 초기화
			nodes[i] = new ArrayList<>();
		}

		dfsInOrder(0, size - 1, 0);

		for (int i = 0; i < K; i++) {
			for (int nd : nodes[i]) {
				System.out.print(nd + " ");
			}
			System.out.println();
		}
		sc.close();
	}

	static void dfsInOrder(int start, int end, int floor) {
		if (start > end)
			return;

		int middle = (start + end) / 2;
		nodes[floor].add(input[middle]);

		dfsInOrder(start	 , middle - 1, floor + 1);
		dfsInOrder(middle + 1, end		 , floor + 1);
	}
}
