package naverapi;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dto.NaverApiDTO;

// 메인에 있는 네이버 API 소스들을 클래스를 만들어서 다 옮기고
// 메인에서는 검색어만 전달하면 결과가 나오게 만들기

// 맛집을 찾아 주는 앱이라 생각하고 json 형식의 데이터로 받는데
// 너무 복잡하네..
// 제목, 링크, 날짜만 가져와서 보기 좋게 출력 해보자
// 제목: 여기 맞집
// 링크: https://sa;dfkjsajdf
// 날짜: 20211129
// 어렵지만 어떻게 해야 될지 고민 또는 검색을 통해서 할 수 있는 사람은 해보기

public class MainClass {

	public static void main(String[] args) {
		NaverAPI nv = new NaverAPI();
		int num = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("검색 카테고리를 번호로 입력해주세요");
		System.out.println("1. 뉴스");
		System.out.println("2. 블로그");
		num = sc.nextInt();


		if ( num == 1 ) {
            System.out.println("검색 키워드를 입력하세요");
			Scanner sc2 = new Scanner(System.in);
			String keyword = sc2.nextLine();
			String responseBody = nv.searchNews(keyword);
			
			Gson gson = new Gson();

			JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
			JsonArray jsonArray = jsonObject.getAsJsonArray("items");
			// System.out.println(jsonArray.get(0));
			for (JsonElement em : jsonArray) {
				NaverApiDTO dto = gson.fromJson(em, NaverApiDTO.class);
				System.out.println("제목: " + dto.getTitle());
				System.out.println("링크: " + dto.getLink());
				System.out.println("날짜: " + dto.getPubDate());
			}
		}

		if (num == 2) {
			System.out.println("검색 키워드를 입력하세요");
			Scanner sc2 = new Scanner(System.in);
			String keyword = sc2.nextLine();
			String responseBody = nv.searchBlog(keyword);
			Gson gson = new Gson();

			JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
			JsonArray jsonArray = jsonObject.getAsJsonArray("items");
			// System.out.println(jsonArray.get(0));
			for (JsonElement em : jsonArray) {
				NaverApiDTO dto = gson.fromJson(em, NaverApiDTO.class);
				System.out.println("제목: " + dto.getTitle());
				System.out.println("링크: " + dto.getLink());
				System.out.println("날짜: " + dto.getPubDate());
			}
		}

//     	System.out.println(responseBody);

		// json 파싱

//		Gson gson = new Gson();
//
//		JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
//		JsonArray jsonArray = jsonObject.getAsJsonArray("items");
//		// System.out.println(jsonArray.get(0));
//		for (JsonElement em : jsonArray) {
//			NaverApiDTO dto = gson.fromJson(em, NaverApiDTO.class);
//			System.out.println("뉴스 제목: " + dto.getTitle());
//			System.out.println("뉴스 링크: " + dto.getLink());
//			System.out.println("뉴스 날짜: " + dto.getPubDate());
//		}

	}

}