package Hello.Hellospring.service;

import Hello.Hellospring.domain.Member;
import Hello.Hellospring.repository.MemberRepository;
import Hello.Hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 일반적인 코드로 시간 넣는 코드 -> 모든 메소드에 추가해야하기 때문에 효율성이 떨어지고 유지보수가 어려움
        /*long start = System.currentTimeMillis();

        try{
            //같은 이름이 있는 중복 회원X
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();

        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("join = " + timeMs + "ms");
        }*/

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
