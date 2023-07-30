package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //회원 서비스를 만들려면 회원리파지토리가 필요.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);      //중복 회원 검증
        memberRepository.save(member);       //회원가입은 멤버리파지토리에서 세이브만 호출해주면 된다.
        return member.getId();              //임의로 Id를 반환하도록 해줌. -> 회원가입을 하면 id만 반환해 주겠다.
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> { //넘어온 멤버에 값이 있으면(ifPresent- null이 아니라 값이 있으면 동작하는 것.옵셔널이기 때문에 가능한 것이다.)(m) -> "이걸 던져줌"
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     * service는 비지니스에 가까운 용어를 써야한다.
     * 그래야 기획자든 개발자든 뭔가 이상할 때 '회원가입쪽이 이상해'하면 'join을 살펴보자' 하고 볼 수 있다.
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}


