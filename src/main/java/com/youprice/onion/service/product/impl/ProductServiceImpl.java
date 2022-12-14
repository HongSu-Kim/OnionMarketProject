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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final MemberRepository memberRepository;
    private final TownRepository townRepositoy;
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

    //?????? ??????
    @Override
    @Transactional
    public Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception {

        Member member = memberRepository.findById(productAddDTO.getMemberId()).orElse(null);
        Town town = townRepositoy.findById(productAddDTO.getTownId()).orElse(null);
        Category category = categoryRepository.findById(productAddDTO.getCategoryId()).orElse(null);

        //?????? ????????? ?????? ??????
        List<String> productImageList = ImageUtil.store(fileList,"product");

        //?????? DB??????
        productAddDTO.setRepresentativeImage(productImageList.get(0));
        Product product = new Product(member,town,category,productAddDTO);
        Long productId = productRepository.save(product).getId();

        //??????????????? DB??????
        List<ProductImage> productImages = productImages(product, productImageList);
        for(ProductImage productImage : productImages){
            productImageRepository.save(productImage);
        }

        return productId;
    }

    //?????? ??????
    @Override
    @Transactional
    public void updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception {

        //????????? ????????????
        Town town = townRepositoy.findById(updateDTO.getTownId()).orElse(null);

        //????????? ??????????????????
        Category category = categoryRepository.findById(updateDTO.getCategoryId()).orElse(null);

        Product product = productRepository.findById(productId).orElse(null);

        // ?????? ????????? ????????? ????????? ?????? ????????? ??????
        //  ????????? ??????
        List<ProductImage> oldImageList = product.getProductImageList();
		// ????????? ?????????
		List<MultipartFile> newImageList = updateDTO.getNewImageList();
		// ??????????????? ????????? id
		List<Long> productImageIdList = updateDTO.getProductImageIdList();

		// ????????? ?????? ??????
		int newImageSize = newImageList == null ? 0 : newImageList.size();
		int imageIdSize = productImageIdList == null ? 0 : productImageIdList.size();
		if (newImageSize + imageIdSize == 0) {
			throw new ArrayIndexOutOfBoundsException("???????????? ?????? ?????? ???????????????");
		}

		// ????????? ??????
        for(ProductImage oldImage : oldImageList) {
            if(imageIdSize == 0 || !productImageIdList.contains(oldImage.getId())) {
                ImageUtil.delete(oldImage.getProductImageName(),"product");
                productImageRepository.deleteById(oldImage.getId());
            }
        }

		// ????????? ??????
		if (newImageSize != 0) {
			List<ProductImage> addList = new ArrayList<>();
			
			// ????????? ?????? ??????
			List<String> imageNameList = ImageUtil.store(newImageList, "product");

			// ????????? DB ??????
			List<ProductImage> productImageList = productImages(product, imageNameList);
			productImageRepository.saveAll(productImageList);
		}

		// ?????? ????????? ??????
		List<ProductImage> productImageList = productImageRepository.findByProductId(productId);
		updateDTO.setRepresentativeImage(productImageList.get(0).getProductImageName());

		// ????????????
        product.updateProduct(town, category, productImageList, updateDTO);
		productRepository.save(product);
    }

    // ???????????? ??????
    @Override
    @Transactional
    public void progressUpdate(Long productId, String progress) {
        Product product = productRepository.findById(productId).orElse(null);
        Order order = orderRepository.findByProductIdAndOrderState(productId, OrderState.ORDER).orElse(null);
        ProductProgress productProgress = ProductProgress.valueOf(progress);

        // ???????????? ??????
        product.progressUpdate(productProgress);

        // ?????? ?????? ??????
        if (productProgress == ProductProgress.SOLDOUT && order != null) {
            order.setOrderState(OrderState.COMPLETE);
        }
    }

    //?????? ??????(DB????????? ?????? ????????????????????? ??????)
    @Override
    @Transactional
    public void deleteProduct(Long productId) throws Exception {
        Product product = productRepository.findById(productId).orElse(null);
        product.blindProduct(true);

        productRepository.save(product);
    }

    //?????? ?????? ???????????? ?????? ??????
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

    //?????? ????????? ?????? ?????????
    @Override
    public ProductDTO getProductDTO(Long productId) {
        return productRepository.findById(productId).map(ProductDTO::new).orElse(null);
    }

    //?????? ????????? ?????? ?????????
    @Override
    public ProductFindDTO getProductFindDTO(Long productId) {
        return productRepository.findById(productId).map(ProductFindDTO::new).orElse(null);
    }

    //?????????????????? ??????
    private List<ProductImage> productImages(Product product, List<String> productImageList) {
        List<ProductImage> productImages = new ArrayList<>();

        for (String imageName: productImageList) {

            ProductImage image = new ProductImage(product, imageName);

            productImages.add(image);

        }
        return productImages;
    }

    //????????? ??????
    @Override
    @Transactional
    public int updateView(Long productId) {
        return productRepository.updateView(productId);
    }

    //?????? ?????? ?????? ??????
    @Override
    public Page<ProductSellListDTO> getProductSellListDTO(SearchRequirements searchRequirements) {
        return productRepository.findAllBySearchRequirements(searchRequirements).map(ProductSellListDTO::new);
    }
    //?????? ?????? ?????? ?????????
    @Override
    public Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable) {
        ProductProgress[] productProgressList = ProductProgress.class.getEnumConstants();

        return productRepository.findAllByMemberIdAndProductProgressIn(memberId,productProgressList, pageable)
                .map(ProductListDTO::new);
    }

}
