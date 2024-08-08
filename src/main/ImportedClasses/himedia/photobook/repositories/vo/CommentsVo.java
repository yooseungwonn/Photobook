package himedia.photobook.repositories.vo;

import java.util.Date;

public class CommentsVo {
   private Long commentId;
   private Long boardId;
   private String userName;
   private String content;
   private Date comDate;

   public CommentsVo() {
   }

   public CommentsVo(Long commentId, String userName, String content, Date comDate) {
      this.commentId = commentId;
      this.userName = userName;
      this.content = content;
      this.comDate = comDate;
   }

   public Long getCommentId() {
      return this.commentId;
   }

   public void setCommentId(Long commentId) {
      this.commentId = commentId;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getComDate() {
      return this.comDate;
   }

   public void setComDate(Date comDate) {
      this.comDate = comDate;
   }

   public Long getBoardId() {
      return this.boardId;
   }

   public void setBoardId(Long boardId) {
      this.boardId = boardId;
   }

   public String toString() {
      String var10000 = String.valueOf(this.commentId);
      return "CommentsVo [commentId=" + var10000 + ", userName=" + this.userName + ", content=" + this.content + ", comDate=" + String.valueOf(this.comDate) + "]";
   }
}
