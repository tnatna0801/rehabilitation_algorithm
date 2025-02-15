package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1522 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int countA = 0; // 입력받은 문자열에서 a의 갯수
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) == 'a') countA++;
        }

        //a의 갯수만큼 a가 연속적으로 와야하니까 a의 갯수만큼 부분 문자열을 보면서 b의 갯수를 센다
        //b의 갯수가 가장 작은 경우가 최소 교환 횟수임

        int min = 1001;
        for(int i = 0; i<input.length(); i++) {
            int countB = 0; // b의 갯수
            for(int j = i; j<countA+i; j++){
                if(input.charAt(j % input.length()) == 'b') countB++;
            }

            min = Math.min(min, countB);
        }

        System.out.println(min);
    }
}
