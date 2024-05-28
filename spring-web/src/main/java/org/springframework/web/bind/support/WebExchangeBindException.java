/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.bind.support;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.*;
import org.springframework.web.server.ServerWebInputException;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

/**
 * {@link ServerWebInputException} 的一个特殊化，当数据绑定和验证失败时抛出。
 * 实现 {@link BindingResult}（及其超接口 {@link Errors}）以允许直接分析绑定和验证错误。
 *
 * @author Rossen Stoyanchev
 * @since 5.0
 */
@SuppressWarnings("serial")
public class WebExchangeBindException extends ServerWebInputException implements BindingResult {
	/**
	 * 绑定结果
	 */
	private final BindingResult bindingResult;


	public WebExchangeBindException(MethodParameter parameter, BindingResult bindingResult) {
		super("Validation failure", parameter);
		this.bindingResult = bindingResult;
	}


	/**
	 * 返回此 BindException 包装的 BindingResult。
	 * 通常会是一个 BeanPropertyBindingResult。
	 *
	 * @see BeanPropertyBindingResult
	 */
	public final BindingResult getBindingResult() {
		return this.bindingResult;
	}


	@Override
	public String getObjectName() {
		return this.bindingResult.getObjectName();
	}

	@Override
	public void setNestedPath(String nestedPath) {
		this.bindingResult.setNestedPath(nestedPath);
	}

	@Override
	public String getNestedPath() {
		return this.bindingResult.getNestedPath();
	}

	@Override
	public void pushNestedPath(String subPath) {
		this.bindingResult.pushNestedPath(subPath);
	}

	@Override
	public void popNestedPath() throws IllegalStateException {
		this.bindingResult.popNestedPath();
	}


	@Override
	public void reject(String errorCode) {
		this.bindingResult.reject(errorCode);
	}

	@Override
	public void reject(String errorCode, String defaultMessage) {
		this.bindingResult.reject(errorCode, defaultMessage);
	}

	@Override
	public void reject(String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage) {
		this.bindingResult.reject(errorCode, errorArgs, defaultMessage);
	}

	@Override
	public void rejectValue(@Nullable String field, String errorCode) {
		this.bindingResult.rejectValue(field, errorCode);
	}

	@Override
	public void rejectValue(@Nullable String field, String errorCode, String defaultMessage) {
		this.bindingResult.rejectValue(field, errorCode, defaultMessage);
	}

	@Override
	public void rejectValue(
			@Nullable String field, String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage) {

		this.bindingResult.rejectValue(field, errorCode, errorArgs, defaultMessage);
	}

	@Override
	public void addAllErrors(Errors errors) {
		this.bindingResult.addAllErrors(errors);
	}


	@Override
	public boolean hasErrors() {
		return this.bindingResult.hasErrors();
	}

	@Override
	public int getErrorCount() {
		return this.bindingResult.getErrorCount();
	}

	@Override
	public List<ObjectError> getAllErrors() {
		return this.bindingResult.getAllErrors();
	}

	@Override
	public boolean hasGlobalErrors() {
		return this.bindingResult.hasGlobalErrors();
	}

	@Override
	public int getGlobalErrorCount() {
		return this.bindingResult.getGlobalErrorCount();
	}

	@Override
	public List<ObjectError> getGlobalErrors() {
		return this.bindingResult.getGlobalErrors();
	}

	@Override
	@Nullable
	public ObjectError getGlobalError() {
		return this.bindingResult.getGlobalError();
	}

	@Override
	public boolean hasFieldErrors() {
		return this.bindingResult.hasFieldErrors();
	}

	@Override
	public int getFieldErrorCount() {
		return this.bindingResult.getFieldErrorCount();
	}

	@Override
	public List<FieldError> getFieldErrors() {
		return this.bindingResult.getFieldErrors();
	}

	@Override
	@Nullable
	public FieldError getFieldError() {
		return this.bindingResult.getFieldError();
	}

	@Override
	public boolean hasFieldErrors(String field) {
		return this.bindingResult.hasFieldErrors(field);
	}

	@Override
	public int getFieldErrorCount(String field) {
		return this.bindingResult.getFieldErrorCount(field);
	}

	@Override
	public List<FieldError> getFieldErrors(String field) {
		return this.bindingResult.getFieldErrors(field);
	}

	@Override
	@Nullable
	public FieldError getFieldError(String field) {
		return this.bindingResult.getFieldError(field);
	}

	@Override
	@Nullable
	public Object getFieldValue(String field) {
		return this.bindingResult.getFieldValue(field);
	}

	@Override
	@Nullable
	public Class<?> getFieldType(String field) {
		return this.bindingResult.getFieldType(field);
	}

	@Override
	@Nullable
	public Object getTarget() {
		return this.bindingResult.getTarget();
	}

	@Override
	public Map<String, Object> getModel() {
		return this.bindingResult.getModel();
	}

	@Override
	@Nullable
	public Object getRawFieldValue(String field) {
		return this.bindingResult.getRawFieldValue(field);
	}

	@Override
	@SuppressWarnings("rawtypes")
	@Nullable
	public PropertyEditor findEditor(@Nullable String field, @Nullable Class valueType) {
		return this.bindingResult.findEditor(field, valueType);
	}

	@Override
	@Nullable
	public PropertyEditorRegistry getPropertyEditorRegistry() {
		return this.bindingResult.getPropertyEditorRegistry();
	}

	@Override
	public String[] resolveMessageCodes(String errorCode) {
		return this.bindingResult.resolveMessageCodes(errorCode);
	}

	@Override
	public String[] resolveMessageCodes(String errorCode, String field) {
		return this.bindingResult.resolveMessageCodes(errorCode, field);
	}

	@Override
	public void addError(ObjectError error) {
		this.bindingResult.addError(error);
	}

	@Override
	public void recordFieldValue(String field, Class<?> type, @Nullable Object value) {
		this.bindingResult.recordFieldValue(field, type, value);
	}

	@Override
	public void recordSuppressedField(String field) {
		this.bindingResult.recordSuppressedField(field);
	}

	@Override
	public String[] getSuppressedFields() {
		return this.bindingResult.getSuppressedFields();
	}


	/**
	 * 返回关于此对象中保存的错误的诊断信息。
	 */
	@Override
	public String getMessage() {
		// 获取方法参数
		MethodParameter parameter = getMethodParameter();
		Assert.state(parameter != null, "No MethodParameter");
		// 构建错误消息字符串
		StringBuilder sb = new StringBuilder("Validation failed for argument at index ")
				.append(parameter.getParameterIndex())
				.append(" in method: ")
				.append(parameter.getExecutable().toGenericString())
				.append(", with ")
				.append(this.bindingResult.getErrorCount())
				.append(" error(s): ");
		// 遍历所有错误
		for (ObjectError error : this.bindingResult.getAllErrors()) {
			// 将错误信息添加到字符串中
			sb.append('[').append(error).append("] ");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || this.bindingResult.equals(other));
	}

	@Override
	public int hashCode() {
		return this.bindingResult.hashCode();
	}

}
