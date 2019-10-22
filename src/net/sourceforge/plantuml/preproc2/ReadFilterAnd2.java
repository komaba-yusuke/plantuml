/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.preproc2;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.preproc.ReadLine;

public class ReadFilterAnd2 implements ReadFilter {

	private final Collection<ReadFilter> all = new ArrayList<ReadFilter>();

	public void add(ReadFilter filter) {
		all.add(filter);
	}

	public ReadLine applyFilter(ReadLine current) {
		for (ReadFilter f : all) {
			current = f.applyFilter(current);
		}
		return current;
		// if (filter3 == null && filter4 == null) {
		// return filter2.applyFilter(filter1.applyFilter(source));
		// }
		// if (filter4 == null) {
		// return filter3.applyFilter(filter2.applyFilter(filter1.applyFilter(source)));
		// }
		// return filter4.applyFilter(filter3.applyFilter(filter2.applyFilter(filter1.applyFilter(source))));
	}

}
