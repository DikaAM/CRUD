package com.example.demo.helper;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseStatus<T>{
    public Boolean status;
    public List<String> messages = new ArrayList<>();
    public T data;


}
