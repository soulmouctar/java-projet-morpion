import com.dyma.game.Player;
import com.dyma.game.TicTacToe;
import com.dyma.game.TicTacToeException;

import java.util.HashMap;
import java.util.Scanner;

import static com.dyma.game.StringContants.BLANK;

public class Main {
    public static void main(String[] args) {

        final var game = new TicTacToe();

        var currentPlayer = Player.FIRST;
        var player = initPlayers();
        while (true) {
            try {
                System.out.println(game);
                System.out.println(player + " / Saisissez un nombre entre 1 et 9 :");
                final var inputUser = getInputUser();

                game.processInput(currentPlayer, inputUser);

                if (game.checkWin()) {
                    System.out.println(game);
                    System.out.println("Le joueur " + player + " a gagné !");
                    break;
                }

                if (game.checkDraw()) {
                    System.out.println(game);
                    System.out.println("personne n'a gagné !");
                    break;
                }
                currentPlayer = nextPlayer(currentPlayer);
            } catch (TicTacToeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Vous devez saisir un nombre entier");
            }
        }
    }

    public static HashMap<Player, String> initPlayers() {
         var players = new HashMap<Player, String>();
         var scanner = new Scanner(System.in);
         do {
             System.out.println("Joueur 1, entrez votre nom : ");
             players.put(Player.FIRST, scanner.nextLine());
         } while (players.get(Player.FIRST).equals(BLANK));

        do {
            System.out.println("Joueur 2, entrez votre nom : ");
            players.put(Player.SECOND, scanner.nextLine());
        } while (players.get(Player.SECOND).equals(BLANK));
         return players;
    }

    private static int getInputUser() throws TicTacToeException {
        final var scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if (input.equals("exit") || input.equals("quit")) {
            System.exit(0);
        }

        var inputEntier = Integer.parseInt(input);
        if(inputEntier < 1 || inputEntier > 9) {
            throw new TicTacToeException("Veuillez saisir un nombre entre 1 et 9 : ");
        }
        return inputEntier;
    }

    private static Player nextPlayer(Player player) {
        if (player == Player.FIRST) {
            return Player.SECOND;
        }else{
            return Player.FIRST;
        }
    }

}