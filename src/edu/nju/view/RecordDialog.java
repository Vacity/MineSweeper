/*
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.nju.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class RecordDialog {
	/**
	 *  
	 */
	public RecordDialog(JFrame parent) {
		super();
		initialization(parent);
	}

	public boolean show(String[] names, String[] score) {
		clear = false;
		this.names = names;
		this.score = score;
		dialog.setVisible(true);
		return clear;
	}
	
	public void show(){
		try{
			int i=0;
			BufferedReader br = new BufferedReader(new FileReader("record.txt"));
			 while((lines[i]=br.readLine())!=null){
				 i++;
	            }
	            br.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		String split1[] = lines[0].split(" ");
		String split2[] = lines[1].split(" ");
		String split3[] = lines[2].split(" ");
		String split4[] = lines[3].split(" ");
		this.names = new String[]{"Easy","Hard","Hell","Custom"};
		this.score = new String[]{split1[4],split2[4],split3[4],split4[4]};
		this.win = new String[]{split1[1],split2[1],split3[1],split4[1]};
		this.total = new String[]{split1[3],split2[3],split3[3],split4[3]};
		show(names,score);
	}

	private void initialization(JFrame parent) {

		dialog = new JDialog(parent, "record", true);

		okBtn = new JButton("ok");
		okBtn.setFont(new Font("Monospaced", Font.PLAIN, 12));
		okBtn.setBounds(100, 155, 70, 23);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});

		clearBtn = new JButton("clear");
		clearBtn.setFont(new Font("Monospaced", Font.PLAIN, 12));
		clearBtn.setBounds(192, 155, 70, 23);
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear = true;
				int length = names.length;
				for (int i = 0; i != length; ++i) {
					names[i] = "Unknow Name";
					score[i] = "0";
				}
				textPanel.repaint();
			}
		});
 
		line = new JSeparator();
		line.setBounds(20, 130, 340, 5);

		panel = new JPanel();
		panel.setLayout(null);

		textPanel = new DescribeTextPanel();
		panel.add(textPanel);

		panel.add(okBtn);
		panel.add(clearBtn);
		panel.add(line);

		dialog.setContentPane(panel);
		dialog.setBounds(parent.getLocation().x + 50,
				parent.getLocation().y + 50, 290, 290);

		clear = false;

	}

	private class DescribeTextPanel extends JPanel {

		DescribeTextPanel() {
			super();
			setBounds(0, 0, 290, 100);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			int length = names.length;
			for (int i = 0; i < length; i++) {
				
				g.drawString("模式："+names[i], 20, 30 * (i + 1));			
				g.drawString("胜率："+win[i]+"/"+total[i]+"  "+String.valueOf(score[i]),150, 30 * (i + 1));
				System.out.println(i);
			}
		}
	}

	private final String[] rank = { "Hard", "Hell","Custom", "Easy"};
  	private JDialog dialog;

	private JPanel panel;

	private JButton okBtn;

	private JButton clearBtn;

	private JSeparator line;

	private String names[];

	private String score[];
	
	private String total[];
	
	private String win [];
   
	private String lines[]= new String[5];
	
	private JPanel textPanel;

	boolean clear;
}