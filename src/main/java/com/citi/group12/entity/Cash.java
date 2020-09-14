package com.citi.group12.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * created by Alex in 09/12/2020
 *
 * cash account entity
 */
@Data
@Document(collection="Cash")
public class Cash implements Serializable {
    @Id
    private String id;

    private String name;
    private String type;
    private Date date;
    private double balance;
}
