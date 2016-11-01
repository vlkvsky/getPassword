package ua.com.vlkvsky;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ua.com.vlkvsky.BuildMainForm;
import ua.com.vlkvsky.Data;

class TableUtility {
    private static List<Data> tableData;
    private static final String sourceData = Data.getDataFile();
    private static final String destinationData = Data.getBackupFile();

    private TableUtility() {
        //noinspection unchecked
        tableData = new ArrayList();
    }

    static void initTable() {
        try {
            backup(sourceData, destinationData);
            read();
        } catch (Exception var3) {
            try {
                (new File(sourceData)).createNewFile();
                (new File(destinationData)).createNewFile();
            } catch (IOException var2) {
                JOptionPane.showMessageDialog(null, "Folder \'files\' not found!\nCreate it here!", "ERROR", 0);
                System.exit(1);
            }
        }

    }

    static void read() {
        BufferedReader br = null;

        try {
            if((new File(sourceData)).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                getReader(br);
            } else {
                (new File(sourceData)).createNewFile();
                read();
            }
        } catch (IOException | NullPointerException var5) {
            JOptionPane.showMessageDialog(null, "");
        } finally {
            closeConnection(br);
        }

    }

    static void readFromBackup() {
        BufferedReader br = null;

        try {
            if((new File(destinationData)).exists()) {
                br = new BufferedReader(new FileReader(destinationData));
                getReader(br);
            } else {
                (new File(destinationData)).createNewFile();
                readFromBackup();
            }
        } catch (IOException | NullPointerException var5) {
            JOptionPane.showMessageDialog(null, "Read from backup is not available.");
        } finally {
            backup(destinationData, sourceData);
            closeConnection(br);
        }

    }

    static void deleteData(Data C) {
        BufferedReader br = null;
        String ReWrite = "";

        try {
            if((new File(sourceData)).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                String ex = "";

                while(true) {
                    String[] _temp;
                    do {
                        if((ex = br.readLine()) == null) {
                            br.close();
                            if(!writeFile(ReWrite)) {
                                JOptionPane.showMessageDialog(null, "Failed to delete data " + C.getSource(), "Error", 0);
                            }

                            read();
                            BuildMainForm.BindIntoJTable();
                            return;
                        }

                        _temp = ex.split(",");
                    } while(_temp[0].equalsIgnoreCase(C.getSource()) && _temp[1].equalsIgnoreCase(C.getLogin()) && _temp[2].equalsIgnoreCase(C.getPassword()));

                    ReWrite = ReWrite + ex + "\r\n";
                }
            } else {
                (new File(sourceData)).createNewFile();
                read();
            }
        } catch (IOException var8) {
            getWindowException(var8);
        } finally {
            closeConnection(br);
        }

    }

    static boolean updateData(String source, String login, String password, String NewStringLine) {
        BufferedReader br = null;
        String ReWrite = "";
        boolean success = false;

        try {
            if((new File(sourceData)).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                String ex = "";

                while(true) {
                    while(true) {
                        do {
                            if((ex = br.readLine()) == null) {
                                success = writeFile(ReWrite);
                                read();
                                return success;
                            }
                        } while("".equals(ex));

                        String[] _temp = ex.split(",");
                        if(_temp[0].equalsIgnoreCase(source) && _temp[1].equalsIgnoreCase(login) && _temp[2].equalsIgnoreCase(password)) {
                            ReWrite = ReWrite + NewStringLine + "\r\n";
                        } else {
                            ReWrite = ReWrite + ex + "\r\n";
                        }
                    }
                }
            } else {
                (new File(sourceData)).createNewFile();
                read();
                success = false;
            }
        } catch (IOException var12) {
            getWindowException(var12);
            success = false;
        } finally {
            closeConnection(br);
        }

        return success;
    }

    static List<Data> search(String searchValue) {
        Object list = new ArrayList();
        BufferedReader br = null;

        try {
            if((new File(sourceData)).exists()) {
                br = new BufferedReader(new FileReader(sourceData));
                br.readLine();

                while(true) {
                    String[] _temp;
                    do {
                        String ex;
                        do {
                            if((ex = br.readLine()) == null) {
                                //noinspection unchecked
                                return (List)list;
                            }
                        } while("".equals(ex));

                        _temp = ex.split(",");
                    } while(!_temp[0].toLowerCase().contains(searchValue.toLowerCase()) && !_temp[1].toLowerCase().contains(searchValue.toLowerCase()) && !_temp[2].toLowerCase().contains(searchValue.toLowerCase()));

                    Data c = new Data();
                    c.setSource(_temp[0]);
                    c.setLogin(_temp[1]);
                    c.setPassword(_temp[2]);
                    //noinspection unchecked
                    ((List)list).add(c);
                }
            } else {
                (new File(sourceData)).createNewFile();
                list = search(searchValue);
            }
        } catch (IOException var9) {
            getWindowException(var9);
        } finally {
            closeConnection(br);
        }

        //noinspection unchecked
        return (List)list;
    }

    private static boolean writeFile(String TextToWrite) {
        FileWriter writer = null;
        boolean successfulWrite = false;

        try {
            writer = new FileWriter(sourceData);
            writer.write(TextToWrite);
            writer.close();
            successfulWrite = true;
        } catch (IOException var12) {
            successfulWrite = false;
            getWindowException(var12);
        } finally {
            try {
                writer.close();
            } catch (IOException var11) {
                getWindowException(var11);
            }

        }

        return successfulWrite;
    }

    static boolean appendText(String appendValue) {
        boolean success = false;

        try {
            PrintWriter e = new PrintWriter(new BufferedWriter(new FileWriter(sourceData, true)));
            e.println(appendValue);
            e.close();
            success = true;
        } catch (IOException var3) {
            getWindowException(var3);
        }

        return success;
    }

    private static void backup(String sourceData, String destinationData) {
        try {
            FileInputStream is = null;
            FileOutputStream os = null;

            try {
                is = new FileInputStream(sourceData);
                os = new FileOutputStream(destinationData);
                byte[] buffer = new byte[1024];

                int length;
                while((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } finally {
                is.close();
                os.close();
            }
        } catch (IOException ignored) {
        }

    }

    static void setTableData(List<Data> aAllData) {
        tableData = aAllData;
    }

    static List<Data> getTableData() {
        return tableData;
    }

    private static void closeConnection(BufferedReader br) {
        if(br != null) {
            try {
                br.close();
            } catch (IOException var2) {
                Logger.getLogger(TableUtility.class.getName()).log(Level.SEVERE, null, var2);
            }
        }

    }

    private static void getReader(BufferedReader br) throws IOException {
        if(tableData == null) {
            //noinspection unchecked
            tableData = new ArrayList();
        } else {
            tableData.clear();
        }

        String line1 = "";
        Data dataClass = null;
        br.readLine();
        String line = null;

        while((line = br.readLine()) != null) {
            if(!line.equalsIgnoreCase("")) {
                dataClass = new Data();
                String[] _temp = line.split(",");
                String _tempValue = _temp[0];
                if(_tempValue.equalsIgnoreCase("NULL")) {
                    _tempValue = "";
                }

                dataClass.setSource(_tempValue);
                _tempValue = _temp[1];
                if(_tempValue.equalsIgnoreCase("NULL")) {
                    _tempValue = "";
                }

                dataClass.setLogin(_tempValue);
                _tempValue = _temp[2];
                if(_tempValue.equalsIgnoreCase("NULL")) {
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
