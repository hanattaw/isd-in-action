package tacos.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Taco;
import tacos.TacoOrder;

@Controller
@RequestMapping("/cart")
@SessionAttributes("tacoOrder")
public class CartController {

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @GetMapping
  public String showCart(@ModelAttribute TacoOrder tacoOrder, Model model) {
    model.addAttribute("tacos", tacoOrder.getTacos());
    model.addAttribute("totalPrice", tacoOrder.getTotalPrice());
    return "cart";
  }

  @PostMapping("/remove/{index}")
  public String removeTaco(@PathVariable("index") int index,
                           @ModelAttribute TacoOrder tacoOrder) {
    List<Taco> tacos = tacoOrder.getTacos();
    if (index >= 0 && index < tacos.size()) {
      tacos.remove(index);
    }
    return "redirect:/cart";
  }

  @PostMapping("/clear")
  public String clearCart(@ModelAttribute TacoOrder tacoOrder) {
    tacoOrder.getTacos().clear();
    return "redirect:/cart";
  }

}