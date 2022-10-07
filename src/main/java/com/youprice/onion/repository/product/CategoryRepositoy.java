package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface CategoryRepositoy extends JpaRepository<Category, Long> {

    Category findByCategory(Category category);

   // Category findByCategoryName(String categoryName);

    List<Category> findAll();

    Category deleteById(CategoryUpdateDTO id);


    @Repository
    @RequiredArgsConstructor


    public class Categoryrepositoy {



        private final EntityManager em;




        public List<Category> finduniform() {

            //  String jpql = "select o from Category o  where o.categoryName='유니폼'";

            //select m from Member m join fetch m.team
            return em.createQuery("select o from Category o  where o.category='540'", Category.class)
                    .getResultList();

        }

        public List<Category> findfootballboot() {

            //  String jpql = "select o from Category o  where o.categoryName='유니폼'";

            //select m from Member m join fetch m.team
            return em.createQuery("select o from Category o  where o.category='544' ", Category.class)
                    .getResultList();

        }

        public List<Category> uniformPARENT_ID() {


            return em.createQuery("select o from Category o where o.category = '540' ", Category.class)
                    .getResultList();

        }

        public List<Category> footballbootPARENT_ID() {


            return em.createQuery("select o from Category o  where o.category='544' ", Category.class)
                    .getResultList();

        }

        public List<Category> update() {


            return em.createQuery(" update Category c set c.categoryName = c.categoryName where c.id = :id", Category.class)
                    .getResultList();

        }




    }
}





