import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by eric on 4/5/2017.
 */
public class MainMenu{
    static int numPlayers = 4;
    public static void main(String[] args){
        JFrame Menu = new JFrame();
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.setSize(600, 400);
        JButton NewGame = new JButton();
        NewGame.setText("New Game");
        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board Playing = new Board(numPlayers);
                Playing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Playing.setSize(1000, 800);
                Playing.setVisible(true);
            }
        });
        JButton Options = new JButton();
        Options.setText("Options");
        Options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display options menu to change game settings
                JOptionPane MenuOptions = new JOptionPane("Options Menu");
                MenuOptions.setSize(1000, 300);
                MenuOptions.setVisible(true);
            }
        });
        JButton Exit = new JButton();
        Exit.setText("Exit");
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel buttons = new JPanel();
        GridBagLayout Selections = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttons.setLayout(Selections);
        buttons.add(NewGame, gbc);
        buttons.add(Options, gbc);
        buttons.add(Exit, gbc);
        buttons.setVisible(true);
        Menu.add(buttons);
        Menu.setVisible(true);
    }
}

