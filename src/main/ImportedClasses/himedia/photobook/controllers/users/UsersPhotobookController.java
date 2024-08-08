package himedia.photobook.controllers.users;

import himedia.photobook.repositories.dao.InventoryDaoImpl;
import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.users.UserPhotobookService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/users"})
public class UsersPhotobookController {
   @Autowired
   private UserPhotobookService userPhotobookService;
   @Autowired
   private InventoryDaoImpl inventoryDaoImpl;

   @GetMapping({"/photobook", "pb", "photo"})
   public String photobook() {
      return "/WEB-INF/views/users/users_photobook.jsp";
   }

   @PostMapping({"/create_photobook"})
   public String createPhotobook(@RequestParam(value = "material",required = false) String material, @RequestParam(value = "color",required = false) String color, @RequestParam(value = "albumSize",required = false) String albumSize, @RequestParam(value = "oQuantity",required = false) Long oQuantity, HttpSession albumsession, Model model) {
      AlbumVo albumVo = this.userPhotobookService.findAlbumIdByOptions(material, color, albumSize);
      if (albumVo == null) {
         model.addAttribute("error");
         return "redirect:/users/photobook";
      } else {
         String albumId = albumVo.getAlbumId();
         InventoryVo inventoryVo = this.inventoryDaoImpl.selectOneByAlbumId(albumId);
         Long aQuantity = inventoryVo.getaQuantity();
         if (aQuantity >= oQuantity) {
            albumsession.setAttribute("albumId", albumId);
            albumsession.setAttribute("oQuantity", oQuantity);
            return "/WEB-INF/views/users/users_create_photobook.jsp";
         } else {
            model.addAttribute("error", "재고가 부족합니다.");
            return "/WEB-INF/views/users/users_photobook.jsp";
         }
      }
   }

   @PostMapping({"/photobookOrder"})
   public String photobookorder(@RequestParam(value = "albumId",required = false) String albumId, @RequestParam("canvasFiles") List<MultipartFile> multipartFiles, Model model, HttpSession session) {
      UsersVo authUser = (UsersVo)session.getAttribute("authUser");
      if (authUser != null) {
         String userId = authUser.getUserId();
         InventoryVo inventoryVo = this.userPhotobookService.findAlbumPriceByAlbumId(albumId);
         Long albumPrice = inventoryVo.getAlbumPrice();
         Long oQuantity = (Long)session.getAttribute("oQuantity");
         albumPrice * oQuantity;
         this.userPhotobookService.orderInsert(userId, albumId, oQuantity, multipartFiles);
         model.addAttribute("success", "주문에 성공했습니다");
         return "redirect:/users/order";
      } else {
         model.addAttribute("error", "로그인이 필요합니다");
         return "/WEB-INF/views/users/users_login.jsp";
      }
   }
}
