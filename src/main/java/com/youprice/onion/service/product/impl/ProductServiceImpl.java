package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.repository.product.*;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
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
    private final OrderService orderService;

    private final ProhibitionKeywordRepositoy prohibitionKeywordRepositoy;

    private final static String COOKIE = "alreadyViewCookie";

	@Override
	public Page<ProductListDTO> getProductListDTO(SearchRequirements searchRequirements) {
		return productRepositoryQuerydsl.findAllBySearchRequirements(searchRequirements).map(ProductListDTO::new);
	}

    //상품 등록
    @Override
    @Transactional
    public Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception {

        Member member = memberRepository.findById(productAddDTO.getMemberId()).orElse(null);
        Town town = townRepositoy.findById(productAddDTO.getTownId()).orElse(null);
        Category category = categoryRepository.findById(productAddDTO.getCategoryId()).orElse(null);

        //대표이미지 설정
        productAddDTO.setRepresentativeImage(getImageName()+fileList.get(0).getOriginalFilename());

        // 상품 등록
        Product product = new Product(member,town,category,productAddDTO);

        Long productId = productRepository.save(product).getId();

        // 상품 이미지 등록
        //반복으로 꺼내면서 하나씩 저장
        List<ProductImage> productImageList = productImages(productId,fileList);
        for(ProductImage productImage : productImageList){
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
        //리턴처리해줘야함

        //대표이미지 설정
        updateDTO.setRepresentativeImage(getImageName()+updateDTO.getProductImageName().get(0).getOriginalFilename());

        Product product = productRepository.findById(productId).orElse(null);
        product.updateProduct(productId, town, category, updateDTO);

        productRepository.save(product);
        //상품 이미지 수정
        //반복으로 지우고 저장
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product";

        //조회한 값
        List<ProductImage> imageList = updateImage(productId, updateDTO.getProductImageName());
        for(ProductImage image : imageList){
            
            List<ProductImage> productImageList = productImageRepository.findByProductId(productId);
            for (ProductImage productImage : productImageList){

                productImageRepository.delete(productImage);

                File file = new File(path+"\\"+productImage.getProductImageName());
                if (file.exists()) {
                    file.delete();
                }
            }

            image.updateImage(image.getId(), product, image.getProductImageName());

            productImageRepository.save(image);

        }

        return productRepository.save(product).getId();
    }

	// 상품상태 수정
	@Override
	@Transactional
	public void progressUpdate(Long productId, String productProgress) {
		productRepository.findById(productId).map(product -> product.progressUpdate(productProgress)).orElse(null);
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

    //상품 전체 조회
    @Override
    public List<ProductListDTO> getProductList(Boolean blindStatus) {
        return productRepository.findByBlindStatus(false)
                .stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //동네 상품 전체 조회
    @Override
    public List<ProductListDTO> getProductList(Long coordinateId,Boolean blindStatus) {

        List<ProductListDTO> list = productRepository.findByBlindStatus(false)
                .stream()
                .filter(gpl -> gpl.getTown().getCoordinate().getId()==coordinateId)
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());

        return list;
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

    //이미지리스트
    private List<ProductImage> productImages(Long productId, List<MultipartFile> fileList) throws Exception {
        List<ProductImage> productImageList = new ArrayList<>();
        Product product = productRepository.findById(productId).orElse(null);

        for (MultipartFile file : fileList) {

            if (!file.isEmpty()) {
                String productImageName = saveFile(file);
                ProductImage image = new ProductImage(product, productImageName);

                productImageList.add(image);
            }
        }
        return productImageList;
    }
    //이미지 수정
    private List<ProductImage> updateImage(Long productId, List<MultipartFile> fileList) throws Exception {
        List<ProductImage> productImageList = new ArrayList<>();
        Product product = productRepository.findById(productId).orElse(null);

        for (MultipartFile file : fileList) {

            if (!file.isEmpty()) {
                String productImageName = saveFile(file);
                ProductImage image = new ProductImage(product, productImageName);

                productImageList.add(image);
            }
        }
        return productImageList;
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

    //카테고리번호 조회
    @Override
    public CategoryFindDTO findCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).map(CategoryFindDTO::new).orElse(null);
    }

    @Override
    public Page<ProductSellListDTO> getProductSellListDTO(Long memberId, Pageable pageable) {
      return productRepository.findByMemberId(memberId, pageable).map(ProductSellListDTO::new);
    }

}
