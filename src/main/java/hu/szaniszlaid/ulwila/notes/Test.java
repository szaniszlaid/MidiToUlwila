package hu.szaniszlaid.ulwila.notes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.rest.EightRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semitone.EightSemiNote;
import hu.szaniszlaid.ulwila.notes.semitone.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;

public class Test extends JFrame {

    private void initComponents() {

        //Dummy notes for testing
        Map<Octave, List<Tone>> notes = new LinkedHashMap<>();
        notes.put(Octave.FIRST, new ArrayList<>());
        for (Tone tone : Tone.values()) {
            notes.get(Octave.FIRST).add(tone);
        }

        notes.put(Octave.SECOND, new ArrayList<>());
        for (Tone tone : Tone.values()) {
            notes.get(Octave.SECOND).add(tone);
        }

        notes.put(Octave.THIRD, new ArrayList<>());
        for (Tone tone : Tone.values()) {
            notes.get(Octave.THIRD).add(tone);
        }

        notes.put(Octave.FOURTH, new ArrayList<>());
        notes.get(Octave.FOURTH).add(Tone.C);

        //Set sizes of root frame
        setBounds(500, 500, 800, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        //TODO Temporary, just for design test
        JPanel menu = new JPanel();
        mainPanel.add(menu, BorderLayout.NORTH);
        JButton btnHha = new JButton("Uccu");
        menu.add(btnHha);

        //Test notes
        JPanel notesPanel = new JPanel();
//        notesPanel.add(new HalfRest());
//        notesPanel.add(new EightRest());
//        notesPanel.add(new SixteenthRest());
//        notesPanel.add(new HalfNote(Color.YELLOW, Octave.FIRST));
//        notesPanel.add(new HalfNote(Color.YELLOW, Octave.SECOND));
//        notesPanel.add(new HalfNote(Color.YELLOW, Octave.THIRD));
//        notesPanel.add(new EighthNote(Color.RED, Octave.FIRST));
//        notesPanel.add(new EighthNote(Color.RED, Octave.SECOND));
//        notesPanel.add(new EighthNote(Color.RED, Octave.THIRD));
//        notesPanel.add(new WholeRest());
//        notesPanel.add(new QuarterNote(Octave.FIRST, Tone.C));
//        notesPanel.add(new QuarterNote(Octave.FIRST, Tone.D));
//  
// 		  notesPanel.add(new EightSemiNote(Octave.THIRD, Tone.CIS));

        //rests
        notesPanel.add(new SixteenthRest());
        notesPanel.add(new EightRest());
        notesPanel.add(new QuarterRest());
        notesPanel.add(new HalfRest());
        notesPanel.add(new WholeRest());

        //whole notes
        notesPanel.add(new SixteenthNote(Octave.THIRD, Tone.H));
        notesPanel.add(new EighthNote(Octave.THIRD, Tone.A));
        notesPanel.add(new QuarterNote(Octave.FIRST, Tone.C));
        notesPanel.add(new HalfNote(Octave.FIRST, Tone.C));
        notesPanel.add(new WholeNote(Octave.SECOND, Tone.E));

        //semi notes      
        notesPanel.add(new EightSemiNote(Octave.FIRST, Tone.GIS));
        notesPanel.add(new QuarterSemiNote(Octave.FIRST, Tone.GIS));

//		for (Octave octave : notes.keySet()) {
//			for (Tone tone : notes.get(octave)) {
//				if (tone.isSemiTone()) {
//					notesPanel.add(new EightSemiNote(octave, tone));
//				} else {
//					notesPanel.add(new EighthNote(octave, tone));
//				}
//				//System.out.println(tone);
//			}
//		}

//			for (Tone tone : Tone.values()) {
//				if (tone.isSemiTone()){
//					notesPanel.add(new QuarterSemiNote(Octave.FOURTH, tone));
//				} else {
//					notesPanel.add(new QuarterNote(Octave.FOURTH, tone));
//				}
//				System.out.println(tone);
//			}

//        notesPanel.add(new QuarterNote(Color.BLUE, Octave.SECOND));
//        notesPanel.add(new QuarterNote(Color.BLUE, Octave.THIRD));
//        notesPanel.add(new SixteenthNote(Color.GREEN, Octave.FIRST));
//        notesPanel.add(new SixteenthNote(Color.GREEN, Octave.SECOND));
//        notesPanel.add(new SixteenthNote(Color.GREEN, Octave.THIRD));

        notesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        notesPanel.setPreferredSize(new Dimension(500, 1500));

        JScrollPane scrollPanel = new JScrollPane(notesPanel);
        mainPanel.add(scrollPanel, BorderLayout.CENTER);

        //set scroll speed TODO properties file 
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);

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
