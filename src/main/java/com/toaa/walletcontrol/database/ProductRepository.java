package com.toaa.walletcontrol.database;

import com.toaa.walletcontrol.model.wallet.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
}
