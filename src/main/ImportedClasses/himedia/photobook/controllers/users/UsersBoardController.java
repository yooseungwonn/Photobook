package himedia.photobook.controllers.users;

import himedia.photobook.repositories.vo.BoardVo;
import himedia.photobook.repositories.vo.CommentsVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.admin.AdminCommentService;
import himedia.photobook.services.users.UBoardService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/users"})
public class UsersBoardController {
   @Autowired
   private UBoardService uBoardService;
   @Autowired
   private AdminCommentService adminCommentService;

   @GetMapping({"/board"})
   public String usersBoard() {
      return "/WEB-INF/views/users/users_board.jsp";
   }

   @RequestMapping({"/boardList"})
   public String list(Model md) {
      List<Map<String, Object>> list = this.uBoardService.getBoardInfos();
      md.addAttribute("postList", list);
      return "/WEB-INF/views/users/users_board.jsp";
   }

   @GetMapping({"/board/write"})
   public String writeForm(HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      return "/WEB-INF/views/users/board/board_write.jsp";
   }

   @PostMapping({"/board/write"})
   public String writeAction(@ModelAttribute BoardVo boardVo, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      boardVo.setUserId(authUser.getUserId());
      this.uBoardService.write(boardVo);
      return "redirect:/users/boardList";
   }

   @GetMapping({"/board/post/{userId}/{boardId}"})
   public String view(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         return "redirect:/users/boardList";
      } else {
         Map<String, Object> boardVo = this.uBoardService.getContent(userId, boardId);
         CommentsVo commentsVo = this.adminCommentService.getCommentsByBoardId(boardId);
         md.addAttribute("vo", boardVo);
         md.addAttribute("commentVo", commentsVo);
         return "/WEB-INF/views/users/board/board_post.jsp";
      }
   }

   @GetMapping({"/board/{userId}/{boardId}/modify"})
   public String modifyForm(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/users/boardList";
      } else {
         Map<String, Object> boardVo = this.uBoardService.getContent(userId, boardId);
         md.addAttribute("vo", boardVo);
         return "/WEB-INF/views/users/board/board_modify.jsp";
      }
   }

   @PostMapping({"/modify"})
   public String modifyAction(@ModelAttribute BoardVo updateVo, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/users/boardList";
      } else {
         System.out.println("updateVo.getUserId : " + updateVo.getUserId());
         System.out.println("updateVo.getBoardId : " + String.valueOf(updateVo.getBoardId()));
         BoardVo boardVo = this.uBoardService.getBoardVo(updateVo.getUserId(), updateVo.getBoardId());
         boardVo.setTitle(updateVo.getTitle());
         boardVo.setContent(updateVo.getContent());
         this.uBoardService.update(boardVo);
         return "redirect:/users/boardList";
      }
   }

   @RequestMapping({"/board/{userId}/{boardId}/delete"})
   public String deleteAction(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/users/boardList";
      } else {
         BoardVo boardVo = this.uBoardService.getBoardVo(userId, boardId);
         this.uBoardService.delete(userId, boardVo.getBoardId());
         return "redirect:/users/boardList";
      }
   }

   @RequestMapping({"/board/mine"})
   public String boardMine(Model md) {
      List<Map<String, Object>> list = this.uBoardService.getBoardInfos();
      md.addAttribute("postList", list);
      return "/WEB-INF/views/users/board/board_mine.jsp";
   }
}
