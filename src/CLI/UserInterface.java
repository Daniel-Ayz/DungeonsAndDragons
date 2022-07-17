package CLI;

import Game.Action;
import Game.PlayerChoice;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public Action getAction(){
        char input = scanner.next().charAt(0);
        switch (input){
            case 'a':
                return Action.LEFT;
            case 'A':
                return Action.LEFT;
            case 'd':
                return Action.RIGHT;
            case 'D':
                return Action.RIGHT;
            case 'w':
                return Action.UP;
            case 'W':
                return Action.UP;
            case 's':
                return Action.DOWN;
            case 'S':
                return Action.DOWN;
            case 'e':
                return Action.SPECIAL_ABILITY;
            case 'E':
                return Action.SPECIAL_ABILITY;
            default:
                return Action.NONE;
        }
    }

    public PlayerChoice getPlayerChoice() {
        char input = scanner.next().charAt(0);
        switch (input) {
            case 1:
                return PlayerChoice.JON_SNOW;
            case 2:
                return PlayerChoice.THE_HOUND;
            case 3:
                return PlayerChoice.MELISANDRE;
            case 4:
                return PlayerChoice.THOROS_OF_MYR;
            case 5:
                return PlayerChoice.ARYA_STARK;
            case 6:
                return PlayerChoice.BRONN;
            case 7:
                return PlayerChoice.YGRITTE;
            default:
                return PlayerChoice.NONE;
        }
    }

}
