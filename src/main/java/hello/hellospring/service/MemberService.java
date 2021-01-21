package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service를 박아줘야 스프링이 Service 클래스를 찾을 수 있다
// 1) MemberService는 MemberRepository가 필요하다.

//데이터를 변경하거나 저장할 때 항상 @Transational이 있어야함
@Transactional
public class MemberService {
    //회원 서비스가 있으려면 회원레퍼지토리가 있어야함
    //Ctrl + Shift + T

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // = new MemoryMemberRepository()부분을 지우고, 직접 new해서 생성하는게 아니라아래처럼 외부에서 넣어주는 것으로 바꿔 줌

    // 2) @Autowired 되어있으면 MemberService를 스프링이 생성을 할 때, @Service를 보고 서비스네?!하고 스프링 컨테이너에 등록을 하면서 이 생성자를 호출한다.
    // (위에 주석 이어서) @Autowired가 있으면 너는 MemberRepository가 필요하구나라고 컨테이너에 있는 MemberRepository를 딱 넣어준다.

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    /*
        *회원 가입
     */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try{
            validateDuplicateMember(member); //중복 회원 검증, extractMethod로 메소드 분리

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finsih = System.currentTimeMillis();
            long timeMs = finsih - start;
            System.out.println("join = " + timeMs + "ms");
        }

        //같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { //member에 null이 아닌 어떤 값이 있으면
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
/*
        validateDuplicateMember(member); //중복 회원 검증, extractMethod로 메소드 분리

        memberRepository.save(member);
        return member.getId();
        */
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> { //member에 null이 아닌 어떤 값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
