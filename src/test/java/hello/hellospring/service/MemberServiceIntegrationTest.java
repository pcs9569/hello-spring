package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional

class MemberServiceIntegrationTest {

    // 기존 코드들은 생성자에 인젝션 하는 것이 좋은데 테스트케이스는 그냥 필트 기반으로 @Autowired하는 것이 편하다 테스트 케이스는 편하게 하는 것이 좋음
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

/*
    // DB의 데이터들을 다음 테스트에 영향이 없게 하기 위해 지워주는 역할, 여기서는 필요 없기 때문에 지금은 지워준다.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
*/

    @Test
    //@Commit
    //Test는 과감하게 한글로 바꿔도 된다.
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring100");

        //when
        Long saveId = memberService.join(member);

        //then
        Member finMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(finMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        //when
        memberService.join(member1);
        // () -> memberService.join(member2)); 이 로직을 실행할건데
        // IllegalStateException.class 이 예외가 터져야 함.
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    private void fail() {
    }


}