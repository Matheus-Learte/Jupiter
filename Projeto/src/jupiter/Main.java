package jupiter;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Selenium.org.openqa.selenium.*;

public class Main {
    public static void main(String[] agrs){
        Scanner read = new Scanner(System.in);
        USP college = readJupiter();

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
                        List_cursos(limite, college);
                        break;
                    case '2':
                        String curso = read.nextLine();

                        Curso_data(curso, limite, college);
                        break;
                    case '3':
                        All_data_Cursos(limite, college);
                        break;
                    case '4':
                        String disciplina = read.nextLine();

                        Data_Disciplina(disciplina, limite, college);
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

    public static USP readJupiter(){
        USP aux = new USP();

        return aux;
    }

    public static boolean busca_bin(List<String> vetor, String chave){
        int i = 0;
        int j = vetor.size()-1;
        int meio;

        while(i<=j){
            meio = i+j/2;
            int comparacao = vetor.get(meio).compareToIgnoreCase(chave);

            if(comparacao<0){
                i=meio;
            }else if(comparacao>0){
                j=meio;
            }else
                return true;
        }

    return false;
    }

    public static void List_cursos(int limite, USP college){
        for(int i=0; i<limite; i++){
            Instituto inst = college.getIntitutos(i);

            System.out.println(inst);
            List<String> numCursos = inst.getChaves();

            for(String j : numCursos){
                System.out.println(inst.getCurso(j).getNome());
            }
        }
    }

    public static void Curso_data(String curso, int limite, USP college){
        boolean flag = false;
        
        for(int i = 0; i<limite; i++){
            Curso course = college.getIntitutos(i).getCurso(curso);

            if(course!=null){
                System.out.println(course);
                flag=true;
                break;
            }
        }

        if(!flag){
            System.out.println("Esse curso não existe existe na USP");
        }
    }

    public static void All_data_Cursos(int limite, USP college){
        List<String> cursos = new ArrayList<>();

        for(int i=0; i<limite; i++){
            Instituto inst = college.getIntitutos(i);

            List<String>courses = inst.getChaves();

            for(String j : courses){
                if(!busca_bin(cursos, j)){
                    System.out.println(inst.getCurso(j));
                    cursos.add(j);
                }
            }
        }
    }

    public static void Data_Disciplina(String disciplina, int limite, USP college){
        boolean flag = false;

        for(int i=0; i<limite; i++){
            Instituto inst = college.getIntitutos(i);

            List<String>courses = inst.getChaves();

            for(String j: courses){
                Disciplina aux= inst.getCurso(j).getDisciplina('O', disciplina);

                if(aux!=null){
                    System.out.println(aux);

                    flag = true;
                }else{
                    aux= inst.getCurso(j).getDisciplina('E', disciplina);

                    if(aux!=null){
                        System.out.println(aux);

                        flag = true;
                    }else{
                        aux= inst.getCurso(j).getDisciplina('L', disciplina);

                        if(aux!=null){
                            System.out.println(aux);

                            flag = true;
                        }
                    }
                }
            }
        }

        if(!flag){
            System.out.println("Essa Disciplina não existe existe na USP");
        }
    }
}