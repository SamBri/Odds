package com.nothing.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

public class OctetStreamMessageConverters extends StringHttpMessageConverter {

	private static final MediaType APPLICATION_PLUS_JSON = new MediaType("application", "*+json");

	/**
	 * The default charset used by the converter.
	 */
	public static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;


	@Nullable
	private volatile List<Charset> availableCharsets;

	private boolean writeAcceptCharset = false;
	
	
	@Override
	protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {
	
		
		
		HttpHeaders headers = outputMessage.getHeaders();
		if (this.writeAcceptCharset && headers.get(HttpHeaders.ACCEPT_CHARSET) == null) {
			headers.setAcceptCharset(getAcceptedCharsets());
		}
		Charset charset = getContentTypeCharset(headers.getContentType());
		StreamUtils.copy(str, charset, outputMessage.getBody());
		
		
	}

	@Override
	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
		return super.readInternal(clazz, inputMessage);
	}
	
	
	private Charset getContentTypeCharset(@Nullable MediaType contentType) {
		if (contentType != null) {
			Charset charset = contentType.getCharset();
			if (charset != null) {
				return charset;
			}
			else if (contentType.isCompatibleWith(MediaType.APPLICATION_JSON) ||
					contentType.isCompatibleWith(APPLICATION_PLUS_JSON)) {
				// Matching to AbstractJackson2HttpMessageConverter#DEFAULT_CHARSET
				return StandardCharsets.UTF_8;
			}
		}
		Charset charset = getDefaultCharset();
		Assert.state(charset != null, "No default charset");
		return charset;
	}

	
	
	
}
