package com.bfdelivery.cavaliers.background.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Panoo on 2017/8/22.
 */

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final ObjectReader adapter;

	JacksonResponseBodyConverter(ObjectReader adapter) {
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return adapter.readValue(value.charStream());
		} finally {
			value.close();
		}
	}
}

final class JacksonRequestBodyConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

	private final ObjectWriter adapter;

	JacksonRequestBodyConverter(ObjectWriter adapter) {
		this.adapter = adapter;
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		byte[] bytes = adapter.writeValueAsBytes(value);
		return RequestBody.create(MEDIA_TYPE, bytes);
	}
}


public class JacksonSafeConvertFactory extends Converter.Factory {
	/**
	 * Create an instance using a default {@link ObjectMapper} instance for conversion.
	 */
	public static JacksonSafeConvertFactory create() {
		return create(new ObjectMapper());
	}

	/**
	 * Create an instance using {@code mapper} for conversion.
	 */
	@SuppressWarnings("ConstantConditions") // Guarding public API nullability.
	public static JacksonSafeConvertFactory create(ObjectMapper mapper) {
		if (mapper == null) throw new NullPointerException("mapper == null");
		return new JacksonSafeConvertFactory(mapper);
	}

	private final ObjectMapper mapper;

	private JacksonSafeConvertFactory(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
	                                                        Retrofit retrofit) {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		JavaType javaType = mapper.getTypeFactory().constructType(type);
		ObjectReader reader = mapper.readerFor(javaType);
		return new JacksonResponseBodyConverter<>(reader);
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type,
	                                                      Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		JavaType javaType = mapper.getTypeFactory().constructType(type);
		ObjectWriter writer = mapper.writerFor(javaType);
		return new JacksonRequestBodyConverter<>(writer);
	}
}
