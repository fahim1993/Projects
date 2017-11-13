import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;




public class Calculator implements ActionListener  {

    private String br1, br2, inputSt, lstRslt="", rs1, rs2, rs, roo1;
    char [] cInput1, cInput2; char ch1, ch2, chSy1, chSy2, chbSy, chbSy1, tm;
    private JMenuBar mb;
    private JMenu file, inputFormat;
    private int  brkt=0, i;
    private final JFrame frame;
    private final JPanel panel, panel1, panel2, panel3, panel4, panel5;
    private final JTextField tfIn, tfOut;
    private Font font, inviFont, bfont1, p3f1, p3f2, p4f2;
    private final JLabel label, invis, invis1, p3l1, p3l2, p4l2, p4l3;
    private JButton b1, b2, b3, b4, b5 , b6, b7, b8, b9, b0, bANS, bDEL, bDot, bDivide, bMultiply, bSubtract,
            bAdd, bClear, bMod, bPower, bRoot, bBracket, back, bk ;
    private final Dimension d1 , d2, d3, d4, d5, d6, d7,d8;
    private final Insets m;
    private final Color c;
    private BigDecimal bd1, bd2, bd3;
    private final MathContext mc ;


    public Calculator(){

        cInput1 = new char[32];
        p3f1 = new Font("Serif", Font.PLAIN, 18);
        p3f2 = new Font("Calibri", Font.BOLD, 18);

        p4f2 = new Font("Calibri", Font.PLAIN, 12);
        cInput2 = new char[32];
        mc = new MathContext(30);
        c=new Color(183, 25, 25);
        frame = new JFrame("Calculator");
        frame.setSize(314,385);


        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d1= new Dimension(160, 200);
        d2= new Dimension(110, 201);
        d3= new Dimension(305, 350);
        d4= new Dimension(3, 20);
        d5= new Dimension(40, 30);
        d6= new Dimension(314, 385);
        d7= new Dimension(150, 270);
        d8= new Dimension(140, 270);
        m=new Insets(1,1,1,1);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(d6);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        mb= new JMenuBar();
        file = new JMenu("Info   ");
        file.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {



                panel.setVisible(false);
                panel4.setVisible(false);
                panel3.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        file.setFocusable(false);

        inputFormat = new JMenu("Input Format");
        inputFormat.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {

                panel.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        inputFormat.setFocusable(false);

        mb.add(file);
        mb.add(inputFormat);
        inviFont = new Font("Serif", Font.PLAIN, 2);
        font = new Font("Serif", Font.PLAIN, 18);

        invis=new JLabel(".............................................................."
                + "........................................................"
                + "....................................................................."
                + ".................................................................");
        invis.setFont(inviFont);
        invis1=new JLabel(".");
        invis1.setFont(inviFont);
        invis1.setPreferredSize(d4);
        invis1.setForeground(frame.getBackground());
        label = new JLabel("  Simple Calculator");
        label.setFont(font);
        p4l2 = new JLabel("<html>Input in following<br>format:<br><br>1.&nbsp;&nbsp;&nbsp;&nbsp;x#y<br>"
                + "2.&nbsp;&nbsp;&nbsp;&nbsp;-x#-y<br>3.&nbsp;&nbsp;&nbsp;&nbsp;√x (x is positive)<br>"
                + "4.&nbsp;&nbsp;&nbsp;&nbsp;√x#√y<br>5.&nbsp;&nbsp;&nbsp;&nbsp;(x#y)<br>"
                + "6.&nbsp;&nbsp;&nbsp;&nbsp;(-x#-y)<br>7.&nbsp;&nbsp;&nbsp;&nbsp;(x#y)#(x#y)<br>"
                + "8.&nbsp;&nbsp;&nbsp;&nbsp;(-x#-y)#(-x#-y)<br>9.&nbsp;&nbsp;&nbsp;&nbsp;x^(any integer)<br><br>"
                + "# = + | - | * | / | % <br><br>x or y = Any decimal number</html>");
        p4l3 = new JLabel ("<html>Errors:<br><br>1.&nbsp;&nbsp;Divide by zero<br>2.&nbsp;&nbsp;Input space"
                + "<br>3.&nbsp;&nbsp;Input alphabets<br>4.&nbsp;&nbsp;Any symbol which is not provided<br>"
                + "5.&nbsp;&nbsp;Input length more that 30<br>6.&nbsp;&nbsp;Output length more than 25<br>"
                + "7.&nbsp;&nbsp;Rooting negetive numbers<br>8.&nbsp;&nbsp;Exponent is a non integer value<br><br><br></html>");
        panel4.setPreferredSize(d6);
        p4l2.setPreferredSize(d7);
        p4l3.setPreferredSize(d8);

        tfIn = new JTextField(20);
        tfIn.setHorizontalAlignment(JTextField.RIGHT);
        tfIn.setFont(font);
        tfIn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                math();
            }
        });
        tfIn.setText("");
        tfOut = new JTextField(20);
        tfOut.setFont(font);

