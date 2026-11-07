package co.edu.sena.adso.cafeteria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.sena.adso.cafeteria.model.Plato;
import co.edu.sena.adso.cafeteria.service.MenuService;

@Controller
@RequestMapping("/admin/platos")   // Todas las rutas de la clase empiezan así
public class AdminController {

    private final MenuService menu;   // El mismo servicio de siempre

    public AdminController(MenuService menu) {
        this.menu = menu;
    }

    // READ · GET /admin/platos  → tabla con todos los platos
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("platos", menu.todos());
        return "admin";
    }

    // CREATE (1) · GET /admin/platos/nuevo  → formulario vacío
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plato", new Plato());
        model.addAttribute("accion", "Nuevo plato");
        return "plato-form";
    }

    // CREATE (2) y UPDATE (2) · POST /admin/platos  → guardar
    @PostMapping
    public String guardar(@ModelAttribute("plato") Plato plato) {
        menu.guardar(plato);
        return "redirect:/admin/platos";
    }

    // UPDATE (1) · GET /admin/platos/3/editar  → formulario con los datos
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plato", menu.buscar(id));
        model.addAttribute("accion", "Editar plato");
        return "plato-form";
    }

    // DELETE · POST /admin/platos/3/eliminar
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable("id") Long id) {
        menu.eliminar(id);
        return "redirect:/admin/platos";
    }
}