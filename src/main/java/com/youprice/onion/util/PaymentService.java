package com.youprice.onion.util;

import com.youprice.onion.dto.order.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class PaymentService {

	// 결제취소
	public void paymentCancel(String imp_uid, String orderNum, int orderPayment) throws IOException {

		// 토큰생성
		String access_token = getToken();

		// url 설정
		HttpURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");
		conn = (HttpURLConnection) url.openConnection();

		// 요청방식, 데이터 타입 지정
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", access_token);
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		// request 설정
		JSONObject obj = new JSONObject();
		obj.put("imp_uid", imp_uid);
		obj.put("merchant_uid ", orderNum);
		obj.put("amount", orderPayment);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(obj.toString());
		bw.flush();
		bw.close();

		// 결과
		int result = 0;
		int responseCode = conn.getResponseCode();
		if (responseCode == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			log.error("paymentCancel() response : " + sb.toString());
			log.error("결제 취소 성공");
		} else {
			log.error("결제 취소 실패");
			log.error(conn.getResponseMessage());
			conn.disconnect();
			throw new IOException("결제 취소 실패");
		}
	}

	// 토큰생성
	private String getToken() throws IOException {

		// url 설정
		HttpURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/users/getToken");
		conn = (HttpURLConnection) url.openConnection();

		// 요청방식, 데이터 타입 지정
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		// key 설정
		JSONObject obj = new JSONObject();
		obj.put("imp_key","1147523670172442");
		obj.put("imp_secret","gbPmphuJCwO4jiLstoAMVbqoFjVZDjpTzTBjx7NLkqyJDVfafZxsqSaRtziHBoeAt6It8o7Aew2N4zDG");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(obj.toString());
		bw.flush();
		bw.close();

		// 결과
		int result = 0;
		int responseCode = conn.getResponseCode();
		if (responseCode == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			String res = sb.toString();
			br.close();
			conn.disconnect();
			String access_token = res.substring(res.indexOf("\":\"") + 3, res.indexOf("\",\""));
			log.error("getToken() response : " + res);
			log.error("access_token : " + access_token);
			return access_token;
		} else {
			log.error("토큰 발급 실패");
			log.error(conn.getResponseMessage());
			conn.disconnect();
			throw new IOException("토큰 발급 실패");
		}

	}
}
