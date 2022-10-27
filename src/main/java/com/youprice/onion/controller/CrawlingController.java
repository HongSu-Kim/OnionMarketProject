package com.youprice.onion.controller;

import com.youprice.onion.dto.member.SessionDTO;
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
import com.youprice.onion.security.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
	@Transactional
	public String crawling(@LoginUser SessionDTO sessionDTO) throws IOException {
		log.error("@GetMapping(\"crawling\")");

		String url = "https://www.daangn.com/region/서울특별시/강남구";
		Document doc = Jsoup.connect(url).get();

		Elements elements = doc.select("article.card-top");

		log.error("elements.size() : " + String.valueOf(elements.size()));

		Member member = memberRepository.findById(sessionDTO.getId()).orElse(null);
		Town defaultTown = townRepositoy.findById(1L).orElse(null);
		Category category = categoryRepositoy.findById(115L).orElse(null);

		boolean payStatus = true;

		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product\\";

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			try {

				Element ele = iterator.next();

				String subject = ele.select(".card-title").text();//제목
				String priceStr = ele.select(".card-price").text();//가격 - String
				String content = subject + " " + priceStr + "에 팝니다.";//내용
				String townName = ele.select(".card-region-name").text();//동내이름
				String imgSrc = ele.select(".card-photo img").attr("src");//이미지 - url

				if (productRepository.existsBySubject(subject)) continue;

				// 상품가격 int
				log.error("price : " + priceStr);
				int price = Integer.parseInt(priceStr.replaceAll(",", "").replaceAll("원", "").replaceAll("만", "0000"));

				String[] townNameSplit = townName.split(" ");//동내이름 - split
				String townNameStr = townNameSplit[townNameSplit.length - 1];

				// 이미지
				log.error("imgSrc : " + imgSrc);
				String temp = imgSrc.split("/")[6];
				log.error("temp : " + temp);
				String representativeImage = temp.substring(0, temp.indexOf("?"));
				log.error("representativeImage : " + representativeImage);

				try {
					// 파일저장
					URL imgUrl = new URL(imgSrc);
					HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
					BufferedImage bi = ImageIO.read(conn.getInputStream());
					FileOutputStream file = new FileOutputStream(path + representativeImage);
					ImageIO.write(bi, "jpg", file);
					log.error("파일저장");
				} catch (Exception e) {
					e.getStackTrace();
					log.error("파일저장 오류");
					continue;
				}

				// 동내이름
				log.error("townName : " + townName);
				Town town = townRepositoy.findByMemberIdAndCoordinateTownNameContains(1L, townNameStr).orElse(defaultTown);


				Product product = new Product(member, town, category, subject, content, price, representativeImage, payStatus);
				productRepository.save(product);
				log.error("Product 저장");
				productImageRepository.save(new ProductImage(product, representativeImage));
				log.error("ProductImage 저장");

				log.error("DB 저장");
				payStatus = !payStatus;

			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		return "redirect:/";
	}
}
