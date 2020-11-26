package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, String> {

    @Query(value = "select case when pon.id is not null then 'I'||ins.id||'_T'||pon.id else 'I'||ins.id end as id, " +
            "eve.id as eventoid, eve.nombre as evento, rol.id as rolid, rol.descripcion as rol, ins.nombre as participante, pon.tema as tema " +
            "from certificados.inscripciones ins " +
            "inner join certificados.eventos eve on (eve.id=ins.evento) " +
            "inner join certificados.roles rol on (rol.id=ins.rol) " +
            "left join certificados.ponentes pot on (pot.inscripcion=ins.id) " +
            "left join certificados.ponencias pon on (pon.id=pot.ponencia) " +
            "where upper(translate(ins.nombre, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) like upper(translate(concat('%', :nombre, '%'), 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) " +
            "order by ins.nombre, eve.id desc", nativeQuery = true)
    List<Certificado> getByNombre(@Param("nombre") String nombre);

}