package com.alten.ecomapp.back.repository;

import com.alten.ecomapp.back.domain.CartProduct;
import com.alten.ecomapp.back.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the CartProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Query("select cartProduct from CartProduct cartProduct where cartProduct.user.username = ?#{authentication.name}")
    List<CartProduct> findByUserIsCurrentUser();

    List<CartProduct> findAllByUser(User user);
}
