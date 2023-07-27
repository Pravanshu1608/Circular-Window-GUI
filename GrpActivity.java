package project;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GrpActivity extends JFrame implements ActionListener{
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JTextField searchField;
    private boolean isTransparent = false;

    public GrpActivity(){
        super("Project");
        setLayout(new BorderLayout());

        addComponentListener(new ComponentAdapter() {
            // Give the window an elliptical shape.
            // If the window is resized, the shape is recalculated here.
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new Ellipse2D.Double(0,0,getWidth(),getHeight()));
            }

        });



        btn1 = new JButton();
        try {
            BufferedImage icon = ImageIO.read(new URL("https://www.gstatic.com/images/branding/product/1x/youtube_64dp.png"));
            btn1.setIcon(new ImageIcon(icon));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);



        btn2 = new JButton("X");
        btn2.setBackground(Color.RED);
        btn2.setFocusable(false);
        btn2.setToolTipText("Close");



        btn3 = new JButton("-");
        btn3.setBackground(Color.RED);
        btn3.setFocusable(false);
        btn3.setToolTipText("Minimize");



        btn4 = new JButton();
        ImageIcon musicIcon = new ImageIcon(getClass().getResource("music3.png"));
        Image scaledImg = musicIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        btn4.setIcon(scaledIcon);
        btn4.setPreferredSize(new Dimension(50, 50));
        btn4.setBorderPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setFocusable(false);
        btn4.setToolTipText("YouTube Music");



        btn5 = new JButton("Search");
        btn5.setFocusable(false);
        btn5.setToolTipText("Browse the web");




        JPanel topleftPanel = new JPanel(new BorderLayout());
        topleftPanel.add(btn1);
        topleftPanel.setBackground(Color.BLACK);


        JPanel bottomLeftPanel = new JPanel(new FlowLayout()){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 255;
                    final int G = 50;
                    final int B = 50;
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                                    getWidth(), getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        bottomLeftPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        bottomLeftPanel.add(btn2);
        bottomLeftPanel.add(btn3);


        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topleftPanel, bottomLeftPanel);
        splitPane1.setOneTouchExpandable(true);
        splitPane1.setDividerLocation(200);


        JPanel topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.add(btn4);
        topRightPanel.setBackground(Color.RED);


        JPanel bottomRightPanel = new JPanel(new FlowLayout()){
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 0;
                    final int G = 0;
                    final int B = 0;
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 255),
                                    getWidth(), getHeight(), new Color(R, G, B, 0), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        searchField = new JTextField(15);
        bottomRightPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
        bottomRightPanel.add(searchField);
        bottomRightPanel.add(btn5);

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topRightPanel, bottomRightPanel);
        splitPane2.setOneTouchExpandable(true);
        splitPane2.setDividerLocation(200);

        JSplitPane splitPane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, splitPane2);
        splitPane3.setDividerLocation(200);



        this.add(splitPane3);




        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTransparent) {
                    setOpacity(1.0f); // Restore the frame's original state
                    isTransparent = false; // Update the flag
                    btn1.setFont(new Font("Roboto", Font.BOLD, 20));
                    btn1.setPreferredSize(new Dimension(100, 40));
                    btn1.setText("");
                    btn1.setForeground(Color.WHITE);

                } else {
                    setOpacity(0.7f); // Make the frame 50% transparent
                    isTransparent = true; // Update the flag
                    btn1.setFont(new Font("Roboto", Font.BOLD, 20));
                    btn1.setPreferredSize(new Dimension(100, 40));
                    btn1.setText("YouTube");
                    btn1.setForeground(Color.WHITE);

                }
            }
        });


        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the application
                dispose();
            }
        });


        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });


        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI("https://music.youtube.com/")); // Redirects to YouTube Music
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        String[] words = searchField.getText().split("\\s+"); // split the words by whitespace
                        String query = String.join("+", words); // join the words with +
                        desktop.browse(new URI("https://www.youtube.com/results?search_query=" + query)); // Redirects to YouTube
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });





        setUndecorated(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // createUI(this);
    }


    public static void main(String[] args) {
        new GrpActivity();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}