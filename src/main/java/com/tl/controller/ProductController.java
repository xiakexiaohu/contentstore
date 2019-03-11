package com.tl.controller;

import com.tl.pojo.OrderIterm;
import com.tl.pojo.Product;
import com.tl.pojo.ProductCustom;
import com.tl.pojo.User;
import com.tl.service.OrderItermService;
import com.tl.service.ProductService;
import com.tl.utils.Constant;
import com.tl.utils.UUIDUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/2/27 3:39 PM
 **/
@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItermService orderItermService;

    @RequestMapping(value = "/publish")
    public String publish(HttpSession session) {
        //判断用户是否登录
        if (session.getAttribute(Constant.USER_SESSION) == null) {
            //没有登录
            return "login_form";
        }
        return "product_form";
    }

    @RequestMapping(value="/edit")
    public ModelAndView editProduct(@RequestParam("pid") String pid){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product_edit");
        Product product = productService.getProductById(pid);
        mv.addObject("product", product);
        return mv;
    }


    @RequestMapping(value="/update")
    public String updateProduct(@RequestParam("pid") String pid,
                               @RequestParam("title") String ptitle, @RequestParam("summary") String psummary,
                               @RequestParam("image") String pimgUrl, @RequestParam("detail") String pdescription,
                               @RequestParam("count") Integer pcount, @RequestParam("price") Double price,
                               HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        if (user == null) {
            return "login_form";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product_detail");
        Product product = new Product();
        product.setPid(pid);
        product.setPtitle(ptitle);
        product.setPsummary(psummary);
        product.setPimage(pimgUrl);
        product.setPdesc(pdescription);
        product.setUid(user.getUid());
        product.setPcount(pcount);
        product.setPrice(price);
        productService.updateProduct(product);

        //发布成功回到查看页面
        return "redirect:/product/detail?pid=" + pid;
    }


    /**
     * @param pid  * @return org.springframework.web.servlet.ModelAndView
     * @description 商品详情
     *  
     */
    @RequestMapping(value = "/detail")
    public ModelAndView detailProduct(@RequestParam("pid") String pid, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product_detail");
        Product product = productService.getProductById(pid);
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        ProductCustom productCustom = new ProductCustom();


        //如果是买家，判断最近一次购买的价格
        if (user != null && user.getType() == Constant.BUYER) {
            String uid = user.getUid();
            OrderIterm lastOrderIterm = orderItermService.getLastOrderItermByUidPid(uid, pid);
            if (lastOrderIterm != null) {
                productCustom.setIsBrought(1);
                productCustom.setLastPrice(lastOrderIterm.getPrice());
            } else {
                //未购买过
                productCustom.setIsBrought(0);
            }
        }
        //填充商品信息
        productCustom.setProduct(product);
        mv.addObject("productCustom", productCustom);
        return mv;
    }




    @RequestMapping(value = "/del")
    public void delProductById(@RequestParam("pid") String pid) {
        productService.remove(pid);
    }

    @RequestMapping(value = "/list")
    public ModelAndView listProduct(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        List<Product> products = productService.listAll();
        //用于判断所有商品是否已经被购物,添加标识位
        List<ProductCustom> productCustoms = new ArrayList<>();
        List<ProductCustom> noBuyProductCustoms = new ArrayList<>();

        //统计所有销量
        List<OrderIterm> allHasBroughtProducts = productService.getProductHasBrought();
        HashMap<String, Integer> hasBroughtMaps = new HashMap<>();
        for (OrderIterm oi : allHasBroughtProducts) {
            String pid = oi.getPid();
            hasBroughtMaps.put(pid, hasBroughtMaps.getOrDefault(pid, 0) + oi.getCount());
        }

        if (user == null) {
            for (Product p : products) {
                //未登录情况下，即不存在是否售出
                ProductCustom productCustom = new ProductCustom();
                productCustom.setIsBrought(0);
                productCustom.setProduct(p);
                if (hasBroughtMaps.containsKey(p.getPid())) {
                    //说明有售出
                    productCustom.setCountBrought(hasBroughtMaps.get(p.getPid()));
                } else {
                    //未出售过
                    productCustom.setCountBrought(0);
                }
                productCustoms.add(productCustom);
            }
        } else {
            //已经登录情况,分别查找对应出售的商品
            if (user.getType() == Constant.SELLER) {
                //查找所有买家下单过的商品
                //查找对应商家发布的商品
                List<Product> productByOwnerId = productService.getProductByOwnerId(user.getUid());
                //分别判断是否被用户下单过
                for (Product p : productByOwnerId) {
                    ProductCustom productCustom = new ProductCustom();
                    if (hasBroughtMaps.get(p.getPid()) != null) {
                        //说明被下单过
                        productCustom.setIsBrought(1);
                        //放入已售出数量
                        productCustom.setCountBrought(hasBroughtMaps.get(p.getPid()));
                    } else {
                        //未被下单
                        productCustom.setIsBrought(0);
                        //售出数量0件
                        productCustom.setCountBrought(0);
                    }
                    productCustom.setProduct(p);
                    productCustoms.add(productCustom);
                }
            } else if (user.getType() == Constant.BUYER) {
                //买家下单过的商品编号
                String uid = user.getUid();
                List<OrderIterm> hasBroughtByUid = productService.getProductByUserId(uid);
                //建立对应pid和sellCount映射表
                HashSet<String> hasBroughtPidSets = new HashSet<>();
                //获取所有用户已经下单过的商品
                for (OrderIterm oi : hasBroughtByUid) {
                    hasBroughtPidSets.add(oi.getPid());
                }

                //判断所有商品中是否有用户购买过的
                for (Product p : products) {
                    String pid = p.getPid();
                    ProductCustom productCustom = new ProductCustom();
                    ProductCustom noBuyProductCustom = new ProductCustom();

                    //对应销量
                    if (hasBroughtMaps.containsKey(pid)) {
                        productCustom.setCountBrought(hasBroughtMaps.get(pid));
                        noBuyProductCustom.setCountBrought(hasBroughtMaps.get(pid));
                    } else {
                        productCustom.setCountBrought(0);
                        noBuyProductCustom.setCountBrought(0);
                    }

                    //判断是否购买过
                    if (hasBroughtPidSets.contains(pid)) {
                        //包含购买过的
                        productCustom.setIsBrought(1);
                    } else {
                        productCustom.setIsBrought(0);
                        //未购买的
                        noBuyProductCustom.setProduct(p);
                        //放入未购买集合
                        noBuyProductCustoms.add(noBuyProductCustom);
                    }

                    productCustom.setProduct(p);
                    //放入结果集
                    productCustoms.add(productCustom);
                }
            }
        }


        //填充数据
        mv.addObject("productCustoms", productCustoms);
        //填充未购物买的数据
        mv.addObject("noBuyProductCustoms", noBuyProductCustoms);
        //返回数据
        return mv;

    }


    /**
     * @param imageFile
     * @param request
     * @param response   * @return java.util.Map<java.lang.String,java.lang.Object>
     * @description 本地上传图片预览
     *  
     */

    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        if (imageFile == null) {
            resultMap.put("message", "上传图片存在问题，请重新选择。");
        } else {
            //图片上传本地文件夹
            String fileUploadPath = request.getSession().getServletContext().getRealPath("/upload");
            //判断文件夹是否存在
            File file = new File(fileUploadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //新图片路径
            String newFilePath = fileUploadPath + "/" + imageFile.getOriginalFilename();
            //将文件上传至本地服务器,以便预览
            imageFile.transferTo(new File(newFilePath));
            //返回点击上传的结果
            resultMap.put("message", "上传成功！");
            resultMap.put("result", "/upload/" + imageFile.getOriginalFilename());
        }
        return resultMap;
    }

    /**
     * @param ptitle
     * @param psummary
     * @param pimgUrl
     * @param pdescription
     * @param price         * @return java.lang.String
     * @description 商品发布
     *  
     */
    @RequestMapping(value = "/submit")
    public String submitProduct(@RequestParam("title") String ptitle, @RequestParam("summary") String psummary,
                                @RequestParam("image") String pimgUrl, @RequestParam("count") Integer pcount, @RequestParam("detail") String pdescription,
                                @RequestParam("price") Double price, HttpServletRequest request) {

        Product product = new Product();
        product.setPid(UUIDUtils.getUUID());
        product.setPtitle(ptitle);
        //本地上传已经将图片上传了服务器,只需回写路径
        product.setPimage(pimgUrl);
        product.setPcount(pcount);
        product.setPrice(price);
        product.setPsummary(psummary);
        product.setPdesc(pdescription);
        //设置所属用户
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
        product.setUid(user.getUid());

        //插入(insert并不会自动填充默认值，insertSelective默认填充值)
        productService.insertSelective(product);

        return "redirect:/dynamic/main";

    }
}
