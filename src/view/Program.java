package view;

public class Program {
    public static void main(String[] args){
        if(args.length<=0){
            System.out.println("levels are wrong");
            return;
        }
        GameRunner gameMaster= new GameRunner(args);
//        gameMaster.startGame();
    }
}
