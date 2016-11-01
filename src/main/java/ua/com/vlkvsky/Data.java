
package ua.com.vlkvsky;

class Data {
    private String source = "";
    private String login = "";
    private String password = "";

//to run from IDE
//    private static final String dataFile = "src/main/resources/files/resource.dll1";
//    private static final String backupFile = "src/main/resources/files/backup.dll1";
//to run builded maven
    private static final String dataFile = "files/resource.dll";
    private static final String backupFile = "files/backup.dll";

    public Data() {
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getDataFile() {
        return dataFile;
    }

    public static String getBackupFile() {
        return backupFile;
    }
}
