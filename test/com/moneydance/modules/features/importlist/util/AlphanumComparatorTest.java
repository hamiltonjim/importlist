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

package com.moneydance.modules.features.importlist.util;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Florian J. Breunig
 */
public final class AlphanumComparatorTest {

    private Comparator<String> alphanum;

    @Before
    public void setUp() {
        this.alphanum = AlphanumComparator.ALPHANUM;
    }

    @Test
    public void testCompare() {
        String string1 = "start123-10";
        String string2 = "start123-2";
        int compare = this.alphanum.compare(string1, string2);
        Assert.assertEquals(1, compare);

        string1 = "start134start";
        string2 = "start123start";
        compare = this.alphanum.compare(string1, string2);
        Assert.assertEquals("compare result must be greater than zero",
                1, compare);

        string1 = "startabc";
        string2 = "startabc";
        compare = this.alphanum.compare(string1, string2);
        Assert.assertTrue("compare result equal to zero",
                compare == 0);
    }
}
