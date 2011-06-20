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
     private Integer idAluno;
     private Integer idTurma;
    
    public AlunoTurma(){
        
    }
    
    public AlunoTurma(Integer id){
        super(id);
    }
    
    public void setIdTurma(Integer idTurma){
        this.idTurma = idTurma;
    }
    
    public Integer getIdTurma(){
        return idTurma;
    }
    
    public void setIdAluno(Integer idAluno){
        this.idAluno = idAluno;
    }
    
    public Integer getIdAluno(){
        return idAluno;
    }
    
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("idAluno");
        attributes.add("idTurma");
        
        return attributes;
    }
}
