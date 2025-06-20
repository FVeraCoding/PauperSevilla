package com.backendPauper.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.backendPauper.entity.TorneoEntity;
import com.backendPauper.vo.TorneoVO;

public class TorneoConverterTest {

	private final TorneoConverter converter = new TorneoConverter();
	
	@Test
	void testToVO() {
		
		TorneoEntity entity = new TorneoEntity(1L, LocalDate.of(2025,  6, 19), 5, 16, "Empire Games");
		
		TorneoVO vo = converter.toVO(entity);
		
		assertNotNull(vo);
		assertEquals(1L, vo.getId());
		assertEquals(LocalDate.of(2025, 6, 19), vo.getFecha());
		assertEquals(5, vo.getNumeroRondas());
		assertEquals(16, vo.getNumeroParticipantes());
		assertEquals("Empire Games", vo.getTiendaOrganizadora());
	}
	
	@Test
	void testToEntity() {
		
		TorneoVO vo = new TorneoVO(1L, LocalDate.of(2025,  6, 19), 5, 16, "Empire Games");
		
		TorneoEntity entity = converter.toEntity(vo);
		
		assertNotNull(entity);
		assertEquals(1L, entity.getId());
		assertEquals(LocalDate.of(2025, 6, 19), entity.getFecha());
		assertEquals(5, entity.getNumeroRondas());
		assertEquals(16, entity.getNumeroParticipantes());
		assertEquals("Empire Games", entity.getTiendaOrganizadora());
	}
	
	@Test
	void testToVO_NullInput() {
	    TorneoVO vo = converter.toVO(null);
	    assertEquals(null, vo);
	}

	@Test
	void testToEntity_NullInput() {
	    TorneoEntity entity = converter.toEntity(null);
	    assertEquals(null, entity);
	}

}
