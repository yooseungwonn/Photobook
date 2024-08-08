package himedia.photobook.controllers.admin;

import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.services.admin.AdminProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin"})
public class AdminProductController {
   @Autowired
   private AdminProductService adminProductService;

   @GetMapping({"/product"})
   public String product(Model model) {
      model.addAttribute("ProductMap", this.adminProductService.getProductInfos());
      return "/WEB-INF/views/admin/product/admin_product.jsp";
   }

   @GetMapping({"/products/search"})
   public String searchProducts(@RequestParam(value = "keyword",required = false) String keyword, Model model) {
      try {
         List<Map<String, Object>> productMap = this.adminProductService.getProductInfos(keyword);
         model.addAttribute("ProductMap", productMap);
      } catch (Exception var4) {
         var4.printStackTrace();
         model.addAttribute("error", "제품 목록을 가져오는 중 오류가 발생했습니다.");
      }

      return "/WEB-INF/views/admin/product/admin_product.jsp";
   }

   @GetMapping({"/product/productEdit"})
   public String editProduct(@RequestParam("albumId") String albumId, Model model) {
      try {
         Map<String, Object> productMap = this.adminProductService.getAlbumInfoMap(albumId);
         model.addAttribute("ProductMap", productMap);
      } catch (Exception var4) {
         var4.printStackTrace();
         model.addAttribute("error", "제품 목록을 가져오는 중 오류가 발생했습니다.");
      }

      return "/WEB-INF/views/admin/product/productEdit.jsp";
   }

   @PostMapping({"/product/update"})
   public String updateProduct(@ModelAttribute("albumId") String albumId, @ModelAttribute("material") String material, @ModelAttribute("color") String color, @ModelAttribute("albumSize") String albumSize, @ModelAttribute("price") Long price, @RequestParam("changeImg") MultipartFile changeImg, Model model) {
      AlbumVo albumVo = new AlbumVo();
      albumVo.setAlbumId(albumId);
      albumVo.setMaterial(material);
      albumVo.setColor(color);
      albumVo.setAlbumSize(albumSize);

      try {
         this.adminProductService.updateProduct(albumVo, price, changeImg);
         model.addAttribute("success", "앨범 업데이트에 성공했습니다.");
      } catch (Exception var10) {
         var10.printStackTrace();
         model.addAttribute("error", "앨범 업데이트 중 오류가 발생했습니다.");
      }

      return "redirect:/admin/product";
   }

   @GetMapping({"/product/update"})
   public String updateForm() {
      return "/WEB-INF/views/admin/product/admin_product.jsp";
   }

   @GetMapping({"/product/delete"})
   public String deleteProduct(@RequestParam("albumId") String albumId, Model model) {
      try {
         this.adminProductService.deleteProduct(albumId);
         model.addAttribute("success", "앨범이 성공적으로 삭제되었습니다.");
      } catch (Exception var4) {
         var4.printStackTrace();
         model.addAttribute("error", "앨범 삭제 중 오류가 발생했습니다.");
      }

      return "redirect:/admin/product";
   }

   @GetMapping({"/product/add"})
   public String addForm() {
      return "/WEB-INF/views/admin/product/productAdd.jsp";
   }

   @PostMapping({"/product/add"})
   public String addProduct(@ModelAttribute("albumId") String albumId, @ModelAttribute("material") String material, @ModelAttribute("color") String color, @ModelAttribute("albumSize") String albumSize, @ModelAttribute("albumPrice") Long albumPrice, @RequestParam("fileUploader") MultipartFile multipartFile, Model model) {
      AlbumVo albumVo = new AlbumVo();
      albumVo.setAlbumId(albumId);
      albumVo.setMaterial(material);
      albumVo.setColor(color);
      albumVo.setAlbumSize(albumSize);

      try {
         boolean result = this.adminProductService.insertProduct(albumVo, albumPrice, multipartFile);
         if (result) {
            model.addAttribute("success", "앨범이 성공적으로 추가되었습니다.");
         } else {
            model.addAttribute("error", "앨범 추가에 실패했습니다.");
         }
      } catch (Exception var10) {
         var10.printStackTrace();
         model.addAttribute("error", "앨범 추가 중 오류가 발생했습니다.");
      }

      return "redirect:/admin/product";
   }
}
