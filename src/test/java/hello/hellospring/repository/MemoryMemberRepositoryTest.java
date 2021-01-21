package hello.hellospring.repository;

import hello.hellospring.domain.Member;


//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 메소드가 끝날 때마다 실행이 됨
    public void afterEach(){    // test가 실행이 되고 끝날 때마다 repository저장소를 지움 -> 순서가 상관이 없어짐
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //shift + F6으로 rename 가능(위에거 복사했을 때)
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    // findAll()메소드까지 작성후 run을 하면 에러가 난다
    // 아까 괜찮았던 findByName()에서 에러가 남, 즉 순서가 보장이 안됨. spring1, spring2가 저장이 되어버렸기 때문
    // 순서에 의존적으로 설계하면 안됨(메소드마다 따로 동작하도록 설계해야 함)
    //
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
