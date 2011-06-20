/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class AlunoTurma extends Model{
     private String nome;
    
    public AlunoTurma(){
        
    }
    
    public AlunoTurma(Integer id){
        super(id);
    }
    
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("idAluno");
        attributes.add("idTurma");
        
        return attributes;
    }
}
