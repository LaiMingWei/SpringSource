/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

/**
 * Strategy interface for persisting {@code java.util.Properties},
 * allowing for pluggable parsing strategies.
 *
 * <p>The default implementation is DefaultPropertiesPersister,
 * providing the native parsing of {@code java.util.Properties},
 * but allowing for reading from any Reader and writing to any Writer
 * (which allows to specify an encoding for a properties file).
 *
 * @author Juergen Hoeller
 * @see DefaultPropertiesPersister
 * @see org.springframework.core.io.support.ResourcePropertiesPersister
 * @see java.util.Properties
 * @since 10.03.2004
 */
public interface PropertiesPersister {

	/**
	 * 将属性从给定的InputStream加载到给定的属性对象中。
	 *
	 * @param props 要加载到的属性对象
	 * @param is    要从其中加载的InputStream
	 * @throws IOException 在IO错误的情况下
	 * @see java.util.Properties#load
	 */
	void load(Properties props, InputStream is) throws IOException;

	/**
	 * 将属性从给定的阅读器加载到给定的属性对象中。
	 *
	 * @param props  要加载到的属性对象
	 * @param reader 要加载的阅读器
	 * @throws IOException 在IO错误的情况下
	 */
	void load(Properties props, Reader reader) throws IOException;

	/**
	 * Write the contents of the given Properties object to the
	 * given OutputStream.
	 *
	 * @param props  the Properties object to store
	 * @param os     the OutputStream to write to
	 * @param header the description of the property list
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#store
	 */
	void store(Properties props, OutputStream os, String header) throws IOException;

	/**
	 * Write the contents of the given Properties object to the
	 * given Writer.
	 *
	 * @param props  the Properties object to store
	 * @param writer the Writer to write to
	 * @param header the description of the property list
	 * @throws IOException in case of I/O errors
	 */
	void store(Properties props, Writer writer, String header) throws IOException;

	/**
	 * Load properties from the given XML InputStream into the
	 * given Properties object.
	 *
	 * @param props the Properties object to load into
	 * @param is    the InputStream to load from
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#loadFromXML(java.io.InputStream)
	 */
	void loadFromXml(Properties props, InputStream is) throws IOException;

	/**
	 * Write the contents of the given Properties object to the
	 * given XML OutputStream.
	 *
	 * @param props  the Properties object to store
	 * @param os     the OutputStream to write to
	 * @param header the description of the property list
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#storeToXML(java.io.OutputStream, String)
	 */
	void storeToXml(Properties props, OutputStream os, String header) throws IOException;

	/**
	 * Write the contents of the given Properties object to the
	 * given XML OutputStream.
	 *
	 * @param props    the Properties object to store
	 * @param os       the OutputStream to write to
	 * @param encoding the encoding to use
	 * @param header   the description of the property list
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#storeToXML(java.io.OutputStream, String, String)
	 */
	void storeToXml(Properties props, OutputStream os, String header, String encoding) throws IOException;

}
