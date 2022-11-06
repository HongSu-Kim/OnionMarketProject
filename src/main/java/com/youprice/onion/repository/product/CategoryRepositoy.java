package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Product;
import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CategoryRepositoy extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
    Optional<Category> findByCategoryNameAndParent(String topcategoryName,Category category);

   List<Category> findAllByCategoryName(String categoryName);

   //  Optional<Category> findByCategoryName(String categoryName);

   List<Category> findAllByParent(Long id);

   List<Category> findAllByParentId(Long id);

   //List<CategoryFindDTO> findByCategoryName(String categoryName);





    Category deleteById(CategoryUpdateDTO id);

    Category deleteByCategoryName(String categoryName);

    List<Category> findByParentId(Long categoryId);

    @Query("select o from Category o where o.parent is null")
    List<Category> findTopCategory();

	@Query("select o from Category o  where o.parent is not null")
	List<Category> findAllSubcategory();


    List<Category> findByIdBetween(Long start, Long end);

	@Query("select c " +
			"from Category c " +
			"where :subject like concat('%', c.categoryName, '%') " +
			"and c.parent is not null")
	List<Category> findBySubjectContains(@Param("subject") String subject);
}





