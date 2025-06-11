package jupiter;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] agrs){
        Scanner read = new Scanner(System.in);
        USP college = Reader.readWeb(Integer.parseInt(agrs[0]));

        sumario();

        while(true){
            System.out.print("OBS: Caso queira ver de novo o \"Sumário de Comandos\" basta digitar \"sumario\";\nR: ");
            String linha = read.nextLine();

            if(linha.length()>1){
                if(linha.equals("exit")){
                    break;
                }else if(linha.equals("sumario")){
                    sumario();
                }else
                    System.out.println("Comando inválido");
            }else{
                char command = linha.toCharArray()[0];
                int limite = college.getNumInstitutos();
                String sigla;

                switch (command) {
                    case '1':
                        System.out.println("Comando 1 chamado:\n");
                        List<String> inst = college.getIntitutos();

                        for(int i =0; i<limite; i++)
                            System.out.println("  - "+inst.get(i));
                        System.out.println();

                        break;
                    case '2':
                        System.out.println("Comando 2 chamado:\n");
                        List<String> institutos = college.getIntitutos();
                    
                        for(int i=0; i<limite; i++)
                            List_cursos(college, institutos.get(i));

                        break;
                    case '3':
                        System.out.println("Comando 3 chamado:\n");
                        System.out.print("Digite a sigla do instituto (OBS: Se errar a sigla terá que chamar de novo o comando 3).\nR: ");
                        sigla = read.nextLine();
                        System.out.println("Cursos disponíveis no instituto:\n");
                        List_cursos(college, college.getSigla(sigla));

                        System.out.print("Escolha um dos cursos.\nR: ");
                        String curso = read.nextLine();
                        System.out.println();
                        
                        boolean flag = false;

                        while(!flag){
                            Curso course = college.getIntituto(college.getSigla(sigla)).getCurso(curso);
                    
                            if(course!=null){
                                System.out.println(course);
                                flag=true;
                            }
                    
                            if(!flag){
                                System.out.print("Curso não encontrado. Digite novamente o nome do curso.\nR: ");
                            }
                        }
                        
                        break;
                    case '4':
                        System.out.println("Comando 4 chamado:\n");
                        All_data_Cursos(limite, college);

                        break;
                    case '5':
                        System.out.println("Comando 5 chamado:\n");
                        System.out.print("Digite a sigla do instituto (OBS: Se errar a sigla terá que chamar de novo o comando 3).\nR: ");
                        sigla = read.nextLine();
                        System.out.println();

                        boolean achou = false;
                        System.out.print("Digite o nome da disciplina que deseja procurar.\nR: ");

                        while(!achou){
                            String disciplina = read.nextLine();
                            System.out.println();

                            achou = Data_Disciplina(disciplina, limite, college, college.getSigla(sigla));
                        }

                        break;
                    case '6':
                        System.out.println("Comando 6 chamado:\n");
                        System.out.print("Digite a sigla do instituto (OBS: Se errar a sigla terá que chamar de novo o comando 3).\nR: ");
                        sigla = read.nextLine();
                        System.out.println();

                        Data_InterCouse_Diciplinas(limite, college, college.getSigla(sigla));
                        break;
                    default:
                        System.out.println("Comando inválido");
                        break;
                }
            }
        }
        read.close();
    }

    public static void List_cursos(USP college, String instituto){
        Instituto inst = college.getIntituto(instituto);

        System.out.println(inst.getNome()+":");
        List<String> numCursos = inst.getChaves();

        for(String j : numCursos){
            System.out.println("  - "+inst.getCurso(j).getNome());
        }
        System.out.println();
    }

    public static void All_data_Cursos(int limite, USP college) {
        Set<String> cursos = new HashSet<>();
        List<String> institutos = college.getIntitutos();
    
        for (int i = 0; i < limite; i++) {
            Instituto inst = college.getIntituto(institutos.get(i));
    
            List<String> courseKeys = inst.getChaves();
    
            for (String j : courseKeys) {
                if (cursos.add(j)) {
                    System.out.println(inst.getCurso(j));
                }
            }
        }
    }

    public static boolean Data_Disciplina(String disciplina, int limite, USP college, String instituto){
        boolean flag = false;

        Instituto inst = college.getIntituto(instituto);
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

        if(!flag){
            System.out.print("Essa Disciplina não foi encontrada. Tente digitar novamente o nome da Disciplina.\nR: ");
        }else
            System.out.println("Cursos do instituto "+inst.getNome()+" que a disciplina aparece: " + college.getIntituto(instituto).getDisciplinasMap().get(disciplina)+"\n");

    return flag;
    }

    public static void Data_InterCouse_Diciplinas(int limite, USP college, String instituto){
        Instituto inst = college.getIntituto(instituto);

        if(inst!=null){
            Map<String, Set<String>> disciplinas = inst.getDisciplinasMap();

            for(Map.Entry<String, Set<String>> i : disciplinas.entrySet()){
                Set<String> courses = i.getValue();

                if(courses.size()>1){
                    System.out.println("Disciplina: "+ i.getKey() + "\n"+"Aparece nos cursos: " + courses+"\n");
                }
            }
            System.out.println();
        }else{
            System.out.println("Instituto não encontrado.\n");
        }
    }

    public static void sumario(){
        System.out.println("\n      Sumário de Comandos:\n"+
        "- 1 - Lista todos os institutos lidos (Entrada: Nenhuma)\n"+
        "- 2 - Lista todos os cursos lidos separados de acordo com cada instituto (Entrada: Nenhuma)\n"+
        "- 3 - Imprime os dados de um curso específico de um instituto específico (Entrada: 1° Sigla (O que está escrito dentro dos \"( )\")) de um instituto; 2° Nome de um curso do instituto)"+"\n"+
        "- 4 - Imprime os dados de todos os cursos lidos (Entrada: Nenhuma)"+"\n"+
        "- 5 - Imprime todas as aparições de uma disciplina específica em todos os cursos de um instituto específico (Entrada: 1° Sigla de um instituto; 2° Nome de uma disciplina"+"\n"+
        "       OBS: Para destacar \"Cálculo I\" é diferente de \"Cálculo I (semipresencial)\""+"\n"+
        "- 6 - Imprime todas as disciplinas que estão presentes em mais do que um curso em um instituto específico (Entrada: 1° Sigla de um instituto)\n\n"+
        "- exit - Termina a execução do programa\n");
    }
}