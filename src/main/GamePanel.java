import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import entities.*;

public class GamePanel extends JFrame {
    private Game game;
    private JLabel[][] gridCells;

    public GamePanel() {
        this.game = new Game();
        this.game.start();

        Board board = game.getBoard();
        int rows = board.getRow();
        int cols = board.getCol();

        setTitle("Board Warriors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(rows, cols));
        setResizable(false);

        gridCells = new JLabel[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setPreferredSize(new Dimension(30,30));
                cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                gridCells[r][c] = cell;
                add(cell);
            }
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { game.executeGuiTurn(-1,0); }
                else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { game.executeGuiTurn(1, 0); }
                else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { game.executeGuiTurn(0, -1); }
                else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { game.executeGuiTurn(0, 1); }

                refreshBoard();
            }
        });

        pack();
        setLocationRelativeTo(null);
        refreshBoard();
        setVisible(true);
    }

    private void refreshBoard() {
        Board board = game.getBoard();

        for (int r = 0; r < board.getRow(); r++) {
            for (int c = 0; c < board.getCol(); c++) {
                Entity entity = board.getEntityAt(r,c);

                if (entity == null) {
                    gridCells[r][c].setText("");
                    gridCells[r][c].setBackground(Color.WHITE);
                }
                else {
                    String name = entity.getClass().getSimpleName();
                    gridCells[r][c].setText(name.substring(0,1));
                    gridCells[r][c].setFont(new Font("Arial", Font.BOLD, 14));

                    if (name.equals("Player")) {
                        gridCells[r][c].setBackground(new Color(173, 216, 230));
                    }
                    else if (name.equals("Enemy")) {
                        gridCells[r][c].setBackground(new Color(255, 182, 193));
                    }
                    else {
                        gridCells[r][c].setBackground(new Color(144, 238, 144));
                    }
                }
            }
        }

        if (game.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GamePanel();
        });
    }
}
