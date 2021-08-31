package com.example.urlshortener.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ulphat
 */
@Getter
@Setter
@Entity
@Table(name = "URLS")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq_gen")
    @GenericGenerator(name = "url_seq_gen",
                      strategy = "com.example.urlshorter.model.entity.IdGenerator")
    private String id;

    @Column(nullable = false, unique = true, length = 6000)
    private String path;
}
