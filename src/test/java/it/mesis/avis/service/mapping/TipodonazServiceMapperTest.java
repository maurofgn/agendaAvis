/*
 * Created on 21 giu 2016 ( Time 11:50:56 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package it.mesis.avis.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import it.mesis.avis.bean.Tipodonaz;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.avis.service.mapping.TipodonazServiceMapper;
import it.mesis.avis.test.MockValues;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//
//import it.mesis.avis.service.mapping.TipodonazServiceMapper;
//import it.mesis.avis.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class TipodonazServiceMapperTest {

	private TipodonazServiceMapper tipodonazServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();
	private static final double delta = 0.0001d;

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		tipodonazServiceMapper = new TipodonazServiceMapper();
		tipodonazServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'TipodonazEntity' to 'Tipodonaz'
	 * @param tipodonazEntity
	 */
	@Test
	public void testMapTipodonazEntityToTipodonaz() {
		// Given
		TipodonazEntity tipodonazEntity = new TipodonazEntity();
		tipodonazEntity.setDescrizione(mockValues.nextString(30));
		tipodonazEntity.setRimborsormat(mockValues.nextDouble());
		tipodonazEntity.setRimborsornomat(mockValues.nextDouble());
		tipodonazEntity.setRimborsosp(mockValues.nextDouble());
		tipodonazEntity.setRpers1(mockValues.nextDouble());
		tipodonazEntity.setRpers2(mockValues.nextDouble());
		tipodonazEntity.setRpers3(mockValues.nextDouble());
		tipodonazEntity.setRpers4(mockValues.nextDouble());
		tipodonazEntity.setRpers5(mockValues.nextDouble());
		tipodonazEntity.setSigla(mockValues.nextString(2));
		
		// When
		Tipodonaz tipodonaz = tipodonazServiceMapper.mapTipodonazEntityToTipodonaz(tipodonazEntity);
		
		// Then
		assertEquals(tipodonazEntity.getDescrizione(), tipodonaz.getDescrizione());
		assertEquals(tipodonazEntity.getRimborsormat(), tipodonaz.getRimborsormat(), delta);
		assertEquals(tipodonazEntity.getRimborsornomat(), tipodonaz.getRimborsornomat(), delta);
		assertEquals(tipodonazEntity.getRimborsosp(), tipodonaz.getRimborsosp(), delta);
		assertEquals(tipodonazEntity.getRpers1(), tipodonaz.getRpers1(), delta);
		assertEquals(tipodonazEntity.getRpers2(), tipodonaz.getRpers2(), delta);
		assertEquals(tipodonazEntity.getRpers3(), tipodonaz.getRpers3(), delta);
		assertEquals(tipodonazEntity.getRpers4(), tipodonaz.getRpers4(), delta);
		assertEquals(tipodonazEntity.getRpers5(), tipodonaz.getRpers5(), delta);
		assertEquals(tipodonazEntity.getSigla(), tipodonaz.getSigla());
	}
	
	/**
	 * Test : Mapping from 'Tipodonaz' to 'TipodonazEntity'
	 */
	@Test
	public void testMapTipodonazToTipodonazEntity() {
		// Given
		TipodonazEntity tipodonaz = new TipodonazEntity();
		tipodonaz.setDescrizione(mockValues.nextString(30));
		tipodonaz.setRimborsormat(mockValues.nextDouble());
		tipodonaz.setRimborsornomat(mockValues.nextDouble());
		tipodonaz.setRimborsosp(mockValues.nextDouble());
		tipodonaz.setRpers1(mockValues.nextDouble());
		tipodonaz.setRpers2(mockValues.nextDouble());
		tipodonaz.setRpers3(mockValues.nextDouble());
		tipodonaz.setRpers4(mockValues.nextDouble());
		tipodonaz.setRpers5(mockValues.nextDouble());
		tipodonaz.setSigla(mockValues.nextString(2));

		TipodonazEntity tipodonazEntity = new TipodonazEntity();
		
		// When
		tipodonazServiceMapper.mapTipodonazToTipodonazEntity(tipodonaz, tipodonazEntity);
		
		
		// Then
		assertEquals(tipodonaz.getDescrizione(), tipodonazEntity.getDescrizione());
		assertEquals(tipodonaz.getRimborsormat(), tipodonazEntity.getRimborsormat(), delta);
		assertEquals(tipodonaz.getRimborsornomat(), tipodonazEntity.getRimborsornomat(), delta);
		assertEquals(tipodonaz.getRimborsosp(), tipodonazEntity.getRimborsosp(), delta);
		assertEquals(tipodonaz.getRpers1(), tipodonazEntity.getRpers1(), delta);
		assertEquals(tipodonaz.getRpers2(), tipodonazEntity.getRpers2(), delta);
		assertEquals(tipodonaz.getRpers3(), tipodonazEntity.getRpers3(), delta);
		assertEquals(tipodonaz.getRpers4(), tipodonazEntity.getRpers4(), delta);
		assertEquals(tipodonaz.getRpers5(), tipodonazEntity.getRpers5(), delta);
		assertEquals(tipodonaz.getSigla(), tipodonazEntity.getSigla(), delta);
	}

}