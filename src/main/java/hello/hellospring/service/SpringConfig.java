package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

// 스프링이 뜰 때 @Configuration을 읽고, @Bean memberService() 메소드 아래 로직을 호출하여 스프링빈에 등록을 해준다.
// memberRepository도 마찬가지로 스프링이 올라올 때 스프링 컨테이너에 올려준다.
@Configuration
public class SpringConfig {

    // @PersistenceContext 원래는 이렇게 해야되지만 아래처럼만 해도 스프링에서 DI해준다.

    private final MemberRepository memberRepository;

    // 생성자 단축키 : Alt + Insert
    @Autowired // 생성자 하나인 경우 @Autowired 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
//  SpringDataJpaMemberRepository 설정 때문에 주석 처리함.
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em=em;
    }
*/

    // @Autowired DataSource dataSource; 이렇게 해도 된다.
/*
    // JPA할 때 주석 처리
    private DataSource dataSource; // application.properties를 보고 스프링이 자체적으로 빈도 생성해준다. datasource(db를 연결할 수 있는 정보가 있는 설정)를 만들어 준다.

    @Autowired
    //아래 함수로 주입(DI) 해준다.
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
*/

/*
//  SpringDataJpaMemberRepository 설정 때문에 주석 처리함.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // MemberService()인자가 memberRepository()이기 때문에 넣어줌.
    }
*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository); // MemberService()인자가 memberRepository()이기 때문에 넣어줌.
    }
/*
    //@Component하고 주석처리함
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
*/
/*
//  SpringDataJpaMemberRepository 설정 때문에 주석 처리함.
    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // MemoryMemberRepository()를 JdbcMemberRepository()로 바꾼다.
//        return new JdbcTemplateMemberRepository(dataSource); //JdbcMemberRepository()를 JdbcTemplateMemberRepository(dataSource)로 바꾼다.
        return new JpaMemberRepository(em);
    }
*/


}
