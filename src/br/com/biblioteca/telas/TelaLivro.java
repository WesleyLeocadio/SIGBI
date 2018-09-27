/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblioteca.telas;

/**
 *
 * @author Weslley Silva
 */
import java.sql.*;
import br.com.biblioteca.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class TelaLivro extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaLivro
     */
   
         Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        public TelaLivro() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
      
        
        private void adicionar() {
        String sql = "insert into livro(titulo,isbn,ano,volume,edicao,estante,valor,editora,autor,quantidade) values(?,?,?,?,?,?,?,?,?,?)";
        try {
              
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLiTitulo.getText());
            pst.setString(2, txtLiIsbn.getText());
            pst.setString(3, txtLiAno.getText());
            pst.setString(4, txtLiVolume.getText());
            pst.setString(5, txtLiEdicao.getText());
            pst.setString(6, txtLiEstante.getText());
            pst.setString(7, txtLiValor.getText());
            pst.setString(8, txtLiEditora.getText());
              pst.setString(9, txtLiAutor.getText());
               pst.setString(10, txtLiQuantidade.getText());
            // atualiza os dados da tabela
            // a estrutura abaixo é usa para confirma inserção de dados na tabela

            if ((txtLiTitulo.getText().isEmpty()) || (txtLiIsbn.getText().isEmpty()) || (txtLiAno.getText().isEmpty()) || (txtLiVolume.getText().isEmpty()) || (txtLiEdicao.getText().isEmpty()||(txtLiEditora.getText().isEmpty()|| (txtLiAutor.getText().isEmpty())))) {
                JOptionPane.showConfirmDialog(null, "prencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showConfirmDialog(null, "Usuário Cadastrado com sucesso!");
                    txtLiTitulo.setText(null);
                    txtLiIsbn.setText(null);
                    txtLiAno.setText(null);
                    txtLiVolume.setText(null);
                    txtLiEdicao.setText(null);
                    txtLiEstante.setText(null);
                    txtLiValor.setText(null);
                    txtLiEditora.setText(null);
                    txtLiAutor.setText(null);
                    txtLiQuantidade.setText(null);
                   

                }
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }
        
      
        
        
        
        private void pesquisar() {
        String sql = "select * from livro where titulo like ?";
        try {
            pst = conexao.prepareStatement(sql);
            
            //passando o conteudo da caixa de conteudo para ?
            // atenção com % que é contiuação  da String sql
            pst.setString(1, txtLiPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // alinha abaixo usar a biblioteca rs2xml que foi importada parapreencher a tabela
            tblLivro.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }
       
        //metodo para setar os campos do formulario com o conteudo da tabela
    public void setar_campo() {
        
        int setar = tblLivro.getSelectedRow();
        txtId.setText(tblLivro.getModel().getValueAt(setar, 0).toString());
        txtLiTitulo.setText(tblLivro.getModel().getValueAt(setar, 1).toString());
        txtLiIsbn.setText(tblLivro.getModel().getValueAt(setar, 2).toString());
        txtLiAno.setText(tblLivro.getModel().getValueAt(setar, 3).toString());
        txtLiVolume.setText(tblLivro.getModel().getValueAt(setar, 4).toString());
        txtLiEdicao.setText(tblLivro.getModel().getValueAt(setar, 5).toString());
        txtLiEstante.setText(tblLivro.getModel().getValueAt(setar, 6).toString());
        txtLiValor.setText(tblLivro.getModel().getValueAt(setar, 7).toString());
        txtLiEditora.setText(tblLivro.getModel().getValueAt(setar, 8).toString());
         txtLiAutor.setText(tblLivro.getModel().getValueAt(setar, 9).toString());
          txtLiQuantidade.setText(tblLivro.getModel().getValueAt(setar, 10).toString());
        
        // a linha desabilita o botao adicionar 
        btnAdicionar.setEnabled(false);
        

    }
      public void alterar(){
        String sql = "update livro set titulo=?,isbn=?,ano=?,volume=?,edicao=?,estante=?,valor=?,editora=?,autor=?,quantidade=? where idlivro=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            
      
         pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLiTitulo.getText());
            pst.setString(2, txtLiIsbn.getText());
            pst.setString(3, txtLiAno.getText());
            pst.setString(4, txtLiVolume.getText());
            pst.setString(5, txtLiEdicao.getText());
            pst.setString(6, txtLiEstante.getText());
            pst.setString(7, txtLiValor.getText());
            pst.setString(8, txtLiEditora.getText());
              pst.setString(9, txtLiAutor.getText());
               pst.setString(10, txtId.getText());
                 pst.setString(11, txtLiQuantidade.getText());
             
            
            if ((txtLiTitulo.getText().isEmpty()) || (txtLiIsbn.getText().isEmpty()) || (txtLiAno.getText().isEmpty()) || (txtLiVolume.getText().isEmpty()) || (txtLiEdicao.getText().isEmpty()||(txtLiEditora.getText().isEmpty()|| (txtLiAutor.getText().isEmpty())))) {
                JOptionPane.showConfirmDialog(null, "prencha todos os campos obrigatórios");

            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showConfirmDialog(null, "Livro editado com sucesso!");
                     txtLiTitulo.setText(null);
                    txtLiIsbn.setText(null);
                    txtLiAno.setText(null);
                    txtLiVolume.setText(null);
                    txtLiEdicao.setText(null);
                    txtLiEstante.setText(null);
                    txtLiValor.setText(null);
                    txtLiEditora.setText(null);
                    txtLiAutor.setText(null);
                      txtId.setText(null);
                       txtLiQuantidade.setText(null);


                }
            }
            
           
            
        } catch (Exception e) {
        }
    
  }
    
    
    
     public void remover(){
    // a estrutua abaixo confirma a remoção de um usuario
    int confirmar=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover?","Atenção!",JOptionPane.YES_NO_OPTION);
        if (confirmar==JOptionPane.YES_OPTION) {
            String sql="delete from livro where idLivro=?";
            try {
                 pst = conexao.prepareStatement(sql);
                   pst.setString(1, txtId.getText());
                   int apagado= pst.executeUpdate();
                   
                   if(apagado>0){
                   JOptionPane.showConfirmDialog(null, "Livro removido com sucesso!");
                   txtLiTitulo.setText(null);
                    txtLiIsbn.setText(null);
                    txtLiAno.setText(null);
                    txtLiVolume.setText(null);
                    txtLiEdicao.setText(null);
                    txtLiEstante.setText(null);
                    txtLiValor.setText(null);
                    txtLiEditora.setText(null);
                    txtLiAutor.setText(null);
                    txtId.setText(null);
                     txtLiQuantidade.setText(null);
                  btnAdicionar.setEnabled(true);

                  
                   
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

        txtLiPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLivro = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLiTitulo = new javax.swing.JTextField();
        txtLiEditora = new javax.swing.JTextField();
        txtLiIsbn = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtLiAno = new javax.swing.JTextField();
        txtLiEstante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLiAutor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtLiValor = new javax.swing.JTextField();
        txtLiVolume = new javax.swing.JTextField();
        txtLiEdicao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtLiQuantidade = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Livro");
        getContentPane().setLayout(null);

        txtLiPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLiPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtLiPesquisar);
        txtLiPesquisar.setBounds(10, 26, 280, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478118009_Zoom.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(300, 10, 50, 50);

        tblLivro.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLivroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLivro);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 580, 80);

        jLabel2.setText("* Título");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 190, 40, 20);

        jLabel3.setText("* ISBN");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 280, 50, 20);

        jLabel5.setText("* Volume");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 370, 60, 30);

        jLabel6.setText("* Edição");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 320, 50, 30);

        jLabel8.setText("* Editora");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 230, 50, 30);
        getContentPane().add(txtLiTitulo);
        txtLiTitulo.setBounds(80, 190, 260, 30);
        getContentPane().add(txtLiEditora);
        txtLiEditora.setBounds(80, 230, 260, 30);
        getContentPane().add(txtLiIsbn);
        txtLiIsbn.setBounds(80, 280, 70, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel9.setText("* Campos Obrigatórios");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(421, 13, 127, 26);
        jPanel1.add(txtLiAno);
        txtLiAno.setBounds(320, 90, 60, 30);
        jPanel1.add(txtLiEstante);
        txtLiEstante.setBounds(400, 180, 60, 30);

        jLabel10.setText("Estante");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(360, 180, 50, 30);
        jPanel1.add(txtLiAutor);
        txtLiAutor.setBounds(290, 130, 260, 30);

        jLabel11.setText("Valor");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(470, 180, 34, 30);
        jPanel1.add(txtLiValor);
        txtLiValor.setBounds(500, 180, 50, 30);
        jPanel1.add(txtLiVolume);
        txtLiVolume.setBounds(60, 190, 50, 30);
        jPanel1.add(txtLiEdicao);
        txtLiEdicao.setBounds(60, 140, 70, 30);

        jLabel4.setText("* Ano de Publicação");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(200, 90, 140, 30);

        jLabel7.setText("* Autor/Autores");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(200, 130, 110, 30);

        jLabel12.setText("ID");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(420, 80, 25, 27);

        txtId.setEnabled(false);
        jPanel1.add(txtId);
        txtId.setBounds(450, 80, 48, 30);

        jLabel13.setText("Quantidade de Livros");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(160, 180, 130, 30);
        jPanel1.add(txtLiQuantidade);
        txtLiQuantidade.setBounds(290, 180, 60, 30);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478020974_file_add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Cadastro");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionar);
        btnAdicionar.setBounds(120, 270, 90, 80);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478021262_file_edit.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(220, 270, 90, 80);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/biblioteca/icones/1478021182_file_delete.png"))); // NOI18N
        jButton3.setToolTipText("Excluir");
        jButton3.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(320, 270, 90, 80);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 180, 560, 360);

        setBounds(0, 0, 611, 604);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtLiPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLiPesquisarKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtLiPesquisarKeyReleased

    private void tblLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLivroMouseClicked
        // TODO add your handling code here:
        setar_campo();
    }//GEN-LAST:event_tblLivroMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLivro;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLiAno;
    private javax.swing.JTextField txtLiAutor;
    private javax.swing.JTextField txtLiEdicao;
    private javax.swing.JTextField txtLiEditora;
    private javax.swing.JTextField txtLiEstante;
    private javax.swing.JTextField txtLiIsbn;
    private javax.swing.JTextField txtLiPesquisar;
    private javax.swing.JTextField txtLiQuantidade;
    private javax.swing.JTextField txtLiTitulo;
    private javax.swing.JTextField txtLiValor;
    private javax.swing.JTextField txtLiVolume;
    // End of variables declaration//GEN-END:variables
}
