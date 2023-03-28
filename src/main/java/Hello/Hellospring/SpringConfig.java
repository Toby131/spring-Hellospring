package Hello.Hellospring;

import Hello.Hellospring.repository.MemberRepository;
import Hello.Hellospring.repository.MemoryMemberRepository;
import Hello.Hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
