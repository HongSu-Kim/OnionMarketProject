package com.youprice.onion.repository.product;

import static com.youprice.onion.entity.product.QCategory.*;
import static com.youprice.onion.entity.product.QProduct.*;
import static com.youprice.onion.entity.product.QTown.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //상품번호로 상품 하나 조회
    @Override
    @EntityGraph(attributePaths = {"productImageList"})
    Optional<Product> findById(Long productId);

    //상품번호로 조회 후 상품의 조회수 증가
    @Modifying
    @Query("update Product p set p.viewCount = p.viewCount + 1 where p.id = ?1")
    int updateView(@RequestParam("productId") Long productId);

    //블라인드 처리가 안된 상품만 조회
    List<Product> findByBlindStatus(Boolean blindStatus);

	// 판매 상품 리스트
	Page<Product> findByMemberId(Long memberId, Pageable pageable);

    //
    List<Product> findByCategoryIdBetween(Long start, Long end);
    //제목과 내용으로 검색한 리스트 조회
    List<Product> findBySubjectContainingOrContentContaining(String subject,String content);

	@Repository
	@RequiredArgsConstructor
	class Querydsl {

		private final JPAQueryFactory queryFactory;

		public Page<Product> findAllBySearchRequirements(SearchRequirements searchRequirements) {

			List<Product> content = queryFactory
					.select(product)
					.from(product)
					.join(product.town, town).fetchJoin()
					.join(product.category, category).fetchJoin()
					.where(
							memberIdEq(searchRequirements.getMemberId()),
							coordinateIdEq(searchRequirements.getCoordinateId()),
							categoryIdEq(searchRequirements.getCategoryId()),
							productProgressEq(searchRequirements.getProductProgress()),
							blindStatusEq(searchRequirements.getBlindStatus()),
							searchValueContains(searchRequirements.getSearchValue())
					)
					.orderBy((product.updateDate != null ? product.updateDate : product.uploadDate).desc())
					.offset(searchRequirements.getPageable().getOffset())
					.limit(searchRequirements.getPageable().getPageSize())
					.fetch();

			Long count = queryFactory
					.select(product.count())
					.from(product)
					.where(
							memberIdEq(searchRequirements.getMemberId()),
							coordinateIdEq(searchRequirements.getCoordinateId()),
							categoryIdEq(searchRequirements.getCategoryId()),
							productProgressEq(searchRequirements.getProductProgress()),
							blindStatusEq(searchRequirements.getBlindStatus()),
							searchValueContains(searchRequirements.getSearchValue())
					)
					.fetchOne();

			return new PageImpl<>(content, searchRequirements.getPageable(), count);
		}

		private BooleanExpression memberIdEq(Long memberId){
			return memberId == null ? null : product.member.id.eq(memberId);
		}
		private BooleanExpression coordinateIdEq(Long coordinateId){
			return coordinateId == null ? null : product.town.coordinate.id.eq(coordinateId);
		}
		private BooleanExpression categoryIdEq(Long categoryId){
			return categoryId == null ? null : product.category.id.eq(categoryId);
		}
		private BooleanExpression productProgressEq(ProductProgress productProgress){
			return productProgress == null ? null : product.productProgress.eq(productProgress);
		}
		private BooleanExpression blindStatusEq(Boolean blindStatus){
			return blindStatus == null ? null : product.blindStatus.eq(blindStatus);
		}
		private BooleanExpression searchValueContains(String searchValue){
			return searchValue == null ? null : product.subject.contains(searchValue).or(product.content.contains(searchValue));
		}
	}
}
