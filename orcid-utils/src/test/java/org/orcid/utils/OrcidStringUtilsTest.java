/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * User: Declan Newman (declan) Date: 10/02/2012
 * </p>
 */
public class OrcidStringUtilsTest {

    @Test
    public void testIsValidOrcid() {
        assertTrue(OrcidStringUtils.isValidOrcid("4444-4444-4444-4446"));        
    }
    
    @Test
    public void testStripHtml() {
        //Should be the same after stripping
        String _1 = "Test's";
        String _2 = "Test\"s";
        String _3 = "Test > s";
        String _4 = "Test < s";
        String _5 = "Test&s";
        
        assertEquals(_1, OrcidStringUtils.stripHtml(_1));
        assertEquals(_2, OrcidStringUtils.stripHtml(_2));
        assertEquals(_3, OrcidStringUtils.stripHtml(_3));
        assertEquals(_4, OrcidStringUtils.stripHtml(_4));
        assertEquals(_5, OrcidStringUtils.stripHtml(_5));
        
        //Html should be removed
        String html_1 = "<a href=\"orcid.org\">This is a link</a>";
        String html_2 = "<a href=\"orcid.org\">This is a link's</a>";
        String html_3 = "<a href=\"orcid.org\">This is a link\"s</a>";
        String html_4 = "<a href=\"orcid.org\">This is a link&s</a>";
        String html_5 = "<a href=\"orcid.org\">This is a link > s</a>";
        String html_6 = "<a href=\"orcid.org\">This is a link < s</a>";
        
        assertEquals("This is a link", OrcidStringUtils.stripHtml(html_1));
        assertEquals("This is a link's", OrcidStringUtils.stripHtml(html_2));
        assertEquals("This is a link\"s", OrcidStringUtils.stripHtml(html_3));
        assertEquals("This is a link&s", OrcidStringUtils.stripHtml(html_4));
        assertEquals("This is a link > s", OrcidStringUtils.stripHtml(html_5));
        assertEquals("This is a link < s", OrcidStringUtils.stripHtml(html_6));
    }
    
    @Test
    public void testHasHtml() {
        assertTrue(OrcidStringUtils.hasHtml("<a>hello</a>"));
        assertTrue(OrcidStringUtils.hasHtml("This is a test <span>"));
        assertTrue(OrcidStringUtils.hasHtml("<this is a test>"));
        assertTrue(OrcidStringUtils.hasHtml("This is <script>another</script> test"));
        assertTrue(OrcidStringUtils.hasHtml("This is a <div>test</div>"));        
        assertTrue(OrcidStringUtils.hasHtml("<div>This is a test</div>"));
        
        assertFalse(OrcidStringUtils.hasHtml("This is a test"));
        assertFalse(OrcidStringUtils.hasHtml("This is a test's"));
        assertFalse(OrcidStringUtils.hasHtml("This < is a test >"));
        assertFalse(OrcidStringUtils.hasHtml("This \"is a test\""));
        assertFalse(OrcidStringUtils.hasHtml("This \" is a test \""));
        assertFalse(OrcidStringUtils.hasHtml("This&this are tests"));
        assertFalse(OrcidStringUtils.hasHtml("Users's test"));
    }
}
