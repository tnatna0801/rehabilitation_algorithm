package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1283 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] shortcut = new boolean[26]; // 단축키 여부

        for(int i =0; i<N; i++){
            String option = br.readLine();
            String[] word = option.split(" "); // 옵션을 단어로 분리

            // 1. 단어의 첫 글자로 단축키 지정
            boolean flag = false;
            for(int j =0; j<word.length; j++){
                StringBuilder sb = new StringBuilder(word[j]);
                char target = word[j].toLowerCase().charAt(0);
                if(!shortcut[target-'a']) {
                    shortcut[target-'a'] = true;
                    flag = true;
                    sb.insert(0,'[');
                    sb.insert(2,']');
                    word[j] = sb.toString(); // 괄호를 삽입한 결과를 다시 삽입
                    break;
                }
            }

            // 첫 글자로 단축키를 지정했다면 다음 단어로 넘어감
            if(flag) {
                printResult(word);
                continue;
            }

            // 2. 아니라면 왼쪽부터 차례대로 단축키로 지정
            StringBuilder sb = new StringBuilder(option);
            for(int j =0; j<option.length(); j++){
                if(option.charAt(j) == ' ') continue;
                char target = option.toLowerCase().charAt(j);
                if(!shortcut[target-'a']) {
                    shortcut[target-'a'] = true;
                    sb.insert(j,'[');
                    sb.insert(j+2,']');
                    break;
                }
            }

            System.out.println(sb);
        }

    }

    private static void printResult(String[] word) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<word.length; i++){
            sb.append(word[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

}
