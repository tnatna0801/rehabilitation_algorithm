import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PG_258712 {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        Map<String, Map<String, Integer>> record = new HashMap<>();
        Map<String, Integer> score = new HashMap<>();
        Map<String, Integer> gift = new HashMap<>();

        // 초기화
        for(int i = 0; i<friends.length; i++){

            String name = friends[i];
            record.put(name, new HashMap<>());
            score.put(name, 0);
            gift.put(name, 0);

        }

        // 선물 확인
        for(int i = 0; i < gifts.length; i++){
            String[] name = gifts[i].split(" ");

            String giver = name[0];
            String receiver = name[1];

            record.get(giver).put(receiver, record.get(giver).getOrDefault(receiver, 0) + 1);
            score.put(giver, score.get(giver) + 1); // 준거
            score.put(receiver, score.get(receiver) - 1); // 받은거

            // System.out.println(giver + " , "  + receiver + "  " +  +score.get(giver) + " , " + score.get(receiver));
        }

        // 계산하자
        for(int i = 0; i<friends.length; i++){
            for(int j = 0; j<friends.length; j++){
                if(i == j) continue;

                int giveCnt = record.get(friends[i]).getOrDefault(friends[j], 0); // 나
                int receiveCnt = record.get(friends[j]).getOrDefault(friends[i], 0); // 비교할 애

                // System.out.println("준사람: " + friends[i] + ", 받은사람: " + friends[j] + ": " + giveCnt + ", " + receiveCnt );

                if(giveCnt < receiveCnt) continue; // 여기 고치면 시간복잡도가 줄어들지 않을까? 이렇게 안하고 어짜피 더 선물 많이 받은 친구만 다음달에 선물받으니까 적게 받은 경우는 인덱스도 건너띄게 하면 될 것 같은데 뇌정지상태임다

                if(giveCnt > receiveCnt)
                    gift.put(friends[i], gift.get(friends[i]) + 1);
                else if (giveCnt == receiveCnt) {
                    if(score.get(friends[i]) <= score.get(friends[j])) continue;

                    System.out.println("여기");

                    gift.put(friends[i], gift.get(friends[i]) + 1); // 유유 여기를 i가 아니라 j를 써서 틀렸음
                }
            }
        }

        return Collections.max(gift.values());
    }
}
