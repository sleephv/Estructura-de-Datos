import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
public class Voto{
    private int id;
    private int votanteID;
    private int candidatoID;
    private String timestamp;
    public Voto(int id, int votanteID, int candidatoID, String timestamp){
        this.id = id;
        this.votanteID = votanteID;
        this.candidatoID = candidatoID;
        this.timestamp = timestamp;
    }
    public int getID(){
        return id;
    }
    public int getVotanteID(){
        return votanteID;
    }
    public int getCandidatoID(){
        return candidatoID;
    }
    public String getTimestamp(){
        return timestamp;
    }
    public void setID(int id){
        this.id = id;
    }
    public void setVotanteID(int votanteID){
        this.votanteID = votanteID;
    }
    public void setCandidatoID(int candidatoID){
        this.candidatoID = candidatoID;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
}