package jupiter;

import java.util.ArrayList;
import java.util.List;

public class USP {
    private List<Instituto> institutos;
    private int numInstitutos;

    public USP() {
        this.institutos = new ArrayList<>();
        this.numInstitutos = 0;
    }

    public void setInstituto(String instituto) {
        this.institutos.add(new Instituto(instituto));
        this.numInstitutos++;
    }
    
    public Instituto getIntitutos(int i){
        return this.institutos.get(i);
    }

    public int getNumInstitutos(){
        return this.numInstitutos;
    }
}