package com.example.democrud.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    private  int page;
    private   int per_page;
    private  int total;
    private  int  total_pages;
    List<EmployeeVO> data;
}
