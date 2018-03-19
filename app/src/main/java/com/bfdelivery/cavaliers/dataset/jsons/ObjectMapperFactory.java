package com.bfdelivery.cavaliers.dataset.jsons;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json的对象映射构建器
 */

public class ObjectMapperFactory {

	public static final ObjectMapper createIgnorePropertiesMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		return mapper;

	}
}
