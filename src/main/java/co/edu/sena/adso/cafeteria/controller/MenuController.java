package co.edu.sena.adso.cafeteria.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.sena.adso.cafeteria.model.Contacto;
import co.edu.sena.adso.cafeteria.model.Plato;
import co.edu.sena.adso.cafeteria.service.MenuService;

@Controller   // CONTROLADOR: recibe la petición y elige la vista
public class MenuController {

    private final MenuService menu;

    public MenuController(MenuService menu) {   // Spring entrega el servicio
        this.menu = menu;
    }

    // GET /menu   y   GET /menu?tipo=bebida
    @GetMapping("/menu")
    public String verMenu(@RequestParam(name = "tipo", required = false) String tipo,
                          Model model) {
        List<Plato> platos = (tipo == null) ? menu.todos() : menu.porTipo(tipo);
        model.addAttribute("titulo", "Menú del día");
        model.addAttribute("platos", platos);
        return "menu";             // VISTA: templates/menu.html
    }

    // GET /menu/2
    @GetMapping("/menu/{id}")
    public String verPlato(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plato", menu.buscar(id));
        return "plato";            // VISTA: templates/plato.html
    }

    // GET /contacto  → formulario de contacto
    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("contacto", new Contacto());
        model.addAttribute("accion", "Formulario de contacto");
        return "contacto";
    }

    // POST /contacto  → recibe el formulario y muestra confirmación
    @PostMapping("/contacto")
    public String enviarContacto(@ModelAttribute("contacto") Contacto contacto, RedirectAttributes redirect) {
        redirect.addFlashAttribute("enviado", true);
        return "redirect:/contacto";
    }

    // GET /menu/AcercaDe  → página de información
    @GetMapping("/menu/AcercaDe")
    public String acercaDe() {
        return "acerca-de";
    }
}