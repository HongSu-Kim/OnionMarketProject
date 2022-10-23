package com.youprice.onion.controller;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.repository.product.TownRepositoy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CrawlingController {

	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final ProductImageRepository productImageRepository;
	private final TownRepositoy townRepositoy;
	private final CategoryRepositoy categoryRepositoy;

	@GetMapping("crawling")
	@Transactional(readOnly = true)
	public String crawling() throws IOException {
		log.error("@GetMapping(\"crawling\")");

		String url = "https://www.daangn.com/region/서울특별시/강남구";
		Document doc = Jsoup.connect(url).get();

		Elements elements = doc.select("article.card-top ");

		log.error("elements.size() : " + String.valueOf(elements.size()));

		Member member = memberRepository.findById(1L).orElse(null);
		Town defaultTown = townRepositoy.findById(1L).orElse(null);
		Category category = categoryRepositoy.findById(115L).orElse(null);

		boolean payStatus = true;

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element ele = iterator.next();

			String subject = ele.select(".card-title").text();//제목
			String price = ele.select(".card-price").text();//가격 - String
			String content = subject + " " + price + "에 팝니다.";//내용
			String townName = ele.select(".card-region-name").text();//동내이름
			String imageUrl = ele.select(".card-photo img").attr("src");//이미지 - url

			// 상품가격 int
			log.error("price : " + price);
			if (price.equals("")) continue;
			int intPrice = Integer.parseInt(price.replaceAll(",", "").replaceAll("원", "").replaceAll("만", "0000"));

			String[] townNameSplit = townName.split(" ");//동내이름 - split
			String townNameStr = townNameSplit[townNameSplit.length - 1];

			// 이미지
			log.error("imageUrl : " + imageUrl);
			String temp = imageUrl.split("/")[6];
			String representativeImage = temp.substring(0, temp.indexOf(".")) + ".jpg";

			// 동내이름
			log.error("townName : " + townName);
			Town town = townRepositoy.findByMemberIdAndCoordinateTownNameContains(1L, townNameStr).orElse(defaultTown);

			Product product = new Product(member, town, category, null, subject, content, intPrice, representativeImage, null, payStatus);
			productRepository.save(product);
			productImageRepository.save(new ProductImage(product, representativeImage));

			payStatus = !payStatus;
		}

		return "redirect:/";
	}
}
