package com.alten.ecomapp.back.repository;

import com.alten.ecomapp.back.domain.FavoryProduct;
import com.alten.ecomapp.back.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the FavoryProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavoryProductRepository extends JpaRepository<FavoryProduct, Long> {
    @Query("select favoryProduct from FavoryProduct favoryProduct where favoryProduct.user.username = ?#{authentication.name}")
    List<FavoryProduct> findByUserIsCurrentUser();

    List<FavoryProduct> findAllByUser(User user);
}
