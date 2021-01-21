package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    // MemberService memberService = new MemberService();
    //  = new MemberService() 삭제 후 아래처럼 바꾸기
    MemberService memberService;

    //돌 때마다 끝나고 나면 DB의 값을 날려줌.
   MemoryMemberRepository memberRepository = new MemoryMemberRepository();

   @BeforeEach
   public void beforeEach(){
       memberRepository = new MemoryMemberRepository();
       memberService = new MemberService(memberRepository);
   }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    //Test는 과감하게 한글로 바꿔도 된다.
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

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


/*
        try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then
    }

    private void fail() {
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}