package hu.szaniszlaid.ulwila.notes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import hu.szaniszlaid.ulwila.note.util.Octave;

public class Test extends JFrame {


    private void initComponents() {
        
        //Set sizes of root frame
        setBounds(500, 500, 800, 500);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        //TODO Temporary, just for design test
        JPanel menu = new JPanel();
        mainPanel.add(menu, BorderLayout.NORTH);
        JButton btnHha = new JButton("Uzsgyi");
        menu.add(btnHha);
                
        //Test notes
        JPanel notesPanel = new JPanel();
        notesPanel.add(new HalfRestPanel());
        notesPanel.add(new EightRestPanel());
        notesPanel.add(new SixteenthRestPanel());
        notesPanel.add(new HalfNotePanel(Color.YELLOW, Octave.FIRST));
        notesPanel.add(new HalfNotePanel(Color.YELLOW, Octave.SECOND));
        notesPanel.add(new HalfNotePanel(Color.YELLOW, Octave.THIRD));
        notesPanel.add(new EighthNotePanel(Color.RED, Octave.FIRST));
        notesPanel.add(new EighthNotePanel(Color.RED, Octave.SECOND));
        notesPanel.add(new EighthNotePanel(Color.RED, Octave.THIRD));
        notesPanel.add(new WholeRestPanel());
        notesPanel.add(new QuarterNotePanel(Color.BLUE, Octave.FIRST));
        notesPanel.add(new QuarterNotePanel(Color.BLUE, Octave.SECOND));
        notesPanel.add(new QuarterNotePanel(Color.BLUE, Octave.THIRD));
        notesPanel.add(new SixteenthNotePanel(Color.GREEN, Octave.FIRST));
        notesPanel.add(new SixteenthNotePanel(Color.GREEN, Octave.SECOND));
        notesPanel.add(new SixteenthNotePanel(Color.GREEN, Octave.THIRD));
        notesPanel.add(new WholeNotePanel(Color.MAGENTA, Octave.SECOND));
        
        notesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        notesPanel.setPreferredSize(new Dimension(500, 500));

       
        JScrollPane scrollPanel = new JScrollPane(notesPanel);
        mainPanel.add(scrollPanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test frame = new Test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Test() {
        //set LookAndFeel to system default
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        initComponents();

    }

}
