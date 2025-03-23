package com.project.javacrm.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Pagination<T> {

    private List<T> data;
    private int total_pages;
    private int current_page;

}
