package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.order.WishRepository;
import com.youprice.onion.repository.product.*;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final MemberRepository memberRepository;
    private final TownRepositoy townRepositoy;
    private final CategoryRepositoy categoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final OrderRepository orderRepository;
    private final WishRepository wishRepository;

    @Override
	@Transactional
    public Page<ProductListDTO> getProductListDTO(Long memberId, SearchRequirements searchRequirements) {

		productRepository.actionBlind();

        return productRepository.findAllBySearchRequirements(searchRequirements).map(product -> {
            ProductListDTO productListDTO = new ProductListDTO(product);

            boolean check = false;
            if (memberId!=null) {
                check = wishRepository.existsByMemberIdAndProductId(memberId, productListDTO.getProductId());
            }
            productListDTO.setWishCheck(check);

            return productListDTO;
        });
    }

    //상품 등록
    @Override
    @Transactional
    public Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception {

        Member member = memberRepository.findById(productAddDTO.getMemberId()).orElse(null);
        Town town = townRepositoy.findById(productAddDTO.getTownId()).orElse(null);
        Category category = categoryRepository.findById(productAddDTO.getCategoryId()).orElse(null);

        //상품 경로에 파일 저장
        List<String> productImageList = ImageUtil.store(fileList,"product");

        //상품 DB등록
        productAddDTO.setRepresentativeImage(productImageList.get(0));
        Product product = new Product(member,town,category,productAddDTO);
        Long productId = productRepository.save(product).getId();

        //상품이미지 DB등록
        List<ProductImage> productImages = productImages(product, productImageList);
        for(ProductImage productImage : productImages){
            productImageRepository.save(productImage);
        }

        return productId;
    }

    //상품 수정
    @Override
    @Transactional
    public void updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception {

        //수정한 동네번호
        Town town = townRepositoy.findById(updateDTO.getTownId()).orElse(null);

        //수정한 카테고리번호
        Category category = categoryRepository.findById(updateDTO.getCategoryId()).orElse(null);

        Product product = productRepository.findById(productId).orElse(null);

        // 상품 경로에 파일이 없으면 저장 있으면 삭제
        //  저장된 파일
        List<ProductImage> oldImageList = product.getProductImageList();
		// 새로운 이미지
		List<MultipartFile> newImageList = updateDTO.getNewImageList();
		// 삭제안하는 이미지 id
		List<Long> productImageIdList = updateDTO.getProductImageIdList();

		int newSize = newImageList == null ? 0 : newImageList.size();
		int idSize = productImageIdList == null ? 0 : productImageIdList.size();

		// 이미지 개수 확인
		if (newSize + idSize == 0) {
			throw new ArrayIndexOutOfBoundsException("이미지를 하나 이상 올려주세요");
		}

		// 이미지 삭제
        for(ProductImage oldImage : oldImageList) {
            if(idSize == 0 || !productImageIdList.contains(oldImage.getId())) {
                ImageUtil.delete(oldImage.getProductImageName(),"product");
                productImageRepository.deleteById(oldImage.getId());
            }
        }

		// 이미지 저장
		if (newSize != 0) {
			List<ProductImage> addList = new ArrayList<>();
			
			// 이미지 파일 저장
			List<String> imageNameList = ImageUtil.store(newImageList, "product");

			// 이미지 DB 저장
			List<ProductImage> productImageList = productImages(product, imageNameList);
			productImageRepository.saveAll(productImageList);
		}

		// 대표 이미지 설정
		List<ProductImage> productImageList = productImageRepository.findByProductId(productId);
		updateDTO.setRepresentativeImage(productImageList.get(0).getProductImageName());

		// 업데이트
        product.updateProduct(town, category, productImageList, updateDTO);
		productRepository.save(product);
    }

    // 상품상태 수정
    @Override
    @Transactional
    public void progressUpdate(Long productId, String progress) {
        Product product = productRepository.findById(productId).orElse(null);
        Order order = orderRepository.findByProductIdAndOrderState(productId, OrderState.ORDER).orElse(null);
        ProductProgress productProgress = ProductProgress.valueOf(progress);

        // 상품상태 수정
        product.progressUpdate(productProgress);

        // 주문 상태 수정
        if (productProgress == ProductProgress.SOLDOUT && order != null) {
            order.setOrderState(OrderState.COMPLETE);
        }
    }

    //상품 삭제(DB삭제가 아닌 조회불가상태로 변경)
    @Override
    @Transactional
    public void deleteProduct(Long productId) throws Exception {
        Product product = productRepository.findById(productId).orElse(null);
        product.blindProduct(true);

        productRepository.save(product);
    }

    //카테고리 전체 상품 조회
    @Override
    public List<ProductListDTO> getProductCategoryList(Long start, Long end) {
        return productRepository.findByCategoryIdBetween(start,end).stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }
    //특정 하위 카테고리 상품 조회
    @Override
    public List<ProductFindDTO> getProductSubCategory(Long productId,Long categoryId) {

        List<ProductFindDTO> subCategoryProduct = productRepository.findByCategoryId(categoryId)
                .stream()
                .filter(spc -> spc.getId()!=productId)
                .map(product -> new ProductFindDTO(product))
                .collect(Collectors.toList());;

        Collections.shuffle(subCategoryProduct);

        return subCategoryProduct;
    }

    //검색에 따른 조회(제목,카테고리,내용)
    @Override
    public List<ProductListDTO> getSearchList(String subject,String content) {
        return productRepository.findBySubjectContainingOrContentContaining(subject,content).stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //상품 하나에 대한 데이터
    @Override
    public ProductDTO getProductDTO(Long productId) {
        return productRepository.findById(productId).map(ProductDTO::new).orElse(null);
    }

    //상품 하나에 대한 데이터
    @Override
    public ProductFindDTO getProductFindDTO(Long productId) {
        return productRepository.findById(productId).map(ProductFindDTO::new).orElse(null);
    }

    //이미지리스트 저장
    private List<ProductImage> productImages(Product product, List<String> productImageList) {
        List<ProductImage> productImages = new ArrayList<>();

        for (String imageName: productImageList) {

            ProductImage image = new ProductImage(product, imageName);

            productImages.add(image);

        }
        return productImages;
    }

    //조회수 증가
    @Override
    @Transactional
    public int updateView(Long productId) {
        return productRepository.updateView(productId);
    }

    //동네번호 조회
    @Override
    public TownFindDTO findTownId(String townName) {
        return townRepositoy.findByCoordinateTownName(townName).map(TownFindDTO::new).orElse(null);
    }

    //유저 판매 상품 목록
    @Override
    public Page<ProductSellListDTO> getProductSellListDTO(SearchRequirements searchRequirements) {
        return productRepository.findAllBySearchRequirements(searchRequirements).map(ProductSellListDTO::new);
    }
    //개인 유저 상품 리스트
    @Override
    public Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable) {
        ProductProgress[] productProgressList = ProductProgress.class.getEnumConstants();

        return productRepository.findAllByMemberIdAndProductProgressIn(memberId,productProgressList, pageable)
                .map(ProductListDTO::new);
    }

}
