package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Repository
    @RequiredArgsConstructor
    public class ProductManager {

        private final EntityManager em;
        //상품 하나 조회
        public Product findOne(Long id) {

            return em.createQuery("select p from Product p where p.id=:id", Product.class)
                    .setParameter("id",id).getSingleResult();
        }

    }

}
