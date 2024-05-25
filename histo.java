import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class histo extends JFrame {
    private Clip clip;

    public histo() {
        setTitle("CATEGORY!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 224, 200));

        JPanel topPanel = new JPanel(new BorderLayout());
        Color customColor = Color.decode("#2D4356");
        topPanel.setBackground(customColor);
        topPanel.setOpaque(true);

        ImageIcon logoIcon = new ImageIcon("logo.png");
        ImageIcon questionIcon = new ImageIcon("qm.png");
        ImageIcon menuIcon = new ImageIcon("mm.png");

        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image questionImage = questionIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Image menuImage = menuIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        JLabel questionLabel = new JLabel(new ImageIcon(questionImage));
        JLabel menuLabel = new JLabel(new ImageIcon(menuImage));

        JPanel logoTitlePanel = new JPanel(new BorderLayout());
        logoTitlePanel.setOpaque(false);
        logoTitlePanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        logoTitlePanel.add(logoLabel, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("   CATEGORY!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        Color foreColor = Color.decode("#EAEAE0");
        titleLabel.setForeground(foreColor);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        logoTitlePanel.add(titlePanel, BorderLayout.EAST);
        topPanel.add(logoTitlePanel, BorderLayout.WEST);

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.add(questionLabel);
        menuPanel.add(menuLabel);
        topPanel.add(menuPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        ImageIcon leftArrowIcon = new ImageIcon("left-arrow.png");
        Image leftArrowImage = leftArrowIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel leftArrowLabel = new JLabel(new ImageIcon(leftArrowImage));

        ImageIcon rightArrowIcon = new ImageIcon("right-arrow.png");
        Image rightArrowImage = rightArrowIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel rightArrowLabel = new JLabel(new ImageIcon(rightArrowImage));

        ImageIcon categoryIcon = new ImageIcon("histo.png");
        Image categoryImage = categoryIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        JLabel categoryLabel = new JLabel(new ImageIcon(categoryImage));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel categoryTitleLabel = new JLabel("History Adventure");
        categoryTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        categoryTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setOpaque(false);
        categoryTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryPanel.add(categoryTitleLabel);
        categoryPanel.add(Box.createVerticalStrut(10));
        categoryPanel.add(categoryLabel);

        JPanel arrowPanel = new JPanel(new BorderLayout());
        arrowPanel.setOpaque(false);
        arrowPanel.add(leftArrowLabel, BorderLayout.WEST);
        arrowPanel.add(rightArrowLabel, BorderLayout.EAST);
        arrowPanel.add(categoryPanel, BorderLayout.CENTER);

        centerPanel.add(arrowPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(new Color(180, 200, 255));
        startButton.setPreferredSize(new Dimension(200, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "padugtog nlng ang code nyo");
            }
        });

        add(mainPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        questionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showWelcomeMessage();
            }
        });

        menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showVolumeControl();
            }
        });

        loadSound("sugar_cookie.wav");
        setVolume(100);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void showWelcomeMessage() {
        String message = "Welcome to Think-Tac-Go!, a dynamic twist on the classic game of Tic-Tac-Toe that challenges both your strategic thinking and quick-witted knowledge! \n\n" +
                "In Think-Tac-Toe, traditional gameplay meets brain-teasing quizzes, adding an exciting layer of complexity and fun to the timeless grid game you know and love.\n\n" +
                "In this innovative version, you can choose a category of your liking! After choosing a fun category, \n\n each turn isn't just about placing your X or O on the board—it's about answering a quiz question correctly within a 15-second timer to earn your move. \n\n" +
                "Every round tests your general knowledge, quick thinking, and ability to stay calm under pressure. Correct answers let you mark your spot, but be quick and accurate—time is of the essence!\n\n" +
                "Prepare to engage in a fast-paced battle of minds where victory goes not just to the most strategic player, but to the sharpest thinker. \n\n" +
                "Get ready to outsmart your opponent, claim your squares, and achieve three in a row in the thrilling, quiz-filled world of Think-Tac-Toe!";
        JOptionPane.showMessageDialog(this, message, "Welcome to Think-Tac-Go!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showVolumeControl() {
        JSlider volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(e -> setVolume(volumeSlider.getValue()));

        JPanel panel = new JPanel();
        panel.add(new JLabel("Volume:"));
        panel.add(volumeSlider);

        JOptionPane.showMessageDialog(this, panel, "Volume Control", JOptionPane.PLAIN_MESSAGE);
    }

    private void loadSound(String soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void setVolume(int volume) {
        if (clip != null && clip.isOpen()) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float volumeRange = max - min;

            float[] volumeLevels = {min, min + 0.25f * volumeRange, min + 0.5f * volumeRange, min + 0.75f * volumeRange, max};
            int index = volume / 25;
            float gain = volumeLevels[index];

            volumeControl.setValue(gain);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new histo();
            }
        });
    }
}