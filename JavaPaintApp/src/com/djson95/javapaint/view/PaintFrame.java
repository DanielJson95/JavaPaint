package com.djson95.javapaint.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.djson95.javapaint.controller.Controller;
import com.djson95.javapaint.model.enums.MenuEvent;

/**
 * <p>
 *     Den här klassen är själva fönstret för applikationen. Den bygger upp all UI
 *     och ansvarar för att lyssna efter händelser i menyen.<br/>
 *	   Alla händelser skickas vidare till controller som sköter dem.
 * </p>
 */
public class PaintFrame extends JFrame {

	private Controller controller;
	private DrawingPanelAdapter drawingPanel;
    private Color selectFillColor = Color.WHITE; // Vitt är default för fyllnad
	private Color selectLineColor = Color.BLACK; // Svart är default för linje
	
	/// MENU BAR
	private JMenuBar menuBar;
	
	// Menyer
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu selectShapeMenu;
	private JMenu formatMenu;
	private JMenu layerMenu;
	private ButtonGroup shapeButtonGroup;
	
	// Menu items under "File"
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem exitMenuItem;
	
	// Menu items under "Edit"
	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;
	
	// Menu items under "Select shape"
	private JRadioButtonMenuItem lineShapeMenuItem;
	private JRadioButtonMenuItem circleShapeMenuItem;
	private JRadioButtonMenuItem rectangleShapeMenuItem;

	// Menus och menu items under "Format"
	private JMenu lineThicknessMenu;
	private ButtonGroup lineThicknessButtonGroup;
	private JRadioButtonMenuItem thinLineMenuItem;
	private JRadioButtonMenuItem mediumLineMenuItem;
	private JRadioButtonMenuItem thickLineMenuItem;

	private JMenu colorMenu;
	private JMenuItem lineColorMenuItem;
	private JMenuItem fillColorMenuItem;

	// Menu items under "Layer"
	private JMenuItem bringToFrontMenuItem;
	private JMenuItem sendToBackMenuItem;
	private JMenuItem bringForwardMenuItem;
	private JMenuItem sendBackwardMenuItem;

	// TODO: Flytta lyssnare till egen klass
	public PaintFrame(Controller controller, DrawingPanelAdapter drawingPanel) {
		this.controller = controller;
		this.drawingPanel = drawingPanel;
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		selectShapeMenu = new JMenu("Select shape");
		formatMenu = new JMenu("Format selection");
		layerMenu = new JMenu("Layer selection");
		
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SAVE);
			}
		});

		saveAsMenuItem = new JMenuItem("Save as");
		saveAsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SAVE_AS);
			}
		});

		openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.OPEN);
			}
		});

		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.EXIT);
			}
		});


		undoMenuItem = new JMenuItem("Undo");
		undoMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.UNDO);
			}
		});

		redoMenuItem = new JMenuItem("Redo");
		redoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.REDO);
			}
		});
		
		
		lineShapeMenuItem = new JRadioButtonMenuItem("Line");
		circleShapeMenuItem = new JRadioButtonMenuItem("Circle");
		rectangleShapeMenuItem = new JRadioButtonMenuItem("Rectangle");
		
		shapeButtonGroup = new ButtonGroup();
		shapeButtonGroup.add(lineShapeMenuItem);
		shapeButtonGroup.add(circleShapeMenuItem);
		shapeButtonGroup.add(rectangleShapeMenuItem);
		
		lineShapeMenuItem.setSelected(true);

		lineThicknessMenu = new JMenu("Line thickness");
		lineThicknessButtonGroup = new ButtonGroup();

		thinLineMenuItem = new JRadioButtonMenuItem("Thin (1px)");
		mediumLineMenuItem = new JRadioButtonMenuItem("Medium (3px)");
		thickLineMenuItem = new JRadioButtonMenuItem("Thick (5px)");

		lineThicknessButtonGroup.add(thinLineMenuItem);
		lineThicknessButtonGroup.add(mediumLineMenuItem);
		lineThicknessButtonGroup.add(thickLineMenuItem);
		mediumLineMenuItem.setSelected(true); // Medium (3px) är default

		thinLineMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SET_THIN_LINE);
			}
		});
		mediumLineMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SET_MEDIUM_LINE);
			}
		});
		thickLineMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SET_THICK_LINE);
			}
		});

		colorMenu = new JMenu("Color");
		lineColorMenuItem = new JMenuItem("Line color");
		fillColorMenuItem = new JMenuItem("Fill color");

		lineColorMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.CHOOSE_LINE_COLOR);
			}
		});
		fillColorMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.CHOOSE_FILL_COLOR);
			}
		});

		bringToFrontMenuItem = new JMenuItem("Bring to front");
		sendToBackMenuItem = new JMenuItem("Send to back");
		bringForwardMenuItem = new JMenuItem("Bring forward");
		sendBackwardMenuItem = new JMenuItem("Send backward");

		bringToFrontMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.BRING_TO_FRONT);
			}
		});
		sendToBackMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SEND_TO_BACK);
			}
		});
		bringForwardMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.BRING_FORWARD);
			}
		});
		sendBackwardMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleMenuEvent(MenuEvent.SEND_BACKWARD);
			}
		});
		
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(selectShapeMenu);
		menuBar.add(formatMenu);
		menuBar.add(layerMenu);
		
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		
		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
		
		selectShapeMenu.add(lineShapeMenuItem);
		selectShapeMenu.add(circleShapeMenuItem);
		selectShapeMenu.add(rectangleShapeMenuItem);

		formatMenu.add(lineThicknessMenu);
		lineThicknessMenu.add(thinLineMenuItem);
		lineThicknessMenu.add(mediumLineMenuItem);
		lineThicknessMenu.add(thickLineMenuItem);
		formatMenu.addSeparator();
		formatMenu.add(colorMenu);
		colorMenu.add(lineColorMenuItem);
		colorMenu.add(fillColorMenuItem);

		layerMenu.add(bringToFrontMenuItem);
		layerMenu.add(sendToBackMenuItem);
		layerMenu.addSeparator();
		layerMenu.add(bringForwardMenuItem);
		layerMenu.add(sendBackwardMenuItem);

		setFormatMenuEnabled(false);
		setLayerMenuEnabled(false);
		setUndoEnabled(false);
		setRedoEnabled(false);
		
		add(drawingPanel, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.handleMenuEvent(MenuEvent.EXIT);
			}
		});

		this.setTitle("JavaPaint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(400, 300));
		this.setVisible(true);
	}

	public void setFormatMenuEnabled(boolean enabled) {
		formatMenu.setEnabled(enabled);
	}

	public void setLayerMenuEnabled(boolean enabled) {
		layerMenu.setEnabled(enabled);
	}

	public void setUndoEnabled(boolean enabled) {
		undoMenuItem.setEnabled(enabled);
	}

	public void setRedoEnabled(boolean enabled) {
		redoMenuItem.setEnabled(enabled);
	}

	public Color showChooseColorDialog(boolean isFill) {
		// Källa för hur man använder en JColorChooser:
		// https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html
		Color defaultColor = isFill ? selectFillColor : selectLineColor;
		Color newColor = JColorChooser.showDialog(this, "Choose color", defaultColor);
		if (newColor != null) {
			if (isFill) {
				selectFillColor = newColor;
			} else {
				selectLineColor = newColor;
			}
		}
		return newColor;
	}
}
