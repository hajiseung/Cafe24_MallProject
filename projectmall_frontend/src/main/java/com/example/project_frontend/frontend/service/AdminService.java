package com.example.project_frontend.frontend.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.project_frontend.frontend.dto.JSONResult;
import com.example.project_frontend.frontend.vo.AdminVo;
import com.example.project_frontend.frontend.vo.BasketListVo;
import com.example.project_frontend.frontend.vo.BasketVo;
import com.example.project_frontend.frontend.vo.CategoryVo;
import com.example.project_frontend.frontend.vo.ItemVo;
import com.example.project_frontend.frontend.vo.UserVo;

@Service
public class AdminService {
	private static final String SAVE_PATH = "/project-frontend";
	private static final String URL = "/images";
	private String tmpUrl = "http://localhost:8080/projectmall_backend/api/admin";

	// get 방식
	public Object getRestTemplateData(String uri, Object vo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(uri);
		JSONResult result = null;
		if (vo instanceof List) {
			System.out.println("vo instanceof List ACCESS");
			result = restTemplate.getForObject(requestUri, JSONResultListCategoryVo.class);
		}
		return result.getData();
	}

	public Object postRestTemplateData(String uri, Object vo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(uri);
		JSONResult result = null;
		if (vo instanceof AdminVo) {
			System.out.println("vo instanceof AdminVo ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultAdminVo.class);
		} else if (vo instanceof CategoryVo) {
			System.out.println("vo instanceof CategoryVo ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultCategoryVo.class);
		} else if (vo instanceof List) {
			System.out.println("vo instanceof List ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		} else if (vo instanceof String) {
			System.out.println("vo instanceof String ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		}
		return result.getData();
	}

	public AdminVo login(String id) throws URISyntaxException {
		AdminVo vo = new AdminVo();
		vo.setId(id);
		AdminVo result = (AdminVo) postRestTemplateData(tmpUrl + "/login", vo);
		return result;
	}

	public CategoryVo addcategory(CategoryVo vo) throws URISyntaxException {
		CategoryVo result = (CategoryVo) postRestTemplateData(tmpUrl + "/category/add", vo);
		return result;
	}

	public Map<String, Set<String>> categoryList() throws URISyntaxException {
		List<CategoryVo> result = new ArrayList<>();
		result.addAll((Collection<CategoryVo>) getRestTemplateData(tmpUrl + "/category/view", result));
		Set<String> topcategory = new TreeSet<>();
		Set<String> lowcategory = new TreeSet<>();
		for (int i = 0; i < result.size(); i++) {
			topcategory.add(result.get(i).getTop_category());
			lowcategory.add(result.get(i).getLow_category());
		}
		Map<String, Set<String>> resultMap = new HashMap<>();
		resultMap.put("topcategory", topcategory);
		resultMap.put("lowcategory", lowcategory);
		return resultMap;
	}

	public Set<String> getLowCategory(CategoryVo vo) throws URISyntaxException {
		List<CategoryVo> result = new ArrayList<>();
		Set<String> lowcategory = new TreeSet<>();
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/lowcategory");

		JSONResult jsonResult = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		result.addAll((Collection<? extends CategoryVo>) jsonResult.getData());

		for (int i = 0; i < result.size(); i++) {
			lowcategory.add(result.get(i).getLow_category());
		}

		return lowcategory;
	}

	// 물품 등록
	public void additem(ItemVo itemVo) throws URISyntaxException {
		// 사진 이름 설정
		List<String> photoList = new ArrayList<String>();
		List<Boolean> is_main = new ArrayList<Boolean>();

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setTop_category(itemVo.getTop_category());
		categoryVo.setLow_category(itemVo.getLow_category());

		int listSize = itemVo.getMultiPartPhoto().size();
		int is_mainSize = itemVo.getIs_main().size();
		for (int i = 0; i < listSize; i++) {
			photoList.add(restore(itemVo.getMultiPartPhoto().get(i)));
			if (i != is_mainSize - 1) {
				is_main.add(false);
			} else {
				is_main.add(true);
			}
		}

		itemVo.setCategoryVo(categoryVo);
		itemVo.setPhoto(photoList);
		itemVo.setIs_main(is_main);
		itemVo.setMultiPartPhoto(null);
		// 카테고리 넘버 가져오기
//		RestTemplate restTemplate = new RestTemplate();
//		URI requestUri = new URI(tmpUrl + "/getcategoryno");
//		JSONResult<CategoryVo> jsonResult = restTemplate.postForObject(requestUri, itemVo, JSONResultCategoryVo.class);
//		itemVo.setCategory_no(jsonResult.getData().getNo());
//		
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/item/add");
		JSONResult<ItemVo> jsonReultItem = restTemplate.postForObject(requestUri, itemVo, JSONResultItemVo.class);
	}

	public List<UserVo> getMemberList() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/memberlist");
		JSONResult<List<UserVo>> jsonReultItem = restTemplate.getForObject(requestUri, JSONResultUserVo.class);
		return jsonReultItem.getData();
	}

	public List<ItemVo> itemList() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/item/list");
		JSONResult<List<ItemVo>> jsonReultItem = restTemplate.getForObject(requestUri, JSONResultItemVoList.class);
		return jsonReultItem.getData();
	}

	public List<ItemVo> itemList(UserVo vo, List<BasketVo> list) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = null;
		JSONResultItemVoList jsonReultItem = null;
		for (int i = 0; i < list.size(); i++) {
			requestUri = new URI(tmpUrl + "/item/list/" + vo.getNo());
			jsonReultItem = restTemplate.getForObject(requestUri, JSONResultItemVoList.class);
		}
		return jsonReultItem.getData();
	}

	public List<BasketListVo> itemList(UserVo vo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = null;
		JSONResultBasketListVo jsonReultItem = null;
		requestUri = new URI(tmpUrl + "/item/list/" + vo.getNo());
		jsonReultItem = restTemplate.getForObject(requestUri, JSONResultBasketListVo.class);
		return jsonReultItem.getData();
	}

	public ItemVo getItem(ItemVo itemNo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/item/get");
		JSONResult<ItemVo> jsonReultItem = restTemplate.postForObject(requestUri, itemNo, JSONResultItemVo.class);
		return jsonReultItem.getData();
	}

	public UserVo getuser(UserVo vo, long no) throws URISyntaxException {
		BasketVo basketVo = new BasketVo();
		basketVo.setItem_count(1L);
		basketVo.setItem_no(no);

		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI("http://localhost:8080/projectmall_backend/api/user/getuserone");
		JSONResult<UserVo> jsonReultItem = restTemplate.postForObject(requestUri, vo, JSONResultUserVoOne.class);
		basketVo.setMember_no(jsonReultItem.getData().getNo());

		requestUri = new URI("http://localhost:8080/projectmall_backend/api/basket/add");
		JSONResult<BasketVo> jsonReultItem2 = restTemplate.postForObject(requestUri, basketVo,
				JSONResultBasketVo.class);
		return jsonReultItem.getData();

	}

	public String restore(MultipartFile itemVo) {
		String url = "";
		MultipartFile multipartFile = itemVo;
		try {
			if (multipartFile.isEmpty()) {
				return url;
			}
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			url = URL + "/" + saveFileName;
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		return url;
	}

	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	// DTO Class
	private static class JSONResultAdminVo extends JSONResult<AdminVo> {
	}

	private static class JSONResultUserVo extends JSONResult<List<UserVo>> {
	}

	private static class JSONResultUserVoOne extends JSONResult<UserVo> {
	}

	private static class JSONResultItemVo extends JSONResult<ItemVo> {
	}

	private static class JSONResultItemVoList extends JSONResult<List<ItemVo>> {
	}

	private static class JSONResultCategoryVo extends JSONResult<CategoryVo> {
	}

	private static class JSONResultBasketListVo extends JSONResult<List<BasketListVo>> {
	}

	private static class JSONResultBasketVo extends JSONResult<BasketVo> {
	}

	private static class JSONResultListCategoryVo extends JSONResult<List<CategoryVo>> {
	}

	private static class JSONResultGoodsList extends JSONResult<String> {
	}

}
