package it.mesis.avis.hsqlDataType;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

/**
 * questa classe maschera il problema di DbUnit che ritorna il tipo (UNKNOW) sconosciuto per i booolean quando il db � H2 (per mySQL o altri non ci sarebbe bisogno)
 * H2 ha aggiunto il tipo boolean (nelle versioni precedenti era bit) solo nelle versioni recenti, mentre DBUnit x h2 ancora non si � allineato, � stato previsto a livello di sorgenti,
 * ma questi non sono ancora usati per produrre l'ultima versione ufficiale.
 * 
 * @see http://blog.carbonfive.com/2005/07/20/dbunit-hsql-and-the-boolean-data-type/
 * 
 * @author mauro
 *
 */
public class DataTypeFactory extends DefaultDataTypeFactory {
	
//	private static final Log log = LogFactory.getLog(HsqlDataTypeFactory.class);

	public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
		if (sqlType == Types.BOOLEAN) {
			return DataType.BOOLEAN;	//questo � necessario per ovviare al problema di dbunit che x h2 non riconosce il tipo boolean, anche se h2 lo gestisce
		}

		return super.createDataType(sqlType, sqlTypeName);
	}
}