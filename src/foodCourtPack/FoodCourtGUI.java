package foodCourtPack;

/**
 * @author Brianne Kerr
 * April 18, 2017
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class FoodCourtGUI implements ActionListener, Runnable
{
	JFrame frame;
	JSplitPane splitPane;
	JPanel inputPanel, statsPanel;
	final int promptNum = 6;
	JLabel[] promptLabels;
	JLabel[] statsLabels, statsValues, eatLabels, eatValues;
	JTextField[] promptFields;
	JButton startSim, pauseSim, addEat, subEat, addCash, subCash;
	boolean isRunning, hasRun;
	Sim simulation;
	
	/**
	 * Creates the GUI frame, adds the appropriate features, and makes it visible
	 */
	public FoodCourtGUI()
	{
		frame = new JFrame("Food Court Simulation");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		inputPanel = new JPanel(new GridLayout(0, 2));
		statsPanel = new JPanel(new GridLayout(0, 4));
		
		inputPanel.setPreferredSize(new Dimension(200, 300));
		statsPanel.setPreferredSize(new Dimension(400, 300));
		
		this.setPromptLabels();
		this.setPromptFields();
		for(int i = 0; i < promptNum; i++)
		{
			inputPanel.add(promptLabels[i]);
			inputPanel.add(promptFields[i]);
		}

		this.setButtons();
		inputPanel.add(startSim);
		inputPanel.add(pauseSim);
		inputPanel.add(addEat);
		inputPanel.add(subEat);
		inputPanel.add(addCash);
		inputPanel.add(subCash);
		
		statsLabels = new JLabel[Sim.numStats];
		statsValues = new JLabel[Sim.numStats];
		eatLabels = new JLabel[9];
		eatValues = new JLabel[9];
		String[] temp = Sim.getStatLabel();
		for(int i = 0; i < statsLabels.length; i++)
		{
			statsLabels[i] = new JLabel(temp[i]);
			statsPanel.add(statsLabels[i]);
			statsValues[i] = new JLabel("0");
			statsPanel.add(statsValues[i]);
			
			eatLabels[i] = new JLabel("");
			statsPanel.add(eatLabels[i]);
			eatValues[i] = new JLabel("");
			statsPanel.add(eatValues[i]);
		}
		
		
		splitPane.add(inputPanel);
		splitPane.add(statsPanel);
		
		isRunning = false;
		hasRun = false;
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
	    
		frame.add(splitPane);
		frame.setSize(new Dimension(1200, 600));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
	
	/**
	 * During initialization, this sets the labels next to the text fields
	 */
	private void setPromptLabels()
	{
		promptLabels = new JLabel[promptNum];
		promptLabels[0] = new JLabel("Seconds To Next Person");
		promptLabels[1] = new JLabel("Average Seconds Per Eatery");
		promptLabels[2] = new JLabel("Average Seconds Per Cashier");
		promptLabels[3] = new JLabel("Seconds Before Person Leaves");
		promptLabels[4] = new JLabel("Number of Eateries");
		promptLabels[5] = new JLabel("Number of Cashiers");
	}
	
	/**
	 * Used during initialization, this creates the JTextFields
	 */
	private void setPromptFields()
	{
		promptFields = new JTextField[promptNum];
		for(int i = 0; i < promptFields.length; i++)
		{
			promptFields[i] = new JTextField(20);
		}
	}
	
	/**
	 * Used during initialization, this creates the needed buttons
	 */
	private void setButtons()
	{
		startSim = new JButton("Start Sim");
		startSim.addActionListener(this);
		pauseSim = new JButton("Pause Sim");
		pauseSim.addActionListener(this);
		addEat = new JButton("Add Eatery");
		addEat.addActionListener(this);
		subEat = new JButton("Subtract Eatery");
		subEat.addActionListener(this);
		addCash = new JButton("Add Cashier");
		addCash.addActionListener(this);
		subCash = new JButton("Subtract Cashier");
		subCash.addActionListener(this);
	}
	
	
	/**
	 * Reacts appropriately to pressed buttons
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == startSim)
		{
			if(!hasRun)
			{
				for(int i = 0; i < promptNum; i++)
				{
					this.promptFields[i].setEditable(false);
				}
				for(int i = 0; i < Integer.parseInt(promptFields[4].getText()); i++)
				{
					eatLabels[i].setText("Eatery " + (i + 1) + ": ");
					eatValues[i].setText("0");
				}
				this.simulation = new Sim(Integer.parseInt(promptFields[0].getText()),
								Integer.parseInt(promptFields[1].getText()), 
								Integer.parseInt(promptFields[2].getText()),
								Integer.parseInt(promptFields[3].getText()),
								Integer.parseInt(promptFields[4].getText()),
								Integer.parseInt(promptFields[5].getText()));
				hasRun = true;
			}
			
			isRunning = true;

		}
		if(e.getSource() == pauseSim)
		{
			isRunning = false;
		}
		
		if(e.getSource() == addEat)
		{
			boolean temp = isRunning;
			int newEatNum = Integer.parseInt(promptFields[4].getText()) + 1;
			isRunning = false;
			simulation.addEatery();
			isRunning = temp;
			promptFields[4].setText(Integer.toString(newEatNum));
			eatLabels[newEatNum - 1].setText("Eatery " + newEatNum + ": ");
			eatValues[newEatNum - 1].setText("0");
			
			if(Integer.parseInt(promptFields[4].getText()) == 9)
			{
				addEat.setEnabled(false);
			}
			else
			{
				subEat.setEnabled(true);
			}
		}
		if(e.getSource() == subEat)
		{
			boolean temp = isRunning;
			int newEatNum = Integer.parseInt(promptFields[4].getText()) - 1;
			isRunning = false;
			simulation.subEatery();
			isRunning = temp;
			
			promptFields[4].setText(Integer.toString((newEatNum)));
			eatValues[newEatNum].setText("");
			eatLabels[newEatNum].setText("");
			
			if(Integer.parseInt(promptFields[4].getText()) == 1)
			{
				subEat.setEnabled(false);
			}
			else
			{
				addEat.setEnabled(true);
			}
		}
		
		if(e.getSource() == addCash)
		{
			boolean temp = isRunning;
			isRunning = false;
			simulation.addCashier();
			isRunning = temp;
			promptFields[5].setText(Integer.toString(Integer.parseInt(promptFields[5].getText()) + 1));
			
			if(Integer.parseInt(promptFields[5].getText()) == 9)
			{
				addCash.setEnabled(false);
			}
			else
			{
				subCash.setEnabled(true);
			}
		}
		if(e.getSource() == subCash)
		{
			boolean temp = isRunning;
			isRunning = false;
			simulation.subCashier();
			isRunning = temp;
			promptFields[5].setText(Integer.toString(Integer.parseInt(promptFields[5].getText()) - 1));
			
			if(Integer.parseInt(promptFields[5].getText()) == 1)
			{
				subCash.setEnabled(false);
			}
			else
			{
				addCash.setEnabled(true);
			}
		}
		
	}
	
	/**
	 * Retrieves updates statistics from simulation and updates the info on the GUI
	 */
	private void updateStats()
	{

		int[] temp = simulation.getStatValue();
		for(int i = 0; i < temp.length; i++)
		{
			this.statsValues[i].setText(Integer.toString(temp[i]));
		}
		
		int[] temp2 = simulation.getNumberInEachLine();
		for(int i = 0; i < temp2.length; i++)
		{
			this.eatValues[i].setText(Integer.toString(temp2[i]));
		}
	}
	
	/**
	 * Implements the method from Runnable. Runs the simulation through a tick and
	 * then updates the statistics, while isRunning is true
	 */
	public void run() 
	{
		if(isRunning == true)
		{
			simulation.tick();
			this.updateStats();
		}
	}
	
	
	public static void main(String [] args)
	{
		@SuppressWarnings("unused")
		FoodCourtGUI sim = new FoodCourtGUI();
	}

	
}
