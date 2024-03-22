package com.humanedu.searchfood;

import com.humanedu.searchfood.naver.NaverAPIClient;
import com.humanedu.searchfood.naver.dto.SearchImageRequestDto;
import com.humanedu.searchfood.naver.dto.SearchImageResponseDto;
import com.humanedu.searchfood.naver.dto.SearchRegionRequestDto;
import com.humanedu.searchfood.naver.dto.SearchRegionResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchfoodApiApplicationTests {

	@Autowired
	private NaverAPIClient naverAPIClient;

	@Test
	void contextLoads() {
	}

	@Test	// 네이버 지역검색 OpenAPI 테스트
	void naverSearchRegionAPITest() {
		String paramQuery = "커피";

		// 네이버 지역검색 OpenAPI Call method 테스트
		SearchRegionRequestDto searchRegionRequestDto = new SearchRegionRequestDto();
		searchRegionRequestDto.setQuery(paramQuery);

		SearchRegionResponseDto searchRegionResponseDto
				= naverAPIClient.searchRegion(searchRegionRequestDto);
		System.out.println("네이버 지역검색 OpenAPI response json " + searchRegionResponseDto);
	}

	@Test	// 네이버 이미지검색 OpenAPI 테스트
	void naverSearchImageAPITest() {
		String paramQuery = "커피";

		// 이미지 API Call method 테스트
		SearchImageRequestDto searchImageRequestDto = new SearchImageRequestDto();
		searchImageRequestDto.setQuery(paramQuery);

		SearchImageResponseDto searchImageResponseDto
				= naverAPIClient.searchImage(searchImageRequestDto);
		System.out.println("이미지 검색 response json: " + searchImageResponseDto);
	}
}
