/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamrecorder;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Pinapelz
 */
public class MainScreen extends javax.swing.JFrame {

    private static final long MEGABYTE = 1024L * 1024L;
    boolean recording = false;
    String res = "";
    int minutes = 0;
    int hours = 0;
    Process record;
    String recordingPath = "";
    boolean beginRecording = false;
    int totalSecs = 0;
    String recordingFileName = "rec1.mp4";

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        outputArea.setText(getTimestamp() + " Application Initialized Succsessfully!");
        useTimeBox.setEnabled(false);
        recordLengthField.setEnabled(false);
        DefaultCaret caret = (DefaultCaret) outputArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.setTitle("Live Stream Recorder V0.1b");
        this.setLocationRelativeTo(null);
        stopwatch.start();
        clock.start();
        t.start();
    }
    Thread clock = new Thread(new Runnable() {
        public void run() {
            while (true) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                timeLabel.setText(now.format(dtf));
            }
        }
    });

    public static long bytesToMeg(long bytes) {
        return bytes / MEGABYTE;
    }
    Thread stopwatch = new Thread(new Runnable() {
        public void run() {
            while (true) {
                System.out.println("Keeping the stopwatch thread alive");
                if (recording) {
                    Path path = Paths.get("rec1.ts");
                    long bytes = 0;
                    try {
                        bytes = Files.size(path);
                        fileSizeLabel.setText(bytesToMeg(bytes) + " MB");
                    } catch (IOException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(1000);
                        totalSecs++;
                        if (totalSecs == 60) {
                            totalSecs = 0;
                            minutes++;
                        }
                        if (minutes == 60) {
                            minutes = 0;
                            hours++;
                        }
                        timeElapsedLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, totalSecs));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    });
    Thread t = new Thread(new Runnable() {

        public void run() {
            System.out.println("Keeping the thread alive");
            while (true) {
                System.out.println("Keeping the thread alive");
                if (beginRecording == true) {
                    System.out.println("its true");
                    try {
                        ProcessBuilder builder = new ProcessBuilder(
                                "cmd.exe", "/c", "streamlink\\streamlink.bat --hls-live-restart " + streamURLInput.getText() + " " + res + " -o rec1.ts");
                        builder.redirectErrorStream(true);

                        record = builder.start();
                        BufferedReader r = new BufferedReader(new InputStreamReader(record.getInputStream()));
                        String line;
                        while (true) {
                            line = r.readLine();
                            if (line == null) {
                                break;
                            }
                            printToConsole(line);
                        }
                        record.waitFor();
                        beginRecording = false;
                    } catch (IOException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                        beginRecording = false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                        beginRecording = false;
                    }

                }
            }
        }
    });

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        githubButton = new javax.swing.JButton();
        qualityCheckBtn = new javax.swing.JButton();
        streamURLInput = new javax.swing.JTextField();
        urlLabel = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fileSizeLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeElapsedLabel = new javax.swing.JLabel();
        useTimeBox = new javax.swing.JCheckBox();
        recordLengthField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        recordingNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        recordButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        recordButton.setText("Record");
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        titleLabel.setText("Livestream Recorder");

        outputArea.setEditable(false);
        outputArea.setColumns(20);
        outputArea.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        outputArea.setRows(5);
        jScrollPane1.setViewportView(outputArea);

        jButton2.setText("Settings");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        githubButton.setText("Github");
        githubButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                githubButtonActionPerformed(evt);
            }
        });

        qualityCheckBtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        qualityCheckBtn.setText("Quality Check");
        qualityCheckBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualityCheckBtnActionPerformed(evt);
            }
        });

        urlLabel.setText("Stream URL");

        stopButton.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        stopButton.setText("STOP");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("File Size:");

        fileSizeLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        fileSizeLabel.setText("0 MB");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Time Elapsed:");

        timeElapsedLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        timeElapsedLabel.setText("00:00:00");

        useTimeBox.setText("Use Time");

        jLabel7.setText("Current System Time:");

        timeLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        timeLabel.setText("13:00");

        jLabel8.setText("Recording File Name: ");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(recordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qualityCheckBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(useTimeBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(recordLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(urlLabel)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(recordingNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                    .addComponent(streamURLInput))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileSizeLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeElapsedLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(githubButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(jLabel5)
                    .addComponent(timeElapsedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(githubButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(fileSizeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(recordLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(useTimeBox)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recordingNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(urlLabel)
                            .addComponent(streamURLInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(timeLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(recordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(qualityCheckBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordButtonActionPerformed
        // TODO add your handling code here:
        if (fieldEmpty(recordingNameField)) {
            recordingFileName = "recording.mp4";
        } else {
            recordingFileName = recordingNameField.getText();
        }
        if (!recordingFileName.contains(".mp4")) {
            recordingFileName = recordingFileName + ".mp4";
        }
        if (fieldEmpty(streamURLInput)) {
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select the directory where you want to save the recording",
                    "Directory Selection",
                    JOptionPane.PLAIN_MESSAGE);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                printToConsole("Directory: " + fileChooser.getSelectedFile() + " has been selected");
                String options = "None Selected";
                recordingPath = file.getAbsolutePath();
                try {
                    ProcessBuilder builder = new ProcessBuilder(
                            "cmd.exe", "/c", "streamlink\\streamlink.bat " + streamURLInput.getText());
                    builder.redirectErrorStream(true);
                    Process p;
                    p = builder.start();
                    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;

                    while (true) {

                        line = r.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.startsWith("Available")) {
                            options = line;
                        }
                        printToConsole(line);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                String[] choices = addResolutions(options);
                res = (String) JOptionPane.showInputDialog(null, "What Resolution Would You Like To Recod At?",
                        "Resolution", JOptionPane.QUESTION_MESSAGE, null,
                        choices,
                        choices[choices.length - 1]);

                printToConsole("Attempting to record stream at: " + res);
                JOptionPane.showMessageDialog(this,
                        "Recording will begin after clicking OK.\n Please watch the console and new window for more information");
                System.out.println(streamURLInput.getText());
                recording = true;
                beginRecording = true;

            }

        }

    }//GEN-LAST:event_recordButtonActionPerformed

    private void qualityCheckBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qualityCheckBtnActionPerformed
        String options = "None Available";
        String input = "0";
        if (!fieldEmpty(streamURLInput)) {
            try {
                // TODO add your handling code here:
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "streamlink\\streamlink.bat " + streamURLInput.getText());
                builder.redirectErrorStream(true);
                Process p;
                p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.startsWith("Available")) {
                        options = line;
                    }
                    printToConsole(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(options);
            String[] choices = addResolutions(options);
            input = (String) JOptionPane.showInputDialog(null, "What Resolution Would You Like To Test?",
                    "Resolution", JOptionPane.QUESTION_MESSAGE, null,
                    choices,
                    choices[choices.length - 1]);
            try {
                printToConsole("Attempting to test stream at: " + input);
                JOptionPane.showMessageDialog(this,
                        "Testing will begin after clicking OK. Please be patient\nCheck for stutters and adjust setting accordingly \nClose the player anytime to end resolution testing.");
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "streamlink\\streamlink.bat " + streamURLInput.getText() + " " + input);
                builder.redirectErrorStream(true);
                Process p;

                p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    printToConsole(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }//GEN-LAST:event_qualityCheckBtnActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        // TODO add your handling code here:
        recording = false;
        minutes = 0;
        hours = 0;
        printToConsole("Attempting to kill task");
        record.destroy();
        t.interrupt();
        totalSecs = 0;
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "taskkill /F /IM \"python.exe\" /T");
        builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            printToConsole("Task killed succsessfully");
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            printToConsole("Error failed to kill task");
        }
        printToConsole("Attempting to convert mp4");
        convertTStoMP4();
        printToConsole("Conversion complete!!");
        File recordingTS = new File("rec1.ts");
        if (recordingTS.delete()) {
            printToConsole("Deleting temporary TS File: " + recordingTS.getName());
        } else {
            printToConsole("Failed to delete temporary TS file: " + recordingTS.getName());
        }
        File recordingMP4 = new File("rec1.mp4");
        recordingMP4.renameTo(new File(recordingPath + "/" + recordingFileName));
        /* JOptionPane.showMessageDialog(this,
                "Process Complete. Please re-open program to record another stream!",
                "Thank You",
                JOptionPane.PLAIN_MESSAGE);*/
    }//GEN-LAST:event_stopButtonActionPerformed

    private void githubButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_githubButtonActionPerformed

        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URL("https://github.com/pinapelz/livestreamRecorder").toURI());
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_githubButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        recording = true;
    }//GEN-LAST:event_jButton2ActionPerformed
    private boolean fieldEmpty(JTextField textField) {
        if (textField.getText().equals("")) {
            System.out.println("Input blank");
            return true;
        } else {
            return false;
        }

    }

    private String getTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "[" + dtf.format(now) + "]";
    }

    private void printToConsole(String text) {
        outputArea.append("\n" + getTimestamp() + " " + text);
    }

    private void convertTStoMP4() {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "streamlink\\ffmpeg\\ffmpeg.exe -i .\\\\rec1.ts -c copy -bsf:a aac_adtstoasc -f mp4 -movflags faststart+separate_moof .\\\\rec1.mp4");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);

            }
        });
    }

    private String[] addResolutions(String text) {
        ArrayList<String> resolution = new ArrayList<String>();
        if (text.contains("144p")) {
            resolution.add("144p");
        }
        if (text.contains("240p")) {
            resolution.add("240p");
        }
        if (text.contains("360p")) {
            resolution.add("360p");
        }
        if (text.contains("480p")) {
            resolution.add("480p");
        }
        if (text.contains("720p")) {
            resolution.add("720p");
        }
        if (text.contains("1080p")) {
            resolution.add("1080p");
        }
        return resolution.toArray(new String[0]);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileSizeLabel;
    private javax.swing.JButton githubButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton qualityCheckBtn;
    private javax.swing.JButton recordButton;
    private javax.swing.JTextField recordLengthField;
    private javax.swing.JTextField recordingNameField;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField streamURLInput;
    private javax.swing.JLabel timeElapsedLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JCheckBox useTimeBox;
    // End of variables declaration//GEN-END:variables
}
