/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.web.method.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 解析 {@link Errors} 方法参数。
 *
 * <p>预期在方法签名中的模型属性后立即出现一个 {@code Errors} 方法参数。通过期望添加到模型中的最后两个属性来解析它们，
 * 分别是模型属性和其 {@link BindingResult}。
 *
 * @author Rossen Stoyanchev
 * @author Juergen Hoeller
 * @since 3.1
 */
public class ErrorsMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 获取参数类型
		Class<?> paramType = parameter.getParameterType();
		// 检查参数类型是否是Errors的子类或实现类
		return Errors.class.isAssignableFrom(paramType);
	}

	@Override
	@Nullable
	public Object resolveArgument(MethodParameter parameter,
								  @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
								  @Nullable WebDataBinderFactory binderFactory) throws Exception {

		Assert.state(mavContainer != null,
				"Errors/BindingResult argument only supported on regular handler methods");
		// 获取模型
		ModelMap model = mavContainer.getModel();
		// 获取模型中的最后一个键
		String lastKey = CollectionUtils.lastElement(model.keySet());
		if (lastKey != null && lastKey.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
			// 如果最后一个键以BindingResult.MODEL_KEY_PREFIX开头，则返回相应的值
			return model.get(lastKey);
		}
		// 如果不符合条件，则抛出异常
		throw new IllegalStateException(
				"An Errors/BindingResult argument is expected to be declared immediately after " +
				"the model attribute, the @RequestBody or the @RequestPart arguments " +
				"to which they apply: " + parameter.getMethod());
	}

}
