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
 */
package net.sourceforge.plantuml.ugraphic.tikz;

import net.sourceforge.plantuml.creole.AtomText;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.FontStyle;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.tikz.TikzGraphics;
import net.sourceforge.plantuml.ugraphic.ColorMapper;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UParam;
import net.sourceforge.plantuml.ugraphic.UShape;

public class DriverAtomTextTikz implements UDriver<TikzGraphics> {

	public void draw(UShape shape, double x, double y, ColorMapper mapper, UParam param, TikzGraphics tikz) {
		final AtomText text = (AtomText) shape;
		final FontConfiguration fontConfiguration = text.getFontConfiguration();
		final UFont font = fontConfiguration.getFont();
		final HtmlColor col = fontConfiguration.getColor();
		tikz.setStrokeColor(mapper.getMappedColor(col));
		final boolean underline = fontConfiguration.containsStyle(FontStyle.UNDERLINE);
		final boolean italic = font.isItalic();
		final boolean bold = font.isBold();
		tikz.text(x, y, text.getText(), underline, italic, bold);

	}

}
