package himedia.photobook.controllers.admin;

import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.services.admin.AdminCommentServiceImpl;
import himedia.photobook.services.admin.AdminDeliveryServiceImpl;
import himedia.photobook.services.admin.AdminInventoryServiceImpl;
import himedia.photobook.services.admin.AdminOrderService;
import himedia.photobook.services.admin.AdminRefundServiceImpl;
import himedia.photobook.services.users.UBoardServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
   @Autowired
   private UBoardServiceImpl uBoardService;
   @Autowired
   private AdminOrderService adminOrderService;
   @Autowired
   private AdminDeliveryServiceImpl adminDeliveryServiceImpl;
   @Autowired
   private AdminRefundServiceImpl adminRefundServiceImpl;
   @Autowired
   private AdminCommentServiceImpl adminCommentServiceImpl;
   @Autowired
   private AdminInventoryServiceImpl adminInventoryServiceImpl;

   @RequestMapping({"", "/home"})
   public String home(Model model) {
      String count = this.adminOrderService.count();
      String scount = this.adminDeliveryServiceImpl.count();
      String rcount = this.adminRefundServiceImpl.count();
      String ccount = this.adminCommentServiceImpl.count();
      model.addAttribute("OrderCount", count);
      model.addAttribute("ShipCount", scount);
      model.addAttribute("RefCount", rcount);
      model.addAttribute("CsCount", ccount);
      Map<String, Object> salesData = this.adminOrderService.Salecount();
      model.addAttribute("salesData", salesData);
      System.out.println("판매량 조회: " + String.valueOf(model));
      List<Map<String, Object>> topData = this.adminOrderService.getTopAlbum();
      model.addAttribute("topData", topData);
      return "/WEB-INF/views/admin/admin_pages.jsp";
   }

   @RequestMapping({"/customerService", "/cs"})
   public String customerService(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "5") int size, Model md) {
      List<Map<String, Object>> list = this.uBoardService.getBoardInfos();
      this.uBoardService.getPagedBoard(page, size);
      int totalItems = this.uBoardService.getTotalCount();
      int totalPages = (int)Math.ceil((double)totalItems / (double)size);
      md.addAttribute("postList", list);
      md.addAttribute("currentPage", page);
      md.addAttribute("totalPages", totalPages);
      return "/WEB-INF/views/admin/admin_customer_service.jsp";
   }

   @RequestMapping({"/dashboard", "/dash"})
   public String dashboard() {
      return "/WEB-INF/views/admin/admin_dashboard.jsp";
   }

   @RequestMapping({"/refund"})
   public String refund() {
      return "/WEB-INF/views/admin/admin_refund.jsp";
   }

   @RequestMapping({"/delivery"})
   public String shipment() {
      return "/WEB-INF/views/admin/admin_delivery.jsp";
   }

   @RequestMapping({"/product"})
   public String product() {
      return "/WEB-INF/views/admin/admin_product.jsp";
   }

   @RequestMapping({"/inventory"})
   public String inventory(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "5") int size, Model md) {
      List<InventoryVo> list = this.adminInventoryServiceImpl.getPagedInventory(page, size);
      int totalItems = this.adminInventoryServiceImpl.getTotalCount();
      int totalPages = (int)Math.ceil((double)totalItems / (double)size);
      md.addAttribute("invenList", list);
      md.addAttribute("currentPage", page);
      md.addAttribute("totalPages", totalPages);
      return "/WEB-INF/views/admin/admin_inventory.jsp";
   }
}
