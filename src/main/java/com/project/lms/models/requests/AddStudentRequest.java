package com.project.lms.models.requests;

import com.project.lms.models.AccountStatus;
import com.project.lms.models.entities.Student;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequest {

    @NotNull
    private String name;

    @NotNull
    private String contact;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    private String address;

    @NotNull
    private AccountStatus accountStatus;

    public Student toStudent(){
        return Student.builder()
                .name(name)
                .contact(contact)
                .email(email)
                .address(address)
                .accountStatus(accountStatus)
                .build();
    }

}
