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
public class ProfessorTest extends TestCase {
    
    public ProfessorTest(String testName) {
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
     * Test of save in database, of class Professor.
     */
    public void testSave() {
        Professor professor = new Professor();
        professor.setNome("João da Silva");
        assertEquals(professor.save(true), true);
    }
    
    /**
     * Test of find in database, of class Professor.
     */
    public void testFind() {
        Professor professor = new Professor(1);
        assertEquals(professor.getNome(),"João da Silva");
        
        professor.setNome("Maria da Silva");
        assertEquals(professor.save(), true);
        
        professor = new Professor(1);
        assertEquals(professor.getNome(),"Maria da Silva");
        
    }
    
    /**
     * Test of destroy of database, of class Professor.
     */
    public void testDestroy() {
        Professor professor = new Professor(1);
        assertEquals(professor.destroy(),true);
    }
}
