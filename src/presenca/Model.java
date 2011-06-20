package presenca;

import java.sql.*;
import java.util.ArrayList;
import java.lang.reflect.Method;

/*
 * Eu sei que esta lógica não era para estar aqui
 * Se der tempo eu penso em algo melhor, mas por enquanto ...
 */
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public abstract class Model {
    private ArrayList<String> Attributes = new ArrayList<String>();
    protected Integer id;
    
    private String join = "";

    static Connection con;
    public Model(){
        getRelationships();
    }
    
    public Model(Integer id){
        this();
        loadModel("id = " + id);
    }
    
    public Model(String classe, Integer id){
        this();
        loadModel("classeObjeto = '" + classe + "' AND idObjeto = " + id);
    }
    
    public Model(String conditions){
        this();
        loadModel(conditions);
    }
    
    private void getRelationships(){
        for(String attr: getAttributes()){
            try{
                Class.forName(attr);
                String tableName = attr.replaceFirst("\\w+\\.", "");
                join += "LEFT OUTER JOIN "+ 
                        tableName +
                        " ON (" +
                        tableName + ".idObjeto = " + this.getClass().getSimpleName() + ".id AND " +
                        tableName + ".classeObjeto = " + this.getClass().getSimpleName() +
                        ")";
            }catch(ClassNotFoundException e){
                Attributes.add(attr);
            }
        }
    }
    
    private void loadModel(String conditions){
        Statement statement;
        ResultSet result;
        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName() + 
                       " WHERE " + conditions + ";";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(query);
            result.first();

            this.id = result.getInt("id");
            for(String attribute: Attributes){
                try{
                    Method setMethod;
                    Method getMethod = this.getClass().getMethod("get" + Utils.titlelize(attribute));
                    Class returnType = getMethod.getReturnType();
                    
                    if(returnType.getSimpleName().equals("Integer")){
                        setMethod = this.getClass().getMethod("set" + Utils.titlelize(attribute), Integer.class);
                        setMethod.invoke(this,result.getInt(attribute));
                    }else{
                        setMethod = this.getClass().getMethod("set" + Utils.titlelize(attribute), String.class);
                        setMethod.invoke(this,result.getString(attribute));
                    }
                }catch(NoSuchMethodException e){
                    System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                }catch(Exception e){
                    System.err.println("O método '" + attribute + "' não é publico");
                }
            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }
        Fechar();

    }
    
    public ArrayList<String> listAttribute(String attribute, String conditions){
        ArrayList<String> contents = new ArrayList<String>();
        Statement statement;
        ResultSet result;

        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName();
        if(!conditions.isEmpty()){
            query += " WHERE " + conditions;
        }
        
        query += ";";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(query);
            
            int i = 0;
            while(result.next()){
                    try{
                        contents.add(result.getString(attribute));
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("O método '" + attribute + "' não é publico");
                    }

            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }
        Fechar();
        
        return contents;
    }
    
    public void toTable(JTable table, String conditions){
       
        Statement statement;
        ResultSet result;

        DefaultTableModel mdlModel = (DefaultTableModel)table.getModel();
        
        int i;
        for(i = mdlModel.getRowCount() - 1; i >= 0; i-- ){
            mdlModel.removeRow(i);
        }
        
        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName();
        if(!conditions.isEmpty()){
            query += " WHERE " + conditions;
        }
        
        query += ";";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(query);
            
            Object contents[] = new Object[Attributes.size()+1];
            
            while(result.next()){
                i=0;
                contents[i++] = result.getString("id");
                for(String attribute: Attributes){
                    try{
                        contents[i++] = result.getString(attribute);
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                }
                mdlModel.addRow(contents);
            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }
        Fechar();
        
    }

    public boolean save(){
        return save(false);
    }
    
    public boolean save(boolean new_record){
        Statement statement;

        String query ="";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            if(new_record){
                String values = "";
                String fields = "";
                
                for(String attribute: Attributes){
                    fields += attribute + ",";
                    try{
                        Method getMethod = this.getClass().getMethod("get" + Utils.titlelize(attribute));
                        Class returnType = getMethod.getReturnType();
                        
                        if(returnType.getSimpleName().equals("Integer")){
                            values += (Integer)getMethod.invoke(this) + ",";
                        }else{
                            String value = (String)getMethod.invoke(this);
                            if(value == null){
                                value = "";
                            }
                            values += "'" + value + "',";
                        }
                    }catch(NoSuchMethodException e){
                        System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                    
                }
                fields = "(" + Utils.chop(fields) + ")";
                values = Utils.chop(values);
                query = "INSERT INTO " + this.getClass().getSimpleName() + " " + fields +" VALUES ("+ values +");";

                statement.execute(query);
                query = "select id from " + this.getClass().getSimpleName() + " ORDER BY id desc";
                ResultSet result = statement.executeQuery(query);
                result.first();
                id = result.getInt("id");
            }else{
                String update = "";
                for(String attribute: Attributes){
                    try{
                        Method getMethod = this.getClass().getMethod("get" + Utils.titlelize(attribute));
                        Class returnType = getMethod.getReturnType();
                        if(returnType.getSimpleName().equals("Integer")){
                            update += attribute + " = '" + (Integer)getMethod.invoke(this) + "',";
                        }else{
                            update += attribute + " = '" + (String)getMethod.invoke(this) + "',";
                        }
                    }catch(NoSuchMethodException e){
                        System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                    
                }
                query = "UPDATE " + 
                        this.getClass().getSimpleName() + 
                        " SET " + 
                        Utils.chop(update) + 
                        "WHERE id = " + id +";";
                statement.executeUpdate(query);
            }
            
        }catch(SQLException e){
            System.out.println("SQL: " + query);
            System.out.println("Problema ao salvar " + this.getClass().getSimpleName());
            Fechar();
            return false;
        }
        Fechar();
        return afterSave(new_record);
    }

    public boolean destroy(){
        Statement statement;

        String query = "";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_READ_ONLY);

            query = "DELETE FROM " + this.getClass().getSimpleName() + " WHERE id = " + id.toString() + ";";
            statement.executeUpdate(query);
            

        }catch(SQLException e){
            System.out.println("SQL: " + query);
            System.out.println("Problema na query delete");
            Fechar();
            return false;
        }
        Fechar();
        return true;
    }
    
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        
        return attributes;
    }
    
    protected boolean afterSave(boolean new_record){
        return true;
    }

    private void Conecta(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Presenca","root","root");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("Não foi possivel conectar com o banco");
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }
    }

    private void Fechar(){
        try{
            con.close();
        }catch(SQLException ex){
            System.out.println("Erro ao encerrar o banco");
        }
    }
}