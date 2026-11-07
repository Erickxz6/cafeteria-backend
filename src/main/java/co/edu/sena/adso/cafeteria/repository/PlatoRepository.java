package co.edu.sena.adso.cafeteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.sena.adso.cafeteria.model.Plato;

// Spring Data escribe el código de esta interfaz al arrancar
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    // SELECT * FROM plato ORDER BY nombre
    List<Plato> findAllByOrderByNombreAsc();

    // SELECT * FROM plato WHERE lower(tipo) = lower(?) ORDER BY nombre
    List<Plato> findByTipoIgnoreCaseOrderByNombreAsc(String tipo);
}