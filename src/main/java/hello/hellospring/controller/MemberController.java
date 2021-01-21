package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //여러개 객체가 필요하지 않음. 하나만 만들고 공용으로 쓰면 된다.
    //이렇게 쓰는 것보다는 스프링 컨테이너에 등록을 하고 쓰는게 좋다.
    //private final MemberService memberService = new MemberService();

    //스프링 컨테이너에 등록을 하면 하나만 등록이 된다. 추가로 부가적 효과를 볼 수 있다.
    private final MemberService memberService;

    @Autowired  //연결시켜줄 때 쓰인다. 생성자에 @Autowired를 쓰면 멤버 컨트롤러가 생성이 될 때, 스프링빈에 등록되어 있는 멤버 서비스 객체를 넣어준다. -> 이것이 Depedency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //@PostMapping은 보통 데이터를 폼같은 것에 넣어서 보낼 때, @GetMapping은 조회할 때 주로 쓰임
    //URL은 같지만 GET이냐 POST에 따라서 다르게 Mapping할 수 있다
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> member = memberService.findMembers();
        model.addAttribute("members", member);
        return "members/memberList";
    }

}
