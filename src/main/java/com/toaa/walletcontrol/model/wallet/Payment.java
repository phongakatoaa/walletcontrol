package com.toaa.walletcontrol.model.wallet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toaa.walletcontrol.model.login.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "product")
    @NotEmpty(message = "*Please provide a product name")
    private String product;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "payment_detail")
    private String detail;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "cost")
    private Long cost;
}
