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
public class Disciplina extends Model{
    private String nome;
    
    public Disciplina(){
        
    }
    
    public Disciplina(Integer id){
        super(id);
    }
    
    public Disciplina(String conditions){
        super(conditions);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
        
       
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("nome");
        
        return attributes;
    }

}
