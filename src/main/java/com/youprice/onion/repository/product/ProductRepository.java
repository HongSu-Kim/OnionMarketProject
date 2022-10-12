package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Repository
    @RequiredArgsConstructor
    public class ProductManager {

    }

}
