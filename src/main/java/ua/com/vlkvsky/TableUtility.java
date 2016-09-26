package ua.com.vlkvsky;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TableUtility {

    private static List<Data> tableData;
    String toRunMainFromIDE = "src/main/resources/";
    private static final String sourceData = "files/resource.dll";
    private static final String destinationData = "files/backup.dll";

    private TableUtility() {
        tableData = new ArrayList<>();
    }

    protected static void initTable() {
        try {
            backupFile();
            read();
        } catch (Exception e) {
            try {
                new File(sourceData).createNewFile();
                new File(destinationData).createNewFile();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Folder 'files' not found!\nCreate it here!", "ERROR", 0);
                System.exit(1);
            }
        }
    }

    protected static void read() {
        BufferedReader br = null;
        try {
            if (new File(sourceData).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                getReader(br);
            } else {
                new File(sourceData).createNewFile();
                read();
            }
        } catch (NullPointerException | IOException ex) {
            JOptionPane.showMessageDialog(null, "");
        } finally {
            closeConnection(br);
        }
    }

    protected static void readFromBackup() {
        BufferedReader br = null;
        try {
            if (new File(destinationData).exists()) {
                br = new BufferedReader(new FileReader(destinationData));
                getReader(br);
            } else {
                new File(destinationData).createNewFile();
                readFromBackup();
            }
        } catch (NullPointerException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Read from backup is not available.");
        } finally {
            closeConnection(br);
        }
    }

    protected static void deleteData(Data C) {

        BufferedReader br = null;
        String ReWrite = "";
        try {
            if (new File(sourceData).exists()) {

                br = new BufferedReader(new FileReader(sourceData));
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] _temp = line.split(",");
                    if (_temp[0].equalsIgnoreCase(C.getSource()) && _temp[1].equalsIgnoreCase(C.getLogin())
                            && _temp[2].equalsIgnoreCase(C.getPassword())) {
                    } else {
                        ReWrite += line + "\r\n";
                    }
                }
                br.close();

                if (writeFile(ReWrite)) {
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete data " + C.getSource(), "Error", 0);
                }

                TableUtility.read();
                BuildMainForm.BindIntoJTable();
            } else {
                new File(sourceData).createNewFile();
                read();
            }
        } catch (IOException ex) {
            getWindowException(ex);
        } finally {
            closeConnection(br);
        }
    }

    protected static boolean updateData(String source, String login, String password, String NewStringLine) {
        BufferedReader br = null;
        String ReWrite = "";
        boolean success = false;
        try {
            if (new File(sourceData).exists()) {

                br = new BufferedReader(new FileReader(sourceData));
                String line = "";
                while ((line = br.readLine()) != null) {
                    if (!"".equals(line)) {
                        String[] _temp = line.split(",");
                        if (_temp[0].equalsIgnoreCase(source) && _temp[1].equalsIgnoreCase(login)
                                && _temp[2].equalsIgnoreCase(password)) {
                            ReWrite += NewStringLine + "\r\n";
                        } else {
                            ReWrite += line + "\r\n";
                        }
                    }
                }
                success = writeFile(ReWrite);
                read();
            } else {
                new File(sourceData).createNewFile();
                read();
                success = false;
            }
        } catch (IOException ex) {
            getWindowException(ex);
            success = false;
        } finally {
            closeConnection(br);
        }
        return success;
    }

    protected static List<Data> search(String searchValue) {
        List<Data> list = new ArrayList<>();
        BufferedReader br = null;

        try {
            if (new File(sourceData).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                String line;
                while ((line = br.readLine()) != null) {
                    if (!"".equals(line)) {
                        String[] _temp = line.split(",");
                        if (_temp[0].toLowerCase().contains(searchValue.toLowerCase()) ||
                                _temp[1].toLowerCase().contains(searchValue.toLowerCase()) ||
                                _temp[2].toLowerCase().contains(searchValue.toLowerCase())) {
                            Data c = new Data();
                            c.setSource(_temp[0]);
                            c.setLogin(_temp[1]);
                            c.setPassword(_temp[2]);
                            list.add(c);
                        }
                    }
                }
            } else {
                new File(sourceData).createNewFile();
                list = search(searchValue);
            }
        } catch (IOException ex) {
            getWindowException(ex);
        } finally {
            closeConnection(br);
        }

        return list;
    }

    private static boolean writeFile(String TextToWrite) {
        FileWriter writer = null;
        boolean successfulWrite = false;
        try {
            writer = new FileWriter(sourceData);
            writer.write(TextToWrite);

            writer.close();
            successfulWrite = true;
        } catch (IOException ex) {
            successfulWrite = false;
            getWindowException(ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                getWindowException(ex);
            }
        }
        return successfulWrite;
    }

    protected static boolean appendText(String appendValue) {
        boolean success = false;
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(sourceData, true)));
            out.println(appendValue);
            out.close();
            success = true;
        } catch (IOException e) {
            getWindowException(e);
        }
        return success;
    }

    private static void backupFile() {
        try {
//            File sourceFile = new File(sourceData);
//            File destinationFile = new File(destinationData);
//            FileUtils.copyFile(sourceFile, destinationFile);

            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(sourceData);
                os = new FileOutputStream(destinationData);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } finally {
                is.close();
                os.close();
            }
        } catch (IOException e) {
        }
    }

    protected static void setTableData(List<Data> aAllData) {
        tableData = aAllData;
    }

    protected static List<Data> getTableData() {
        return tableData;
    }

    private static void closeConnection(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(TableUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void getReader(BufferedReader br) throws IOException {
        if (tableData == null) {
            tableData = new ArrayList<>();
        } else {
            tableData.clear();
        }
        StringBuilder sb = new StringBuilder();
        String line = "";
        Data dataClass = null;
        while ((line = br.readLine()) != null) {
            if (!line.equalsIgnoreCase("")) {
                dataClass = new Data();
                String[] _temp = line.split(",");
                String _tempValue = _temp[0];
                if (_tempValue.equalsIgnoreCase("NULL")) {
                    _tempValue = "";
                }
                dataClass.setSource(_tempValue);

                _tempValue = _temp[1];
                if (_tempValue.equalsIgnoreCase("NULL")) {
                    _tempValue = "";
                }
                dataClass.setLogin(_tempValue);

                _tempValue = _temp[2];
                if (_tempValue.equalsIgnoreCase("NULL")) {
                    _tempValue = "";
                }
                dataClass.setPassword(_tempValue);
                tableData.add(dataClass);
            }
        }
    }

    private static void getWindowException(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
    }
}