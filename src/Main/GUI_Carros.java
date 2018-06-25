package Main;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

/**
 *
 * @author radames
 */
class GUI_Carros extends JFrame {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel pnSul = new JPanel(cardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnEast = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel pnWest = new JPanel(new GridLayout(2,2));

    private JLabel lbIDCarro = new JLabel("<html><FONT COLOR=#29088A>Número(ID)/Nome do carro</FONT></html>");
    private JLabel lbMarca = new JLabel("<html><FONT COLOR=#29088A>Marca(Fabricante)</FONT></html>");
    private JLabel lbModelo = new JLabel("<html><FONT COLOR=#29088A>Modelo</FONT></html>");
    
    private JColorChooser corzinhabg = new JColorChooser();
    
    private JTextField tfIDCarro = new JTextField(5);
    private JTextField tfMarca = new JTextField(30);
    private JTextField tfModelo = new JTextField(30);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Inserir");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton("Gravar");
    private JButton btColorChooserBackground = new JButton("(F1)Cor do fundo");
    private JButton btVoltarMenu = new JButton();
    
    private JComboBox comboBox = new JComboBox();
    private JRadioButton radiobtcombobox = new JRadioButton();
    private JRadioButton radiobtlistbox = new JRadioButton();
    
    String[] listatt = new String[]{"Layout limpo (branco)","Layout padrão","Pesquisar mais modelos na internet"};
    private JList listBox = new JList(listatt);
    private ButtonGroup btGroup = new ButtonGroup();
    
    private JLabel lbcombobox = new JLabel("<html><FONT COLOR=#29088A>        Combo box</FONT></html>");
    private JLabel lblistbox = new JLabel("<html><FONT COLOR=#29088A>List box</FONT></html>");
    private JLabel lbData = new JLabel("<html><FONT COLOR=BLACK>Data</FONT></html>");
    
    String [] listapop = new String[]{"Voltar ao menu principal"};
    private JPopupMenu popmenu = new JPopupMenu();
    
    private DateTextField_Carros dateTextField1 = new DateTextField_Carros();
    
    String[] colunas = new String[]{"NomeDoCarro", "Marca", "Modelo","Data"};
    String[][] dados = new String[0][4];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    JScrollPane scrollList = new JScrollPane();
    Color color;

    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem

    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas

    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar

    private Controle_Carros controle = new Controle_Carros(); //essa é a classe de processamento (controle da lista de contatos)
    private Contato_Carros contato = new Contato_Carros(); //ver classe contato

    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea
    UsarGridBagLayout usarGridBagLayoutCentro = new UsarGridBagLayout(pnCentro);

    Ferramentas fer = new Ferramentas();

    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void travarTextFields(boolean campo) {
        tfIDCarro.setEditable(campo); //permite que o usuario digite nesse textField
        tfMarca.setEditable(!campo);
        tfModelo.setEditable(!campo);
        if (!campo) {
            tfMarca.requestFocus();
            tfMarca.selectAll();
        }
    }

    private void limparValoresDosAtributos() {
        tfMarca.setText("");
        tfModelo.setText("");
    }
    
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    color = corzinhabg.showDialog(null,"Escolha uma cor para o fundo", Color.CYAN);
                    pnNorte.setBackground(color);
                    pnCentro.setBackground(color);
                    pnEast.setBackground(color);
                    pnWest.setBackground(color);
                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                //System.out.println("2test2");
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                // System.out.println("3test3");
            }
            return false;
        }
    }
    
    //Um tooltip bem estiloso :D
    private String getDefaultToolTip(String message){
   return "<html><p style='background:#555;color:white'>" + message + "</p></html>"; 
}
    private String getDefaultToolTip2(String message){
   return "<html><p style='background:#555;color:yellow'>" + message + "</p></html>"; 
}
    
    
    

    //construtor da classe GUI
    public GUI_Carros() {
        //abrir o arquivo
        List<String> listaAuxiliar = fer.abrirArquivo("Catalogo de carros.txt");
        if (listaAuxiliar != null) {
            for (int i = 0; i < listaAuxiliar.size(); i++) {
                String aux[] = listaAuxiliar.get(i).split(";");
                Contato_Carros c = new Contato_Carros(Integer.valueOf(aux[0]), aux[1], aux[2], aux[3]);
                controle.inserir(c);
                
            }
        }

        //faz com que a última linha do 
        //jTextArea seja exibida
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        pnNorte.setBackground(Color.CYAN);
        cp.add(pnCentro, BorderLayout.CENTER);
        pnCentro.setBackground(Color.CYAN);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnEast, BorderLayout.EAST);
        pnEast.setBackground(Color.CYAN);
        
        cp.add(pnWest);
        pnWest.setBackground(Color.CYAN);
        
        pnNorte.add(lbIDCarro);
        lbIDCarro.setToolTipText(getDefaultToolTip("Apenas um label"));
        pnNorte.add(tfIDCarro);
        tfIDCarro.setToolTipText(getDefaultToolTip("Apenas um textfield"));
       
        pnNorte.add(btBuscar);
        btBuscar.setFont(new Font("Serif", Font.BOLD, 14));
        btBuscar.setIcon(new ImageIcon("Images/lupa.png"));
        btBuscar.setToolTipText(getDefaultToolTip2("Um botão de busca"));
       
        pnNorte.add(btInserir);
        btInserir.setFont(new Font("Serif", Font.BOLD, 14));
        btInserir.setIcon(new ImageIcon("Images/add.png"));
        btInserir.setToolTipText(getDefaultToolTip2("Um botão para inserir"));
        
        pnNorte.add(btAlterar);
        btAlterar.setFont(new Font("Serif", Font.BOLD, 14));
        btAlterar.setIcon(new ImageIcon("Images/alterar.png"));
        btAlterar.setToolTipText(getDefaultToolTip2("Um botão para alterar"));
       
        pnNorte.add(btExcluir);
        btExcluir.setFont(new Font("Serif", Font.BOLD, 14));
        btExcluir.setIcon(new ImageIcon("Images/delete.png"));
        btExcluir.setToolTipText(getDefaultToolTip2("Um botão para excluir"));
        
        pnNorte.add(btSalvar);
        btSalvar.setFont(new Font("Serif", Font.BOLD, 14));
        btSalvar.setIcon(new ImageIcon("Images/salvar.png"));
        btSalvar.setToolTipText(getDefaultToolTip2("Um botão para salvar"));
            
        pnNorte.add(btCancelar);
        btCancelar.setFont(new Font("Serif", Font.BOLD, 14));
        btCancelar.setIcon(new ImageIcon("Images/cancelar.png"));
        btCancelar.setToolTipText(getDefaultToolTip2("Um botão para cancelar"));
        
        pnNorte.add(btListar);
        btListar.setFont(new Font("Serif", Font.BOLD, 14));
        btListar.setIcon(new ImageIcon("Images/lista.png"));
        btListar.setToolTipText(getDefaultToolTip2("Um botão para listar"));
        
        pnNorte.add(btVoltarMenu);
        btVoltarMenu.setToolTipText(getDefaultToolTip2("Voltar ao menu principal"));
        btVoltarMenu.setIcon(new ImageIcon("Images/backarrow.png"));
        
        pnNorte.add(dateTextField1);
        dateTextField1.setToolTipText(getDefaultToolTip2("Escolha a data que será inserida"));
        
        
        comboBox.setVisible(true);
        listBox.setVisible(false);
        
        pnEast.add(lbcombobox);
        pnEast.add(radiobtcombobox);
        pnEast.add(lblistbox);
        pnEast.add(radiobtlistbox);
        radiobtcombobox.setSelected(true);
       
        lbcombobox.setToolTipText(getDefaultToolTip("Apenas um label"));
        radiobtcombobox.setToolTipText(getDefaultToolTip2("Marque esse botão para acionar a forma de combo box (caixinha)"));
        lblistbox.setToolTipText(getDefaultToolTip("Apenas um label"));
        radiobtlistbox.setToolTipText(getDefaultToolTip2("Marque esse botão para acionar a forma de list box (lista)"));
        
        
        
        radiobtcombobox.setBackground(color);
        radiobtlistbox.setBackground(color);
        
        pnEast.add(btColorChooserBackground);
        btColorChooserBackground.setFont(new Font("Serif", Font.BOLD, 14));
        btColorChooserBackground.setIcon(new ImageIcon("Images/paletadecores.png"));
        btColorChooserBackground.setToolTipText(getDefaultToolTip2("Um botão para escolher a cor de fundo (PRESSIONE A TECLA F1)"));
        
        pnEast.add(comboBox);
        comboBox.addItem("Layout limpo (branco)");
        comboBox.addItem("Layout padrão");
        comboBox.addItem("Pesquisar mais modelos na internet");
        comboBox.setSelectedItem(null);
        comboBox.setToolTipText(getDefaultToolTip2("Funções extras"));
        
        pnEast.add(listBox);
        listBox.setToolTipText(getDefaultToolTip2("Funções extras"));
        
