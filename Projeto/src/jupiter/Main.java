package jupiter;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] agrs){
        Scanner read = new Scanner(System.in);
        USP college = new USP();

        while(true){
            String linha = read.nextLine();

            if(linha.length()>1){
                if(linha.equals("exit")){
                    break;
                }else{
                    System.out.println("Comando inválido");
                }
            }else{
                char command = linha.toCharArray()[0];
                int limite = college.getNumInstitutos();

                switch (command) {
                    case '1':
                        for(int i=0; i<limite; i++){
                            Instituto inst = college.getIntitutos(i);

                            System.out.println(inst);
                            List<String> numCursos = inst.getChaves();

                            for(String j : numCursos){
                                System.out.println(inst.getCurso(j).getNome());
                            }
                        }

                        break;
                    case '2':
                        String curso = read.nextLine();
                        boolean flag = false;
                        
                        for(int i = 0; i<limite; i++){
                            Curso course = college.getIntitutos(i).getCurso(curso);

                            if(course!=null){
                                System.out.println(course);
                                flag=true;
                            }
                        }

                        if(!flag){
                            System.out.println("Esse curso não existe existe na USP");
                        }
                        
                        break;
                    case '3':

                        break;
                    case '4':

                        break;
                    case '5':

                        break;
                    default:
                        break;
                }
            }
        }
        read.close();
    }

    public static boolean busca_bin(String[] vetor, String chave){
        int i = 0;
        int j = vetor.length-1;
        int meio;

        while(i<=j){
            int comparacao = vetor[meio].compareToIgnoreCase(chave);
            meio = i+j/2;

            if(comparacao<0){
                i=meio;
            }else if(comparacao>0){
                j=meio;
            }else
                return true;
        }

    return false;
    }
}
