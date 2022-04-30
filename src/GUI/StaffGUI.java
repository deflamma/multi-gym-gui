package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
//import fit_exception.RecordNotFoundException;
import DTO.*;
import DAO.*;

// ���� ���� gui
public class StaffGUI {

	Frame frame;
	Panel[] panel;
	Label lbTitle, lbCode, lbName, lbGender, lbAge, lbPhone, lbProgram, lbStatus;
	TextField tCode, tName, tGender, tAge, tPhone, tProgram;
	Button bSearch, bSave, bUpdate, bDelete, bBack, bReset;
	List list;
	Checkbox chMale, chFemale;
	CheckboxGroup chGroup;
	
	
	//FGUI ������
	
	public StaffGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ���� ����");
		panel = new Panel[20];
		for(int i=0; i<panel.length; i++) {
			panel[i]= new Panel(); 
		}
		lbTitle = new Label("���� ����", Label.CENTER);
		Font font = new Font("dialog", Font.BOLD, 20);
		lbTitle.setFont(font);
		lbTitle.setForeground(new Color(142,24,232));
	
		lbCode = new Label("�����ڵ�");
		lbName = new Label("�̸�");
		lbGender = new Label("����");
		lbAge = new Label("����");
		lbPhone = new Label("��ȭ��ȣ");
		lbProgram = new Label("������α׷�");
		
		lbStatus = new Label("");
		lbStatus.setBackground(Color.LIGHT_GRAY);
		
		tCode = new TextField(10);
		tName = new TextField(10);
		tAge = new TextField(5);
		tPhone = new TextField(15);
		tProgram = new TextField(15);
		
		bSearch = new Button("�˻�");
		bSave = new Button("����");
		bUpdate = new Button("����");
		bDelete = new Button("����");
		bReset = new Button("�ʱ�ȭ");
		bBack = new Button("�ڷΰ���");
		
		chGroup = new CheckboxGroup();
		chMale = new Checkbox("��", chGroup, false);
		chFemale = new Checkbox("��", chGroup, false);
		
		list = new List(20, false);
		
