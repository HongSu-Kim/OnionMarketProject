package com.youprice.onion.repository.product;

import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQueryDsl {

	Page<Product> findAllBySearchRequirements(SearchRequirements searchRequirements);
	Page<Product> findByMemberIdAndProductProgress(Long memberId, ProductProgress productProgress, Pageable pageable);

}
