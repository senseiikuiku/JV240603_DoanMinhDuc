package com.ra.jv240603_doanminhduc.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataError <T>{
    private T messages;
    private int statusCode;
}