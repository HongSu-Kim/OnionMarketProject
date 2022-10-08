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
        //전체조회

        //가격으로 조회(변경예정)
        public Product findByPrice(int price) {

            return em.createQuery("select p from Product p where p.price=:price", Product.class)
                    .setParameter("price",price).getSingleResult();
        }

    }

}
