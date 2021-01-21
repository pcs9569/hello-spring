package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP 사용시 쓰이는 어노테이션 @Aspect
@Aspect
//만든 AOP함수를 스프링빈에 등록시켜야 되는데 @Component로 가능하지만 지금은 SpringConfig에 등록 시켜 사용(이게 더 주로 쓰임)
@Component
public class TimeTraceAop {

    //원하는 곳에 적용하기 위한 @Around
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() +" " + timeMs + "ms");
        }
    }
}
