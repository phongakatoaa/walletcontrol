package com.toaa.walletcontrol.database;

import com.toaa.walletcontrol.model.wallet.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);
}
