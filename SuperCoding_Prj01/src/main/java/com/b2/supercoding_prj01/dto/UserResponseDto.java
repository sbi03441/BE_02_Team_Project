package com.b2.supercoding_prj01.dto;

import com.b2.supercoding_prj01.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String password;


}
