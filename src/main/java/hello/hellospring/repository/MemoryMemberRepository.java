package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
     // save를 할 때, 저장을 어딘가에 해야되겠죠? 메모리니까~ 그래서 map을 만들어줌.
    // key는 회원의 id니까 Long으로, 값은 Member. / 실무에서는 동시성 문제가 있어서 이렇게 공유되는 변수일 때는 콘코너테이션? 뭐를 써야 하는데 여기선 단순한 예제니까 단순하게 HashMap을 쓴다.
     private static Map<Long, Member> store = new HashMap<>();

     //sequence는 0, 1, 2이렇게 키값을 생성해 주는 것이라고 생각하면 됨. 이것도 실무에서는 그냥 long이렇게 안씀.
     private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 멤버를 저장할 때 sequence값을 하나 올려줄거임.
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // 스토어에서 꺼내면 된다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()                          //루프를 돌리는 것.
                .filter(member -> member.getName().equals(name)) // 람다 사용. member에서 member.getName()이 파라미터로 넘어온 name이랑 같은지 확인. 같은 경우에만 필터링 된다.
                .findAny(); // 찾으면 반환해줌. findAny는 하나로도 찾는 것임. 루프를 다 돌면서 (map에서 돌면서) 하나 찾아지면 그걸 그냥 반환하는 것. 그런데 끝까지 돌렸는데도 없으면 옵셔널에 null이 포함되서 반환이 되는 것.
    }

    @Override
    public List<Member> findAll() {
        //위에는 map인데 여기는 List로 되어있다. 실무에선 list를 많이 쓴다. 루프 돌리기도 편하고 그래서.
        return new ArrayList<>(store.values()); //스토어에 있는 values(map<Long, Member> 에서 value값인 Member를 가르키는 것. 즉,  store.values()는 Member다. -> Member들이 쫙 반환이 된다!)
    }
}
