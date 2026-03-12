package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class SelectTestFigureOptionListener implements ActionListener {

	private DriverManager driverManager;
	private int figureNum;
	public SelectTestFigureOptionListener(DriverManager driverManager, int figureNum) {

		this.figureNum = figureNum;
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.figureNum == 1){
			FiguresJoe.figureScript1(driverManager.getCurrentDriver());
		}
		else if (this.figureNum == 2){
			FiguresJoe.figureScript2(driverManager.getCurrentDriver());
		}
	}
}
