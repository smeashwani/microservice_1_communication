package com.training.userms.model;

import lombok.Data;

@Data
public class ResponseDto {
    private DepartmentDto department;
    private UserDto user;
}