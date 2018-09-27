package br.com.biblioteca.telas;

/**
 *
 * @author Weslley Silva
 */
import java.sql.*;
import br.com.biblioteca.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /*private void consultar() {
        String sql = "select * from usuario where idusuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuEndereco.setText(rs.getString(7));
                txtUsuCpf.setText(rs.getString(5));
                txtUsuFone.setText(rs.getString(3));

            } else {
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }*/
    private void adicionar() {
        String sql = "insert into usuario(usuario,fone,email,cpf,perfil,endereco,login,senha) values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuEmail.getText());
            pst.setString(4, txtUsuCpf.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuEndereco.getText());
            pst.setString(7, txtUsuLogin.getText());
            pst.setString(8, senha.getText());
            // atualiza os dados da tabela
            // a estrutura abaixo é usa para confirma inserção de dados na tabela

            if ((txtUsuNome.getText().isEmpty()) || (txtUsuCpf.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (senha.getText().isEmpty()) || (cboUsuPerfil.getSelectedItem().toString().isEmpty())) {
                JOptionPane.showConfirmDialog(null, "prencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showConfirmDialog(null, "Usuário Cadastrado com sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuEndereco.setText(null);
                    txtUsuLogin.setText(null);
                    senha.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }
    //metodo pesquisar clientes

    private void pesquisar() {
        String sql = "select * from usuario where usuario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            
            //passando o conteudo da caixa de conteudo para ?
            // atenção com % que é contiuação  da String sql
            pst.setString(1, txtUsuPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // alinha abaixo usar a biblioteca rs2xml que foi importada parapreencher a tabela
            tblUsuario.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

    //metodo para setar os campos do formulario com o conteudo da tabela
    public void setar_campo() {
        int setar = tblUsuario.getSelectedRow();
        txtId.setText(tblUsuario.getModel().getValueAt(setar, 0).toString());
        txtUsuNome.setText(tblUsuario.getModel().getValueAt(setar, 1).toString());
        txtUsuEndereco.setText(tblUsuario.getModel().getValueAt(setar, 6).toString());
        txtUsuCpf.setText(tblUsuario.getModel().getValueAt(setar, 4).toString());
        txtUsuEmail.setText(tblUsuario.getModel().getValueAt(setar, 3).toString());
        txtUsuFone.setText(tblUsuario.getModel().getValueAt(setar, 2).toString());
        senha.setText(tblUsuario.getModel().getValueAt(setar, 8).toString());
        txtUsuLogin.setText(tblUsuario.getModel().getValueAt(setar, 7).toString());
        
        // a linha desabilita o botao adicionar 
        btnCadastrar.setEnabled(false);

    }
    //metodo para alterar dados do cliente
    public void alterar(){
        String sql = "update usuario set usuario=?,fone=?,email=?,cpf=?,perfil=?,endereco=?,login=?,senha=? where idusuario=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            
      
         pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuEmail.getText());
            pst.setString(4, txtUsuCpf.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuEndereco.getText());
            pst.setString(7, txtUsuLogin.getText());
            pst.setString(8, senha.getText());
            pst.setString(9, txtId.getText());
            
            
            if ((txtUsuNome.getText().isEmpty()) || (txtUsuCpf.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (senha.getText().isEmpty()) || (cboUsuPerfil.getSelectedItem().toString().isEmpty())) {
                JOptionPane.showConfirmDialog(null, "prencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showConfirmDialog(null, "Usuário alterado com sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuEndereco.setText(null);
                    txtUsuLogin.setText(null);
                    senha.setText(null);
                     btnCadastrar.setEnabled(true);


                }
            }
            
           
            
        } catch (Exception e) {
        }
    
    
    }
    public void remover(){
    // a estrutua abaixo confirma a remoção de um usuario
    int confirmar=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover?","Atenção!",JOptionPane.YES_NO_OPTION);
        if (confirmar==JOptionPane.YES_OPTION) {
            String sql="delete from usuario where idusuario=?";
            try {
                 pst = conexao.prepareStatement(sql);
                   pst.setString(1, txtId.getText());
                   int apagado= pst.executeUpdate();
                   
                   if(apagado>0){
                   JOptionPane.showConfirmDialog(null, "Usuário removido com sucesso!");
                   txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuEndereco.setText(null);
                    txtUsuLogin.setText(null);
                    senha.setText(null);
                  btnCadastrar.setEnabled(true);

                  
                   
                   }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);

            }
            
        } else {
        }
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuEndereco = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuEmail = new javax.swing.JTextField();
        senha = new javax.swing.JPasswordField();
        txtUsuPesquisar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtUsuCpf = new javax.swing.JFormattedTextField();
        txtUsuFone = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuário");
        setToolTipText("");
        getContentPane().setLayout(null);

        jLabel1.setText("* Nome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 184, 50, 20);

        jLabel2.setText("Endereco");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 224, 60, 20);

        jLabel3.setText("* CPF");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 260, 50, 20);

        jLabel4.setText("* Senha ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(310, 300, 90, 30);

        jLabel5.setText("* Login");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(310, 260, 60, 30);

        jLabel6.setText("Email");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 300, 40, 30);

        jLabel7.setText("* Perfil");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(310, 340, 60, 30);

        jLabel8.setText(" Fone");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 344, 40, 30);

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuNome);
        txtUsuNome.setBounds(90, 180, 410, 30);
        getContentPane().add(txtUsuEndereco);
        txtUsuEndereco.setBounds(90, 220, 240, 30);
        getContentPane().add(txtUsuLogin);
        txtUsuLogin.setBounds(360, 260, 200, 30);

        txtUsuEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuEmail);
        txtUsuEmail.setBounds(90, 300, 200, 30);
        getContentPane().add(senha);
        senha.setBounds(360, 300, 200, 30);

        txtUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuPesquisarActionPerformed(evt);
            }
        });
        txtUsuPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuPesquisar);
        txtUsuPesquisar.setBounds(20, 20, 350, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478118009_Zoom.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(380, 20, 50, 30);

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 70, 560, 90);

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(cboUsuPerfil);
        cboUsuPerfil.setBounds(360, 340, 200, 30);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478020974_file_add.png"))); // NOI18N
        btnCadastrar.setToolTipText("Cadastro");
        btnCadastrar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(130, 430, 80, 80);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478021262_file_edit.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(250, 430, 80, 80);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478021182_file_delete.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(370, 430, 80, 80);

        jLabel10.setText("* Campos Obrigatórios!");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(440, 390, 150, 30);

        jLabel11.setText("ID");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(50, 390, 30, 20);

        txtId.setEnabled(false);
        getContentPane().add(txtId);
        txtId.setBounds(90, 380, 80, 30);

        try {
            txtUsuCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtUsuCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuCpfActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuCpf);
        txtUsuCpf.setBounds(90, 260, 190, 30);

        try {
            txtUsuFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtUsuFone);
        txtUsuFone.setBounds(90, 340, 200, 30);

        setBounds(0, 0, 607, 611);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuEmailActionPerformed

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void txtUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuPesquisarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // chamando metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    // O EVENTO A BAIXO É QUANDO FOR DIGITANDO VAI APARECENDO NA TABELA
    private void txtUsuPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuPesquisarKeyReleased
        // CHAMAR O METODO PERQUISAR
        pesquisar();
    }//GEN-LAST:event_txtUsuPesquisarKeyReleased
    // evento para setar os campos quando clikar na tabela
    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // TODO add your handling code here:
        setar_campo();
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        remover();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed

    private void txtUsuCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuCpfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField senha;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtId;
    private javax.swing.JFormattedTextField txtUsuCpf;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuEndereco;
    private javax.swing.JFormattedTextField txtUsuFone;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuPesquisar;
    // End of variables declaration//GEN-END:variables
}
