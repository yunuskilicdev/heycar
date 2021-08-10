package com.kilic.yunus.heycar.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Yunus Kilic
 */
@Entity
@Data
public class Dealer implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Listing> listings;
}
