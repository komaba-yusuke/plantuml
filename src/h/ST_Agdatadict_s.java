/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *

 * This file is part of Smetana.
 * Smetana is a partial translation of Graphviz/Dot sources from C to Java.
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * This translation is distributed under the same Licence as the original C program:
 * 
 *************************************************************************
 * Copyright (c) 2011 AT&T Intellectual Property 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: See CVS logs. Details at http://www.graphviz.org/
 *************************************************************************
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC
 * LICENSE ("AGREEMENT"). [Eclipse Public License - v 1.0]
 * 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 * 
 * You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package h;

import smetana.core.UnsupportedStructAndPtr;
import smetana.core.__ptr__;
import smetana.core.amiga.StarStruct;

public class ST_Agdatadict_s extends UnsupportedStructAndPtr {

	private final ST_Agrec_s h = new ST_Agrec_s(this); /* installed in list of graph recs */
	public ST_dt_s dict_n;
	public ST_dt_s dict_e;
	public ST_dt_s dict_g;

	private final StarStruct parent;

	public ST_Agdatadict_s() {
		this(null);
	}

	public ST_Agdatadict_s(StarStruct parent) {
		this.parent = parent;
	}

	public StarStruct getParent() {
		return parent;
	}

	@Override
	public __ptr__ setPtr(String fieldName, __ptr__ newData) {
		if (fieldName.equals("dict.n")) {
			this.dict_n = (ST_dt_s) newData;
			return dict_n;
		}
		if (fieldName.equals("dict.e")) {
			this.dict_e = (ST_dt_s) newData;
			return dict_e;
		}
		if (fieldName.equals("dict.g")) {
			this.dict_g = (ST_dt_s) newData;
			return dict_g;
		}
		return super.setPtr(fieldName, newData);
	}

	@Override
	public __ptr__ castTo(Class dest) {
		if (dest == ST_Agrec_s.class) {
			return h;
		}
		return super.castTo(dest);
	}

	// public static List<String> DEFINITION = Arrays.asList(
	// "struct Agdatadict_s",
	// "{",
	// "Agrec_t h",
	// "struct",
	// "{",
	// "Dict_t *n, *e, *g",
	// "}",
	// "dict",
	// "}");
}

// struct Agdatadict_s { /* set of dictionaries per graph */
// Agrec_t h; /* installed in list of graph recs */
// struct {
// Dict_t *n, *e, *g;
// } dict;
// };