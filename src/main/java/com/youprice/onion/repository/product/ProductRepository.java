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
        public List<Product> findAll() {
            return em.createQuery("select p from Product p", Product.class).getResultList();
        }
        //가격으로 조회(변경예정)
        public List<Product> findByPrice(Long id) {
            return em.createQuery("select p from Product p where p.id=:id", Product.class)
                    .setParameter("id",id)
                    .getResultList();
        }
        //이미지로 조회(변경예정)
        public List<ProductImage> findAllImg(Long id) {

            return em.createQuery("select i from ProductImage i join i.product p where p.id=:id", ProductImage.class)
                    .setParameter("id", id).getResultList();
        }
    }

}
