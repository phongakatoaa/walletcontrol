package com.toaa.walletcontrol.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")

public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_id")
    private long id;

    @Column(name = "due_date")
    @NotNull(message = "*Please provide subscription due date")
    private LocalDate dueDate;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "active")
    private Boolean active;
}
