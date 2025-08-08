package com.backendPauper.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backendPauper.ServiceImpl.JugadorServiceImpl;
import com.backendPauper.converter.JugadorConverter;
import com.backendPauper.entity.JugadorEntity;
import com.backendPauper.repository.JugadorRepository;
import com.backendPauper.repository.TorneoRepository;
import com.backendPauper.vo.JugadorVO;

public class JugadorServiceImplTest {

	private JugadorRepository jugadorRepository;
	private JugadorConverter jugadorConverter;
	private TorneoRepository torneoRepository;
	private JugadorServiceImpl jugadorService;

	@BeforeEach
	void setUp() {
		jugadorRepository = mock(JugadorRepository.class);
		jugadorConverter = mock(JugadorConverter.class);
		torneoRepository = mock(TorneoRepository.class);
		jugadorService = new JugadorServiceImpl(jugadorRepository, jugadorConverter, torneoRepository);
	}

	@Test
	void testFindByNombre() {

		// Creamos los datos con los que vamos a trabajar.
		JugadorEntity entity = new JugadorEntity("Fvera", 5, 3, 1);
		JugadorVO vo = new JugadorVO(1L, "Fvera", 5, 3, 1);

		// Definimos el comportamiento de los mocks.
		when(jugadorRepository.findByNombre("Fvera")).thenReturn(entity);
		when(jugadorConverter.toVO(entity)).thenReturn(vo);

		// Llamamos al método que queremos probar.
		JugadorVO resultado = jugadorService.findByNombre("Fvera");

		// Realizamos las comprobaciones.
		assertNotNull(resultado);
		assertEquals("Fvera", resultado.getNombre());
		assertEquals(5, resultado.getVictorias());
		assertEquals(3, resultado.getEmpates());
		assertEquals(1, resultado.getDerrotas());

		// Verificamos que se han llamado a los mocks.
		verify(jugadorRepository).findByNombre("Fvera");
		verify(jugadorConverter).toVO(entity);

	}

	@Test
	void testFindAll() {
		
		// Datos
		JugadorEntity entity1 = new JugadorEntity("Fvera", 5, 3, 1);
		JugadorVO vo1 = new JugadorVO(1L, "Fvera", 5, 3, 1);
		
		JugadorEntity entity2 = new JugadorEntity("Chen", 3, 1, 1);
		JugadorVO vo2 = new JugadorVO(2L, "Chen", 3, 1, 1);
		
		List<JugadorEntity> jugadores = new ArrayList<>();
		jugadores.add(entity1);
		jugadores.add(entity2);
		
		// Comportamiento de mocks
		when(jugadorRepository.findAll()).thenReturn(jugadores);
		when(jugadorConverter.toVO(entity1)).thenReturn(vo1);
		when(jugadorConverter.toVO(entity2)).thenReturn(vo2);
		
		//Llamada al método
		List<JugadorVO> resultado = jugadorService.findAll();
		
		//Realizamos comprobaciones.
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		
		assertEquals("Fvera", resultado.get(0).getNombre());
		assertEquals("Chen", resultado.get(1).getNombre());
		
		//Verificamos las llamadas a los mocks.
		verify(jugadorRepository).findAll();
		verify(jugadorConverter).toVO(entity1);
		verify(jugadorConverter).toVO(entity2);
		
	}
	
	@Test
	void testFindById() {
		
		//Datos
		JugadorEntity entity = new JugadorEntity("Fvera", 5, 3, 1);
		entity.setId(1L);
		
		JugadorVO vo = new JugadorVO(1L, "Fvera", 5, 3, 1);
		
		// Comportamiendo de mocks
		when(jugadorRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(jugadorConverter.toVO(entity)).thenReturn(vo);
		
		// Llamada al método
		JugadorVO resultado = jugadorService.findById(1L);
		
		// Realizamos comprobaciones
		assertNotNull(resultado);
		assertEquals(1L, resultado.getId());
		assertEquals("Fvera", resultado.getNombre());
		assertEquals(5, resultado.getVictorias());
		assertEquals(3, resultado.getEmpates());
		assertEquals(1, resultado.getDerrotas());
		
		//Verificamos las llamadas a los mocks
		verify(jugadorRepository).findById(1L);
		verify(jugadorConverter).toVO(entity);
		
	}
	
	@Test
	void testUpdateJugador() {
		//Datos
		JugadorEntity existente = new JugadorEntity("Fvera", 5, 3, 1);
		existente.setId(1L);
		
		JugadorVO actualizacionVO = new JugadorVO(1L, "Fvera", 6, 3, 1);
				
		JugadorVO actualizadoVO = new JugadorVO(1L, "Fvera", 6, 3, 1);
		
		// Definimos comportamiento de mocks.
		when(jugadorRepository.findById(actualizacionVO.getId())).thenReturn(Optional.of(existente));
		when(jugadorRepository.save(existente)).thenReturn(existente);
		when(jugadorConverter.toVO(existente)).thenReturn(actualizadoVO);
		
		// Llamada al método
		JugadorVO resultadoVO = jugadorService.updateJugador(actualizacionVO);
		
		// Comprobaciones
		assertNotNull(resultadoVO);
		assertEquals(1L, resultadoVO.getId());
		assertEquals("Fvera", resultadoVO.getNombre());
		assertEquals(6, resultadoVO.getVictorias());
		assertEquals(3, resultadoVO.getEmpates());
		assertEquals(1, resultadoVO.getDerrotas());
		
		//Verificaciones
		verify(jugadorRepository).findById(1L);
		verify(jugadorRepository).save(existente);
		verify(jugadorConverter).toVO(existente);
		
	}
	
}
