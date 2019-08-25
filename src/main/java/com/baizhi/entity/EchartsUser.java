package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author miion
 * @create 2019-08-21 19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsUser implements Serializable {

    private Integer counts;
    private String sex;


}

