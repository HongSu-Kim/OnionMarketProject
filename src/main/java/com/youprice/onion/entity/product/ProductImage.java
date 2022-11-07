package com.youprice.onion.entity.product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ProductImage {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "product_image_id")
        private Long id; //이미지번호 PK

        @ManyToOne(fetch = FetchType.LAZY )
        @JoinColumn(name = "product_id")
        private Product product; //상품번호 FK

        private String productImageName; //이미지파일이름

        public ProductImage(Product product,String productImageName) {
                this.product = product;
                this.productImageName = productImageName;
        }

}
