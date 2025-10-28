package rockpaperscissorgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissorGame extends JFrame implements ActionListener {

    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private JLabel resultLabel, computerChoiceLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;
    private Random random;

    public RockPaperScissorGame() {
        setTitle("🎮 Rock Paper Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255)); // light blue

        // 🧱 Title
        JLabel titleLabel = new JLabel("Choose Rock, Paper, or Scissors", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel, BorderLayout.NORTH);

        // 🎮 Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));

        rockButton = new JButton("🪨 Rock");
        paperButton = new JButton("📄 Paper");
        scissorsButton = new JButton("✂️ Scissors");
        resetButton = new JButton("🔄 Reset");

        for (JButton btn : new JButton[]{rockButton, paperButton, scissorsButton, resetButton}) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(173, 216, 230));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
            buttonPanel.add(btn);
            btn.addActionListener(this);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // 💬 Labels for result and computer choice
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setBackground(new Color(240, 248, 255));

        computerChoiceLabel = new JLabel("Computer's choice will appear here", JLabel.CENTER);
        computerChoiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        resultLabel = new JLabel("Make your move!", JLabel.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        scoreLabel = new JLabel("Score - You: 0 | Computer: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        infoPanel.add(computerChoiceLabel);
        infoPanel.add(resultLabel);
        infoPanel.add(scoreLabel);

        add(infoPanel, BorderLayout.SOUTH);

        random = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("🔄 Reset")) {
            playerScore = 0;
            computerScore = 0;
            resultLabel.setText("Game Reset! Play Again.");
            computerChoiceLabel.setText("Computer's choice will appear here");
            scoreLabel.setText("Score - You: 0 | Computer: 0");
            return;
        }

        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[random.nextInt(3)];
        computerChoiceLabel.setText("Computer chose: " + computerChoice);

        String userChoice = command.contains("Rock") ? "Rock"
                : command.contains("Paper") ? "Paper" : "Scissors";

        String result;
        if (userChoice.equals(computerChoice)) {
            result = "🤝 It's a Draw!";
        } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors"))
                || (userChoice.equals("Paper") && computerChoice.equals("Rock"))
                || (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "🎉 You Win!";
            playerScore++;
        } else {
            result = "❌ You Lose!";
            computerScore++;
        }

        resultLabel.setText(result);
        scoreLabel.setText("Score - You: " + playerScore + " | Computer: " + computerScore);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorGame game = new RockPaperScissorGame();
            game.setVisible(true);
            game.setLocationRelativeTo(null);
        });
    }
}
