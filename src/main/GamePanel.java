import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import entities.*;

public class GamePanel extends JFrame {
    private Game game;
    private JLabel[][] gridCells;
    
    // ui components (reside in side bar)
    private JProgressBar healthBar;
    private JTextArea logArea;
    private JRadioButton moveButton;
    private JRadioButton attackButton;

    public GamePanel() {
        this.game = new Game();
        this.game.start(); 

        Board board = game.getBoard();
        int rows = board.getRows();
        int cols = board.getCols();

        setTitle("OOP in Java - Board Warriors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // board panel (grid)
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
        gridCells = new JLabel[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setPreferredSize(new Dimension(30, 30));
                cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
               
                // mouse adapter uses final vars
                final int finalR = r;
                final int finalC = c;

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean isAttack = attackButton.isSelected();
                        game.executeMouseAction(finalR, finalC, isAttack);
                        refreshBoard();
                    }
                });

                gridCells[r][c] = cell;
                boardPanel.add(cell);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        
        JLabel modeLabel = new JLabel("Action Mode:");
        modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moveButton = new JRadioButton("Move (1 Space)", true);
        attackButton = new JRadioButton("Attack", false);
    
        // group to select one or the other explicity
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(moveButton);
        modeGroup.add(attackButton);

        // health bar
        JLabel hpLabel = new JLabel("Player Health:");
        hpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(100);
        healthBar.setStringPainted(true);
        healthBar.setFocusable(false);
        healthBar.setForeground(Color.GREEN);
        
        // combat log
        JLabel logLabel = new JLabel("Combat Log:");
        logLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFocusable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // add all components to side bar
        sidePanel.add(modeLabel);
        sidePanel.add(moveButton);
        sidePanel.add(attackButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(hpLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        sidePanel.add(healthBar);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        sidePanel.add(logLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        sidePanel.add(scrollPane);
        
        add(sidePanel, BorderLayout.EAST);
        
        pack();
        setLocationRelativeTo(null); 
        refreshBoard();
        setVisible(true);
    }

    private void refreshBoard() {
        Board board = game.getBoard();

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Entity entity = board.getEntityAt(r, c);

                if (entity == null) {
                    gridCells[r][c].setText("");
                    gridCells[r][c].setBackground(Color.WHITE);
                } else {
                    String name = entity.getClass().getSimpleName();
                    gridCells[r][c].setText(name.substring(0, 1));
                    gridCells[r][c].setFont(new Font("Arial", Font.BOLD, 14));

                    if (name.equals("Player")) gridCells[r][c].setBackground(new Color(173, 216, 230));
                    else if (name.equals("Enemy")) gridCells[r][c].setBackground(new Color(255, 182, 193)); 
                    else gridCells[r][c].setBackground(new Color(144, 238, 144)); 
                }
            }
        }

        // updates health
        int currentHp = game.getPlayer().getHp();
        if (currentHp > 0) healthBar.setValue(currentHp);
        else healthBar.setValue(0);
        healthBar.setString(currentHp + " / 100 HP");
        if (currentHp < 30) healthBar.setForeground(Color.RED);
        else if (currentHp < 60) healthBar.setForeground(Color.ORANGE);
        else healthBar.setForeground(Color.GREEN);

        // updates combat log
        logArea.setText("");
        for (String msg : game.getCombatLog()) {
            logArea.append(msg + "\n");
        }
        logArea.setCaretPosition(logArea.getDocument().getLength());

        // game over
        if (game.isGameOver()) {
            if (game.getPlayer().isDead()) {
                JOptionPane.showMessageDialog(this, "You have been defeated! Game Over.");
            } else {
                JOptionPane.showMessageDialog(this, "Victory! You defeated all enemies!");
            }
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GamePanel();
        });
    }
}
