package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface ProductCategoryRepositoy extends JpaRepository<ProductCategory,Long> {

   @Repository
   @RequiredArgsConstructor

    class ProductCategoryrepositoy{

        private final EntityManager em;

       public List<Category> findCategory() {


           return em.createQuery("select o from Category o where o.category is null ", Category.class)
                   .getResultList();

       }

       public List<ProductCategory> findProductCategory(){
           return em.createQuery("select o from ProductCategory o join fetch o.category ", ProductCategory.class)
                   .getResultList();

       }


       public Category findtopcategoryName(String topcategoryName){
           return em.createQuery("select o from Category o where o.categoryName= : topcategoryName", Category.class)
                   .setParameter("topcategoryName", topcategoryName)
                   .getSingleResult();
       }


       public List<Category> findSubcategory() { //하위카테고리조회


           return em.createQuery("select o from Category o  where o.category is not null ", Category.class)
                   .getResultList();

       }



    }



}
