package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//다른데서 가져다쓸게 아니라 굳이 public을 붙일 필요 없다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //save만든게 잘 동작하는지 확인.
    @Test
    public void save() {

        Member member = new Member();
        member.setName("spring"); // 멤머의 이름 세팅


        repository.save(member); // repository에 이 member를 save해줌.

        // 내가 저장한 멤버를 id로 찾아보며 검증.
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member)); // 이렇게 확인해봐도 된다.
//        Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result); // member == result 다.
    }

    @Test
    public void findByName() { //이름으로 찾는 것 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //  멤버1 저장.

        //정교한 테스트를 위해 멤버2 만들기.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //  멤버12 저장.

        // findByName가 잘 동작하는지 테스트.
        Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }
}
