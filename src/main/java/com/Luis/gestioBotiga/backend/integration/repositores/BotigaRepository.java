package com.Luis.gestioBotiga.backend.integration.repositores;

import com.Luis.gestioBotiga.bussiness.model.Botiga;
import com.Luis.gestioBotiga.bussiness.model.District;
import com.Luis.gestioBotiga.bussiness.model.Sector;
import com.Luis.gestioBotiga.bussiness.model.Subsector;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BotigaRepository extends JpaRepository<Botiga, Long> {

    @Query("SELECT b FROM Botiga b WHERE b.open = true AND b.district = :district")
    List<Botiga> findObertsByDistrict(District district);

    @Query("SELECT b FROM Botiga b WHERE b.open = true AND b.sector = :sector")
    List<Botiga> findObertsBySector(Sector sector);

    @Query("SELECT b FROM Botiga b WHERE b.subsector = :subsector AND b.open = true")
    List<Botiga> findObertsBySubsector(Subsector subsector);

}