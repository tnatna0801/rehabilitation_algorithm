package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1700 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<Integer> multi = new ArrayList<Integer>();
		int[] arr = new int[k];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (n >= k) {// 멀티탭 크기가 사용해야하는 개수보다 크커나 같으면 굳이 뺄 필요 없어용
			System.out.println(0);
		} else {
			int use = 0;// 멀티탭 사용중이 개수
			int del = 0;// 멀티탭 빼는 수(답으로 사용될 예정)

			for (int i = 0; i < k; i++) {
				int now = arr[i];

				if (multi.contains(now))// 멀티탭에 이미 꽂혀있는 종류라면 그냥 넘김
					continue;
				else if (use < n) {// 아직 멀티탭 자리가 남았다면
					multi.add(now);// 그냥 멀티탭에 꽂으면 됨
					use++;// 사용중인 수 증가 시키기
				} else if (use == n) {// 멀티탭 사용중인 개수가 최대가 됐다면 이제 뺄 차례가 됐다
					// 여기서 포인트는 최소한으로 멀티탭 제거를 해야하니까 현재 꽂혀있는 애들 중에 가장 나중에 다시 필요하거나 안 필요한 애들을 뺄 것!
					Deque<Integer> stack = new ArrayDeque<>();
					boolean[] chk = new boolean[k + 1];// 후보군 체킹
					boolean[] multiChk = new boolean[n];// 멀티탭 체킹

					for (int j = i + 1; j < k; j++) {// 현재 이후의 수들을 탐색
						if (multi.contains(arr[j]) && !chk[arr[j]]) {// 현재 멀티탭에 꽂혀있는 종류이면서 아직 들어오지 않은 수 일 경우
							// chk[]를 사용하는 이유: 같은 종류를 여러번 입력이 들어올 수 있으므로 한번만 카운트 한다.
							chk[arr[j]] = true;
							int multiIdx = multi.indexOf(arr[j]);// 멀티탭에 몇번째 인덱스에 있는 애인지 체크
							stack.push(multiIdx);// 스택에 넣어준다
							multiChk[multiIdx] = true;// 멀티탭에서도 체크해줌
						}
						if (stack.size() == n) {// 근데 for문 다 돌기전에 멀티탭에 꽂힌 수만큼 stack이 찼다면 for 더 돌 필요 없음
							break;// 나오셩
						}
					}
					if (stack.size() == n) {// 멀티탭에 꽂힌 애들이 뒤에 한번 이상은 다 등장한다는 말임
						// 그러면 가장 늦게 등장하는 애들 빼는것이 효율적임
						int cur = stack.pop();// 스택의 가장 나중에 들어온 종류를
						multi.remove(cur);// 뺀다
						multi.add(now);// 그리고 현재값을 넣는다
						del++;// 멀티탭 빼기 +1
					} else {// 근데 만약에 현재 멀티탭 꽂힌 애들이 뒤에 나오는 것도 있고 아닌것도 있음요 그러면? 안 나오는 애를 우선으로 뺍니다(다시 꽂을 일
							// 없으므로)
						for (int j = 0; j < n; j++) {
							if (multiChk[j])// 나중에 나올 애들이면 안 뺌
								continue;
							// 이후에 다시 꽂지 않을 애면
							multi.remove(j);// 해당 위치 값 없애고
							multi.add(now);// 현재값 넣고
							del++;// 변경 카운트 추가
							break;
						}
					}
				}
			}

			System.out.println(del);
		}

	}
}
