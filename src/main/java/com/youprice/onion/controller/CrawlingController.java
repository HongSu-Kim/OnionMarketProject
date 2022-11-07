package com.youprice.onion.controller;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.*;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CrawlingController {

	private String[] districts = { "강남구", "서초구", "동작구", "관악구", "송파구", "강동구", "광진구", "성동구", "용산구", "중구", "동대문구" };

	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final ProductImageRepository productImageRepository;
	private final TownRepository townRepository;
	private final CoordinateRepositoy coordinateRepositoy;
	private final CategoryRepositoy categoryRepositoy;

	@GetMapping("crawling")
	@Transactional
	public String crawling(@LoginUser SessionDTO sessionDTO) throws IOException {
		log.info("@GetMapping(\"crawling\")");

		int crawlingProduct = 0;

		Member member = memberRepository.findById(sessionDTO.getId()).orElse(null);
		Category defaultCategory = categoryRepositoy.findByCategoryName("기타상품").orElse(null);
		Town defaultTown = townRepository.findByMemberIdAndCoordinateTownNameContains(sessionDTO.getId(), "역삼1동")
				.orElseGet(() -> {
					Coordinate coordinate = coordinateRepositoy.findByTownNameContaining("역삼1동").get(0);
					return townRepository.save(new Town(coordinate, member));
				});

		String path = System.getProperty("user.dir") + "/src/img/" + "product/";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
			log.info("폴더 생성 : " + path);
		}

		for (String district : districts) {

			String url = "https://www.daangn.com/region/서울특별시/" + district;
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("article.card-top");

			log.info("elements.size() : " + String.valueOf(elements.size()));

			for (Element ele : elements) {
				try {

					String subject = ele.select(".card-title").text();//제목
					String priceStr = ele.select(".card-price").text();//가격 - String
					String content = subject + " " + priceStr + "에 팝니다.";//내용
					String townName = ele.select(".card-region-name").text();//동내이름
					String imgSrc = ele.select(".card-photo img").attr("src");//이미지 - url

					if (productRepository.existsBySubject(subject)) continue;

					// 상품가격 int
					log.info("price : " + priceStr);
					int price = Integer.parseInt(priceStr.replaceAll(",", "").replaceAll("원", "").replaceAll("만", "0000"));
					if (price < 10)
						price *= 1000;
					else if (price < 100)
						price *= 100;
					else if (price < 1000)
						price *= 10;

					String[] townNameSplit = townName.split(" ");//동내이름 - split
					String townNameStr = townNameSplit[townNameSplit.length - 1];

					// 이미지
					log.info("imgSrc : " + imgSrc);
					String temp = imgSrc.split("/")[6];
					log.info("temp : " + temp);
					String representativeImage = temp.substring(0, temp.indexOf("?"));
					log.info("representativeImage : " + representativeImage);

					// 동내이름
					log.info("townName : " + townName);
					Town town = townRepository.findByMemberIdAndCoordinateTownNameContains(member.getId(), townNameStr)
							.orElseGet(() -> {
								List<Coordinate> coordinate = coordinateRepositoy.findByTownNameContaining(townNameStr);
								return coordinate.size() == 0 ? defaultTown : townRepository.save(new Town(coordinate.get(0), member));
							});

					// 카테고리
					log.info("subject : " + subject);
					String categoryStr = getCategoryStr(subject);
					log.info("categoryStr : " + categoryStr);
					List<Category> categoryList = categoryRepositoy.findBySubjectContains(categoryStr);
					Category category = categoryList.size() == 0 ? defaultCategory : categoryList.get(0);

					boolean payStatus = price < 500 ? false : Math.random() < 0.8;
					boolean auctionStatus = Math.random() < 0.3;

					try {
						// 파일저장
						URL imgUrl = new URL(imgSrc);
						HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
						BufferedImage bi = ImageIO.read(conn.getInputStream());
						FileOutputStream file = new FileOutputStream(path + representativeImage);
						ImageIO.write(bi, "jpg", file);
						log.info("파일저장");
					} catch (Exception e) {
						e.getStackTrace();
						log.error("파일저장 오류");
						continue;
					}

					Product product = new Product(member, town, category, subject, content, price, representativeImage, auctionStatus, payStatus);
					productRepository.save(product);
					log.info("Product 저장");
					productImageRepository.save(new ProductImage(product, representativeImage));
					log.info("ProductImage 저장");

					log.info("DB 저장");
					crawlingProduct++;

				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		}

		log.info("crawlingProduct : " + crawlingProduct);
		return "redirect:/";
	}

	private String getCategoryStr(String subject) {
		for (Map.Entry<String, String> entry : categoryStr.entrySet()) {
			if (subject.contains(entry.getKey())){
				return entry.getValue();
			}
		}
		return subject;
	}

	private Map<String, String> categoryStr = new HashMap<>(){{
		put("갤럭시", "모바일");
		put("아이폰", "모바일");
		put("플립", "모바일");
		put("아이패드", "모바일");
		put("태블릿", "모바일");
		put("냉장고", "가전제품");
		put("냉동고", "가전제품");
		put("티비", "가전제품");
		put("TV", "가전제품");
		put("tv", "가전제품");
		put("소독기", "가전제품");
		put("세탁기", "가전제품");
		put("건조기", "가전제품");
		put("청소기", "가전제품");
		put("난로", "가전제품");
		put("커피머신", "가전제품");
		put("에어컨", "가전제품");
		put("선풍기", "가전제품");
		put("전자레인지", "가전제품");
		put("전자렌지", "가전제품");
		put("밥솥", "가전제품");
		put("공기청정기", "가전제품");
		put("히터", "가전제품");
		put("버너", "가전제품");
		put("제습기", "가전제품");
		put("가습기", "가전제품");
		put("라디에이터", "가전제품");
		put("헤드셋", "오디오/영상/관련기기");
		put("이어폰", "오디오/영상/관련기기");
		put("에어팟", "오디오/영상/관련기기");
		put("버즈", "오디오/영상/관련기기");
		put("스피커", "오디오/영상/관련기기");
		put("오디오", "오디오/영상/관련기기");
		put("PC", "PC/노트북");
		put("노트북", "PC/노트북");
		put("컴퓨터", "PC/노트북");
		put("모니터", "PC/노트북");
		put("키보드", "PC/노트북");
		put("마우스", "PC/노트북");
		put("게임", "게임/타이틀");
		put("플스", "게임/타이틀");
		put("PS5", "게임/타이틀");
		put("ps5", "게임/타이틀");
		put("PS4", "게임/타이틀");
		put("ps4", "게임/타이틀");
		put("카메라", "카메라/DSLR");
		put("DSLR", "카메라/DSLR");
		put("망원경", "카메라/DSLR");
		put("메인보드", "PC부품/저장장치");
		put("RAM", "PC부품/저장장치");
		put("AMD", "PC부품/저장장치");
		put("라이젠", "PC부품/저장장치");
		put("인텔", "PC부품/저장장치");

		put("책상", "가구");
		put("의자", "가구");
		put("선반", "가구");
		put("테이블", "가구");
		put("이케아", "가구");
		put("침대", "가구");
		put("서랍장", "가구");
		put("수납장", "가구");
		put("행거", "가구");
		put("쇼파", "가구");
		put("식탁", "가구");
		put("책장", "가구");
		put("트립트랩", "가구");
		put("옷장", "가구");
		put("트롤리", "가구");
		put("카펫", "인테리어");
		put("오르골", "인테리어");
		put("커튼", "인테리어");
		put("파티션", "인테리어");
		put("촛대", "인테리어");

		put("냄비", "주방용품");
		put("후라이팬", "주방용품");
		put("압력솥", "주방용품");
		put("믹서기", "주방용품");
		put("인덕션", "주방용품");
		put("에어프라이", "주방용품");
		put("가스렌지", "주방용품");
		put("가스렌인지", "주방용품");
		put("접시", "주방용품");
		put("그릇", "주방용품");
		put("반죽기", "주방용품");
		put("전기매트", "생활용품");
		put("전기장판", "생활용품");
		put("이불", "생활용품");
		put("베개", "생활용품");
		put("유모차", "생활용품");
		put("콜라", "식품");
		put("된장", "식품");
		put("간장", "식품");
		put("스팸", "식품");
		put("식용유", "식품");
		put("공구", "산업용품");
		put("드릴", "산업용품");

		put("베이비", "베이비의류(0~2세)");
		put("유아동", "유아동용품");
		put("교육", "교육/완구/인형");
		put("인형", "교육/완구/인형");
		put("장난감", "교육/완구/인형");
		put("레고", "교육/완구/인형");

		put("패딩", "패딩/점퍼");
		put("점퍼", "패딩/점퍼");
		put("후드티", "후드티/후드집업");
		put("후드집업", "후드티/후드집업");
		put("니트", "니트/스웨터");
		put("스웨터", "니트/스웨터");
		put("조끼", "조끼/트레이닝");
		put("트레이닝", "조끼/트레이닝");

		put("가방", "남성가방");
		put("크로스백", "남성가방");
		put("루이비통", "여성가방");
		put("샤넬", "여성가방");
		put("에르메스", "여성가방");
		put("프라다", "여성가방");
		put("구찌", "여성가방");
		put("디올", "여성가방");
		put("캐리어", "여행용");
		put("케리어", "여행용");

		put("바디", "바디/헤어케어");
		put("헤어케어", "바디/헤어케어");
		put("고데기", "바디/헤어케어");
		put("드라이기", "바디/헤어케어");
		put("향수", "향수/아로마");
		put("아로마", "향수/아로마");
		put("네일아트", "네일아트/케어");
		put("다이어트", "다이어트/이너뷰티");
		put("이너뷰티", "다이어트/이너뷰티");

		put("캐디백", "골프");
		put("하프백", "골프");
		put("스텐드백", "골프");
		put("아이언", "골프");
		put("텐트", "캠핑");
		put("등산", "등산/클라이밍");
		put("클라이밍", "등산/클라이밍");
		put("헬스", "헬스/요가/필라테스");
		put("요가", "헬스/요가/필라테스");
		put("필라테스", "헬스/요가/필라테스");

		put("CD", "CD/DVD/LP");
		put("DVD", "CD/DVD/LP");
		put("LP", "CD/DVD/LP");
		put("드럼", "악기");
		put("기타", "악기");

		put("만화책", "도서");
		put("전권", "도서");
		put("기프티콘", "기프티콘/쿠폰");
		put("기프트콘", "기프티콘/쿠폰");
		put("기프트카드", "기프티콘/쿠폰");
		put("쿠폰", "기프티콘/쿠폰");
		put("숙박권", "티켓");
		put("뷔페", "티켓");
		put("뷔폐", "티켓");
		put("식사권", "티켓");

		put("강아지", "강아지 용품");
		put("고양이", "고양이 용품");
	}};

}
