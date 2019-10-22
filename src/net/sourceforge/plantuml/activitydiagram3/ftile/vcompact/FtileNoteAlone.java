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
package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.awt.geom.Dimension2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.CreoleParser;
import net.sourceforge.plantuml.creole.Sheet;
import net.sourceforge.plantuml.creole.SheetBlock1;
import net.sourceforge.plantuml.creole.SheetBlock2;
import net.sourceforge.plantuml.creole.Stencil;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.svek.image.Opale;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UStroke;

public class FtileNoteAlone extends AbstractFtile implements Stencil {

	private final Opale opale;
	private final boolean withOutPoint;
	private final Swimlane swimlane;
	
	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.emptyList();
	}

	public Set<Swimlane> getSwimlanes() {
		if (swimlane == null) {
			return Collections.emptySet();
		}
		return Collections.singleton(swimlane);
	}

	public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	public Swimlane getSwimlaneOut() {
		return swimlane;
	}

	public FtileNoteAlone(boolean shadow, Display note, ISkinParam skinParam, boolean withOutPoint, Swimlane swimlane) {
		super(skinParam);
		this.swimlane = swimlane;
		this.withOutPoint = withOutPoint;
		final Rose rose = new Rose();

		final HtmlColor noteBackgroundColor = rose.getHtmlColor(skinParam, ColorParam.noteBackground);
		final HtmlColor borderColor = rose.getHtmlColor(skinParam, ColorParam.noteBorder);

		final FontConfiguration fc = new FontConfiguration(skinParam, FontParam.NOTE, null);

		final Sheet sheet = new CreoleParser(fc, skinParam.getDefaultTextAlignment(HorizontalAlignment.LEFT),
				skinParam, CreoleMode.FULL).createSheet(note);
		final TextBlock text = new SheetBlock2(new SheetBlock1(sheet, LineBreakStrategy.NONE, skinParam.getPadding()), this, new UStroke(1));
		opale = new Opale(borderColor, noteBackgroundColor, text, skinParam.shadowing(null), false);

	}

	public void drawU(UGraphic ug) {
		opale.drawU(ug);
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final Dimension2D dimTotal = calculateDimensionInternal(stringBounder);
		if (withOutPoint) {
			return new FtileGeometry(dimTotal, dimTotal.getWidth() / 2, 0, dimTotal.getHeight());
		}
		return new FtileGeometry(dimTotal, dimTotal.getWidth() / 2, 0);
	}

	private Dimension2D calculateDimensionInternal(StringBounder stringBounder) {
		return opale.calculateDimension(stringBounder);
	}

	public double getStartingX(StringBounder stringBounder, double y) {
		return -opale.getMarginX1();
	}

	public double getEndingX(StringBounder stringBounder, double y) {
		return opale.calculateDimension(stringBounder).getWidth() - opale.getMarginX1();
	}

}
