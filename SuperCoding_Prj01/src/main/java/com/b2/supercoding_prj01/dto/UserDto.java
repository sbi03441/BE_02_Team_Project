package com.b2.supercoding_prj01.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class UserDto {

    private String email;
    private String password;

}
