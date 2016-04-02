package hu.szaniszlaid.ulwila.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hu.szaniszlaid.ulwila.midi.TimeSignature;

public class UlwilaRow {

	private List<UlwilaBar> bars = new ArrayList<>();

	private TimeSignature timeSignature;

	private int barCount;

	public UlwilaRow(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
		barCount = 0;
	}

	public void add(UlwilaBar bar) {
		if (isFull()){
			throw new RuntimeException("Cannot add more components to this row, it is full.");
		} else {
			bars.add(bar);
			barCount++;
		}
	}

	public boolean isFull() {
		return barCount >= barsPerRow(timeSignature) ? true : false;
	}

	public boolean isNotFull() {
		return !isFull();
	}

	public JPanel getRowPanel() {
		JPanel row = new JPanel();
		row.setBackground(Color.white);
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (UlwilaBar bar : bars) {
			row.add(bar.getPanel());
		}
		return row;
	}

	public List<UlwilaBar> getBars() {
		return bars;
	}

	/**
	 * This method gets the count of how many bars can fit in one row, based on
	 * timeSignature.
	 * @return the bars count in the row
	 */
	private Integer barsPerRow(TimeSignature timeSignature) {
		if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 2) {
			return 4;
		} else if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 4) {
			return 3;
		} else if (timeSignature.getNumerator() == 3 && timeSignature.getDenominator() == 4) {
			return 3;
		} else if (timeSignature.getNumerator() == 4 && timeSignature.getDenominator() == 4) {
			return 2;
		} else if (timeSignature.getNumerator() == 6 && timeSignature.getDenominator() == 8) {
			return 4;
		} else if (timeSignature.getNumerator() == 12 && timeSignature.getDenominator() == 8) {
			return 4;
		} else {
			return 4;
		}
	}

	@Override
	public String toString() {
		return  "bars per row: " + barsPerRow(timeSignature) + " bars count: " + barCount + " isRowFull: " + isFull() ;
	}

	public int getUlwilaComponentsSize() {
		int sum = 0;
		for (UlwilaBar ulwilaBar : bars) {
			sum += ulwilaBar.getComponents().size();
		}

		return sum;
	}
}
