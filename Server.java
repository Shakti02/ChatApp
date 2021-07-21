package chatting.application;

import static chatting.application.Client.a1;
import static chatting.application.Client.f1;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;//SOCKET CLAASS
import java.io.*;


import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Server implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    Socket socket;
    static JPanel a1;//use text area not good
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();
    
    static ServerSocket skt;//OBJECTDECLARE
    static Socket s;//OBJECT
    static DataInputStream din;//OBJECT FOT IO
    static DataOutputStream dout;
    
    Boolean typing;
    
    Server(){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();// CREATE DIV
        p1.setLayout(null);//NOT WANT TO USE SWING LAYOUT
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);//MAKE OWN LAYOUT(LOCATION,LOACTION,LENGTH,BREADTH)
        f1.add(p1);
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));//NOT IMAGE GO DIRECTLY IN FRAME MAKE LABEL FOR THIS
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);//IMAGE LENGTH BREADTH CHANGE
       ImageIcon i3 = new ImageIcon(i2);// AGAIN CHANGE TO IMAGEICON FOR GOING TO LABEL
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);
       
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);//(FOR ARROW EXIT(FOR THIS IMPLEMENT ACTIONLISTENER AND ADD PACKAGE AWT.EVENT.*
           }
       });
       
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/1.png"));
       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
       JLabel l2 = new JLabel(i6);
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
       
       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i9 = new ImageIcon(i8);
       JLabel l5 = new JLabel(i9);
       l5.setBounds(290, 20, 30, 30);
       p1.add(l5);
       
       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
       Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i13 = new ImageIcon(i12);
       JLabel l6 = new JLabel(i13);
       l6.setBounds(350, 20, 35, 30);
       p1.add(l6);
       
       ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
       Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
       ImageIcon i16 = new ImageIcon(i15);
       JLabel l7 = new JLabel(i16);
       l7.setBounds(410, 20, 13, 25);
       p1.add(l7);
       
       
       JLabel l3 = new JLabel("Gaitonde");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);   
       
       
       JLabel l4 = new JLabel("Active Now");
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 100, 20);
       p1.add(l4);  
       
       Timer t = new Timer(1, new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
           }
       });
       
       t.setInitialDelay(2000);
       
       
       a1 = new JPanel();
       a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       f1.add(a1);
       
       JScrollPane sp=new JScrollPane(a1);
       sp.setBounds(5, 75, 440, 570);
       sp.setBorder(BorderFactory.createEmptyBorder());
      // f1.add(sp);
       
       ScrollBarUI ui=new BasicScrollBarUI(){
           protected JButton createDecreaseButton(int oritention){
               JButton button=super.createDecreaseButton(oritention);
               button.setBackground(new Color(7,94,84));
               button.setForeground(Color.WHITE);
               return button;
           }
           protected JButton createIncreaseButton(int oritention){
               JButton button=super.createDecreaseButton(oritention);
               button.setBackground(new Color(7,94,84));
               button.setForeground(Color.WHITE);
               return button;
           }
       };
       
       sp.getVerticalScrollBar().setUI(ui);
       f1.add(sp);
       
       
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       f1.add(t1);
       
       t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){
               l4.setText("typing...");
               
               t.stop();
               
               typing = true;
           }
           
           public void keyReleased(KeyEvent ke){
               typing = false;
               
               if(!t.isRunning()){
                   t.start();
               }
           }
       });
       
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       f1.add(b1);
        
       f1.getContentPane().setBackground(Color.WHITE);
       f1.setLayout(null);
       f1.setSize(450, 700);
       f1.setLocation(400, 100); 
       f1.setUndecorated(true);//REMOVE FRAME DEFAULT DIV
       f1.setVisible(true);//ALWAYS LAST END WRITE
        
    }
    //OVERRIDE THE INTERFACE ACTIONPERFORMED
    
    public void actionPerformed(ActionEvent ae){
        try{
            String out = t1.getText();//TEXT FEILD IS NIKALNI H VALUE
            sendTextToFile(out);
            JPanel p2 = formatLabel(out);
            
            a1.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            
            a1.add(vertical, BorderLayout.PAGE_START);
            
            //a1.add(p2);
            dout.writeUTF(out);
            t1.setText("");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void sendTextToFile(String message) throws FileNotFoundException
    {
        //when we deal with eternal file chance of file is present or not
        try(FileWriter f=new FileWriter("chat.txt");
                PrintWriter p=new PrintWriter(new BufferedWriter(f));){
            p.println("Gaitonde:" +message);
          
        }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);//for visible blue color
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
    
    public static void main(String[] args){
        new Server().f1.setVisible(true);
        
        String msginput = "";
        try{
            skt = new ServerSocket(6001);//DEFINE SOCKET PORT NO(6001)
            while(true){
                s = skt.accept();//SERVER OBJECT THAT ACCEPT DATA
                din = new DataInputStream(s.getInputStream());//DATA COMES FROM SOCKET SO S.//CAME DATA
                dout = new DataOutputStream(s.getOutputStream());//SEND DATA
            
	        while(true){
	                msginput = din.readUTF();
                        JPanel p2 = formatLabel(msginput);
                        
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(p2, BorderLayout.LINE_START);
                        vertical.add(left);
                        f1.validate();
            	}
                
            }
            
        }catch(Exception e){}
    }    
}
