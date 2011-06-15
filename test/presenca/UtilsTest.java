/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

import junit.framework.TestCase;

/**
 *
 * @author drubin
 */
public class UtilsTest extends TestCase {
    
    public UtilsTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of titlelize method, of class Utils.
     */
    public void testTitlelize() {
        assertEquals(Utils.titlelize("name"), "Name");
    }
    
    /**
     * Test of chop method, of class Utils.
     */
    public void testChop() {
        assertEquals(Utils.chop("name,"), "name");
    }
}
