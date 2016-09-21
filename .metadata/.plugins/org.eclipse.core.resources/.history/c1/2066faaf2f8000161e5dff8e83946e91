package com.ecomindo.common.rest;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ecomindo.common.dto.FileResponse;
/*import com.ecomindo.auth.dto.AuthRoleDto;*/
import com.ecomindo.common.dto.JsonInfo;

public class RestTemplateUtil {

	public static JsonInfo postForEntity(String url, String data) throws Exception {
		try {
			ResponseEntity<JsonInfo> result = (new RestTemplate()).postForEntity(url, data, JsonInfo.class);
			JsonInfo info = result.getBody();
			info.setUrl(url);

			if (!info.getResponseStatus().equals(HttpStatus.OK)) {
				throw new Exception(info.getData().toString());
			}
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static JsonInfo postForEntity(URI url, Object data) throws Exception {
		try {
			ResponseEntity<JsonInfo> result = (new RestTemplate()).postForEntity(url, data, JsonInfo.class);
			JsonInfo info = result.getBody();
			info.setUrl(url.toString());

			if (!info.getResponseStatus().equals(HttpStatus.OK)) {
				throw new Exception(info.getData().toString());
			}
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static JsonInfo getForEntity(URI url) throws Exception {
		try {
			ResponseEntity<JsonInfo> result = (new RestTemplate()).getForEntity(url, JsonInfo.class);
			JsonInfo info = result.getBody();
			info.setUrl(url.toString());

			if (!info.getResponseStatus().equals(HttpStatus.OK)) {
				throw new Exception(info.getData().toString());
			}
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static JsonInfo getForEntity(URI url, Object data) throws Exception {
		try {
			ResponseEntity<JsonInfo> result = (new RestTemplate()).getForEntity(url, JsonInfo.class);
			JsonInfo info = result.getBody();
			info.setUrl(url.toString());

			if (!info.getResponseStatus().equals(HttpStatus.OK)) {
				throw new Exception(info.getData().toString());
			}
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static JsonInfo exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity) throws Exception {
		try {
			ResponseEntity<JsonInfo> result = (new RestTemplate()).exchange(url, method, requestEntity, JsonInfo.class);
			JsonInfo info = result.getBody();
			info.setUrl(url.toString());

			// if(!info.getResponseStatus().equals(HttpStatus.OK)){
			// throw new Exception(info.getData().toString());
			// }
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static FileResponse postFileForEntity(URI url, Object data) throws Exception {
		try {
			ResponseEntity<FileResponse> result = (new RestTemplate()).postForEntity(url, data, FileResponse.class);
			FileResponse info = result.getBody();

			if (!info.getResponseStatus().equals(HttpStatus.OK)) {
				throw new Exception(info.getFilename().toString());
			}
			return info;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
