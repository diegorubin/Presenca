/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * winMain.java
 *
 * Created on Jun 3, 2011, 3:25:29 PM
 */
package presenca;
import javax.swing.JPanel;

/**
 *
 * @author drubin
 */
public class winMain extends javax.swing.JFrame {
    private JPanel frm;
    /** Creates new form winMain */
    public winMain() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnArquvio = new javax.swing.JMenu();
        mnSair = new javax.swing.JMenuItem();
        mnProfessores = new javax.swing.JMenu();
        mnGerenciar = new javax.swing.JMenuItem();
        mnTurmas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnArquvio.setText("Arquivo");

        mnSair.setText("Sair");
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        mnArquvio.add(mnSair);

        jMenuBar1.add(mnArquvio);

        mnProfessores.setText("Professores");

        mnGerenciar.setText("Gerenciar");
        mnGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGerenciarActionPerformed(evt);
            }
        });
        mnProfessores.add(mnGerenciar);

        jMenuBar1.add(mnProfessores);

        mnTurmas.setText("Turmas");

        jMenuItem1.setText("Gerar Lista");
        mnTurmas.add(jMenuItem1);

        jMenuBar1.add(mnTurmas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnSairActionPerformed

    private void mnGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGerenciarActionPerformed
        
    }//GEN-LAST:event_mnGerenciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new winMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu mnArquvio;
    private javax.swing.JMenuItem mnGerenciar;
    private javax.swing.JMenu mnProfessores;
    private javax.swing.JMenuItem mnSair;
    private javax.swing.JMenu mnTurmas;
    // End of variables declaration//GEN-END:variables
}
