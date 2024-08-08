package himedia.photobook.controllers.admin;

import himedia.photobook.repositories.vo.BoardVo;
import himedia.photobook.repositories.vo.CommentsVo;
import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.admin.AdminCommentServiceImpl;
import himedia.photobook.services.admin.AdminInventoryServiceImpl;
import himedia.photobook.services.users.UBoardServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/admin"})
public class AdminBoardController {
   @Autowired
   private UBoardServiceImpl uBoardServiceImpl;
   @Autowired
   private AdminCommentServiceImpl adminCommentServiceImpl;
   @Autowired
   private AdminInventoryServiceImpl adminInventoryServiceImpl;

   @RequestMapping({"/boardList"})
   public String list(Model md) {
      List<Map<String, Object>> list = this.uBoardServiceImpl.getBoardInfos();
      md.addAttribute("postList", list);
      return "/WEB-INF/views/admin/admin_board.jsp";
   }

   @GetMapping({"/board/write"})
   public String writeForm(HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      return "/WEB-INF/views/admin/board/board_write.jsp";
   }

   @PostMapping({"/board/write"})
   public String writeAction(@ModelAttribute BoardVo boardVo, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      boardVo.setUserId(authUser.getUserId());
      this.uBoardServiceImpl.write(boardVo);
      return "redirect:/admin/boardList";
   }

   @GetMapping({"/board/post/{userId}/{boardId}"})
   public String view(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         return "redirect:/admin/boardList";
      } else {
         Map<String, Object> boardVo = this.uBoardServiceImpl.getContent(userId, boardId);
         CommentsVo commentsVo = this.adminCommentServiceImpl.getCommentsByBoardId(boardId);
         boolean hasComment = this.adminCommentServiceImpl.hasComment(boardId);
         md.addAttribute("vo", boardVo);
         md.addAttribute("commentVo", commentsVo);
         md.addAttribute("hasComment", hasComment);
         return "/WEB-INF/views/admin/board/board_post.jsp";
      }
   }

   @GetMapping({"/board/{userId}/{boardId}/modify"})
   public String modifyForm(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/admin/boardList";
      } else {
         Map<String, Object> boardVo = this.uBoardServiceImpl.getContent(userId, boardId);
         md.addAttribute("vo", boardVo);
         return "/WEB-INF/views/admin/board/board_modify.jsp";
      }
   }

   @PostMapping({"/modify"})
   public String modifyAction(@ModelAttribute BoardVo updateVo, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/admin/boardList";
      } else {
         BoardVo boardVo = this.uBoardServiceImpl.getBoardVo(updateVo.getUserId(), updateVo.getBoardId());
         boardVo.setTitle(updateVo.getTitle());
         boardVo.setContent(updateVo.getContent());
         this.uBoardServiceImpl.update(boardVo);
         return "redirect:/admin/boardList";
      }
   }

   @RequestMapping({"/board/{userId}/{boardId}/delete"})
   public String deleteAction(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId, Model md, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/admin/boardList";
      } else {
         BoardVo boardVo = this.uBoardServiceImpl.getBoardVo(userId, boardId);
         this.uBoardServiceImpl.delete(userId, boardVo.getBoardId());
         return "redirect:/admin/boardList";
      }
   }

   @PostMapping({"/comment/write"})
   public String commentAction(@ModelAttribute CommentsVo commentsVo, @RequestParam("boardId") Long boardId, HttpSession session, RedirectAttributes redirectAtt) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser == null) {
         redirectAtt.addFlashAttribute("errorMsg", "자격이 없습니다.");
         return "redirect:/users/boardList";
      } else {
         commentsVo.setUserName(authUser.getUserName());
         this.adminCommentServiceImpl.write(commentsVo);
         redirectAtt.addAttribute("boardId", boardId);
         return "redirect:/admin/boardList";
      }
   }

   @GetMapping({"/customerService/search"})
   public String searchBoard(@RequestParam("keyword") String keyword, Model md) {
      List<Map<String, Object>> boardDetail = this.uBoardServiceImpl.getContentByName(keyword);
      md.addAttribute("boardDetail", boardDetail);
      return "/WEB-INF/views/admin/admin_customer_service.jsp";
   }

   @GetMapping({"/inventory/search"})
   public String searchInventory(@RequestParam("keyword") String keyword, Model md) {
      InventoryVo invenDetail = this.adminInventoryServiceImpl.findAlbumPriceByAlbumId(keyword);
      md.addAttribute("invenDetail", invenDetail);
      return "/WEB-INF/views/admin/admin_inventory.jsp";
   }

   @GetMapping({"/inventory/store"})
   public String putStore(@ModelAttribute InventoryVo inventoryVo, HttpSession session, RedirectAttributes redirectAtt) {
      this.adminInventoryServiceImpl.updateQuantity(inventoryVo);
      System.out.println(inventoryVo);
      return "redirect:/admin/inventory";
   }
}
