package actividad6;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends javax.swing.JFrame {
    public VentanaPrincipal() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre");

        jLabel2.setText("Número");

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnRead.setText("Read");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel1)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(37, 37, 37)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txtNumber)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreate)
                        .addGap(36, 36, 36)
                        .addComponent(btnEdit)
                        .addGap(39, 39, 39)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnRead)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnRead)
                    .addComponent(btnClear))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            String newName = txtName.getText();
            long newNumber = Long.parseLong(txtNumber.getText());
 
            String nameNumberString;
            String name;
            long number;
            int index;
 
            File file = new File("friendsContact.txt");
             if (!file.exists()) {
                file.createNewFile();
            }
 
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
 
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                
                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }

            if (found == true) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
 
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {
                        nameNumberString = name + "!" + String.valueOf(newNumber);
                    }

                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);
                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }
                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                tmpFile.delete();
                showMessage(" Amigo actualizado ");
                clearTexts();
            }

            else {
                raf.close();
                showMessage(" El nombre: " + newName + " no existe. ");
            }
        }
 
        catch (IOException | NumberFormatException ioe) {
            System.out.println(ioe);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try
        {
            String newName = txtName.getText();
            long newNumber = Long.parseLong(txtNumber.getText());
            String nameNumberString;
            String name;
            long number;
 
            File file = new File("friendsContact.txt");
 
            if (!file.exists()) {
                file.createNewFile();
            }
 
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
 
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
 
                if (name.equals(newName) || number == newNumber) { 
                    showMessage("El contacto ya existe");
                    raf.close();
                    return;
                }
            }
            
            nameNumberString = newName + "!" + String.valueOf(newNumber);
            raf.writeBytes(nameNumberString);
            raf.writeBytes(System.lineSeparator());
            showMessage("Contacto añadido");
            clearTexts();
            raf.close();
        }
 
        catch (IOException | NumberFormatException ioe) {
 
            System.out.println(ioe);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String newName = txtName.getText();
 
            String nameNumberString;
            String name;
            int index;
            
            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
 
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
            
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                
                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }
            
            if (found == true) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine(); 
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);
                    
                    if (name.equals(newName)) {
                        continue;
                    }
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                }
                
                raf.seek(0);
                tmpraf.seek(0);
                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                tmpFile.delete();
 
                showMessage(" Se eliminó el amigo ");
                clearTexts();
            }
            else {
                raf.close();
                showMessage(" El nombre " + newName + " no existe ");
            }
        }
 
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearTexts();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        try {
            String newName = txtName.getText();
            String nameNumberString;
            String name;
 
            File file = new File("friendsContact.txt");
 
            if (!file.exists()) {
                file.createNewFile();
            }
 
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);
                if (name.equals(newName))
                {
                    txtNumber.setText(String.valueOf(number));
                    raf.close();
                    return;
                }
            }
            
            showMessage(" El amigo: " + newName + " no existe ");
            raf.close();
        }
        catch (IOException | NumberFormatException ioe)
        {
            System.out.println(ioe);
        }
    }//GEN-LAST:event_btnReadActionPerformed

    private void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(
            null, 
            msg, 
            "INFORMATION_MESSAGE",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void clearTexts() {
        txtName.setText("");
        txtNumber.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRead;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    // End of variables declaration//GEN-END:variables
}
