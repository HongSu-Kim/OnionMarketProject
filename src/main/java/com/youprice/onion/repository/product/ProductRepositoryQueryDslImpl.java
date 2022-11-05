package com.youprice.onion.repository.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.youprice.onion.entity.order.QDelivery.delivery;
import static com.youprice.onion.entity.order.QOrder.order;
import static com.youprice.onion.entity.product.QCategory.category;
import static com.youprice.onion.entity.product.QProduct.product;
import static com.youprice.onion.entity.product.QTown.town;

@RequiredArgsConstructor
public class ProductRepositoryQueryDslImpl implements ProductRepositoryQueryDsl{

	private final JPAQueryFactory queryFactory;

	@Override
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
						searchValueContains(searchRequirements.getSearchValue()),
						auctionStatus(searchRequirements.getAuctionStatus()),
						coordinateIdListIn(searchRequirements.getCoordinateIdList()),
						categoryIdListIn(searchRequirements.getCategoryIdList())
				)
				.orderBy(orderBy(searchRequirements.getPageable()))
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
						searchValueContains(searchRequirements.getSearchValue()),
						auctionStatus(searchRequirements.getAuctionStatus()),
						coordinateIdListIn(searchRequirements.getCoordinateIdList()),
						categoryIdListIn(searchRequirements.getCategoryIdList())
				)
				.fetchOne();

		return new PageImpl<>(content, searchRequirements.getPageable(), count);
	}

	private OrderSpecifier<?> orderBy(Pageable pageable) {

		for (Sort.Order o : pageable.getSort()) {
			PathBuilder<Product> orderByExpression = new PathBuilder<>(Product.class, "product");
			return new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, orderByExpression.get(o.getProperty()));
		}

		return null;
	}

	private BooleanExpression memberIdEq(Long memberId) {
		return memberId == null ? null : product.member.id.eq(memberId);
	}

	private BooleanExpression coordinateIdEq(Long coordinateId) {
		return coordinateId == null ? null : product.town.coordinate.id.eq(coordinateId);
	}

	private BooleanExpression categoryIdEq(Long categoryId) {
		return categoryId == null ? null : product.category.id.eq(categoryId);
	}

	private BooleanExpression productProgressEq(ProductProgress productProgress) {
		return productProgress == null ? null : product.productProgress.eq(productProgress);
	}

	private BooleanExpression blindStatusEq(Boolean blindStatus) {
		return blindStatus == null ? null : product.blindStatus.eq(blindStatus);
	}

	private BooleanExpression searchValueContains(String searchValue) {
		return searchValue == null ? null : product.subject.contains(searchValue).or(product.content.contains(searchValue));
	}

	private BooleanExpression auctionStatus(Boolean auctionStatus) {
		return auctionStatus == null ? null :
				(auctionStatus ? product.auctionDeadline.isNotNull() : product.auctionDeadline.isNull());
	}

	private BooleanExpression coordinateIdListIn(List<Long> coordinateIdList) {
		return coordinateIdList == null || coordinateIdList.size() == 0 ? null : product.town.coordinate.id.in(coordinateIdList);
	}

	private BooleanExpression categoryIdListIn(List<Long> categoryIdList) {
		return categoryIdList == null || categoryIdList.size() == 0 ? null : product.category.id.in(categoryIdList);
	}
}
