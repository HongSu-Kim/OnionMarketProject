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
    public Page<ProductListDTO> getProductListDTO(SearchRequirements searchRequirements) {
        List<ProductListDTO> blindList = getAuctionList(false);

        LocalDateTime now = LocalDateTime.now();

        for(ProductListDTO blindDTO : blindList) {

            Product product = productRepository.findById(blindDTO.getProductId()).orElse(null);

            if(blindDTO.getAuctionDeadline().isBefore(now)){

                blindDTO.setBlindStatus(true);

                product.updateAuctionProduct(blindDTO);

                productRepository.save(product);
            }
        }
/*
        return productRepositoryQuerydsl.findAllBySearchRequirements(searchRequirements).map(product -> {
            ProductListDTO productListDTO = new ProductListDTO(product);

            boolean check = false;

            if(searchRequirements.getMemberId()!=null){

                check = wishRepository.existsByMemberIdAndProductId(
                    searchRequirements.getMemberId(), productListDTO.getProductId());

            }
            productListDTO.setWishCheck(check);

            return productListDTO;
        });
*/
        return productRepository.findAllBySearchRequirements(searchRequirements).map(ProductListDTO::new);
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
        List<ProductImage> productImages = productImages(productId,productImageList);
        for(ProductImage productImage : productImages){
            productImageRepository.save(productImage);
        }

        return productId;
    }

    //상품 수정
    @Override
    @Transactional
    public Long updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception {

        //수정한 동네번호
        Town town = townRepositoy.findById(updateDTO.getTownId()).orElse(null);

        //수정한 카테고리번호
        Category category = categoryRepository.findById(updateDTO.getCategoryId()).orElse(null);

        Product product = productRepository.findById(productId).orElse(null);

        //상품 경로에 파일이 없으면 저장 있으면 삭제
        //DB에 저장된 파일
        List<ProductImage> oldImageList = product.getProductImageList();
        //업데이트할 이미지
        List<MultipartFile> newImageList = updateDTO.getProductImageName();
        //새로 저장해야할 리스트
        List<MultipartFile> addImageList = new ArrayList<>();

        if(CollectionUtils.isEmpty(oldImageList)) {//DB에 없을 때
            if(!CollectionUtils.isEmpty(newImageList)) {//전달해야할 파일이 하나라도 있을 시
                for(MultipartFile multipartFile : newImageList)
                    addImageList.add(multipartFile); //저장할 파일 목록에 추가
            }
        }//DB에 한 장 이상 존재할 시
        else {
            if (CollectionUtils.isEmpty(newImageList)) { //전달 될 파일이 없을 경우
                //파일 삭제
                for (ProductImage oldImage : oldImageList)
                    ImageUtil.delete(oldImage.getProductImageName(), "product");
            } else {//전달 된 파일이 하나 이상일 경우

                //DB에 저장되어 있는 파일 원본 목록
                List<String> originalNameList = new ArrayList<>();

                for (ProductImage oldImage : oldImageList) {
                    ProductImage productImage = productImageRepository.findById(oldImage.getId()).orElse(null);
                    //저장된 파일 중 전달 된게 없으면 삭제
                    if (!newImageList.contains(productImage.getProductImageName())) {
                        ImageUtil.delete(oldImage.getProductImageName(), "product");
                    } else {//아니면 DB에 추가
                        originalNameList.add(productImage.getProductImageName());
                    }
                }

                for (MultipartFile multipartFile : newImageList) {

                    if (!originalNameList.contains(multipartFile.getOriginalFilename())) {
                        addImageList.add(multipartFile);
                    }
                }
            }
        }

//        List<MultipartFile> productNewImageLIst = updateDTO.getProductImageName();
        List<String> productImageList = ImageUtil.store(updateDTO.getProductImageName(),"product");

        //상품 DB업데이트
        updateDTO.setRepresentativeImage(productImageList.get(0));
        product.updateProduct(productId, town, category, updateDTO);

        Long updateProductId = productRepository.save(product).getId();

//        //상품이미지 DB등록
//        List<ProductImage> productImages = productImages(productId,productImageList);
//        for(ProductImage productImage : productImages){
//            productImageRepository.save(productImage);
//        }

        return updateProductId;
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
        product.blindProduct(product.getBlindStatus());

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

    //전체 경매 상품 조회
    @Override
    public List<ProductListDTO> getAuctionList(Boolean blindStatus) {
        return productRepository.findByAuctionDeadlineNotNullAndBlindStatus(false).stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
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
    private List<ProductImage> productImages(Long productId, List<String> productImageList) throws Exception {
        List<ProductImage> productImages = new ArrayList<>();
        Product product = productRepository.findById(productId).orElse(null);

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
    public Page<ProductSellListDTO> getProductSellListDTO(Long memberId, ProductProgress productProgress, Pageable pageable) {
        return productRepository.findByMemberIdAndProductProgress(memberId, productProgress, pageable).map(ProductSellListDTO::new);
    }
    //개인 유저 상품 리스트
    @Override
    public Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable) {
        ProductProgress[] productProgressList = ProductProgress.class.getEnumConstants();

        return productRepository.findAllByMemberIdAndProductProgressIn(memberId,productProgressList, pageable)
                .map(ProductListDTO::new);
    }

}
