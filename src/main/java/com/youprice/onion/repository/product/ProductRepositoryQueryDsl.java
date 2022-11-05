package com.youprice.onion.repository.product;

import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.Product;
import org.springframework.data.domain.Page;

public interface ProductRepositoryQueryDsl {

	Page<Product> findAllBySearchRequirements(SearchRequirements searchRequirements);
}
