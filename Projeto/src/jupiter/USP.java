package jupiter;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class USP {
    private Map<String,Instituto> institutos;
    private Map<String, String> Siglas;
    private int numInstitutos;

    public USP() {
        this.institutos = new HashMap<>();
        this.Siglas= new HashMap<>();
        this.numInstitutos = 0;
    }

    public void setInstituto(String instituto) {
        String aux="";
        this.institutos.put(instituto, new Instituto(instituto)); 

        boolean flag = false;
        char[] array = instituto.toCharArray();

        for(int i = 0; i<array.length; i++){
            if(array[i]=='('){
                flag=!flag;
                i++;
            }

            if(flag){
                if(array[i+1]==')'){
                    break;
                }else
                    aux+=array[i];
            }
        }

        this.Siglas.put(aux, instituto);
        this.numInstitutos++;
    }
 
    public Instituto getIntituto(String nome){
        return this.institutos.get(nome);
    }

    public List<String> getIntitutos(){
        return new ArrayList<>(this.institutos.keySet());
    }

    public int getNumInstitutos(){
        return this.numInstitutos;
    }

    public String getSigla(String sigla){
        return this.Siglas.get(sigla);
    }
}
