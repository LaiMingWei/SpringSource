/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.web.servlet.mvc.condition;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * 为 {@link RequestCondition} 类型提供 {@link #equals(Object)}, {@link #hashCode()},
 * 和 {@link #toString()} 的实现的基类。
 *
 * @param <T> 此 RequestCondition 可与之组合和比较的对象的类型
 * @author Rossen Stoyanchev
 * @since 3.1
 */
public abstract class AbstractRequestCondition<T extends AbstractRequestCondition<T>> implements RequestCondition<T> {

	/**
	 * 表示此条件是否为空，即它是否包含任何离散项。
	 *
	 * @return 如果为空，则为 {@code true}; 否则为 {@code false}
	 */
	public boolean isEmpty() {
		return getContent().isEmpty();
	}

	/**
	 * 返回请求条件由哪些离散项组成。
	 * <p>例如 URL 模式、HTTP 请求方法、参数表达式等。
	 *
	 * @return 对象的集合（永远不会是 {@code null}）
	 */
	protected abstract Collection<?> getContent();

	/**
	 * 在打印内容的离散项时要使用的表示法。
	 * <p>例如对于 URL 模式为 {@code " || "}，参数表达式为 {@code " && "}。
	 */
	protected abstract String getToStringInfix();


	@Override
	public boolean equals(@Nullable Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		return getContent().equals(((AbstractRequestCondition<?>) other).getContent());
	}

	@Override
	public int hashCode() {
		return getContent().hashCode();
	}

	@Override
	public String toString() {
		String infix = getToStringInfix();
		StringJoiner joiner = new StringJoiner(infix, "[", "]");
		for (Object expression : getContent()) {
			joiner.add(expression.toString());
		}
		return joiner.toString();
	}

}
