package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.szaniszlaid.ulwila.note.util.Octave;

public class Test extends JFrame {

	private JPanel contentPane;
	
    private void initUI() {

        //add(new TestJPanel());
        add(new HalfRestPanel());
        add(new EightRestPanel());
        add(new SixteenthRestPanel());
        add(new HalfNotePanel(Color.YELLOW, Octave.FIRST));
        add(new HalfNotePanel(Color.YELLOW, Octave.SECOND));
        add(new HalfNotePanel(Color.YELLOW, Octave.THIRD));
        add(new EighthNotePanel(Color.RED, Octave.FIRST));
        add(new EighthNotePanel(Color.RED, Octave.SECOND));
        add(new EighthNotePanel(Color.RED, Octave.THIRD));
        add(new WholeRestPanel());                
        add(new QuarterNotePanel(Color.BLUE, Octave.FIRST));
        add(new QuarterNotePanel(Color.BLUE, Octave.SECOND));
        add(new QuarterNotePanel(Color.BLUE, Octave.THIRD));
        add(new SixteenthNotePanel(Color.GREEN, Octave.FIRST));
        add(new SixteenthNotePanel(Color.GREEN, Octave.SECOND));
        add(new SixteenthNotePanel(Color.GREEN, Octave.THIRD));
        add(new WholeNotePanel(Color.MAGENTA, Octave.SECOND));


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		setContentPane(contentPane);
		
		initUI();
	}
	
}
