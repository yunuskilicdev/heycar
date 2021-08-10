package com.kilic.yunus.heycar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Yunus Kilic
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Listing implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Dealer dealer;

    @Column
    private String code;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private long kW;

    @Column
    private long year;

    @Column
    private String color;

    @Column
    private long price;
}
