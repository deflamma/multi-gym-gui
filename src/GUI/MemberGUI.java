package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import DAO.*;
import DTO.*;

// ȸ�� ���� ���� gui(���� �̿�)
public class MemberGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblmCode, lblproCode, lblmName, lblmGender, lblmAge, lblmPhone, lblmLocker, lblmVaccine, lblmPro, lblStatus;
	TextField tfmCode, tfproCode, tfmName, tfmAge, tfmPhone, tfmLocker, tfmPro;
	Button btnSearch, btnSave, btnUpdate, btnDelete, btnBack, btnReset;
	List list;
	Checkbox chMale, chFemale, chYes, chNo;
	CheckboxGroup chGroup1, chGroup2;
	
	public MemberGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ȸ������");
		panel = new Panel[21];
		for (int i=0; i<panel.length; i++) {
			panel[i]=new Panel();	
		}
		lblTitle = new Label ("�ｺ�� ȸ������", Label.CENTER);
		Font font = new Font ("dialog", Font.BOLD, 30);
		lblTitle.setFont(font);
		lblTitle.setForeground(new Color(142,24,232));
		lblmCode = new Label("ȸ����ȣ");
		lblproCode = new Label("���α׷��ڵ�");
		lblmName = new Label("�� ��");
		lblmAge = new Label("�� ��");
		lblmPhone = new Label("��ȭ��ȣ");
		lblmLocker = new Label("��Ŀ��");
    	lblmGender = new Label("�� ��");
    	lblmVaccine = new Label("��ſ���");
    	lblmPro = new Label("���α׷���");
    	lblStatus = new Label("");
    	lblStatus.setBackground(Color.LIGHT_GRAY);
    	
    	tfmCode = new TextField("");
    	tfmName = new TextField("");
    	tfproCode = new TextField("");
    	tfmPhone = new TextField("");
    	tfmPro = new TextField("");
    	tfmLocker = new TextField("");
    	tfmAge = new TextField("");
    
    	btnSearch = new Button("�˻�");
    	btnSave = new Button("����");
    	btnUpdate = new Button("����");
    	btnDelete = new Button("����");
    	btnReset = new Button("�ʱ�ȭ");
    	btnBack = new Button("�ڷΰ���");
    
    	chGroup1 = new CheckboxGroup();
    	chGroup2 = new CheckboxGroup();
    	chMale = new Checkbox("Male", chGroup1,false);
    	chFemale = new Checkbox("Female", chGroup1,false);
    	chYes = new Checkbox("Yes", chGroup2, false);
    	chNo = new Checkbox("No", chGroup2, false);
    	list = new List(11,false);
    	
    	btnBack.addActionListener(new ActionListener() { // �ڷΰ��� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoStaffGUI sisg = new SelectInfoStaffGUI();
                sisg.launchFrame();
                frame.setVisible(false);
            }
        });
    	
    	frame.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			System.exit(0);
    		}
    	});
	}
	
	public void launchFrame() {
		// ȭ�鼳���ϱ�
		//�̸� ȸ����ȣ
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lblmName);
		panel[1].add(lblmCode);
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tfmName);
		panel[2].add(tfmCode);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		//���α׷��� ���α׷��ڵ�
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(lblmPro);
		panel[4].add(lblproCode);
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(tfmPro);
		panel[5].add(tfproCode);
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "West");
		panel[6].add(panel[5], "Center");
		panel[6].add(new Label(""), "East");
		//��ȭ��ȣ ����
		panel[7].setLayout(new GridLayout(2,1));
		panel[7].add(lblmPhone);
		panel[7].add(lblmAge);
		panel[8].setLayout(new GridLayout(2,1));
		panel[8].add(tfmPhone);
		panel[8].add(tfmAge);
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[7], "West");
		panel[9].add(panel[8], "Center");
		panel[9].add(new Label(""), "East");
		//��Ŀ�� ������� ����
		panel[10].setLayout(new GridLayout(1,2));
		panel[10].add(lblmLocker);
		panel[10].add(tfmLocker);
		panel[11].setLayout(new GridLayout(2,1));
		panel[11].add(lblmVaccine);
		panel[11].add(lblmGender);
		panel[12].setLayout(new GridLayout(2,1));
		panel[12].add(chYes);
		panel[12].add(chMale);
		panel[13].setLayout(new GridLayout(2,1));
		panel[13].add(chNo);
		panel[13].add(chFemale);
		panel[14].add(panel[11]);
		panel[14].add(panel[12]);
		panel[14].add(panel[13]);
		panel[15].setLayout(new BorderLayout());
		panel[15].add(panel[10], "North");
		panel[15].add(panel[14], "Center");
		//3 6 9 15 ����
		panel[16].setLayout(new GridLayout(1,4));
		panel[16].add(panel[3]);
		panel[16].add(panel[6]);
		panel[16].add(panel[9]);
		panel[16].add(panel[15]);
		//Ÿ��Ʋ
		panel[17].setLayout(new BorderLayout());
		panel[17].add(lblTitle, "North");
		panel[17].add(panel[16], "Center");
		panel[17].add(panel[0], "South");
		//�� �̸�
		panel[18].setLayout(new GridLayout(1,8));
		panel[18].setBackground(Color.black);
		panel[18].setForeground(Color.white);
		panel[18].add(new Label(" ȸ����ȣ "));
		panel[18].add(new Label(" ���α׷��ڵ�  "));
		panel[18].add(new Label(" �̸� "));
		panel[18].add(new Label(" ���� "));
		panel[18].add(new Label(" ���� "));
		panel[18].add(new Label(" ��ȭ��ȣ "));
		panel[18].add(new Label(" ���α׷��� "));
		panel[18].add(new Label(" ��Ŀ�� "));
		panel[18].add(new Label(" ��� "));
		panel[19].setLayout(new BorderLayout());
		panel[19].add(panel[18], "North");
		panel[19].add(list, "Center");
		//��ư
		panel[0].add(btnSave);
		panel[0].add(new Label(" "));
		panel[0].add(btnSearch);
		panel[0].add(new Label(" "));
		panel[0].add(btnDelete);
		panel[0].add(new Label(" "));
		panel[0].add(btnUpdate);
		panel[0].add(new Label(" "));
		panel[0].add(btnReset);
		panel[0].add(new Label(" "));
		panel[0].add(btnBack);
		
		panel[20].setLayout(new BorderLayout());
		panel[20].add(panel[17], "North");
		panel[20].add(panel[19], "Center");
		
		frame.add(panel[20], "Center");
		frame.add(lblStatus, "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - 
		frame.getWidth())/2 -100,
		((frame.getToolkit().getScreenSize()).height - frame.getHeight())/2 -100);
		frame.setSize(1200,700);
		frame.setVisible(true);
		displayAll();
		
		//ȸ������ �����ϱ� (�����ϱ� ��ư�� ������ �����ϱ� �̺�Ʈ)
				btnSave.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//�����ư�� ������ �������� �ڵ带 ����
						String mCode = tfmCode.getText();
						String proCode = tfproCode.getText(); //tfAge�� �Է��� ���� ��� ���ڿ� ó����(int x, String o)
						String mName = tfmName.getText();
						String mGender = "��";
						if(chMale.getState()) {
							mGender = "��";
						}
						String mAge = tfmAge.getText();
						String mPhone = tfmPhone.getText();
						String mPro = tfmPro.getText();
						String mLocker = tfmLocker.getText();
						String mVaccine = "����";
						if(chNo.getState()) {
							mVaccine = "������";
						}
						
						MemberDAO mdao = new MemberDAO();
						mdao.insert(mCode, proCode, mName, mGender, mAge , mPhone, mLocker, mVaccine, mPro ); // �������� �������� �ִ� �޼ҵ�� ȣ��
						displayAll();
					}
				});
				
				
				list.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						// ����Ʈ�� �׸���� ������ ó������ ������ �ڵ带 ����
						String str = list.getSelectedItem();//"������  17 50 150 ��"
						StringTokenizer st = new StringTokenizer(str);
						tfmCode.setText(st.nextToken());
						tfproCode.setText(st.nextToken());
						tfmName.setText(st.nextToken());
						String mGender = st.nextToken();
						if(mGender.equals("��")) chMale.setState(true);
						else chFemale.setState(true);
						tfmAge.setText(st.nextToken());
						tfmPhone.setText(st.nextToken());
						tfmPro.setText(st.nextToken());
						tfmLocker.setText(st.nextToken());
						String mVaccine = st.nextToken();
						if(mVaccine.equals("������")) chNo.setState(true);
						else chYes.setState(true);
					}
				});
				
				//������ �����ϱ�
				btnDelete.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// �����ϱ�
						String mName = tfmName.getText();
						MemberDAO mdao = new MemberDAO();
						mdao.delete(mName);
						displayAll();
					}
				});
				
				//�����ϱ�
				btnUpdate.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String mCode = tfmCode.getText();
						String proCode = tfproCode.getText(); //tfAge�� �Է��� ���� ��� ���ڿ� ó����(int x, String o)
						String mName = tfmName.getText();
						String mGender = "��";
						if(chMale.getState()) {
							mGender = "��";
						}
						String mAge = tfmAge.getText();
						String mPhone = tfmPhone.getText();
						String mPro = tfmPro.getText();
						String mLocker = tfmLocker.getText();
						String mVaccine = "����";
						if(chNo.getState()) {
							mVaccine = "������";
						}						
						MemberDAO mdao = new MemberDAO();
						mdao.update(mCode, proCode, mName, mGender, mAge, mPhone, mPro, mLocker, mVaccine);
						displayAll();
					}
				});
				
				//�˻��ϱ� ��ư ����
				btnSearch.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String mName = tfmName.getText();
						MemberDAO mdao = new MemberDAO();
						display(mdao, mName);		
					}
				});
				
				//�ʱ�ȭ ��ư ����
				btnReset.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						tfmCode.setText("");
						tfproCode.setText("");
						tfmName.setText("");
						tfmAge.setText("");
						tfmPhone.setText("");
						tfmLocker.setText("");
						tfmPro.setText("");
						chGroup1.setSelectedCheckbox(null);
						chGroup2.setSelectedCheckbox(null);
						displayAll();
					}
					
				});
	     }//end launch()
				
				// ȸ������ ����
				private void displayAll() {
					list.removeAll(); // ȭ�鿡 �ִ� �߰��� �������� ������ ����Ʈ�� ������ ������ ��
					MemberDAO mdao = new MemberDAO();
					ArrayList<MemberDTO> allDate = mdao.select();
					for(MemberDTO mdto : allDate) {
						int mCode = mdto.getmCode();
					    String proCode = mdto.getProCode();
						String mName = mdto.getmName();	
						String mGender = mdto.getmGender();
						int mAge = mdto.getmAge();
						String mPhone = mdto.getmPhone();
						String mPro = mdto.getmPro();
						String mLocker = mdto.getmLocker();
						String mVaccine = mdto.getmVaccine();
						list.add(mCode + "                                         " +proCode + "                          " + 
								mName + "                                       " + mGender + 
								"                                     " + mAge + "                               " + mPhone + 
								"                          " + mPro + "                                 " + mLocker + 
								"                                  "+ mVaccine ); //ȭ�鿡 �ִ� ����Ʈ�� �ٿ���(add)
					}
					
				}
				
				private void display(MemberDAO mdao, String mName) {
					list.removeAll();
					ArrayList<MemberDTO> allData = mdao.search(mName);
					for(MemberDTO mdto : allData) {
					int mCode = mdto.getmCode();
						String proCode = mdto.getProCode();
						String mName1 = mdto.getmName();	
						String mGender = mdto.getmGender();
						int mAge = mdto.getmAge();
						String mPhone = mdto.getmPhone();
						String mPro = mdto.getmPro();
						String mLocker = mdto.getmLocker();
						String mVaccine = mdto.getmVaccine();
						list.add(mCode + "                                         " +proCode + "                          " + 
								mName1 + "                                       " + mGender + 
								"                                     " + mAge + "                               " + mPhone + 
								"                          " + mPro + "                                 " + mLocker + 
								"                                  "+ mVaccine ); //ȭ�鿡 �ִ� ����Ʈ�� �ٿ���(add)
				}
				
		}

				
	public static void main(String[] args) {
		
		MemberGUI meb = new MemberGUI();
		meb.launchFrame();
	}

}




