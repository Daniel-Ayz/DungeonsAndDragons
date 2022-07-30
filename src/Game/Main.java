package Game;

public class Main {
    public static void main(String[] args){
        GameManager gameManager = new GameManager(/*args[0]*/ "C:\\Users\\mafia\\Desktop\\gameExample\\levels_dir");
        gameManager.playGame();
    }
}
