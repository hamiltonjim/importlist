/*
 * Import List - http://my-flow.github.com/importlist/
 * Copyright (C) 2011-2012 Florian J. Breunig
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.moneydance.modules.features.importlist.table;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.moneydance.modules.features.importlist.view.OddColorSchemeHelper;
import com.moneydance.util.CustomDateFormat;

/**
 * @author Florian J. Breunig
 */
public final class LabelModifiedRendererTest {

    private LabelModifiedRenderer labelModifiedRenderer;

    @Before
    public void setUp() {
        this.labelModifiedRenderer = new LabelModifiedRenderer(
                new OddColorSchemeHelper(
                        Color.white,
                        Color.white,
                        Color.white),
                new CustomDateFormat(""),
                new SimpleDateFormat());
    }

    @Test
    public void
    testGetTableCellRendererComponentJTableObjectBooleanBooleanIntInt() {
        Assert.assertNotNull(
                "component must not be null",
                this.labelModifiedRenderer.getTableCellRendererComponent(
                        new JTable(),
                        null, // null value
                        false,
                        false,
                        0,
                        0));

        Assert.assertNotNull(
                "component must not be null",
                this.labelModifiedRenderer.getTableCellRendererComponent(
                        new JTable(),
                        new Date(), // date value
                        false,
                        false,
                        0,
                        0));

        Assert.assertNotNull(
                "component must not be null",
                this.labelModifiedRenderer.getTableCellRendererComponent(
                        new JTable(),
                        "", // string value
                        false,
                        false,
                        0,
                        0));
    }

    @Test
    public void testSetDateFormatter() {
        this.labelModifiedRenderer.setDateFormatter(new CustomDateFormat(""));
    }

    @Test
    public void testSetTimeFormatter() {
        this.labelModifiedRenderer.setTimeFormatter(new SimpleDateFormat());
    }

}
