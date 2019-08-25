package com.baizhi.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "province")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String code;
    private String name;
    private String provincecode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }
}