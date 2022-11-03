package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CategoryRepositoy extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String topcategoryName);
    Optional<Category> findByCategoryNameAndParent(String topcategoryName,Category category);

     Category findAllByCategoryName(String categoryName);

   List<Category> findAllByParent(Long id);

   List<Category> findAllByParentId(Long id);



    Category deleteById(CategoryUpdateDTO id);

    List<Category> findByParentId(Long categoryId);

    @Query("select o from Category o where o.parent is null")
    List<Category> findTopCategory();

	@Query("select o from Category o  where o.parent is not null")
	List<Category> findAllSubcategory();


}





