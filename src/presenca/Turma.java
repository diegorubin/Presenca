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
public class Turma extends Model{
    private Integer disciplinaId;
    private String semestre;
    private String ano;
    private Integer professorId;
    
    private Professor professor;
    private Disciplina disciplina;
    
    public Turma(){
    }
    
    public Turma(Integer id){
        super(id);
        professor = new Professor(professorId);
        disciplina = new Disciplina(disciplinaId);
    }
    
    public void setDisciplinaId(Integer disciplinaId){
        this.disciplinaId = disciplinaId;
        disciplina = new Disciplina(disciplinaId);
    }
    
    public Integer getDisciplinaId(){
        return disciplinaId;
    }
    
    public void setAno(String ano){
        this.ano = ano;
    }
    
    public String getAno(){
        return ano;
    }
    
    public void setSemestre(String semestre){
        this.semestre = semestre;
    }
    
    public String getSemestre(){
        return semestre;
    }
    
    public Integer getProfessorId(){
        return professorId;
    }
    
    public void setProfessorId(Integer professorId){
        this.professorId = professorId;
        professor = new Professor(professorId);
    }
    
    public Professor getProfessor(){
        return professor;
    }
    
    public Disciplina getDisciplina(){
        return disciplina;
    }
    
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("disciplinaId");
        attributes.add("professorId");
        attributes.add("semestre");
        attributes.add("ano");

        return attributes;
    }
}
