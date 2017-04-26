import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by eric on 4/5/2017.
 */
public class MainMenu{
    static int numPlayers = 4;
    static boolean SkipRule = false;
    static String[] names = new String[4];
    public static void main(String[] args){
        names[0] = "Bob";
        names[1] = "Joe";
        names[2] = "Emily";
        names[3] = "Janet";
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
                JPanel OPanel = new JPanel();
                JCheckBox rule = new JCheckBox();
                rule.setText("Skip on same rank");
                rule.setSelected(false);
                JTextField name1 = new JTextField();
                name1.setText(names[0]);
                JTextField name2 = new JTextField();
                name2.setText(names[1]);
                JTextField name3 = new JTextField();
                name3.setText(names[2]);
                JTextField name4 = new JTextField();
                name4.setText(names[3]);
                OPanel.setLayout(new GridLayout(5, 1));
                OPanel.add(rule);
                OPanel.add(name1);
                OPanel.add(name2);
                OPanel.add(name3);
                OPanel.add(name4);
                int result = JOptionPane.showConfirmDialog(null, OPanel, "Enter player names and select game options", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                    names[0] = name1.getText();
                    names[1] = name2.getText();
                    names[2] = name3.getText();
                    names[3] = name4.getText();
                    SkipRule = rule.isSelected();
                }
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

