import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_28702 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = 0;
        for (int i = 3; i > 0; i--) {

            String input = br.readLine();

            if (Character.isDigit(input.charAt(0))) { // 숫자라면
                target = Integer.parseInt(input) + i;
                break;
            }
        }

        if (target % 15 == 0) System.out.println("FizzBuzz");
        else if (target % 3 == 0) System.out.println("Fizz");
        else if (target % 5 == 0) System.out.println("Buzz");
        else System.out.println(target);
    }
}