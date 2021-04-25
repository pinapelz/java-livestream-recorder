/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java;

import javax.swing.JFrame;

/**
 *
 * @author Donald Shan
 */
public class SelectorScreen extends javax.swing.JFrame {

    /**
     * Creates new form SelectorScreen
     */
    public SelectorScreen() {
     
        initComponents();
         selector.setEnabled(false);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        organization = new javax.swing.ButtonGroup();
        selector = new javax.swing.JComboBox<>();
        nijisanji = new javax.swing.JRadioButton();
        hololive = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        parseAllButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        selector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorActionPerformed(evt);
            }
        });

        organization.add(nijisanji);
        nijisanji.setText("Nijisanji");
        nijisanji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nijisanjiActionPerformed(evt);
            }
        });

        organization.add(hololive);
        hololive.setText("Hololive");
        hololive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hololiveActionPerformed(evt);
            }
        });

        jButton1.setText("OK");

        parseAllButton.setText("Parse All");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hololive, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nijisanji, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(selector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(parseAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hololive)
                    .addComponent(nijisanji))
                .addGap(18, 18, 18)
                .addComponent(selector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(parseAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorActionPerformed

    private void nijisanjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nijisanjiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nijisanjiActionPerformed

    private void hololiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hololiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hololiveActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton hololive;
    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton nijisanji;
    private javax.swing.ButtonGroup organization;
    private javax.swing.JButton parseAllButton;
    private javax.swing.JComboBox<String> selector;
    // End of variables declaration//GEN-END:variables
}
