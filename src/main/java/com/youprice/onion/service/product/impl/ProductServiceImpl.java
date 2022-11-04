package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.*;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final ProductRepository.Querydsl productRepositoryQuerydsl;
    private final ProductImageRepository productImageRepository;
    private final OrderRepository orderRepository;

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
        return productRepositoryQuerydsl.findAllBySearchRequirements(searchRequirements).map(ProductListDTO::new);
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
        List<Long> newImageIdList = updateDTO.getNewImageId();
        List<ProductImage> oldImageIdList = product.getProductImageList();
//        for(Long newImageId : newImageIdList) {
//
//            for(int i=0;i<oldImageIdList.size();i++)
//                boolean isExists = newImageIdList.contains(oldImageIdList.get(i).getId());
//
//                if(oldImageIdList.get(i).getId() == newImageId) {
//                    continue;
//                }
//
//
//        }

        List<MultipartFile> productNewImageLIst = updateDTO.getProductImageName();
        List<String> productImageList = ImageUtil.store(updateDTO.getProductImageName(),"product");

        //상품 DB업데이트
        updateDTO.setRepresentativeImage(productImageList.get(0));
        product.updateProduct(productId, town, category, updateDTO);

        Long updateProductId = productRepository.save(product).getId();

        //상품이미지 DB등록
        List<ProductImage> productImages = productImages(productId,productImageList);
        for(ProductImage productImage : productImages){
            productImageRepository.save(productImage);
        }

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

    // imageName 생성
    public String getImageName() {

        LocalDateTime now = LocalDateTime.now();
        String imageName;

        do {
            imageName = now.format(DateTimeFormatter.BASIC_ISO_DATE).substring(2)
                    + now.format(DateTimeFormatter.ISO_LOCAL_TIME).replaceAll(":","").substring(0,6)
//                    + //고유값
            ;
        } while (productImageRepository.findByProductImageName(imageName).isPresent());

        return imageName;
    }

    //이미지파일 경로,저장
    private String saveFile(MultipartFile multipartFile)throws  Exception{
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product";

        if(multipartFile.isEmpty()){
            return null;
        }

        String fileName = getImageName()+multipartFile.getOriginalFilename();

        multipartFile.transferTo(new File(filePath, fileName));

        return fileName;
    }

    //조회수 증가
    @Override
    @Transactional
    public int updateView(Long productId) {
        return productRepository.updateView(productId);
    }

    //경매기한이 끝나면 상품 조회X & 주문 목록으로
    @Override
    @Transactional
    public List<ProductListDTO> getProductAuctionList() {

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

        List<ProductListDTO> list = getAuctionList(false);

        return list;
    }

    //동네번호 조회
    @Override
    public TownFindDTO findTownId(String townName) {
        return townRepositoy.findByCoordinateTownName(townName).map(TownFindDTO::new).orElse(null);
    }

    //유저 판매 상품 목록
    @Override
    public Page<ProductSellListDTO> getProductSellListDTO(Long memberId, ProductProgress productProgress, Pageable pageable) {
        return productRepositoryQuerydsl.findByMemberIdAndProductProgress(memberId, productProgress, pageable).map(ProductSellListDTO::new);
    }
    //개인 유저 상품 리스트
    @Override
    public Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable) {
        ProductProgress[] productProgressList = ProductProgress.class.getEnumConstants();

        return productRepository.findAllByMemberIdAndProductProgressIn(memberId,productProgressList, pageable)
                .map(ProductListDTO::new);
    }

}
