package com.backendPauper.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.vo.JugadorVO;

public class JugadorConverterTest {

	private final JugadorConverter converter = new JugadorConverter();
	
	@Test
	void testToVo() {
		
		JugadorEntity entity = new JugadorEntity(1L, "FVera", 5, 2, 1);
		
		JugadorVO vo = converter.toVO(entity);
		
		assertNotNull(vo);
		assertEquals(1L, vo.getId());
		assertEquals("FVera", vo.getNombre());
		assertEquals(5, vo.getVictorias());
		assertEquals(2, vo.getEmpates());
		assertEquals(1, vo.getDerrotas());
		
	}
	
	@Test
	void testToEntity() {
		JugadorVO vo = new JugadorVO(1L, "FVera", 5, 2, 1);
		
		JugadorEntity entity = converter.toEntity(vo);
		
		assertNotNull(entity);
		assertEquals(1L, entity.getId());
		assertEquals("FVera", entity.getNombre());
		assertEquals(5, entity.getVictorias());
		assertEquals(2, entity.getEmpates());
		assertEquals(1, entity.getDerrotas());
		
	}
	
	@Test
	void testToVo_NullInput() {
		JugadorVO vo = converter.toVO(null);
		
		assertEquals(null, vo);
	}
	
	@Test
	void testToEntity_NullInput(){
		JugadorEntity entity = converter.toEntity(null);
		assertEquals(null, entity);
	}
	
	
}
