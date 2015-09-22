package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import hu.szaniszlaid.ulwila.note.util.Octave;

public class Test extends JFrame {

    private JPanel contentPane;

    private void initUI() {

        //add(new TestJPanel());
        getContentPane().add(new HalfRestPanel());
        getContentPane().add(new EightRestPanel());
        getContentPane().add(new SixteenthRestPanel());
        getContentPane().add(new HalfNotePanel(Color.YELLOW, Octave.FIRST));
        getContentPane().add(new HalfNotePanel(Color.YELLOW, Octave.SECOND));
        getContentPane().add(new HalfNotePanel(Color.YELLOW, Octave.THIRD));
        getContentPane().add(new EighthNotePanel(Color.RED, Octave.FIRST));
        getContentPane().add(new EighthNotePanel(Color.RED, Octave.SECOND));
        getContentPane().add(new EighthNotePanel(Color.RED, Octave.THIRD));
        getContentPane().add(new WholeRestPanel());
        getContentPane().add(new QuarterNotePanel(Color.BLUE, Octave.FIRST));
        getContentPane().add(new QuarterNotePanel(Color.BLUE, Octave.SECOND));
        getContentPane().add(new QuarterNotePanel(Color.BLUE, Octave.THIRD));
        getContentPane().add(new SixteenthNotePanel(Color.GREEN, Octave.FIRST));
        getContentPane().add(new SixteenthNotePanel(Color.GREEN, Octave.SECOND));
        getContentPane().add(new SixteenthNotePanel(Color.GREEN, Octave.THIRD));
        getContentPane().add(new WholeNotePanel(Color.MAGENTA, Octave.SECOND));

        setLocationRelativeTo(null);
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
        setBounds(500, 500, 524, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        setContentPane(contentPane);

        initUI();
    }

}
