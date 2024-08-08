package himedia.photobook.repositories.vo;

public class UsersVo {
   private String userId;
   private String email;
   private String password;
   private String userName;
   private String address;
   private String phoneNumber;
   private String role;

   public UsersVo() {
   }

   public UsersVo(String userId, String email, String password, String userName, String address, String phoneNumber, String role) {
      this.userId = userId;
      this.email = email;
      this.password = password;
      this.userName = userName;
      this.address = address;
      this.phoneNumber = phoneNumber;
      this.role = role;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String toString() {
      return "UsersVo [userId=" + this.userId + ", email=" + this.email + ", password=" + this.password + ", userName=" + this.userName + ", address=" + this.address + ", phoneNumber=" + this.phoneNumber + ", role=" + this.role + "]";
   }
}
