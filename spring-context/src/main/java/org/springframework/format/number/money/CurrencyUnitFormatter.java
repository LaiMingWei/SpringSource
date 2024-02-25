/*
 * Copyright 2002-2015 the original author or authors.
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

package org.springframework.format.number.money;

import org.springframework.format.Formatter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

/**
 * JSR-354 {@link javax.money.CurrencyUnit} 值的格式化器，从货币代码字符串到货币单位。
 *
 * 该格式化器将货币代码字符串转换为 {@link javax.money.CurrencyUnit} 对象，并且可以将 {@link javax.money.CurrencyUnit} 对象格式化为货币代码字符串。
 *
 * @author Juergen Hoeller
 * @since 4.2
 */
public class CurrencyUnitFormatter implements Formatter<CurrencyUnit> {

	@Override
	public String print(CurrencyUnit object, Locale locale) {
		return object.getCurrencyCode();
	}

	@Override
	public CurrencyUnit parse(String text, Locale locale) {
		return Monetary.getCurrency(text);
	}

}