        tfOut.setHorizontalAlignment(JTextField.RIGHT);
        tfOut.setEditable(false);
        tfOut.setBackground(Color.WHITE);


        panel1.setLayout(new GridLayout(4,3,10,10));
        panel1.setPreferredSize(d1);


        panel2.setLayout(new GridLayout(5,2,8,8));
        panel2.setPreferredSize(d2);

        panel3.setPreferredSize(d3);
        p3l1 = new JLabel("Created By:");
        p3l1.setFont(p3f1);
        p3l2 = new JLabel("<html>FAHIM SHAHRIAR<br>011141091</html>");
        p3l2.setFont(p3f2);
        p3l2.setBorder(BorderFactory.createEmptyBorder(10,0,110,0));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(80,0,0,00));
        panel3.add(p3l1);
        panel3.add(p3l2);
        panel5.setPreferredSize(d5);
        panel5.setLayout(new FlowLayout());

        panel3.add(panel5);

        panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        p4l2.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        panel4.add(p4l2);panel4.add(p4l3);


        panel3.setVisible(false);
        panel4.setVisible(false);
        createButtons();



        frame.setResizable(false);


        frame.setJMenuBar(mb);
        panel.add(label);
        panel.add(tfIn);
        panel.add(tfOut);
        panel.add(invis);
        panel.add(panel1);
        panel.add(invis1);
        panel.add(panel2);
        frame.add(panel);
        frame.add(panel3);
        frame.add(panel4);

        frame.setVisible(true);

    }

    public void math(){
        int k;
        nullify();
        inputSt = tfIn.getText();
        if(inputSt.length()==0) return;

        if(inputSt.length()>30){
            tfOut.setText("Input too long");
            return;
        }
        tfOut.setText("");
        cInput1 = inputSt.toCharArray();
        ch1 = cInput1[0];
        switch (ch1){
            case '(':
                int flag =0, j;
                for(i=1; i<cInput1.length; i++){
                    if(cInput1[i] == ')'){flag=1; break;}
                }
                if(flag==0 || i==1){
                    tfOut.setText("");
                    return;
                }
                else {

                    if(inputSt.length()>2 && cInput1[1]=='-'){
                        j = chknum(inputSt,2);
                        br1 = inputSt.substring(1, j);
                    }
                    else {
                        j = chknum(inputSt,1);
                        br1 = inputSt.substring(1, j);
                    }
                    if(br1.length()==0){
                        tfOut.setText("Invalid input");
                        return;
                    }
                    if(j<inputSt.length()){
                        chbSy = cInput1[j];

                    }
                    else {
                        tfOut.setText("");
                        return;
                    }
                    if(j+1<inputSt.length()){
                        j++;
                    }
                    else {
                        tfOut.setText("");
                        return;
                    }

                    if(inputSt.length()>1+j && cInput1[j]=='-'  ){

                        br2 = inputSt.substring(j, chknum(inputSt,1+j));

                    }
                    else {
                        br2 = inputSt.substring(j, chknum(inputSt,j));

                    }
                    if(br2.length()==0){
                        tfOut.setText("Invalid input");
                        return;
                    }
                    else {
                        rs1 = sum(br1, chbSy, br2);
                        if(rs1.equals("\0")){
                            tfOut.setText("Invalid input");
                            return;
                        }
                        else {
                            outFrmt(rs1);
                            i++;
                            break;
                        }
                    }
                }
            case '√' :
                if(inputSt.length()>2 && cInput1[1]=='-'){
                    tfOut.setText("Invalid input");
                    return;
                }
                if(inputSt.length()==1)
                {
                    tfOut.setText("");
                    return;
                }
                try{
                    i = chknum(inputSt, 1);
                    roo1 = inputSt.substring(1, i);
                    bd1 = new BigDecimal(roo1);
                    bd3 = bigSqrt(bd1);
                    rs1 = bd3.toPlainString();
                    outFrmt (rs1);
                    break;
                }
                catch(Exception e){
                    tfOut.setText("Invalid input");
                    return;
                }
            case '-' :
            case '.' :
                i=chknum(inputSt, 1);
                rs1= inputSt.substring(0,i);
                break;
            default :
                i = chknum(inputSt, 0);
                rs1= inputSt.substring(0,i);

        }
        if(rs1.length()==0){
            tfOut.setText("");
            return;
        }
        if(i<inputSt.length()){
            chSy1=cInput1[i];
            tfOut.setText("");
        } else return;

        if(i+1<inputSt.length()){
            i++;
            ch2 = cInput1[i];
        } else return;
        k=i;

        switch (ch2){
            case '(':
                int flag =0, j;
                for(i=i+1; i<cInput1.length; i++){
                    if(cInput1[i] == ')'){flag=1; break;}
                }
                if(flag==0 || i-k==1){
                    tfOut.setText("");
                    return;
                }
                else {

                    if(inputSt.length()>2+k && cInput1[1+k]=='-'){
                        j = chknum(inputSt,2+k);
                        br1 = inputSt.substring(1+k, j);
                    }
                    else {
                        j = chknum(inputSt,1+k);
                        br1 = inputSt.substring(1+k, j);
                    }
                    if(br1.length()==0){
                        tfOut.setText("Invalid input");
                        return;
                    }
                    if(j<inputSt.length()){
                        chbSy = cInput1[j];

                    }
                    else {
                        tfOut.setText("");
                        return;
                    }
                    if(j+1<inputSt.length()){
                        j++;
                    }
                    else {
                        tfOut.setText("");
                        return;
                    }

                    if(inputSt.length()>1+j && cInput1[j]=='-'  ){

                        br2 = inputSt.substring(j, chknum(inputSt,1+j));

                    }
                    else {
                        br2 = inputSt.substring(j, chknum(inputSt,j));

                    }
                    if(br2.length()==0){
                        tfOut.setText("Invalid input");
                        return;
                    }
                    else {
                        rs2 = sum(br1, chbSy, br2);
                        if(rs2.equals("\0")){
                            tfOut.setText("Invalid input");
                            return;
                        }
                        else {

                            i++;
                            break;
                        }
                    }
                }

            case '√' :

                if(inputSt.length()>2+k && cInput1[1+k]=='-'){
                    System.out.println(cInput1[k]);
                    tfOut.setText("Invalid input");
                    return;
                }

                if(inputSt.length()-k==1)
                {
                    tfOut.setText("");
                    return;
                }
                try{
                    i = chknum(inputSt, 1+k);
                    roo1 = inputSt.substring(1+k, i);
                    bd1 = new BigDecimal(roo1);
                    bd3 = bigSqrt(bd1);
                    rs2 = bd3.toPlainString();

                    break;
                }
                catch(Exception e){
                    tfOut.setText("Invalid input");
                    return;
                }
            case '-' :
            case '.' :
                i=chknum(inputSt, 1+k);
                rs2= inputSt.substring(k,i);

                break;
            default :
                i = chknum(inputSt, k);
                rs2= inputSt.substring(k,i);


        }

        if(rs2.length()==0){
            tfOut.setText("");
            return;
        }
        rs = sum (rs1, chSy1, rs2);
        outFrmt (rs);

        if(i<inputSt.length()){
            chSy2=cInput1[i];
            tfIn.setText(lstRslt+chSy2);
            tfOut.setText("");
        } else return;




    }

    public String sum(String sA, char symbol, String sB){
        String rss;
        int power;
        try{
            bd1=new BigDecimal(sA);
            bd2 = new BigDecimal(sB);

        }
        catch (Exception e){
            return "/0";
        }
        try{

            if(symbol=='+'){

                bd3=bd1.add(bd2, mc);
                rss = bd3.toPlainString();
                return rss;
            }
            else if(symbol=='-'){

                bd3=bd1.subtract(bd2, mc);
                rss = bd3.toPlainString();
                return rss;

            }
            else if(symbol=='*'){

                bd3=bd1.multiply(bd2, mc);
                rss = bd3.toPlainString();
                return rss;

            }
            else if(symbol=='/'){
                if(sB.equals("0")){
                    return "/0";
                }
                bd3=bd1.divide(bd2, mc);
                rss = bd3.toPlainString();

                return rss;

            }
            else if(symbol=='%'){

                bd3=bd1.remainder(bd2, mc);
                rss = bd3.toPlainString();
                return rss;
            }
            else if(symbol=='^'){
                try{
                    power= Integer.parseInt(sB);
                }
                catch(Exception e){

                    return "/0";
                }
                bd3=bd1.pow(power, mc);
                rss = bd3.toPlainString();
                return rss;
            }

        }
        catch (Exception e){
            return "/0";
        }
        return "/0";

    }

    public int chknum (String s, int i){
        int l = s.length();
        char [] temp = s.toCharArray();
        for (; i<l; i++){
            if(temp[i]=='1' ||temp[i]=='2' ||temp[i]=='3' ||temp[i]=='4' ||temp[i]=='5' ||temp[i]=='6' ||
                    temp[i]=='7' ||temp[i]=='8' ||temp[i]=='9' ||temp[i]=='0' ||temp[i]=='.' )continue;
            else return i;

        }
        return i;
    }

    public void outFrmt(String s){
        if(s.equals("/0")){
            tfOut.setText("Invalid input");
            return;
        }
        int i =s.length(), flag=0,k=0;
        for(int j=0; j<i; j++){
            if(s.charAt(j)=='.')flag=1;
            if(flag==0)k++;
        }
        if(flag ==1){
            if(s.length()>k+11)
                s = s.substring(0, k+11);
        }

        if(s.length()>26){
            tfOut.setText("Output too big");
            return;
        }
        else {
            tfOut.setText(s);
            lstRslt =s;
            return;
        }


    }

    public  BigDecimal bigSqrt(BigDecimal value) {

        /*
        COLLECTED FROM:
        http://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java
        */
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void nullify(){
        br1=null; br2=null; inputSt=null; rs1=null; rs2=null; rs=null; roo1= null;
        ch1=0; ch2=0; chSy1=0; chSy2=0; chbSy=0; i = 0; tm=0;
    }

    public void createButtons(){

        bfont1 = new Font("Serif", Font.BOLD, 18);
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        b0 = new JButton("0");
        bDEL = new JButton("DEL");
        bDot = new JButton(".");
        bDivide = new JButton("/");
        bMultiply = new JButton("*");
        bSubtract = new JButton("-");
        bAdd = new JButton("+");
        bClear = new JButton("C");
        bMod = new JButton("%");
        bPower = new JButton("^");
        bRoot = new JButton("√");
        bANS = new JButton("ANS");
        bBracket = new JButton("( )");
        back = new JButton("Back");
        bk = new JButton("Back");

        b1.setFocusable(false); b1.setFont(bfont1);
        b2.setFocusable(false); b2.setFont(bfont1);
        b3.setFocusable(false);  b3.setFont(bfont1);
        b4.setFocusable(false);  b4.setFont(bfont1);
        b5.setFocusable(false);   b5.setFont(bfont1);
        b6.setFocusable(false);   b6.setFont(bfont1);
        b7.setFocusable(false);   b7.setFont(bfont1);
        b8.setFocusable(false);   b8.setFont(bfont1);
        b9.setFocusable(false);   b9.setFont(bfont1);
        b0.setFocusable(false);    b0.setFont(bfont1);
        bDEL.setFocusable(false); bDEL.setFont(bfont1); bDEL.setMargin(m); bDEL.setForeground(c);
        bDot.setFocusable(false);   bDot.setFont(bfont1);
        bDivide.setFocusable(false);   bDivide.setFont(bfont1);
        bMultiply.setFocusable(false);  bMultiply.setFont(bfont1);
        bSubtract.setFocusable(false);  bSubtract.setFont(bfont1);
        bAdd.setFocusable(false);   bAdd.setFont(bfont1);
        bClear.setFocusable(false); bClear.setFont(bfont1); bClear.setMargin(m); bClear.setForeground(c);
        bMod.setFocusable(false);   bMod.setFont(bfont1); bMod.setMargin(m);
        bPower.setFocusable(false);   bPower.setFont(bfont1);
        bRoot.setFocusable(false);   bRoot.setFont(bfont1); bRoot.setMargin(m);
        bANS.setFocusable(false); bANS.setFont(bfont1); bANS.setMargin(m);
        bBracket.setFocusable(false); bBracket.setFont(bfont1); bBracket.setMargin(m);


        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"1";
                tfIn.setText(inputSt);

                math();
            }
        });
        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"2";
                tfIn.setText(inputSt);

                math();
            }
        });
        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"3";
                tfIn.setText(inputSt);

                math();
            }
        });
        b4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"4";
                tfIn.setText(inputSt);

                math();
            }
        });
        b5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"5";
                tfIn.setText(inputSt);

                math();
            }
        });
        b6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"6";
                tfIn.setText(inputSt);

                math();
            }
        });
        b7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"7";
                tfIn.setText(inputSt);

                math();
            }
        });
        b8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"8";
                tfIn.setText(inputSt);

                math();
            }
        });
        b9.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"9";
                tfIn.setText(inputSt);

                math();
            }
        });
        b0.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"0";
                tfIn.setText(inputSt);

                math();
            }
        });
        bDEL.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText();
                if(inputSt.length()==0)return;

                tm = inputSt.charAt(inputSt.length()-1);
                if(tm==')'){
                    brkt=1;
                }
                else if (tm=='('){
                    brkt=0;
                }

                inputSt = inputSt.substring(0, inputSt.length()-1);

                tfIn.setText(inputSt);
                math();
            }
        });
        bDot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt  = tfIn.getText()+".";
                tfIn.setText(inputSt );
                math();
            }
        });
        bDivide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"/";
                tfIn.setText(inputSt);
                math();
            }
        });
        bMultiply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"*";
                tfIn.setText(inputSt);
                math();
            }
        });
        bSubtract.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"-";
                tfIn.setText(inputSt);
                math();
            }
        });
        bAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"+";
                tfIn.setText(inputSt);
                math();
            }
        });

        bMod.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"%";
                tfIn.setText(inputSt);
                math();
            }
        });
        bPower.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt = tfIn.getText()+"^";
                tfIn.setText(inputSt);
                math();
            }
        });

        bClear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                tfIn.setText("");
                tfOut.setText("");
                math();

            }
        });

        bRoot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt= tfIn.getText()+ "√";
                tfIn.setText(inputSt);
                math();
            }
        });

        bANS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inputSt=tfIn.getText()+lstRslt;
                tfIn.setText(inputSt);
                math();

            }
        });

        bBracket.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(brkt==0){
                    inputSt=tfIn.getText()+"(";
                    tfIn.setText(inputSt);
                    math();
                    brkt=1;
                }
                else {
                    inputSt=tfIn.getText()+")";
                    tfIn.setText(inputSt);
                    math();
                    brkt=0;
                }
            }
        });

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                panel3.setVisible(false);
                panel4.setVisible(false);
                panel.setVisible(true);
            }
        });
        bk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                panel3.setVisible(false);
                panel4.setVisible(false);
                panel.setVisible(true);
            }
        });



        panel1.add(b7); panel1.add(b8); panel1.add(b9);
        panel1.add(b4); panel1.add(b5); panel1.add(b6);
        panel1.add(b1); panel1.add(b2);  panel1.add(b3);
        panel1.add(b0); panel1.add(bDot); panel1.add(bBracket);

        panel2.add(bDEL);  panel2.add(bClear);
        panel2.add(bDivide);  panel2.add(bRoot);
        panel2.add(bMultiply); panel2.add(bPower);
        panel2.add(bSubtract);  panel2.add(bMod);
        panel2.add(bAdd); panel2.add(bANS);
        panel4.add(bk);
        panel5.add(back);



    }
}