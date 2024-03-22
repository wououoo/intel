package com.humanedu.searchfood.naver;

import com.humanedu.searchfood.naver.dto.SearchImageRequestDto;
import com.humanedu.searchfood.naver.dto.SearchImageResponseDto;
import com.humanedu.searchfood.naver.dto.SearchRegionRequestDto;
import com.humanedu.searchfood.naver.dto.SearchRegionResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverAPIClient {
    // 1. 지역검색 API Call method(request dto, response dto)
    public SearchRegionResponseDto searchRegion(
            SearchRegionRequestDto searchRegionRequestDto) {
        // API Call Library -> HttpURLConnection, WebClient, RestTemplate, Retry etc... (여기서는 RestTemplate사용)

        // (3,4) Response 설정, 실제 API Cal

        return new RestTemplate()
                .exchange(getURI("/v1/search/local.json", searchRegionRequestDto.getQuery())
                        , HttpMethod.GET
                        , getHttpEntity()
                        , SearchRegionResponseDto.class
                ).getBody();
    }

    // 2. 지역 이미지 검색 API Call method(request dto, response dto)
    public SearchImageResponseDto searchImage(SearchImageRequestDto searchImageRequestDto) {
        // 실제 API Call

        ResponseEntity<SearchImageResponseDto> responseRestTemplate = new RestTemplate()
                .exchange(getURI("/v1/search/image", searchImageRequestDto.getQuery())
                        , HttpMethod.GET
                        , getHttpEntity()
                        , SearchImageResponseDto.class
                );

        return responseRestTemplate.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "JzDprqkd6wofEbQ92jny");
        headers.set("X-Naver-Client-Secret", "S2CgAgm5UF");
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    private URI getURI(String path, String query) {
        return UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")     // 호출할 서버 domain 주소
                .path(path)             // 호출할 서버 path
                .queryParam("query", query)     // 호출할 query string
                .encode()                                   // 한글처리
                .build()
                .toUri();
    }

    private HttpEntity getHttpEntity() {
        return new HttpEntity(getHttpHeaders());
    }
}
