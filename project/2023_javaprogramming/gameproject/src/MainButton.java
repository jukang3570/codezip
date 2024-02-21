import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BallStar.*;
import Galag.*;
import GOMOKU.*;
import OX.*;
import TexttingGame.*;

public class MainButton extends JFrame {
	java.net.URL url =getClass().getClassLoader().getResource("0.png");
	java.net.URL url1 =getClass().getClassLoader().getResource("1.png");
	java.net.URL url2 =getClass().getClassLoader().getResource("2.png");
	java.net.URL url3 =getClass().getClassLoader().getResource("3.png");
	java.net.URL url4 =getClass().getClassLoader().getResource("4.png");
	java.net.URL url5 =getClass().getClassLoader().getResource("5.png");
    private ImageIcon bgIcon = new ImageIcon(url);
    private ImageIcon im1 = new ImageIcon(url1);
    private ImageIcon im2 = new ImageIcon(url2);
    private ImageIcon im3 = new ImageIcon(url3);
    private ImageIcon im4 = new ImageIcon(url4);
    private ImageIcon im5 = new ImageIcon(url5);
    private Image bgImage = bgIcon.getImage();
    private ButtonPanel panel = new ButtonPanel();

    public MainButton() {
        setTitle("7조 프로젝트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(900, 500);
        setVisible(true);
    }

    class ButtonPanel extends JPanel implements ActionListener {
        private JButton button1 = new JButton(im1);
        private JButton button2 = new JButton(im2);
        private JButton button3 = new JButton(im3);
        private JButton button4 = new JButton(im4);
        private JButton button5 = new JButton(im5);

        private JLabel label1 = new JLabel("OX");
        private JLabel label2 = new JLabel("Gomoku");
        private JLabel label3 = new JLabel("Galaga");
        private JLabel label4 = new JLabel("Ball Star");
        private JLabel label5 = new JLabel("Texting Game");

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }

        public ButtonPanel() {
            this.setLayout(null);
            setLocationRelativeTo(null);

            button1.setBounds(170, 70, 150, 150);
            button2.setBounds(360, 70, 150, 150);
            button3.setBounds(550, 70, 150, 150);
            button4.setBounds(250, 270, 150, 150);
            button5.setBounds(450, 270, 150, 150);

            label1.setBounds(170, 30, 150, 30);
            label2.setBounds(360, 30, 150, 30);
            label3.setBounds(550, 30, 150, 30);
            label4.setBounds(250, 230, 150, 30);
            label5.setBounds(450, 230, 150, 30);

            label1.setForeground(Color.WHITE);
            label1.setFont(new Font("Arial", Font.BOLD, 14));
            label1.setHorizontalAlignment(JLabel.CENTER);

            label2.setForeground(Color.WHITE);
            label2.setFont(new Font("Arial", Font.BOLD, 14));
            label2.setHorizontalAlignment(JLabel.CENTER);

            label3.setForeground(Color.WHITE);
            label3.setFont(new Font("Arial", Font.BOLD, 14));
            label3.setHorizontalAlignment(JLabel.CENTER);

            label4.setForeground(Color.WHITE);
            label4.setFont(new Font("Arial", Font.BOLD, 14));
            label4.setHorizontalAlignment(JLabel.CENTER);

            label5.setForeground(Color.WHITE);
            label5.setFont(new Font("Arial", Font.BOLD, 14));
            label5.setHorizontalAlignment(JLabel.CENTER);

            Image scaledImage1 = im1.getImage().getScaledInstance(button1.getWidth(), button1.getHeight(),
                    Image.SCALE_SMOOTH);
            button1.setIcon(new ImageIcon(scaledImage1));
            button1.addActionListener(this);

            Image scaledImage2 = im2.getImage().getScaledInstance(button2.getWidth(), button2.getHeight(),
                    Image.SCALE_SMOOTH);
            button2.setIcon(new ImageIcon(scaledImage2));
            button2.addActionListener(this);

            Image scaledImage3 = im3.getImage().getScaledInstance(button3.getWidth(), button3.getHeight(),
                    Image.SCALE_SMOOTH);
            button3.setIcon(new ImageIcon(scaledImage3));
            button3.addActionListener(this);

            Image scaledImage4 = im4.getImage().getScaledInstance(button4.getWidth(), button4.getHeight(),
                    Image.SCALE_SMOOTH);
            button4.setIcon(new ImageIcon(scaledImage4));
            button4.addActionListener(this);

            Image scaledImage5 = im5.getImage().getScaledInstance(button5.getWidth(), button5.getHeight(),
                    Image.SCALE_SMOOTH);
            button5.setIcon(new ImageIcon(scaledImage5));
            button5.addActionListener(this);

            add(button1);
            add(button2);
            add(button3);
            add(button4);
            add(button5);
            add(label1);
            add(label2);
            add(label3);
            add(label4);
            add(label5);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
            	dispose();
            	SetBoardSize s = new SetBoardSize(); 
	            int size = s.getBoardSize(); //사용자로부터 게임 보드 크기 반환
	            new TicTacToeGame(size);
     
            } else if (e.getSource() == button2) {
            	SwingUtilities.invokeLater(() -> {
            		dispose();
            		new RunGame();
            	});
            } else if (e.getSource() == button3) {
            	SwingUtilities.invokeLater(() -> {
                    dispose();
                    new main();
                });
            } else if (e.getSource() == button4) {
            	dispose();
            	BallGame game = new BallGame();
                Thread thread = new Thread(game);
                thread.start();
            } else if (e.getSource() == button5) {
            	dispose();
            	new MainFrame();
            }
        }
    }

    public static void main(String[] args) {
        new MainButton();
    }
}
