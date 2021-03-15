/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamrecorder;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    int usedNumber = 1;
    int minutes = 0;
    int hours = 0;
    Process record;
    boolean scheduledTask = false;
    String recordingPath = "";
    boolean beginRecording = false;
    String startTime = "";
    int totalSecs = 0;
    String recordingFileName = "rec1.mp4";

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        outputArea.setText(getTimestamp() + " Application Initialized Succsessfully!");
        DefaultCaret caret = (DefaultCaret) outputArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.setTitle("Live Stream Recorder V0.1b");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
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
    Thread scheduler = new Thread(new Runnable() {
        public void run() {
            scheduleText.setText("Recording Scheduled For: " + startTime);
            while (!timeLabel.getText().equals(startTime)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            printToConsole("Scheduled Recording has started");
            scheduleText.setText("Currently Recording");
            recording = true;
            beginRecording = true;

        }
    });

    public static long bytesToMeg(long bytes) {
        return bytes / MEGABYTE;
    }
    Thread stopwatch = new Thread(new Runnable() {
        public void run() {
            while (true) {
                System.out.println(" ");
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
            System.out.println(" ");
            while (true) {
                System.out.println(" ");
                if (beginRecording == true) {
                    System.out.println("its true");
                    try {
                        printToConsole("streamlink\\streamlink.bat --hls-live-restart " + streamURLInput.getText() + " " + res + " -o rec1.ts");
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

    void disableButtonsForRecord() {
        recordButton.setEnabled(false);
        qualityCheckBtn.setEnabled(false);
        timedRecordButton.setEnabled(false);
        quickRecordButton.setEnabled(false);
        quickRecordButton.setEnabled(false);

    }

    void enableButtonsForRecord() {
        recordButton.setEnabled(true);
        qualityCheckBtn.setEnabled(true);
        timedRecordButton.setEnabled(true);
        quickRecordButton.setEnabled(true);
        quickRecordButton.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        recordButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        quickRecordButton = new javax.swing.JButton();
        githubButton = new javax.swing.JButton();
        qualityCheckBtn = new javax.swing.JButton();
        streamURLInput = new javax.swing.JTextField();
        urlLabel = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fileSizeLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeElapsedLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        recordingNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        timedRecordButton = new javax.swing.JButton();
        scheduleText = new javax.swing.JLabel();
        presetSetting = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

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

        quickRecordButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        quickRecordButton.setText("Preset Record");
        quickRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickRecordButtonActionPerformed(evt);
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

        streamURLInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                streamURLInputActionPerformed(evt);
            }
        });

        urlLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
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

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Current System Time:");

        timeLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        timeLabel.setText("13:00");

        jLabel8.setText("Recording File Name: ");

        timedRecordButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        timedRecordButton.setText("Timed Record");
        timedRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timedRecordButtonActionPerformed(evt);
            }
        });

        scheduleText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        scheduleText.setText("No Recordings Scheduled");

        presetSetting.setText("Preset Settings");
        presetSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetSettingActionPerformed(evt);
            }
        });

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeLabel)
                                        .addGap(49, 49, 49))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(timedRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(45, 45, 45))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeElapsedLabel))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(urlLabel)
                                            .addGap(23, 23, 23)
                                            .addComponent(streamURLInput))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(recordingNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(fileSizeLabel)
                                                    .addGap(114, 114, 114))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(presetSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(93, 93, 93)))
                                            .addComponent(scheduleText))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quickRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(titleLabel))
                            .addComponent(githubButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fileSizeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presetSetting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(urlLabel)
                            .addComponent(streamURLInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recordingNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(timeLabel)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timedRecordButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(recordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(qualityCheckBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(githubButton)
                            .addComponent(scheduleText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quickRecordButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordButtonActionPerformed
        // TODO add your handling code here:
        disableButtonsForRecord();
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
                scheduleText.setText("Currently Recording");
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
        scheduleText.setText("No Recordings Scheduled");
        enableButtonsForRecord();
        recording = false;
        minutes = 0;
        hours = 0;
        printToConsole("Attempting to kill task");
        record.destroy();
        t.interrupt();
        totalSecs = 0;
        File file = new File("defaultName.txt");
        String st;
        if (fieldEmpty(recordingNameField)) {
            printToConsole("Name Field is Empty!");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((st = br.readLine()) != null) {
                    recordingFileName = st + ".mp4";
                }
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            recordingFileName = recordingNameField.getText();
        }
        if (!recordingFileName.contains(".mp4")) {
            recordingFileName = recordingFileName + ".mp4";
        }
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
        printToConsole("Conversion to MP4 with file name");
        File recordingMP4 = new File("rec1.mp4");
        printToConsole(recordingPath + "/" + recordingFileName);
        recordingMP4.renameTo(new File(recordingPath + "/" + recordingFileName));
        if (scheduledTask) {
            JOptionPane.showMessageDialog(this,
                    "Task complete please re-open program to use again!");
            System.exit(0);
        }
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

    private void quickRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickRecordButtonActionPerformed
        disableButtonsForRecord();
        File file = new File("defaultPath.txt");
        String st;
        String st2;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
               printToConsole(st);
               st = st.replaceAll("\\\\", "/");
                recordingPath = st;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        File file2 = new File("defaultRes.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file2));
            while ((st2 = br.readLine()) != null) {
                res = st2;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        printToConsole("Attempting to record stream at: " + res);
        scheduleText.setText("Currently Recording");
        recording = true;
        beginRecording = true;
    }//GEN-LAST:event_quickRecordButtonActionPerformed

    private void timedRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timedRecordButtonActionPerformed
        // TODO add your handling code here:
        scheduledTask = true;
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
            startTime = JOptionPane.showInputDialog("What time would you like to start recording? (24 Hours including seconds)");
            LocalTime t = LocalTime.parse(startTime);
            File file2 = new File("defaultRes.txt");
            String st2;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file2));
                while ((st2 = br.readLine()) != null) {
                    res = st2;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            printToConsole("Scheduled Recording queued, currently waiting for time");
            scheduler.start();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                printToConsole("Directory: " + fileChooser.getSelectedFile() + " has been selected");
                String options = "None Selected";
                recordingPath = file.getAbsolutePath();
                disableButtonsForRecord();
            } else {

            }
        }


    }//GEN-LAST:event_timedRecordButtonActionPerformed

    private void streamURLInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_streamURLInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_streamURLInputActionPerformed

    private void presetSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presetSettingActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,
                "Please select the default directory where you want to save your recording",
                "Directory Selection",
                JOptionPane.PLAIN_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter myWriter = new FileWriter("defaultPath.txt");
                File file = fileChooser.getSelectedFile();
                myWriter.write(file.getAbsolutePath());
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            String resolution = JOptionPane.showInputDialog(this, "What resoluton do you want to record at? eg.1080p");
            try {
                FileWriter myWriter = new FileWriter("defaultRes.txt");
                myWriter.write(resolution);
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            String defaultFileName = JOptionPane.showInputDialog(this, "What do you want the default file name to be (don't include .mp4)");
            try {
                FileWriter myWriter = new FileWriter("defaultName.txt");
                myWriter.write(defaultFileName);
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(this,
                    "Preset settings have been saved",
                    "Succsess!",
                    JOptionPane.PLAIN_MESSAGE);
        } else {

        }


    }//GEN-LAST:event_presetSettingActionPerformed
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
            Logger.getLogger(MainScreen.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        if (text.contains("720p60")) {
            resolution.add("720p60");
        }
        if (text.contains("1080p60")) {
            resolution.add("1080p60");
        }

        return resolution.toArray(new String[0]);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileSizeLabel;
    private javax.swing.JButton githubButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton presetSetting;
    private javax.swing.JButton qualityCheckBtn;
    private javax.swing.JButton quickRecordButton;
    private javax.swing.JButton recordButton;
    private javax.swing.JTextField recordingNameField;
    private javax.swing.JLabel scheduleText;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField streamURLInput;
    private javax.swing.JLabel timeElapsedLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton timedRecordButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel urlLabel;
    // End of variables declaration//GEN-END:variables
}
