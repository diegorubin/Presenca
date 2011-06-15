/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

import junit.framework.TestCase;
import java.util.ArrayList;

/**
 *
 * @author drubin
 */
public class ListaChamadaTest extends TestCase {
    
    public ListaChamadaTest(String testName) {
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
     * Test of templateOk method, of class ListaChamada.
     */
    public void testTemplateOk() {
        ListaChamada lista = new ListaChamada("test/trabalhofinal/template.svg");
        assertTrue(lista.templateOk());

        
        lista = new ListaChamada();
        assertFalse(lista.templateOk());
        
        lista = new ListaChamada("um_arquivo_que_nunca_existira.svg");
        assertFalse(lista.templateOk());
        
        lista = new ListaChamada();
        lista.setTemplate("test/trabalhofinal/template.svg");
        assertTrue(lista.templateOk());
    }
    
    public void testSaveOutput() {
        ListaChamada lista = new ListaChamada("test/trabalhofinal/template.svg");
        lista.setOutput("test/trabalhofinal/lista.svg");
        
        Professor professor = new Professor();
        professor.setNome("Fernando da Silva");
        lista.setProfessor(professor);
        
        ArrayList<Aluno> alguns_alunos = new ArrayList<Aluno>();
        
        Aluno joao = new Aluno();
        joao.setNome("João dos Santos");
        alguns_alunos.add(joao);
        
        Aluno maria = new Aluno();
        maria.setNome("Maria Souza");
        alguns_alunos.add(maria);
        
        Aluno diego = new Aluno();
        diego.setNome("Diego Rubin");
        alguns_alunos.add(diego);
        
        lista.setAlunos(alguns_alunos);
        
        assertTrue(lista.saveOutput());
    }
}
