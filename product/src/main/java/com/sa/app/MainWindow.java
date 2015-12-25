package com.sa.app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang.math.NumberUtils;

import com.lowagie.text.DocumentException;
import com.sa.app.util.TrustUtil;
import com.sa.excel.ReadExcel;
import com.sa.execl.vo.Receipt;
import com.sa.execl.vo.Trust;
import com.sa.pdf.PdfGenerator;
import javax.swing.JCheckBox;

public class MainWindow {

	private JFrame frame;
	private JTextField textTrustName;
	private JTextField textPan;
	private JTextField textRegnNo;
	private JTextField textRegnDate;
	private JTextField textTreasurer;
	private JTextField textPhone;
	private JTextField textFileName;
	private JTextField textSkip;
	private JTextField textOutFolder;
    private int skipRows = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 654, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(66, 77, 510, 327);
		frame.getContentPane().add(panel);
		panel.setVisible(false);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(27, 62, 46, 14);
		panel.add(lblName);
		
		textTrustName = new JTextField();
		textTrustName.setBounds(131, 62, 176, 20);
		panel.add(textTrustName);
		textTrustName.setColumns(10);
		
		JLabel lblPan = new JLabel("PAN");
		lblPan.setBounds(27, 87, 46, 14);
		panel.add(lblPan);
		
		textPan = new JTextField();
		textPan.setBounds(131, 87, 176, 20);
		panel.add(textPan);
		textPan.setColumns(10);
		
		JLabel lblRegnNo = new JLabel("Regn No.");
		lblRegnNo.setBounds(27, 112, 67, 14);
		panel.add(lblRegnNo);
		
		textRegnNo = new JTextField();
		textRegnNo.setBounds(131, 112, 176, 20);
		panel.add(textRegnNo);
		textRegnNo.setColumns(10);
		
		JLabel lblRegnDate = new JLabel("Regn Date");
		lblRegnDate.setBounds(27, 143, 67, 14);
		panel.add(lblRegnDate);
		
		textRegnDate = new JTextField();
		textRegnDate.setBounds(131, 143, 176, 20);
		panel.add(textRegnDate);
		textRegnDate.setColumns(10);
		
		JLabel lblTreasurer = new JLabel("Treasurer");
		lblTreasurer.setBounds(27, 168, 94, 14);
		panel.add(lblTreasurer);
		
		textTreasurer = new JTextField();
		textTreasurer.setBounds(131, 174, 176, 20);
		panel.add(textTreasurer);
		textTreasurer.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(27, 203, 67, 14);
		panel.add(lblAddress);
		
		JTextArea textAreaAddr = new JTextArea();
		textAreaAddr.setBounds(131, 205, 176, 47);
		panel.add(textAreaAddr);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(27, 262, 46, 14);
		panel.add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setBounds(131, 263, 176, 20);
		panel.add(textPhone);
		textPhone.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(363, 83, 89, 23);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(363, 139, 89, 23);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(363, 199, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblAddeditTrust = new JLabel("Add/Edit Trust");
		lblAddeditTrust.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddeditTrust.setBounds(121, 11, 269, 32);
		lblAddeditTrust.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAddeditTrust);
		
		JLabel lblTaxExempt = new JLabel("Tax exempt");
		lblTaxExempt.setBounds(27, 302, 67, 14);
		panel.add(lblTaxExempt);
		
		JCheckBox checkBoxTax = new JCheckBox("");
		checkBoxTax.setSelected(true);
		checkBoxTax.setBounds(131, 290, 176, 29);
		panel.add(checkBoxTax);
		
		
		JLabel lblTitle = new JLabel("Generate Receipt");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(254, 11, 141, 23);
		frame.getContentPane().add(lblTitle);
		
		JComboBox<Trust> comboTrust = new JComboBox<Trust>();
		loadTrustCombo(comboTrust);
		comboTrust.setBounds(150, 77, 229, 33);
		comboTrust.setEditable(false);
		frame.getContentPane().add(comboTrust);
		
		JLabel lblTrust = new JLabel("Trust");
		lblTrust.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTrust.setBounds(10, 72, 125, 40);
		frame.getContentPane().add(lblTrust);
		