//https://stackoverflow.com/questions/11316778/action-listener-on-a-radio-button
        btGroup.add(radiobtcombobox);
        btGroup.add(radiobtlistbox);
        
        
        
//        usarGridBagLayoutCentro.add(lbMarca, tfMarca, lbModelo, tfModelo);
        
        pnWest.add(lbMarca);
        pnWest.add(tfMarca);
        pnWest.add(lbModelo);
        pnWest.add(tfModelo);
        lbMarca.setToolTipText(getDefaultToolTip("Apenas um label"));
        tfMarca.setToolTipText(getDefaultToolTip("Apenas um textfield"));
        lbModelo.setToolTipText(getDefaultToolTip("Apenas um label"));
        tfModelo.setToolTipText(getDefaultToolTip("Apenas um textfield"));

        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("<html><FONT COLOR=#0A2A29><FONT SIZE=-1><i>Atividades Recentes</i></font></html>"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");
        
        pnSul.setBackground(Color.red);

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btInserir.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);

        travarTextFields(true);
        textAreaMsg.setEditable(false);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btGravar.doClick();
                // Sai   
                dispose();
            }
        });

        
        
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //converter a lista<contato> em lista de string
                List<String> listaStr = controle.listar();
                fer.salvarArquivo("Catalogo de carros.txt", listaStr);
       
            }
        });
        
        
