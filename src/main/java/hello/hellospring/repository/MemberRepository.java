package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면, 저장된 회원이 반환됨(저장됨).(id생김)

    Optional<Member> findById(Long id); // 위에서 저장 된 멤버를 id로 회원을 찾는다.

    Optional<Member> findByName(String name); // name으로 회원을 찾는다.
    List<Member> findAll(); // 저장된 모든 회원을 찾음.
}