		JButton btnAddTrust = new JButton("Add/Edit Trust");
		btnAddTrust.setBounds(389, 77, 132, 33);
		frame.getContentPane().add(btnAddTrust);
		
		JPanel panelGenerate = new JPanel();
		panelGenerate.setBounds(10, 156, 612, 207);
		frame.getContentPane().add(panelGenerate);
		panelGenerate.setLayout(null);
		
		JLabel lblFile = new JLabel("File:");
		lblFile.setBounds(0, 4, 110, 14);
		panelGenerate.add(lblFile);
		
		textFileName = new JTextField();
		textFileName.setBounds(131, 1, 380, 20);
		panelGenerate.add(textFileName);
		textFileName.setColumns(10);
		
		JButton btnFile = new JButton("File");
		btnFile.setBounds(521, 0, 89, 23);
		panelGenerate.add(btnFile);
		
		JLabel lblSkipoptional = new JLabel("Skip (optional)");
		lblSkipoptional.setBounds(0, 47, 125, 14);
		panelGenerate.add(lblSkipoptional);
		
		textSkip = new JTextField();
		textSkip.setBounds(127, 47, 80, 20);
		panelGenerate.add(textSkip);
		textSkip.setColumns(10);
		
		JLabel btnOutputFolder = new JLabel("Output Folder");
		btnOutputFolder.setBounds(0, 103, 125, 23);
		panelGenerate.add(btnOutputFolder);
		
		textOutFolder = new JTextField();
		textOutFolder.setBounds(131, 104, 380, 20);
		textOutFolder.setText(new File(System.getProperty("user.home")).getAbsolutePath());
		panelGenerate.add(textOutFolder);
		textOutFolder.setColumns(10);
		
		JButton btnFolder = new JButton("Folder");
		btnFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    textOutFolder.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnFolder.setBounds(523, 103, 89, 23);
		panelGenerate.add(btnFolder);
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.setBounds(10, 156, 592, 14);
		panelGenerate.add(progressBar);
		
		JButton btnGeneratePdfs = new JButton("Generate Pdfs");
		
		btnGeneratePdfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFileName.getText().trim().length()==0){
					JOptionPane.showConfirmDialog(null, "Please select excel file.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(textOutFolder.getText().trim().length()==0){
					JOptionPane.showConfirmDialog(null, "Please select output folder.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(comboTrust.getSelectedItem() != null && ((Trust)comboTrust.getSelectedItem()).getRegnNo().equals("-1")){
					JOptionPane.showConfirmDialog(null, "Please select avalid trust.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					return;
				}
				skipRows = 0;
				if(textSkip.getText().trim().length() == 0 || !NumberUtils.isDigits(textSkip.getText().trim())){
					JOptionPane.showConfirmDialog(null, "Skip should be numeric. Ignoring skip","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				}else{
					skipRows = Integer.parseInt(textSkip.getText().trim());
				}
				btnGeneratePdfs.setEnabled(false);
				progressBar.setValue(0);
				SwingWorker<Integer, Integer> workerTask = new SwingWorker<Integer, Integer>(){
					int count = 0;
					long recNo = 0;
					int val = 0;
					@Override
					protected Integer doInBackground() throws Exception {
						Trust trust= (Trust)comboTrust.getSelectedItem();
						List<Receipt> receipts;
						final String FS = System.getProperty("file.separator");
						try {
							receipts = ReadExcel.readXLSXFile(textFileName.getText(), trust ,skipRows);
							for (Iterator<Receipt> iterator = receipts.iterator(); iterator.hasNext();) {
								Receipt receipt = (Receipt) iterator.next();
								recNo= receipt.getReceiptNo();
								PdfGenerator.generate(receipt, textOutFolder.getText() + FS ,true);
								PdfGenerator.generate(receipt, textOutFolder.getText() + FS + "duplicate" +FS,false);
								count++;
								val = (count * 100)/receipts.size()  ;
								setProgress( val);
							}
							
						} catch (IOException e1) {
							JOptionPane.showConfirmDialog(null, "Unable to read excel file:"+e1.getMessage(),"Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (DocumentException e1) {
							e1.printStackTrace();
						}catch (Exception e2) {
							JOptionPane.showConfirmDialog(null, "Failed to generate pdf for receipt no"+ recNo
									,"Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
						} 
						finally {
							btnGeneratePdfs.setEnabled(true);
						}						
						return new Integer(val);
					}
					
				};
				workerTask.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						 if ("progress".equals(evt.getPropertyName())) {
			                 progressBar.setValue((Integer)evt.getNewValue());
			                 progressBar.repaint();
			             }
					}
				});
					     

				workerTask.execute();
			}
		});
		
		btnGeneratePdfs.setBounds(217, 184, 168, 23);
		panelGenerate.add(btnGeneratePdfs);
				
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("MS Excel", "xlsx"));
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    textFileName.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		btnAddTrust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBounds(66, 77, 510, 327);
				panel.setVisible(true);
				panelGenerate.setVisible(false);
				btnAddTrust.setEnabled(false);
				btnDelete.setEnabled(false);
				if(comboTrust.getSelectedItem() != null && !((Trust)comboTrust.getSelectedItem()).getRegnNo().equals("-1")){
					Trust t = (Trust)comboTrust.getSelectedItem();
					textTrustName.setText(t.getName());
					textPan.setText(t.getPan());
					textRegnNo.setText(t.getRegnNo());
					textRegnDate.setText(t.getRegnDate());
					textAreaAddr.setText(t.getAddress());
					textPhone.setText(t.getPhone());
					textTreasurer.setText(t.getTreasurerName());
					if(t.getTax().equals("true")){
						checkBoxTax.setSelected(true);
					}else{
						checkBoxTax.setSelected(false);
					}
					btnDelete.setEnabled(true);
					btnAdd.setText("Save");
				}else{
					textTrustName.setText("");
					textPan.setText("");
					textRegnNo.setText("");
					textRegnDate.setText("");
					textAreaAddr.setText("");
					textPhone.setText("");
					textTreasurer.setText("");
					checkBoxTax.setSelected(true);
					btnAdd.setText("Add");
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panelGenerate.setVisible(true);
				btnAddTrust.setEnabled(true);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboTrust.getSelectedItem() != null && !((Trust)comboTrust.getSelectedItem()).getRegnNo().equals("-1")){
					comboTrust.removeItemAt(comboTrust.getSelectedIndex());
					btnCancel.doClick();
					TrustUtil.save(comboTrust);
					}
				}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textTrustName.getText().trim().length() == 0 || 
						textPan.getText().trim().length() == 0 || 
						textRegnNo.getText().trim().length() == 0 || 
						textRegnDate.getText().trim().length() == 0 || 
						textAreaAddr.getText().trim().length() == 0 || 
						textPhone.getText().trim().length() == 0 || 
						textTreasurer.getText().trim().length() == 0){
					JOptionPane.showConfirmDialog(null, "Please fill all field.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					return;
				}
				Trust t ;
				boolean skipAdd = false;
				if(comboTrust.getSelectedItem() != null && ((Trust)comboTrust.getSelectedItem()).getRegnNo().equals("-1")){
					t = new Trust();
					skipAdd = true;
				}else{
					t = (Trust)comboTrust.getSelectedItem();					
				}
				comboTrust.setEditable(true);
				t.setName(textTrustName.getText());
				t.setPan(textPan.getText());
				t.setRegnNo(textRegnNo.getText());
				t.setRegnDate(textRegnDate.getText());
				t.setAddress(textAreaAddr.getText());
				t.setPhone(textPhone.getText());
				t.setTreasurerName(textTreasurer.getText());
				t.setTax(String.valueOf(checkBoxTax.isSelected()));
				if(skipAdd){comboTrust.addItem(t);}
				comboTrust.setEditable(false);
				TrustUtil.save(comboTrust);
				btnCancel.doClick();
			}
		});
		
	}

	
	private void loadTrustCombo(JComboBox<Trust> comboTrust) {
		List<Trust> trusts = TrustUtil.load();
		if(trusts.isEmpty()){
		Trust item = new Trust();
		item.setRegnNo("-1");
		item.setName("Add new Trust");
		comboTrust.addItem(item);
		TrustUtil.save(comboTrust);
		}else{
			for (Iterator<Trust> iterator = trusts.iterator(); iterator.hasNext();) {
				Trust trust = (Trust) iterator.next();
				comboTrust.addItem(trust);
			}
		}
		
		
	}
}
