package br.com.systemsgs.vo;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class UserVO implements Serializable {

    private String userName;
    private String password;

}