		bBack.addActionListener(new ActionListener() { // �ڷΰ��� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
                simg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		
		//������ �����ư Ȱ��ȭ
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public void launchFrame() {
		
		
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lbName);
		panel[1].add(lbCode);
		
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tName);
		panel[2].add(tCode);
		
		panel[3].setLayout(new GridLayout(2,1));
		panel[3].add(lbPhone);
		panel[3].add(lbProgram);
		
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(tPhone);
		panel[4].add(tProgram);
		
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(lbAge);
		panel[5].add(lbGender);
		
		panel[6].setLayout(new BorderLayout());
		panel[6].add(tAge, "North");
		panel[6].add(chMale, "West");
		panel[6].add(chFemale, "Center");
		
		panel[7].setLayout(new BorderLayout());
		panel[7].add(panel[1], "West");
		panel[7].add(panel[2], "Center");
		panel[7].add(new Label(""), "East");
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "West");
		panel[8].add(panel[4], "Center");
		panel[8].add(new Label(""), "East");
		
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[5], "West");
		panel[9].add(panel[6], "Center");
		panel[9].add(new Label(""), "East");
		
		// �� + �ؽ�Ʈ�ʵ� �г�
		panel[10].setLayout(new BorderLayout());
		panel[10].add(panel[7], "West");
		panel[10].add(panel[8], "Center");
		panel[10].add(panel[9], "East");
		
		panel[11].add(bSearch);
		panel[11].add(new Label(" "));
		panel[11].add(bSave);
		panel[11].add(new Label(" "));
		panel[11].add(bUpdate);
		panel[11].add(new Label(" "));
		panel[11].add(bDelete);
		panel[11].add(new Label(" "));
		panel[11].add(bReset);
		panel[11].add(new Label(" "));
		panel[11].add(bBack);
		
		panel[16].setLayout(new BorderLayout());
		panel[16].add(lbTitle, "North");
		panel[16].add(panel[10], "Center");
		panel[16].add(panel[11], "South");
		
		panel[12].setLayout(new GridLayout(1,6));
		panel[12].setBackground(Color.black);
		panel[12].setForeground(Color.white);
		panel[12].add(new Label("�����ڵ�"));
		panel[12].add(new Label("�̸�"));
		panel[12].add(new Label("����"));
		panel[12].add(new Label("����"));
		panel[12].add(new Label("��ȭ��ȣ"));
		panel[12].add(new Label("������α׷�"));
		
		panel[13].setLayout(new BorderLayout());
		panel[13].add(panel[12], "North");
		panel[13].add(list, "Center");
		
		panel[14].setLayout(new BorderLayout());
		panel[14].add(panel[16], "North");
		panel[14].add(panel[13], "Center");
		
		frame.add(panel[14]);
		frame.add(lbStatus, "South");
		
		frame.pack();
		frame.setResizable(false);
		
		//�����쿡���� ȭ���� ��ġ
		frame.setLocation(((frame.getToolkit().getScreenSize()).width -
				frame.getWidth())/2,
				((frame.getToolkit().getScreenSize()).height - 
						frame.getHeight())/2);
		
		frame.setSize(600,450);
		frame.setVisible(true);
		
		displayAll();
		
	
		//ȸ������ �����ϱ�. �����ϱ� ��ư�� ������ �����ϰ� �����
		bSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String sCode = tCode.getText();
				String sName = tName.getText();
				int sAge = Integer.parseInt(tAge.getText());  //String? Int?
				String sPhone = tPhone.getText();
				String sProgram = tProgram.getText();
				
				String sGender = "��";
				if (chMale.getState()) {
					sGender = "��";
				}
				
				StaffDAO fa = new StaffDAO();
				fa.insert(sCode, sName, sGender, sAge, sPhone, sProgram);
				
				displayAll();

			}
		});
		
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				String str = list.getSelectedItem(); //"������ 17..."
				StringTokenizer st = new StringTokenizer(str);
				
				tCode.setText(st.nextToken());
				tName.setText(st.nextToken());
				String gender = st.nextToken();
				
				if (gender.equals("��")) {					
					chMale.setState(true);					
				} else { chFemale.setState(true);

				}
				tAge.setText(st.nextToken());
				tPhone.setText(st.nextToken());
				tProgram.setText(st.nextToken());
				
			}
		});
		
		
		//ȸ������ ������ �����ϱ�
		
		bDelete.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String sCode = tCode.getText();	
				StaffDAO fa = new StaffDAO();						
				fa.delete(sCode);
				displayAll();			
			}
		});
				
		bUpdate.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String sCode = tCode.getText();
				String sName = tName.getText();
				String sGender = "��";		
				if (chMale.getState()) {	
					sGender = "��";
				}
				int sAge = Integer.parseInt(tAge.getText());
				String sPhone = tPhone.getText();
				String sProgram = tProgram.getText();	
				
				
						
				StaffDAO fa = new StaffDAO();		
				fa.update(sCode, sName, sGender, sAge, sPhone, sProgram);		
				displayAll();						
			}
		});
				
				
		//�˻��ϱ� ��ư ����
		bSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String sName = tName.getText();

				
				StaffDAO fa = new StaffDAO();				
				display(fa, sName);
				
			}
		});
		
		//�ʱ�ȭ ��ư ����
		bReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tCode.setText("");
				tName.setText("");
				tAge.setText("");
				tPhone.setText("");
				tProgram.setText("");
				chGroup.setSelectedCheckbox(null);
				displayAll();
			}
			
		});
		
	}//end launch
	
		//ȸ������ ����
		
		private void displayAll() {

		list.removeAll();  //ȭ�鿡 �ִ� �߰��� �������� ������ ����Ʈ�� ������ ����� ��
		StaffDAO fa = new StaffDAO();
		ArrayList<StaffDTO> allData = fa.select();
		for(StaffDTO ft : allData) {
			
			String code = ft.getsCode();
			String name = ft.getsName();
			String gender = ft.getsGender();
			int age = ft.getsAge();
			String phone = ft.getsPhone();
			String program = ft.getsProgram();
			
			list.add(code+"          "+
					name+"                        "+
					gender+"                             "+
					age+"                      "+
					phone+"            "+
					program	);
			
		}
	} 
	
		private void display(StaffDAO fa, String sName) {
			
			list.removeAll();
			ArrayList<StaffDTO> allData = fa.search(sName);
		
			for(StaffDTO ft : allData) {				
				String sCode = ft.getsCode();
				String sName1 = ft.getsName();
				String sGender = ft.getsGender();
				int sAge = ft.getsAge();
				String sPhone = ft.getsPhone();
				String sProgram = ft.getsProgram();
			
				list.add(sCode+"          "+
						sName1+"                        "+
						sGender+"                             "+
						sAge+"                      "+
						sPhone+"            "+
						sProgram	);
			}
		}
		
		public static void main(String[] args) {
			StaffGUI sg = new StaffGUI();
			sg.launchFrame();
			
	}

}



