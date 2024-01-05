import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();

        System.out.println(kmp(s, p));
    }

    private static int kmp(String s, String p) {

        if(s.length() < p.length()) // 거 이러면 볼 필요도 없이 부분 문자열이라고 볼 수 없지용가리
            return 0;

        //  패턴 구하기 ( 접두사와 접미사가 일치하는 패턴)
        int[] pi = new int[p.length()];
        failure(pi, p);

        int pointS = 0; // s문자열 인덱스
        int pointP  = 0; // p문자열 인덱스
        int result  = 0; // 결과

        while (true) {
            if(pointS == s.length()) break; // 문자열을 모두 탐색했다면 종료

            if(s.charAt(pointS) == p.charAt(pointP)) { // 일치하면 다음 인덱스로
                pointP++;
                pointS++;
                if(pointP == p.length()) { // p문자열 인덱스가 p 길이와 같아졌다는 것은 p가 s의 부분 문자열이라는 뜻이므로 종료
                    result = 1;
                    break;
                }
            }
            else { // 일치하지 않는다면 패턴만큼 건너 뛰기! KMP!
                //pointP = pointS - pi[pointP-1];// 구했던 pi 배열로 일치하지 않는 부분을 건너뛴다. / 좀 이상하게 생각한거 남겨둘라고 안지웠습니다.
                if(pointP > 0) // 현재는 일치하지 않지만 이전엔 일치한게 있었다면?
                    pointP = pi[pointP - 1]; // 일치하지 않는다면 접두사와 접미사가 아닌 부분을 뛰어 넘으면 된다.
                else
                    pointS++;
            }
        }
        return result;
    }

    // 부분 문자열 P의 접두사 접미사 패턴 구하기
    private static void failure(int[] pi, String p) {
        int i = 1; // 패턴을 탐색하는 인덱스
        int j = 0; // 일치하는 부분의 길이 및 인덱스
        pi[0] = 0; // 첫번째 원소의 pi 값은 항상 0

        while(true){

            if(i == p.length()) break; // 패턴을 모두 탐색했을 경우

            if(p.charAt(j) == p.charAt(i)) { // 일치한다면 일치하는 길이를 저장 ( j + 1 )
                pi[i++] = j+1;
                j++;
            } else if (j > 0) { // 현재 문자가 일치하진 않지만 이전 위치에선 일치했을 경우
                j = pi[j-1]; // j를 이전 위치에서 일치하는 부분의 길이로 이동하여 다시 비교한다.
                //failure(pi, j-1, i);
            } else { // 현재 문자가 일치하지 않고, 이전 위치에서도 일치하지 않을 경우
                pi[i] = 0; // 일치하는 부분의 길이를 0으로 설정
                i++; // 다음 인덱스로 이동해 탐색한다.
            }
        }
    }
}
