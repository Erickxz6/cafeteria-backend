package co.edu.sena.adso.cafeteria.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.sena.adso.cafeteria.model.Plato;
import co.edu.sena.adso.cafeteria.repository.PlatoRepository;

@Service   // MODELO (lógica): las reglas del menú. Los datos están en PostgreSQL
public class MenuService {

    private final PlatoRepository repo;

    public MenuService(PlatoRepository repo) {   // Spring entrega el repositorio
        this.repo = repo;
    }

    public List<Plato> todos() {
        return repo.findAllByOrderByNombreAsc();
    }

    public List<Plato> porTipo(String tipo) {
        return repo.findByTipoIgnoreCaseOrderByNombreAsc(tipo);
    }

    public Plato buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el plato " + id));
    }

    // Regla: el tipo siempre se guarda en minúsculas
    public Plato guardar(Plato plato) {
        plato.setTipo(plato.getTipo().toLowerCase());
        return repo.save(plato);          // sin id: INSERT · con id: UPDATE
    }

    // Regla: no se puede eliminar un plato que no existe
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el plato " + id);
        }
        repo.deleteById(id);
    }
}