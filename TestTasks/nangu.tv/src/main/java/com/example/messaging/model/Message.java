package com.example.messaging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @JsonIgnore
    @ManyToOne
    private User author;

}