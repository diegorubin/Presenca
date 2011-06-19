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
    private Integer semestre;
    private Integer ano;
    private Integer professorId;
    
    private Professor professor;
    
    public Turma(){
        professor = new Professor();
    }
    
    public Turma(Integer id){
        super(id);
        professor = new Professor(professorId);
    }
    
    public void setDisciplinaId(Integer disciplinaId){
        this.disciplinaId = disciplinaId;
    }
    
    public Integer getDisciplinaId(){
        return disciplinaId;
    }
    
    public void setAno(Integer ano){
        this.ano = ano;
    }
    
    public Integer getAno(){
        return ano;
    }
    
    public void setSemestre(Integer semestre){
        this.semestre = semestre;
    }
    
    public Integer getSemestre(){
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
}
