package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//ORM(O: Object(객체)와 Relational DB Table(관계형 DB Table)을 매핑한다는 뜻
//매핑은 @Entity 어노테이션을 이용하게 된다. 이러면 Member는 JPA가 관리하는 Entitiy가 되는 것이다.
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 ID값을 주는 것을 IDENTITY전략이라고 한다.
    private Long id;    // 시스템이 저장하는 id(임의의 값), 고객이 정하는 id아님
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
