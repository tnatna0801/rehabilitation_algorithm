package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_16916 {
    static int[] pi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();//전체 문자열
        String P = br.readLine();//부분 문자열

        pi = new int[P.length()];//부분문자열 접두사와 접미사 같은 count 세는 배열

        //pi배열값 먼저 만들기
        //패턴값을 탐색해서 해당 인덱스까지 접두사와 접미사가 같은 문자 개수 카운트를 모두 저장한다
        int j = 0;
        for (int i = 1; i < P.length(); i++) {
            while (j > 0 && P.charAt(j) != P.charAt(i)) {
                j = pi[j - 1];
            }
            if (P.charAt(i) == P.charAt(j)) {
                pi[i] = ++j;
            }
        }
        //전체 문자열에서 패턴 문자열 탐색시작
        System.out.println(doKMP(S,P));
    }

    private static int doKMP(String S, String P) {
        int idx = 0;//패턴 문자열의 인덱스

        for (int i = 0; i < S.length(); i++) {//전체 문자열을 돌면서 볼거임
            while (idx > 0 && S.charAt(i) != P.charAt(idx)) {//패턴문자열이 하나 이상 맞으면서 더이상 맞지 않는 경우가 온다면
                idx = pi[idx - 1];//idx는  최대 0으로 까지 다시 밀려남
            }

            if (S.charAt(i) == P.charAt(idx)) {//문자가 같은경우
                if (idx == P.length() - 1) {//패턴문자 마지막 글자 까지 같은경우
                    return 1;//이 부분문자열이 존재하니까 더이상 탐색 안해도 됨
                } else {
                    idx++;//다음 문자 볼 것
                }
            }
        }
        return 0;//다 찾아봤지만 부분 문자열은 존재하지 않어유
    }
}