//************************* radio box *****************************************

        radiobtcombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                comboBox.setVisible(true);
                listBox.setVisible(false);
                radiobtlistbox.setText("");
            }
        });
        
        radiobtlistbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                comboBox.setVisible(false);
                listBox.setVisible(true);
            }
        });
        
        
//******************** list box ********************************************

        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    JList listat = (JList)lse.getSource();
                    String selected = listat.getSelectedValue().toString();
                    if (selected=="Layout limpo (branco)") {
                        pnNorte.setBackground(Color.WHITE);
                        pnCentro.setBackground(Color.WHITE);
                        pnEast.setBackground(Color.WHITE);
                        pnWest.setBackground(Color.WHITE);
                        comboBox.setSelectedItem("Layout limpo (branco)");
                    }else if (selected=="Layout padrão") {
                        pnNorte.setBackground(Color.CYAN);
                        pnCentro.setBackground(Color.CYAN);
                        pnEast.setBackground(Color.CYAN);
                        pnWest.setBackground(Color.CYAN);
                        comboBox.setSelectedItem("Layout padrão");
                    }else if(selected=="Pesquisar mais modelos na internet"){
                        String url="www.google.com";
                        comboBox.setSelectedItem("Pesquisar mais modelos na internet");
                        try {
                            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                        } catch (IOException ex) {
                            Logger.getLogger(GUI_Carros.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                }
            }
        });


//************************* combo box ***************************************************
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange()==ItemEvent.SELECTED) {
                    Object item = ie.getItem();
                    if (item=="Layout limpo (branco)") {
                        pnNorte.setBackground(Color.WHITE);
                        pnCentro.setBackground(Color.WHITE);
                        pnEast.setBackground(Color.WHITE);
                        pnWest.setBackground(Color.WHITE);
                        listBox.setSelectedValue("Layout limpo (branco)", true);
                    }else if(item=="Pesquisar mais modelos na internet"){
                        String url="www.google.com";
                        listBox.setSelectedValue("Pesquisar mais modelos na internet", true);
                        try {
                            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                        } catch (IOException ex) {
                            Logger.getLogger(GUI_Carros.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(item=="Layout padrão"){
                        pnNorte.setBackground(Color.CYAN);
                        pnCentro.setBackground(Color.CYAN);
                        pnEast.setBackground(Color.CYAN);
                        pnWest.setBackground(Color.CYAN);
                        listBox.setSelectedValue("Layout padrão", true);
                    }
                }
            }
        });

// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {

            if (tfIDCarro.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Querido usuário, vazio não pode (espaços também não)");
            } else {
                try {
                    tfIDCarro.setBackground(Color.green);
                    travarTextFields(true);
                    cardLayout.show(pnSul, "pnMsg");
                    contato = controle.buscar(Integer.valueOf(tfIDCarro.getText()));
                    if (contato == null) { //nao achou
                        btInserir.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        limparValoresDosAtributos();
                        JOptionPane.showMessageDialog(cp, "O carro(ID) inserido não está disponível no estoque, caso queira adicioná-lo clique em inserir");
                    } else { //achou
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btInserir.setVisible(false);
                        tfIDCarro.setText(String.valueOf(contato.getNomeDoCarro()));
                        tfMarca.setText(contato.getMarca());
                        tfModelo.setText(contato.getModelo());
                        setLog("Achou [" + contato.getNomeDoCarro() + "-" + contato.getMarca() + "-" + contato.getData() + "]");
                        
                        
                    }
                } catch (Exception x) {
                    tfIDCarro.selectAll();
                    tfIDCarro.requestFocus();
                    tfIDCarro.setBackground(Color.red);
                    setLog("Erro no tipo de dados da chave. (" + x.getMessage() + ")");
                    JOptionPane.showMessageDialog(cp, "Querido usuário, ID's são compostos por apenas números.");
                }//else
            }
            tfIDCarro.selectAll();
            tfIDCarro.requestFocus();
        });
//********************************* CLIQUE DIREITO (POPUP) ********************************        
      
    

//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Contato_Carros contatoOriginal = contato; //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                contato = new Contato_Carros(); //criar um novo contato
            }
            //transfere os valores da GUI para classe contato
            contato.setNomeDoCarro(Integer.valueOf(tfIDCarro.getText()));
            contato.setMarca(String.valueOf(tfMarca.getText()));
            contato.setModelo(String.valueOf(tfModelo.getText()));
            contato.setData(String.valueOf(dateTextField1.getText()));

            if (inserindo) { //a variavel inserindo é preenchida nos
                controle.inserir(contato);
                setLog("Inseriu [" + contato.getNomeDoCarro() + "-" + contato.getMarca() + contato.getData() + "]");
            } else {//alterar
                controle.alterar(contatoOriginal, contato);
                setLog("Alterou [" + contato.getNomeDoCarro() + "-" + contato.getMarca() + "-" + contato.getData() + "]");
            }

            //voltar para tela inicial
            tfIDCarro.requestFocus();
            tfIDCarro.selectAll();
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            limparValoresDosAtributos();
            travarTextFields(true);
        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIDCarro.requestFocus();
                tfIDCarro.selectAll();
                btInserir.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                travarTextFields(true);
                limparValoresDosAtributos();
                setLog("O usuáriio cancelou a alteração ou inclusão do registro");
            }
        });

//############################# BOTAO ALTERAR #################################
        btAlterar.addActionListener((ActionEvent e) -> {
            tfMarca.requestFocus();
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            btListar.setVisible(false);
            inserindo = false;
            travarTextFields(false);
            setLog("O usuário está alterando o(s) dado(s) de um carro");
        });
//||||||||||||||||||||||||||| BOTÃO INSERIR |||||||||||||||||||||||||||||||||||
        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfMarca.requestFocus();
                btInserir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
                travarTextFields(false);
                limparValoresDosAtributos();
                setLog("O usuário está inserindo um carro novo");
            }
        });

//======================= LISTAR =============================================
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "pnLst");
                scrollList.setPreferredSize(tabela.getPreferredSize());
                pnSulListagem.add(scrollList);
                scrollList.setViewportView(tabela);
                List<String> listaDeContatos = controle.listar();//busca a lista de contatos
                String[] aux;
                colunas = new String[]{"ID do carro", "Marca", "Modelo","Data inserida"};
                dados = new String[0][3];
                model.setDataVector(dados, colunas);
                for (int i = 0; i < listaDeContatos.size(); i++) {
                    aux = listaDeContatos.get(i).split(";");
                    String[] linha = new String[]{aux[0], aux[1], aux[2], aux[3]};
                    // String[] linha = new String[]{"João", "15", "Masculino"};
                    model.addRow(linha);
                }
            }
        });
//***************************** BOTÃO EXCLUIR *************************************
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(cp, "Deseja excluir [ "
                        + tfIDCarro.getText() + "-" + tfMarca.getText() + "]?", "Deletar elemento", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    controle.excluir(contato);
                    setLog("O usuário excluiu [" + contato.getNomeDoCarro() + "-" + contato.getMarca() + "]");

                }
            }
        });
        
//******************** BT VOLTAR MENU *****************************************

        btVoltarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI_Menu gui = new GUI_Menu();
                btGravar.doClick();
                dispose();
            }
        });


//$$$$$$$$$$$$$$ FIM DOS LISTENERS $$$$$$$$$$$$$
        // parâmetros para janela inicial
        setSize(840, 240);
        setTitle("Locadora de automóveis (Carros)");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
