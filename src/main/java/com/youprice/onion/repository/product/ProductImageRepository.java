package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Repository
    @RequiredArgsConstructor
    public class ProductImageManager{

        private final EntityManager em;

        public List<ProductImage> findByProduct_ProductId(Long id) {
            return em.createQuery("select i from ProductImage i join i.product p where p.id=:id", ProductImage.class)
                    .setParameter("id",id).getResultList();
        }

//        public List<ProductImage> findAllImg(Long id) {
//
//            return em.createQuery("select i from ProductImage i join i.product p where p.id=:id", ProductImage.class)
//                    .setParameter("id", id).getResultList();
//        }
    }


}
