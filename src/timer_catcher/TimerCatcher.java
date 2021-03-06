package timer_catcher;

import timer_catcher.exeptions.FimNaoIniciadoExeption;
import timer_catcher.exeptions.InicioMaiorQueFimExeption;
import timer_catcher.exeptions.InicioNaoIniciadoExeption;

import java.util.LinkedList;
import java.util.List;

public class TimerCatcher {

    private static float inicioDaOperacao=-1;
    private static float fimDaOperacao=-1;
    private static List<Float> times=new LinkedList<Float>();
    //Setters and Getters

    public static float getInicioDaOperacao() {
       try {
           if (inicioDaOperacao == -1)
               throw new InicioNaoIniciadoExeption();
           else
               return inicioDaOperacao;
       }catch (Exception e) {
            e.printStackTrace();
            return -1;
       }
    }
    public static void setInicioDaOperacao() {
        inicioDaOperacao = System.nanoTime();
    }

    public static float getFimDaOperacao() {
        try {
            if(fimDaOperacao==-1){
                throw new FimNaoIniciadoExeption();
            }
            else if(inicioDaOperacao>fimDaOperacao){
                throw new InicioMaiorQueFimExeption();
            }
            else
            return fimDaOperacao;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    public static void setFimDaOperacao(){
        fimDaOperacao = System.nanoTime();
    }

    //Functions

    private static void zerarTempo(){
        inicioDaOperacao=-1;
        fimDaOperacao=-1;
    }

    public static float tempoDeExecucaoDaOperacao(){
        Float cronometroResultante = getFimDaOperacao() - getInicioDaOperacao();
        zerarTempo();
        times.add(cronometroResultante);
        return  cronometroResultante;
    }

    public static float tempoDeExecucaoMedia(){
        float soma = 0;
        float timeMedia = 0;
        for (int i=0; i < times.size();i++){
            soma += times.get(i);
        }
        timeMedia = soma/times.size();
        times.clear();
        return timeMedia;
    }
}

