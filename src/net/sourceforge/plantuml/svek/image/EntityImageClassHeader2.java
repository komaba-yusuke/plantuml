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
package net.sourceforge.plantuml.svek.image;

import h.tedge_t;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.SkinParamUtils;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.EntityPortion;
import net.sourceforge.plantuml.cucadiagram.ILeaf;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.cucadiagram.PortionShower;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.CircledCharacter;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockGeneric;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.HeaderLayout;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageClassHeader2 extends AbstractEntityImage {

	final private HeaderLayout headerLayout;

	public EntityImageClassHeader2(ILeaf entity, ISkinParam skinParam, PortionShower portionShower) {
		super(entity, skinParam);

		final boolean italic = entity.getLeafType() == LeafType.ABSTRACT_CLASS
				|| entity.getLeafType() == LeafType.INTERFACE;

		final Stereotype stereotype = entity.getStereotype();
		final boolean displayGenericWithOldFashion = skinParam.displayGenericWithOldFashion();
		final String generic = displayGenericWithOldFashion ? null : entity.getGeneric();
		FontConfiguration fontConfigurationName = new FontConfiguration(getSkinParam(), FontParam.CLASS, stereotype);
		if (italic) {
			fontConfigurationName = fontConfigurationName.italic();
		}
		Display display = entity.getDisplay();
		if (displayGenericWithOldFashion && entity.getGeneric() != null) {
			display = display.addGeneric(entity.getGeneric());
		}
		TextBlock name = display.createWithNiceCreoleMode(fontConfigurationName, HorizontalAlignment.CENTER, skinParam);
		final VisibilityModifier modifier = entity.getVisibilityModifier();
		if (modifier == null) {
			name = TextBlockUtils.withMargin(name, 3, 3, 0, 0);
		} else {
			final Rose rose = new Rose();
			final HtmlColor back = rose.getHtmlColor(skinParam, modifier.getBackground());
			final HtmlColor fore = rose.getHtmlColor(skinParam, modifier.getForeground());

			final TextBlock uBlock = modifier.getUBlock(skinParam.classAttributeIconSize(), fore, back, false);
			name = TextBlockUtils.mergeLR(uBlock, name, VerticalAlignment.CENTER);
			name = TextBlockUtils.withMargin(name, 3, 3, 0, 0);
		}

		final TextBlock stereo;
		if (stereotype == null || stereotype.getLabel(false) == null
				|| portionShower.showPortion(EntityPortion.STEREOTYPE, entity) == false) {
			stereo = null;
		} else {
			stereo = TextBlockUtils.withMargin(
					Display.create(stereotype.getLabels(skinParam.useGuillemet())).create(
							new FontConfiguration(getSkinParam(), FontParam.CLASS_STEREOTYPE, stereotype),
							HorizontalAlignment.CENTER, skinParam), 1, 0);
		}

		TextBlock genericBlock;
		if (generic == null) {
			genericBlock = null;
		} else {
			genericBlock = Display.getWithNewlines(generic).create(
					new FontConfiguration(getSkinParam(), FontParam.CLASS_STEREOTYPE, stereotype),
					HorizontalAlignment.CENTER, skinParam);
			genericBlock = TextBlockUtils.withMargin(genericBlock, 1, 1);
			final HtmlColor classBackground = SkinParamUtils
					.getColor(getSkinParam(), stereotype, ColorParam.background);

			final HtmlColor classBorder = SkinParamUtils.getFontColor(getSkinParam(), FontParam.CLASS_STEREOTYPE,
					stereotype);
			genericBlock = new TextBlockGeneric(genericBlock, classBackground, classBorder);
			genericBlock = TextBlockUtils.withMargin(genericBlock, 1, 1);
		}

		final TextBlock circledCharacter;
		if (portionShower.showPortion(EntityPortion.CIRCLED_CHARACTER, (ILeaf) getEntity())) {
			circledCharacter = TextBlockUtils.withMargin(getCircledCharacter(entity, skinParam), 4, 0, 5, 5);
		} else {
			circledCharacter = null;
		}
		this.headerLayout = new HeaderLayout(circledCharacter, stereo, name, genericBlock);
	}

	private TextBlock getCircledCharacter(ILeaf entity, ISkinParam skinParam) {
		final Stereotype stereotype = entity.getStereotype();
		if (stereotype != null && stereotype.getSprite() != null) {
			return skinParam.getSprite(stereotype.getSprite()).asTextBlock(stereotype.getHtmlColor(), 1);
		}
		final UFont font = SkinParamUtils.getFont(getSkinParam(), FontParam.CIRCLED_CHARACTER, null);
		final HtmlColor classBorder = SkinParamUtils.getColor(getSkinParam(), stereotype, ColorParam.classBorder);
		final HtmlColor fontColor = SkinParamUtils.getFontColor(getSkinParam(), FontParam.CIRCLED_CHARACTER, null);
		if (stereotype != null && stereotype.getCharacter() != 0) {
			return new CircledCharacter(stereotype.getCharacter(), getSkinParam().getCircledCharacterRadius(), font,
					stereotype.getHtmlColor(), classBorder, fontColor);
		}
		final LeafType leafType = entity.getLeafType();
		final HtmlColor spotBackColor = SkinParamUtils.getColor(getSkinParam(), stereotype, spotBackground(leafType));
		HtmlColor spotBorder = SkinParamUtils.getColor(getSkinParam(), stereotype, spotBorder(leafType));
		if (spotBorder == null) {
			spotBorder = classBorder;
		}
		char circledChar = 0;
		if (stereotype != null) {
			circledChar = getSkinParam().getCircledCharacter(stereotype);
		}
		if (circledChar == 0) {
			circledChar = getCircledChar(leafType);
		}
		return new CircledCharacter(circledChar, getSkinParam().getCircledCharacterRadius(), font, spotBackColor,
				spotBorder, fontColor);
	}

	private ColorParam spotBackground(LeafType leafType) {
		switch (leafType) {
		case ANNOTATION:
			return ColorParam.stereotypeNBackground;
		case ABSTRACT_CLASS:
			return ColorParam.stereotypeABackground;
		case CLASS:
			return ColorParam.stereotypeCBackground;
		case INTERFACE:
			return ColorParam.stereotypeIBackground;
		case ENUM:
			return ColorParam.stereotypeEBackground;
		case ENTITY:
			return ColorParam.stereotypeCBackground;
		}
		assert false;
		return null;
	}

	private ColorParam spotBorder(LeafType leafType) {
		switch (leafType) {
		case ANNOTATION:
			return ColorParam.stereotypeNBorder;
		case ABSTRACT_CLASS:
			return ColorParam.stereotypeABorder;
		case CLASS:
			return ColorParam.stereotypeCBorder;
		case INTERFACE:
			return ColorParam.stereotypeIBorder;
		case ENUM:
			return ColorParam.stereotypeEBorder;
		case ENTITY:
			return ColorParam.stereotypeCBorder;
		}
		assert false;
		return null;
	}

	private char getCircledChar(LeafType leafType) {
		switch (leafType) {
		case ANNOTATION:
			return '@';
		case ABSTRACT_CLASS:
			return 'A';
		case CLASS:
			return 'C';
		case INTERFACE:
			return 'I';
		case ENUM:
			return 'E';
		case ENTITY:
			return 'E';
		}
		assert false;
		return '?';
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return headerLayout.getDimension(stringBounder);
	}

	final public void drawU(UGraphic ug) {
		throw new UnsupportedOperationException();
	}

	public void drawU(UGraphic ug, double width, double height) {
		headerLayout.drawU(ug, width, height);
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

}
