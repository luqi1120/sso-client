package com.ohaotian.ssoclientrest.dao;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created: luQi
 * Date:2018-4-27 10:14
 */
@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String email, String phone) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
