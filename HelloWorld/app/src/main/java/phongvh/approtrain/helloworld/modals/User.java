package phongvh.approtrain.helloworld.modals;
import java.io.Serializable;

public class User implements Serializable {
    private String address;
    private String dateOfBirth;
    private String fullName;
    private String passWord;
    private String phoneNumber;
    private String userName;

    private int id;

    public User() {

    }
    public User(String userName2, String passWord2, String fullName2, String dateOfBirth2, String phoneNumber2, String address2) {
        this.userName = userName2;
        this.passWord = passWord2;
        this.fullName = fullName2;
        this.dateOfBirth = dateOfBirth2;
        this.phoneNumber = phoneNumber2;
        this.address = address2;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
