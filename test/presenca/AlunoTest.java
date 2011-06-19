/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author diego
 */
public class AlunoTest extends TestCase {
    
    public AlunoTest(String testName) {
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

    public void testSave() {
        Aluno aluno = new Aluno();
        aluno.setNome("Joãozinho");
        assertEquals(aluno.save(true), true);
    }
    
    public void testFind() {
        Aluno aluno = new Aluno(1);
        assertEquals(aluno.getNome(),"Joãozinho");
        
        aluno.setNome("Mariazinha");
        assertEquals(aluno.save(), true);
        
        aluno = new Aluno(1);
        assertEquals(aluno.getNome(),"Mariazinha");
        
    }

    public void testEndereco() {
        Aluno aluno = new Aluno();
        aluno.getEndereco().setBairro("Rua dos bobos");
        aluno.setNome("Morador da rua do bobos");
        
        assertEquals(aluno.save(true), true);
        
        aluno = new Aluno(2);
        assertEquals(aluno.getEndereco().getBairro(),"Rua dos bobos");
    }
   
    public void testDestroy() {
        Aluno aluno = new Aluno(1);
        assertEquals(aluno.destroy(),true);
    }
}
