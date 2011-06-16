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
public class Professor extends Model{
    private String nome;
    private Endereco endereco;
    
    public Professor(){
    }
    
    public Professor(Integer id){
        super(id);
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
    
    @Override
    protected boolean afterSave(){
        if(!endereco.save())
            return false;
        
        return true;
    }
    
}
