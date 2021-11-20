package com.todayworker.springboot.domain.user;


import com.todayworker.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙. 스트링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야지만 auto_increment가 된다
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) //JPA로 DB에 저장할때 Enum값을 어떤 형태로 저장할지 결정. 기본 int 숫자가 저장. 숫자로 저장되면 그 값이 무슨 코드를 의미하는지 파악x. 그래서 문자열로 EnumType.STRING로 선언
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        if (name != null) {
            this.name = name;
        }
        else if (picture != null) {
            this.picture = picture;
        }

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
