package co.edu.sena.adso.cafeteria.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.sena.adso.cafeteria.model.Plato;

@Service   // MODELO (lógica): guarda los datos y sabe buscarlos
public class MenuService {

    private final List<Plato> platos = List.of(
            new Plato(1L, "Arepa de huevo", "desayuno", 4000),
            new Plato(2L, "Arroz con pollo", "almuerzo", 12000),
            new Plato(3L, "Mojarra frita con patacón", "almuerzo", 18000),
            new Plato(4L, "Jugo de corozo", "bebida", 3000),
            new Plato(5L, "Limonada de coco", "bebida", 5000));

    public List<Plato> todos() {
        return platos;
    }

    public List<Plato> porTipo(String tipo) {
        return platos.stream()
                .filter(p -> p.tipo().equalsIgnoreCase(tipo))
                .toList();
    }

    public Plato buscar(Long id) {
        return platos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el plato " + id));
    }
}