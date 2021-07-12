package com.example.democrud.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeVO {
    private   int id;
    private String email;
    private  String first_name;
    private  String last_name;
    private  String avatar;

    public EmployeeVO(int id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }


}
