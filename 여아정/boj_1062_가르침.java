package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1062_가르침 {
	static int n, k, map[][], max = 0;
	static int word[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int know = init();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if (k - 5 < 0) {

		} else if (k == 26) {// 가르칠게 알파벳 모두라면 단어 모두를 알 수 있음
			max = n;
		} else {
			word = new int[n];// 입력으로 주어진 단어를 비트화 한 int형 수로 넣을 배열
			int all = 0;// 단어마다 배울 수 있는 알파벳의 비트를 넣을 변수

			for (int i = 0; i < n; i++) {
				String temp = br.readLine();

				word[i] = know;
				for (int j = 4; j < temp.length() - 4; j++) {
					word[i] |= 1 << temp.charAt(j) - 96;// 입력으로 주어진 단어를 알파벳마다 비트1로 비트마스킹함
				}
				all |= word[i];

			}

			all ^= know;// xor(know에 있는 비트값 지우고 새로 배울 수 있는 알파벳 위치의 비트만 남김)
			if (Integer.bitCount(all) < k - 5) {// Integer.bitCount(all)은 all의 1인 비트를 카운트
				// 최대 배울 수 있는 수보다 배울 알파벳 수가 작다면 무조건 입력으로 들어오는 단어는 다 알 수 있음.
				max = n;
			} else
				re(k - 5, 2, know, all);
		}
		System.out.println(max);

	}

	private static void re(int cnt, int idx, int know, int all) {// 조합으로 재귀를 돌립니다리
		if (cnt == 0) {// 최대로 다 배웠다면
			int now = checkWord(know);// 몇단어를 알 수 있는지 체크하기
			max = Math.max(max, now);

			return;
		}

		for (int i = idx; i <= 26; i++) {// 알파벳 하나하나 확인해보기
			if ((all & (1 << i)) > 0) {// 배울 수 있는 알파벳일경우(all의 해당 알파벳 자리 비트가 1일 때)
				re(cnt - 1, i + 1, (know | (1 << i)), all);// 배운 알파벳을 저장하는 know에 추가
			}
		}
	}

	private static int checkWord(int know) {
		int cnt = 0;

		for (int i = 0; i < n; i++) {

			if (word[i] == (know & word[i]))// 배울수 있는 단어가 있으면
				cnt++;// 카운트 추가
		}

		return cnt;
	}

	private static int init() {// 초기 알파벳 저장하기
		String str = "antatica";// 단어마다 필수로 들어가는 알파벳들이므로
		int know = 0;
		for (int i = 0; i < str.length(); i++) {
			know = (know | (1 << (str.charAt(i) - 96)));// know에 저장한다
		}

		return know;
	}
}